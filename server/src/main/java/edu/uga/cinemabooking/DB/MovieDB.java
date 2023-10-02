package edu.uga.cinemabooking.DB;

import static org.mockito.Mockito.description;

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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void addMovie(int id, short adult, String[] language, String title[], 
                         double popularity, String posterPath, String backdropPath,
                         String releaseDay, String state, String[] category, 
                         String[] trailerPath, String synopsis, String cast, double rating) {
     
                            
        String sql = "INSERT INTO movie (ID, adult, lanuage, title, popularity, poster_path, backdrop_path, " + 
                     "release_day, state, category, trailer_path, synopsis, cast, rating) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setShort(2, adult);
            preparedStatement.setString(3, director);
            preparedStatement.setString(4, genre);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, description);
            preparedStatement.setString(7, description);
            preparedStatement.set(8, description);
            preparedStatement.setString(9, description);
            preparedStatement.setString(10, description);
            preparedStatement.setString(11, description);
            preparedStatement.setString(12, description);
            preparedStatement.setString(13, description);
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
