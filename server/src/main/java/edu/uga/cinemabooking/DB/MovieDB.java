package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDB {

    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    public MovieDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void addMovie(int id, int adult, String language, String title, 
                         double popularity, String posterPath, String backdropPath,
                         String releaseDay, int state, String category, 
                         String trailerPath, String synopsis, String cast, double rating) {
     
                            
        String sql = "INSERT INTO movie (ID, adult, language, title, popularity, poster_path, backdrop_path, " + 
                     "release_day, state, category, trailer_path, synopsis, cast, rating) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, 0);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, title);
            preparedStatement.setDouble(5, popularity);
            preparedStatement.setString(6, posterPath);
            preparedStatement.setString(7, backdropPath);
            preparedStatement.setDate(8, java.sql.Date.valueOf("2013-09-04"));
            preparedStatement.setInt(9, state);
            preparedStatement.setString(10, category);
            preparedStatement.setString(11, trailerPath);
            preparedStatement.setString(12, synopsis);
            preparedStatement.setString(13, cast);
            preparedStatement.setDouble(14, rating);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // this method is for update movie
    public void updateMovie(String type, String newInfo) {

    }

    // this method is for search movie
    public void searchMovie() {

    }

    // this method is for delete movie
    public void deleteMovie(String movieName) {

    }

}
