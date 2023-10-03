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

    public void addMovie(String language, String title, double popularity, String posterPath, 
                         String backdropPath, String releaseDay, int state, String category, 
                         String trailerPath, String synopsis, String cast, double rating, 
                         String director, String producer) {
     
                            
        String sql = "INSERT INTO movie (language, title, popularity, poster_path, backdrop_path, " + 
                     "release_day, state, category, trailer_path, synopsis, cast, rating, director, producer) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, language);
            preparedStatement.setString(2, title);
            preparedStatement.setDouble(3, popularity);
            preparedStatement.setString(4, posterPath);
            preparedStatement.setString(5, backdropPath);
            preparedStatement.setDate(6, java.sql.Date.valueOf("2013-09-04"));
            preparedStatement.setInt(7, state);
            preparedStatement.setString(8, category);
            preparedStatement.setString(9, trailerPath);
            preparedStatement.setString(10, synopsis);
            preparedStatement.setString(11, cast);
            preparedStatement.setDouble(12, rating);
            preparedStatement.setString(13, director);
            preparedStatement.setString(14, producer);
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // this method is for update movie
    public void updateMovie(String type, String newInfo) {

    }

    // this method is for search movie
    public void searchMovie(String term) {
        public ResultSet searchMovie(String term) {
            String query = "SELECT FROM movie"
                    + " WHERE title LIKE \'?%\'";
            try (PreparedStatement prestate = connection.prepareStatement(query)) {
                prestate.setString(1, term);
                return prestate.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // ResultSet
    } // searchMovie

    // this method is for delete movie
    public void deleteMovie(int id) {
        String sql = "DELETE FROM movie WHERE ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
