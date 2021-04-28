output "db_address" {
  description = "The database hostname"
  value       = aws_db_instance.quickhacks_db.address
}

output "db_password" {
  description = "The randomly generated password"
  value       = aws_db_instance.quickhacks_db.password
  sensitive   = true
}
