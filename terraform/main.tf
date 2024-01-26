provider "google" {
  project = var.project_id
  region  = var.region
  zone    = var.zone
}

data "google_client_config" "default" {
}

resource "google_service_account" "default" {
  account_id   = "service-account-id"
  display_name = "Service Account"
}

resource "google_project_iam_member" "registry_reader_binding" {
  for_each = toset([
    "roles/artifactregistry.reader",
    "roles/storage.objectViewer",
    "roles/pubsub.editor",
    "roles/cloudsql.editor",
  ])
  role = each.key
  member = "serviceAccount:${google_service_account.default.email}"
  project = var.project_id
}

module "my-app-workload-identity" {
  source              = "terraform-google-modules/kubernetes-engine/google//modules/workload-identity"
  use_existing_gcp_sa = true
  name                = google_service_account.default.account_id
  project_id          = var.project_id

  # wait for the custom GSA to be created to force module data source read during apply
  # https://github.com/terraform-google-modules/terraform-google-kubernetes-engine/issues/1059
  depends_on = [google_service_account.default]
}

###
# module "my-app-workload-identity" {
#   source              = "terraform-google-modules/kubernetes-engine/google//modules/workload-identity"
#   name                = "test-app"
#   namespace           = "namespace"
#   project_id          = var.project_id
#   roles               = ["roles/storage.admin", "roles/compute.admin", "roles/containerregistry.ServiceAgent", "roles/artifactregistry.reader", "roles/artifactregistry.writer"]
# }
###

resource "google_container_cluster" "primary" {
  name     = "my-gke-cluster"
  location = var.region

  # We can't create a cluster with no node pool defined, but we want to only use
  # separately managed node pools. So we create the smallest possible default
  # node pool and immediately delete it.
  remove_default_node_pool = true
  initial_node_count       = 1

  workload_identity_config {
    workload_pool = "${var.project_id}.svc.id.goog"
  }
}

resource "google_container_node_pool" "primary_preemptible_nodes" {
  name       = "my-node-pool"
  location   = var.region
  cluster    = google_container_cluster.primary.name
  node_count = 1

  node_config {
    preemptible  = true
    machine_type = "e2-medium"
    disk_size_gb = 10

    # Google recommends custom service accounts that have cloud-platform scope and permissions granted via IAM Roles.
    service_account = google_service_account.default.email
    oauth_scopes    = [
      "https://www.googleapis.com/auth/cloud-platform",
      "https://www.googleapis.com/auth/devstorage.read_only",
      "https://www.googleapis.com/auth/logging.write",
      "https://www.googleapis.com/auth/monitoring",
    ]
  }
}

provider "kubernetes" {
  #   load_config_file = false
  host = "https://${google_container_cluster.primary.endpoint}"
  token = data.google_client_config.default.access_token
  cluster_ca_certificate = base64decode(google_container_cluster.primary.master_auth.0.cluster_ca_certificate)
}

resource "kubernetes_service" "app-service" {
  metadata {
    name = "app-service"
  }
  spec {
    type = "LoadBalancer"
    selector = {
      app = "app"
    }
    port {
      port = 8080
      protocol = "TCP"
      target_port = 8080
    }
  }
}

resource "kubernetes_deployment" "app" {
  metadata {
    name = "app"
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "app"
      }
    }
    template {
      metadata {
        labels = {
          app = "app"
        }
      }
      spec {
        service_account_name = google_service_account.default.account_id
        container {
          name = "app"
          image = format("%s%s%s", "europe-central2-docker.pkg.dev/", var.project_id, "/artifactrepo/hello-java:v13")
          env {
            name = "GOOGLE_CLOUD_PROJECT"
            value = "sublime-oxygen-123456"
          }
        }
      }
    }
  }

  depends_on = [kubernetes_service.app-service]
}


resource "kubernetes_service" "app-ui-service" {
  metadata {
    name = "app-ui-service"
  }
  spec {
    type = "LoadBalancer"
    selector = {
      app = "app-ui"
    }
    port {
      port = 3000
      protocol = "TCP"
      target_port = 3000
    }
  }
}

resource "kubernetes_deployment" "app-ui" {
  metadata {
    name = "app-ui"
  }
  spec {
    replicas = 1
    selector {
      match_labels = {
        app = "app-ui"
      }
    }
    template {
      metadata {
        labels = {
          app = "app-ui"
        }
      }
      spec {
        container {
          name = "app-ui"
          image = format("%s%s%s", "europe-central2-docker.pkg.dev/", var.project_id, "/artifactrepo/hello-react:v10")
          port {
            container_port = 3000
            protocol = "TCP"
          }
        }
      }
    }
  }

  depends_on = [kubernetes_service.app-ui-service]
}

resource "kubernetes_ingress_v1" "ingress" {
  metadata {
    name = "my-ingress"
  }

  spec {
    rule {
      http {
        path {
          backend {
            service {
              name = "app-service"
              port {
                number = 8080
              }
            }
          }

          path = "/api/*"
        }

        path {
          backend {
            service {
              name = "app-ui-service"
              port {
                number = 3000
              }
            }
          }

          path = "/*"
        }
      }
    }
  }
}

module "cloudsql" {
  source     = "./cloudsql"
  project_id = "sublime-oxygen-123456"
  region     = "europe-central2"
  zone       = "europe-central2-a"
}

resource "google_pubsub_topic" "topic" {
  name = "sub-one"
  project = var.project_id
}