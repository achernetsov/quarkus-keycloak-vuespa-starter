package ru.archertech.starter.resource;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import ru.archertech.starter.model.Robot;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/api/v1/robot")
public class RobotResource {

    @Inject
    PgPool client;
    @Inject
    SecurityIdentity identity;
    @Inject
    @ConfigProperty(name = "app.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }


    //<editor-fold desc="api">

    @GET
    public Multi<Robot> get() {
        return Robot.findAllByOwner(client, identity.getPrincipal().getName());
    }

    @POST
    public Response create(Robot robot) {
        Robot.create(client, robot, identity.getPrincipal().getName());
        return Response.status(201).build();
    }

    @DELETE
    public Response deleteRobot(long robotId) {
        Robot.deleteByIdAndOwner(client, robotId, identity.getPrincipal().getName());
        return Response.status(301)
                .location(URI.create("/robots"))
                .build();
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS robots").execute()
                .flatMap(r -> client.query("CREATE TABLE robots " +
                                "(id SERIAL PRIMARY KEY, " +
                                "name varchar(30) NOT NULL, " +
                                "owner varchar(30) NOT NULL" +
                                ")")
                        .execute())
                .flatMap(r -> client.query("INSERT INTO robots (name, owner) VALUES ('Orange', 'alice')").execute())
                .flatMap(r -> client.query("INSERT INTO robots (name, owner) VALUES ('Pear', 'alice')").execute())
                .await().indefinitely();
    }
    //</editor-fold>
}
