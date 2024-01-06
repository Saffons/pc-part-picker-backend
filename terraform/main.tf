#terraform {
#  required_providers {
#    google = {
#      source = "hashicorp/google"
#      version = "3.88.0"
#    }
#  }
#}
#
#resource "google_compute_network" "vpc" {
#  name                    = "test-network"
#  auto_create_subnetworks = false
#}
#
#resource "google_compute_subnetwork" "vpc_subnet" {
#  name          = "test-subnetwork"
#  ip_cidr_range = "10.2.0.0/16"
#  region        = var.region
#  network       = google_compute_network.vpc.id
#  secondary_ip_range {
#    range_name    = "services-range"
#    ip_cidr_range = "192.168.1.0/24"
#  }
#  secondary_ip_range {
#    range_name    = "pod-ranges"
#    ip_cidr_range = "192.168.64.0/22"
#  }
#}
#
#resource "google_service_account" "default" {
#  account_id   = "k8s-service-account-id"
#  display_name = "K8s Service Account"
#}
#resource "google_project_iam_member" "registry_reader_binding" {
#  role   = "roles/containerregistry.ServiceAgent"
#  member = "serviceAccount:${google_service_account.default.email}"
#  project = var.project_id
#}
#
#resource "google_container_cluster" "vpc_native_cluster" {
#  name                     = "my-gke-cluster"
#  remove_default_node_pool = true
#  initial_node_count       = 1
#  network    = google_compute_network.vpc.id
#  subnetwork = google_compute_subnetwork.vpc_subnet.id
#  ip_allocation_policy {
#    cluster_secondary_range_name  = google_compute_subnetwork.vpc_subnet.secondary_ip_range.0.range_name
#    services_secondary_range_name = google_compute_subnetwork.vpc_subnet.secondary_ip_range.1.range_name
#  }
#}
#
#resource "google_container_node_pool" "vpc_native_cluster_preemptible_nodes" {
#  name       = "my-node-pool"
#  cluster    = google_container_cluster.vpc_native_cluster.name
#  node_count = 1
#  node_config {
#    preemptible  = true
#    machine_type = "e2-medium"
#    service_account = google_service_account.default.email
#    oauth_scopes    = [
#      "https://www.googleapis.com/auth/cloud-platform"
#    ]
#  }
#}