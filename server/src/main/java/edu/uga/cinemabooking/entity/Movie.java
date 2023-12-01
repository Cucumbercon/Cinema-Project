package edu.uga.cinemabooking.entity;

public class Movie {
    int id;
    String title;
    String language;
    double popularity;
    String posterPath;
    String backdropPath;
    String date;
    int state;
    String category;
    String trailerPath;
    String synopsis;
    String cast;
    String director;
    String producer;
    double rating;
    int duration;
    public Movie() {

    }

    public Movie(int id, String titile, String language, double popularity,
            String posterPath, String backdropPath, String date,
            int state, String category, String trailerPath, String synopsis,
            String cast, String director, String producer, double rating, int duration) {
        this.id = id;
        this.title = titile;
        this.language = language;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.date = date;
        this.state = state;
        this.category = category;
        this.trailerPath = trailerPath;
        this.synopsis = synopsis;
        this.cast = cast;
        this.director = director;
        this.producer = producer;
        this.rating = rating;
        this.duration = duration;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", date='" + date + '\'' +
                ", state=" + state +
                ", category='" + category + '\'' +
                ", trailerPath='" + trailerPath + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", cast='" + cast + '\'' +
                ", director='" + director + '\'' +
                ", producer='" + producer + '\'' +
                ", rating=" + rating +
                ", duration=" + duration +
                '}';
    }

}
