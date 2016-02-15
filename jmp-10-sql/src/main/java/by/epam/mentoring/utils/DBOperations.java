package by.epam.mentoring.utils;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.field;

public class DBOperations {

    private DBCredentials cred;

    public DBOperations(DBCredentials cred) {
        this.cred = cred;
    }

    public ResultSet executeTask() {
        Connection conn = cred.getDBConnection(cred.getDbName());
        DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
        return create
                .select(count(), DSL.field("name"), DSL.field("surname"))
                .from(DSL.table("user"))
                .join(DSL.table("post")).on(DSL.field("user_id").equal(DSL.table("user").field("id")))
                .join(DSL.table("likes")).on(DSL.field("post_id").equal(DSL.table("post").field("id")))
                .where(DSL.field("timestamp").equal("2015-03-01"))
                .groupBy(DSL.table("user").field("id"))
                .having(count().equal(100))
                .and(DSL.val(100)
                        .equal(
                                create
                                        .selectCount()
                                        .from(DSL.table("user"))
                                        .join(DSL.table("friends"))
                                        .on(DSL.field("id_1").equal(DSL.field("id")))
                                        .or(DSL.field("id_2").equal(DSL.field("id"))))
                ).fetchResultSet();
    }
}
