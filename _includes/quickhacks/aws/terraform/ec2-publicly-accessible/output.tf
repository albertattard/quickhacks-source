output "instance_ip" {
  description = "Elastic (public) IP, associated to the network interface connected to the EC2 instance"
  value       = coalesce(aws_eip.quickhacks_eip.*.public_ip)
}
