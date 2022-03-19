# Overview

Quarkus backend, vue.js SPA frontend, keycloak.

# Prerequisites
1. Docker, docker-compose
2. Postgres

# Init databases
1. Keycloak DB (see dev-env/README.md)
2. Backend database (name it 'starter-dev', see application.properties)

# Running
Start docker and postgres services.

## Keycloak
Bootstrap keycloak: 
> cd backend
> docker-compose up -d

Export keycloak realm: 
1. Create realm 'starter'
2. Go to manage > export, and export backend/config/starter-realm.json

## Backend

> ./mvnw quarkus:dev

## Frontend
> cd frontend
> npm i
> npm run dev

Now go to localhost:3000 (where SPA listens), login as alice/1, you'll see alice's robots.

# References
- https://github.com/quarkusio/quarkus-quickstarts
- https://quarkus.io/guides/qute
- https://quarkus.io/guides/security-openid-connect
- security-openid-connect-web-authentication-quickstart
- security-openid-connect-quickstart
- About OIDC logout: https://github.com/keycloak/keycloak-documentation/blob/main/securing_apps/topics/oidc/java/logout.adoc