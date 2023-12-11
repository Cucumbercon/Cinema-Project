package edu.uga.cinemabooking.entity;

public class MovieInfo {
    private String movieTitle;
    private String startTime;

    // Constructor
    public MovieInfo() {
    }

    public MovieInfo(String movieTitle, String startTime) {
        this.movieTitle = movieTitle;
        this.startTime = startTime;
    }

    // Getter for movieTitle
    public String getMovieTitle() {
        return movieTitle;
    }

    // Setter for movieTitle
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    // Getter for startTime
    public String getStartTime() {
        return startTime;
    }

    // Setter for startTime
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "movieTitle='" + movieTitle + '\'' +
                ", startTime='" + startTime + '\'' +
                '}';
    }
}
