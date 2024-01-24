### GitHub Actions with Azure helper (by me)

In Azure Portal:
1. Create User-Assigned Managed Identity (https://portal.azure.com/#view/HubsExtension/BrowseResource/resourceType/Microsoft.ManagedIdentity%2FuserAssignedIdentities)
2. In Federated Credentials section configure GitHub issued token for Azure deployments.
3. In Azure Spring Apps (or sth else) go to Access Control (IAM) section and add proper privileges (for example Privileged administrator roles - Contributor)

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

Logs stream:
az spring app logs --resource-group Projekt_Mariusz_Marszalek_EU_West --service asa-qo5x7ny3q7hme-standard --name projekt-backend --follow