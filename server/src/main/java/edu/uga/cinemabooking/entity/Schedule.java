package edu.uga.cinemabooking.entity;

public class Schedule {
    int movie_id;
    int showroom_id;
    String start_time;
    String end_time;

    public Schedule() {

    }
    public Schedule(int id, int movie_id, int showroom_id, String start_time, String end_time) {
        this.movie_id = movie_id;
        this.showroom_id = showroom_id;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getMovieId() {
        return movie_id;
    }

    public int getShowroomId() {
        return showroom_id;
    }

    public String getStartTime() {
        return start_time;
    }

    public String getEndTime() {
        return end_time;
    }

    public void setMovieId() {
        this.movie_id = movie_id;
    }

    public void setShowroomId() {
        this.showroom_id = showroom_id;
    }

    public void setStartTime() {
        this.start_time = start_time;
    }

    public void setEndTime() {
        this.end_time = end_time;
    }
}

