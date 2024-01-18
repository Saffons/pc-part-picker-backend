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
  ])
  role = each.key
  member = "serviceAccount:${google_service_account.default.email}"
  project = var.project_id
}

resource "google_container_cluster" "primary" {
  name     = "my-gke-cluster"
  location = var.region

  # We can't create a cluster with no node pool defined, but we want to only use
  # separately managed node pools. So we create the smallest possible default
  # node pool and immediately delete it.
  remove_default_node_pool = true
  initial_node_count       = 1
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
        container {
          name = "app"
          image = format("%s%s%s", "europe-central2-docker.pkg.dev/", var.project_id, "/artifactrepo/hello-java:v3")
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
          image = format("%s%s%s", "europe-central2-docker.pkg.dev/", var.project_id, "/artifactrepo/hello-react:v2")
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

module "cloudsql" {
  source     = "./cloudsql"
  project_id = var.project_id
  region     = var.region
  zone       = var.zone
}