package model;

import java.sql.*;

public class SQLQuery {

    public static String query(String query) {
        final String DATABASE_URL = "jdbc:derby:firstdb";
        System.out.println(query);
        // use try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next())
                return resultSet.getObject(1).toString();
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
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



