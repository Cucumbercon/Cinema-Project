package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cinemabooking.entity.Movie;

public class MovieDB {

    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
    public MovieDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * This method will add the movie to the db
     * 
     * @param language language
     * @param title title
     * @param popularity popularity
     * @param posterPath posterPath
     * @param backdropPath backdropPath
     * @param releaseDay releaseDay
     * @param state state
     * @param category category
     * @param trailerPath trailerPath
     * @param synopsis synopsis
     * @param cast cast
     * @param rating rating
     * @param director director
     * @param producer producer
     */
    public void addMovie(String language, String title, double popularity, String posterPath,
            String backdropPath, String releaseDay, int state, String category,
            String trailerPath, String synopsis, String cast, double rating,
            String director, String producer) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(releaseDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


        String sql = "INSERT INTO movie (language, title, popularity, poster_path, backdrop_path, " +
                "release_day, state, category, trailer_path, synopsis, cast, rating, director, producer) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, language);
            preparedStatement.setString(2, title);
            preparedStatement.setDouble(3, popularity);
            preparedStatement.setString(4, posterPath);
            preparedStatement.setString(5, backdropPath);
            preparedStatement.setDate(6, sqlDate);
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

    /**
     * This method is for update movie
     * @param type
     * @param newInfo
     */
    public void updateMovie(String type, String newInfo) {

    }

    /**
     * This method is used to search for the available movie from db
     * @return the lis of movies
     */
    public List<Movie> getAvailableMovie() {

        String sql = "SELECT * FROM movie WHERE state = ?";
        List<Movie> movies = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            movies = new ArrayList<>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setTitle(resultSet.getString("title"));
                movie.setState(resultSet.getInt("state"));
                movie.setPopularity(resultSet.getDouble("popularity"));
                movie.setPosterPath(resultSet.getString("poster_path"));
                movie.setBackdropPath(resultSet.getString("backdrop_path"));
                movie.setDate(resultSet.getString("release_day"));
                movie.setCategory(resultSet.getString("category"));
                movie.setTrailerPath(resultSet.getString("trailer_path"));
                movie.setSynopsis(resultSet.getString("synopsis"));
                movie.setCast(resultSet.getString("cast"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setDirector(resultSet.getString("director"));
                movie.setProducer(resultSet.getString("producer"));

                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;

    }

    /**
     * This method is used to search for upcoming movie
     * @return up-coming movie list
     */
    public List<Movie> getUpComingMovie() {
        String sql = "SELECT * FROM movie WHERE state = ?";
        List<Movie> movies = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();

            movies = new ArrayList<>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setTitle(resultSet.getString("title"));
                movie.setState(resultSet.getInt("state"));
                movie.setPopularity(resultSet.getDouble("popularity"));
                movie.setPosterPath(resultSet.getString("poster_path"));
                movie.setBackdropPath(resultSet.getString("backdrop_path"));
                movie.setDate(resultSet.getString("release_day"));
                movie.setCategory(resultSet.getString("category"));
                movie.setTrailerPath(resultSet.getString("trailer_path"));
                movie.setSynopsis(resultSet.getString("synopsis"));
                movie.setCast(resultSet.getString("cast"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setDirector(resultSet.getString("director"));
                movie.setProducer(resultSet.getString("producer"));

                movies.add(movie);
            }

            // for (Movie movie : movies) {
            // System.out.println(movie.toString());
            // }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;

    }

    // this method is for delete movie
    // public void deleteMovie(int id) {
    //     String sql = "DELETE FROM movie WHERE ID = ?";
    //     try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    //         preparedStatement.setInt(1, id);
    //         preparedStatement.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    
    /**
     * This method will search the db with keyword
     * @param movieName keyword
     * @return the list of movies
     */
    public List<Movie> searchMovieByName(String movieName) {
        String sql = "SELECT * FROM movie WHERE title LIKE ?";
        List<Movie> movies = new ArrayList<>();
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + movieName + "%"); // 使用通配符匹配部分电影名称
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt("id"));
                movie.setLanguage(resultSet.getString("language"));
                movie.setTitle(resultSet.getString("title"));
                movie.setState(resultSet.getInt("state"));
                movie.setPopularity(resultSet.getDouble("popularity"));
                movie.setPosterPath(resultSet.getString("poster_path"));
                movie.setBackdropPath(resultSet.getString("backdrop_path"));
                movie.setDate(resultSet.getString("release_day"));
                movie.setCategory(resultSet.getString("category"));
                movie.setTrailerPath(resultSet.getString("trailer_path"));
                movie.setSynopsis(resultSet.getString("synopsis"));
                movie.setCast(resultSet.getString("cast"));
                movie.setRating(resultSet.getDouble("rating"));
                movie.setDirector(resultSet.getString("director"));
                movie.setProducer(resultSet.getString("producer"));
    
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }
}
