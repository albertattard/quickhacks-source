resource "azurerm_resource_group" "myterraformgroup" {
  name     = "quickhacks_resourcegroup"
  location = "GermanyWestCentral"

  tags = {
    Name      = "Quickhacks - Resource Group"
    Quickhack = "VM - Publicly Accessible"
    ManagedBy = "Terraform"
  }
}