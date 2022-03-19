# Postgres
Local postgres configured by instructions: https://wiki.archlinux.org/title/PostgreSQL

## How db and user inited
db name = db user

sudo -iu postgres

Example: 

```shell
[postgres@ac-acer ~]$ createuser --interactive
Enter name of role to add: starter-dev-keycloak
Shall the new role be a superuser? (y/n) n
Shall the new role be allowed to create databases? (y/n) n
Shall the new role be allowed to create more new roles? (y/n) n
createdb starter-dev-keycloak -O starter-dev-keycloak
```


# Keycloak
## DB

starter-dev-keycloak

## Realm
Imported from config/starter-realm.json

Importing keycloak realm:
- https://github.com/keycloak/keycloak-documentation/blob/main/server_admin/topics/export-import.adoc
- https://www.keycloak.org/docs/latest/server_admin/index.html#proc-creating-a-realm_server_administration_guide

## Application security
See application.properties: quarkus.oidc

See https://quarkus.io/guides/security-keycloak-authorization

## Resources
- https://blog.codecentric.de/en/2021/12/keycloak-keycloak-x/
- https://www.keycloak.org/2020/12/first-keycloak-x-release.adoc
- https://keycloak.discourse.group/t/differences-between-keycloak-and-keycloak-x-docker-images/9459/4
- https://github.com/keycloak/keycloak-containers/blob/16.1.1/server/README.md
- https://github.com/keycloak/keycloak-community/blob/main/design/keycloak.x/configuration.md#databaseq
- Usage from app: https://quarkus.io/guides/security-keycloak-authorization
