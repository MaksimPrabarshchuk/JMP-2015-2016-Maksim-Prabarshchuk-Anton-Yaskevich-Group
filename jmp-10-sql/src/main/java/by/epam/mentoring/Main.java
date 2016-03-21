package by.epam.mentoring;

import by.epam.mentoring.utils.DBCredentials;
import by.epam.mentoring.utils.DBGenerator;
import by.epam.mentoring.utils.DBOperations;
import by.epam.mentoring.utils.Faker;
import org.jooq.impl.DSL;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

class Main {

    public static void main(String[] args) throws Exception {
        DBCredentials cred = new DBCredentials(
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/",
                "root",
                "root",
                "jmp"
        );
        DBGenerator gen = new DBGenerator(cred);
        Faker faker = new Faker(cred);
        DBOperations op = new DBOperations(cred);
        gen.generateData();
        faker.fillFakeData();
        ResultSet rs = op.executeTask();

    }
}