output "redshift_master_password" {
  value     = aws_redshift_cluster.redshift.master_password
  sensitive = true
}

output "redshift_dns_name" {
  value = aws_redshift_cluster.redshift.dns_name
}
