package by.epam.mentoring.utils;

import org.fluttercode.datafactory.impl.DataFactory;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.IntStream;

public class Faker {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBGenerator.class);

    private DBCredentials cred;

    public Faker(DBCredentials cred) {
        this.cred = cred;
    }

    public void fillFakeData() throws SQLException {
        Connection conn = cred.getDBConnection(this.cred.getDbName());
        createFakeUsers(conn);
        createFakeFriends(conn);
        createFakePosts(conn);
        createFakeLikes(conn);
        conn.close();
    }

    private void createFakeUsers(Connection connection) {
        DataFactory df = new DataFactory();
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        InsertValuesStep4 insert = create.insertInto(
                DSL.table("user"),
                DSL.field("id"),
                DSL.field("name"),
                DSL.field("surname"),
                DSL.field("birth")
        );
        IntStream.range(1, 200).forEach(i -> insert.values(i, df.getFirstName(), df.getLastName(), df.getBirthDate()));
        insert.execute();
    }

    private void createFakeFriends(Connection connection) {
        DataFactory df = new DataFactory();
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        Result users = create.select().from("user").fetch();
        InsertValuesStep3 insert = create.insertInto(
                DSL.table("friends"),
                DSL.field("id_1"),
                DSL.field("id_2"),
                DSL.field("timestamp")
        );

        Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());

        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                insert.values(users.getValue(i, "id"), users.getValue(j, "id"), sqlDate);
            }
        }
        insert.execute();
    }

    private void createFakePosts(Connection connection) {
        DataFactory df = new DataFactory();
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        Result users = create.select().from("user").fetch();
        InsertValuesStep4 insert = create.insertInto(
                DSL.table("post"),
                DSL.field("id"),
                DSL.field("user_id"),
                DSL.field("text"),
                DSL.field("timestamp")
        );

        Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());

        IntStream.range(0, users.size()).forEach(i -> insert.values(users.getValue(i, "id"), users.getValue(i, "id"), df.getRandomText(50), sqlDate));
        insert.execute();
    }

    private void createFakeLikes(Connection connection) {
        DataFactory df = new DataFactory();
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        Result users = create.select().from("user").fetch();
        InsertValuesStep3 insert = create.insertInto(
                DSL.table("likes"),
                DSL.field("post_id"),
                DSL.field("user_id"),
                DSL.field("timestamp")
        );

        Random r = new Random();

        create.select().from("post").fetch().forEach(post ->
                IntStream.range(0, r.ints(90, 110).findFirst().getAsInt()).forEach(i ->
                        insert.values(post.getValue("id"), users.getValue(i, "id"), df.getDate(2015, 3, 1)))
        );
        insert.execute();
    }
}
