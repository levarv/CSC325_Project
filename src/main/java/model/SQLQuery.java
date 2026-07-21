package model;

import java.sql.*;
import java.util.LinkedList;

public class SQLQuery {

    public static void query(String query) {
        final String DATABASE_URL = "jdbc:derby:firstdb";
        String[] tbReturned = null;
        System.out.println(query);
        // use try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            for (int i = 1; resultSet.next(); ++i)
                System.out.println( resultSet.getObject(i).toString() );
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void update(String update) {
        final String DATABASE_URL = "jdbc:derby:firstdb";
        final String UPDATE = update;
        System.out.println(update);
        // use try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);

                Statement statement = connection.createStatement();
        ) {

            statement.executeUpdate(UPDATE);

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}



