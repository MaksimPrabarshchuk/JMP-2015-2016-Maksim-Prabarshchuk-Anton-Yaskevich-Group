package by.epam.mentoring.utils;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.StringUtils;
import org.jooq.util.mysql.MySQLDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.jooq.impl.DSL.constraint;

public class DBGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBGenerator.class);

    private DBCredentials cred;

    public DBGenerator(DBCredentials cred) {
        this.cred = cred;
    }

    public void generateData() {
        try {
            createDB();
            Connection connection = cred.getDBConnection(cred.getDbName());
            createUserTable(connection);
            createFriendshipsTable(connection);
            createPostTable(connection);
            createLikeTable(connection);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserTable(Connection connection) {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        create
                .createTable("user")
                .column("id", MySQLDataType.INTEGER)
                .column("name", MySQLDataType.VARCHAR.length(20))
                .column("surname", MySQLDataType.VARCHAR.length(20))
                .column("birth", MySQLDataType.DATE)
                .execute();
        create.alterTable("user")
                .add(constraint("PK_user").primaryKey("id"))
                .execute();
    }

    private void createFriendshipsTable(Connection connection) {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        create
                .createTable("friends")
                .column("id_1", MySQLDataType.INTEGER)
                .column("id_2", MySQLDataType.INTEGER)
                .column("timestamp", MySQLDataType.TIMESTAMP)
                .execute();
    }

    private void createPostTable(Connection connection) {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        create
                .createTable("post")
                .column("id", MySQLDataType.INTEGER)
                .column("user_id", MySQLDataType.INTEGER)
                .column("text", MySQLDataType.TEXT)
                .column("timestamp", MySQLDataType.TIMESTAMP)
                .execute();
        create.alterTable("post")
                .add(constraint("PK_post").primaryKey("id"))
                .execute();
        create.alterTable("post")
                .add(constraint("FK_user_id")
                        .foreignKey("user_id")
                        .references("user", "id"))
                .execute();
    }

    private void createLikeTable(Connection connection) {
        DSLContext create = DSL.using(connection, SQLDialect.MYSQL);
        create
                .createTable("likes")
                .column("post_id", MySQLDataType.INTEGER)
                .column("user_id", MySQLDataType.INTEGER)
                .column("timestamp", MySQLDataType.TIMESTAMP)
                .execute();
        create.alterTable("likes")
                .add(constraint("FK_post_id")
                        .foreignKey("post_id")
                        .references("post", "id"))
                .execute();
        create.alterTable("likes")
                .add(constraint("FK_likes_user_id")
                        .foreignKey("user_id")
                        .references("user", "id"))
                .execute();
    }

    private void createDB() throws ClassNotFoundException, SQLException {
        Connection connection = cred.getDBConnection(StringUtils.EMPTY);
        Statement s = connection.createStatement();
        s.executeUpdate("CREATE DATABASE " + cred.getDbName());
        connection.close();
    }
}
