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
     * @param language     language
     * @param title        title
     * @param popularity   popularity
     * @param posterPath   posterPath
     * @param backdropPath backdropPath
     * @param releaseDay   releaseDay
     * @param state        state
     * @param category     category
     * @param trailerPath  trailerPath
     * @param synopsis     synopsis
     * @param cast         cast
     * @param rating       rating
     * @param director     director
     * @param producer     producer
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
     * This method is used to get the movie information by id
     * 
     * @param id movie id
     */
    public Movie getMovieDetail(int id) {

        String sql = "SELECT * FROM movie WHERE id = ?";
        Movie movie = new Movie();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
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
                movie.setDuration(resultSet.getInt("duration"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;

    }

    /**
     * This method is used to search for the available movie from db
     * 
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
                movie.setDuration(resultSet.getInt("duration"));

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(movies);
        return movies;

    }

    /**
     * This method is used to search for upcoming movie
     * 
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
                movie.setDuration(resultSet.getInt("duration"));
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

    /**
     * This method is used to search for the archive movie from db
     * 
     * @return the lis of movies
     */
    public List<Movie> getArchiveMovie() {
        String sql = "SELECT * FROM movie WHERE state = ?";
        List<Movie> movies = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, -1);
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
                movie.setDuration(resultSet.getInt("duration"));
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
    // String sql = "DELETE FROM movie WHERE ID = ?";
    // try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
    // {
    // preparedStatement.setInt(1, id);
    // preparedStatement.executeUpdate();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    /**
     * This method will search the db with keyword
     * 
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

    public boolean updateMovieInfo(Movie movie) {
        String sql = "UPDATE movie SET language = ?, title = ?, state = ?, popularity = ?, poster_path = ?, backdrop_path = ?, release_day = ?, category = ?, trailer_path = ?, synopsis = ?, cast = ?, rating = ?, director = ?, producer = ?, duration = ? WHERE id = ?";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie.getLanguage());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setInt(3, movie.getState());
            preparedStatement.setDouble(4, movie.getPopularity());
            preparedStatement.setString(5, movie.getPosterPath());
            preparedStatement.setString(6, movie.getBackdropPath());
            preparedStatement.setString(7, movie.getDate());
            preparedStatement.setString(8, movie.getCategory());
            preparedStatement.setString(9, movie.getTrailerPath());
            preparedStatement.setString(10, movie.getSynopsis());
            preparedStatement.setString(11, movie.getCast());
            preparedStatement.setDouble(12, movie.getRating());
            preparedStatement.setString(13, movie.getDirector());
            preparedStatement.setString(14, movie.getProducer());
            preparedStatement.setInt(15, movie.getDuration());
            preparedStatement.setInt(16, movie.getId());
    
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Movie getMovieID(String movie_title) {

        String sql = "SELECT * FROM movie WHERE title = ?";
        Movie movie = new Movie();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, movie_title);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
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
                movie.setDuration(resultSet.getInt("duration"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;

    }
    
}
