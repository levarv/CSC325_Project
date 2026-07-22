package model;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class SQLQuery {

    public static ArrayList<Song> query(String query) {
        final String DATABASE_URL = "jdbc:derby:firstdb";
        ArrayList<Song> tbReturned = new ArrayList<>();
        System.out.println(query);
        // use try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(7));
                tbReturned.add(
                        new Song(new File(resultSet.getString(7)))
                );
            }

            return tbReturned;
        }

        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void playlistQuery(String query) {
        final String DATABASE_URL = "jdbc:derby:firstdb";
        ArrayList<Playlist> tbReturned = new ArrayList<>();
        System.out.println(query);
        // use try-with-resources to connect to and query the database
        try (
                Connection connection = DriverManager.getConnection(
                        DATABASE_URL);

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

            }
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