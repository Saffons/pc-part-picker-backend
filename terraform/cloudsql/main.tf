resource "google_sql_database_instance" "main" {
  name = "main-instance"
  database_version = "POSTGRES_15"
  region = var.region

  settings {
    tier = "db-f1-micro"
  }
}

resource "google_sql_database" "database" {
  name = "vradszky"
  instance = google_sql_database_instance.main.name
}