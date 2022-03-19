package ru.archertech.starter.model;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

/**
 * @see <a href="https://quarkus.io/guides/reactive-sql-clients">reactive-sql-clients</a>
 */
public class Robot {
    private Long id;
    private String name;
    private String owner;

    public Robot() {
    }

    public Robot(Long id, String name, String owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }

    //<editor-fold desc="Datasource">
    public static Robot from(Row row) {
        return new Robot(row.getLong("id"),
                row.getString("name"),
                row.getString("owner"));
    }

    public static Multi<Robot> findAllByOwner(PgPool client, String owner) {
        return client.preparedQuery("SELECT id, name, owner FROM robots WHERE owner=$1 ORDER BY name ASC")
                .execute(Tuple.of(owner))
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Robot::from);
    }

    public static void create(PgPool client, Robot robot, String owner) {
        client.preparedQuery("INSERT into robots (name,owner) VALUES ($1,$2)")
                .execute(Tuple.of(robot.getName(), owner))
                .await().indefinitely();
        // TODO return robot
//                .onItem().
    }

//    public static Uni<Robot> create(PgPool client, Robot robot, String name) {
//        return client.preparedQuery()
//    }

    public static void deleteByIdAndOwner(PgPool client, long robotId, String name) {
        // TODO parameters
        client.preparedQuery("DELETE from robots where id=$1 and owner=$2")
                .execute(Tuple.of(robotId, name));

    }
    //</editor-fold>

}
