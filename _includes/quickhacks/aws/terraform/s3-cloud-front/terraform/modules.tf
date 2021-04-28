module "template_files" {
  source   = "hashicorp/dir/template"
  base_dir = "${path.module}/../web-app"
}
