import Keycloak from "keycloak-js";

// Setup Keycloak instance as needed
// Pass initialization options as required or leave blank to load from 'keycloak.json'
const keycloak = new Keycloak({
  url: "http://localhost:8890/",
  realm: "bmw-app",
  clientId: "bmw-app-gateway-client",
});

export default keycloak;
