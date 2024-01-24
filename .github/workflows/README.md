### GitHub Actions with Azure helper (by me)

In Azure Portal:
1. Create User-Assigned Managed Identity (https://portal.azure.com/#view/HubsExtension/BrowseResource/resourceType/Microsoft.ManagedIdentity%2FuserAssignedIdentities)
2. In Access Control (IAM) section add proper roles to the identity (for example Privileged administrator roles - Contributor)
3. In Federated Credentials section configure GitHub issued token for Azure deployments.

In GitHub .yml file:
1. Checkout (actions/checkout@v4)
2. Login to Azure (azure/login@v1)
3. Set repository secrets
* `client-id: ${{ secrets.AZURE_CLIENT_ID }}`
* `tenant-id: ${{ secrets.AZURE_TENANT_ID }}`
* `subscription-id: ${{ secrets.AZURE_SUBSCRIPTION_ID }})`
4. (Don't know if needed) Add\
permissions: \
&nbsp;&nbsp;&nbsp;&nbsp;id-token: write \
&nbsp;&nbsp;&nbsp;&nbsp;contents: read \
in the global scope of .yml file