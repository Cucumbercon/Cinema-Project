package edu.uga.cinemabooking.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.util.Date;

public class Schedule {
    int movie_id;
    int showroom_id;
    Date start_time;
    Date end_time;

    public Schedule() {

    }
    public Schedule(int id, int movie_id, int showroom_id, Date start_time, Date end_time) {
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

    public Date getStartTime() {
        return start_time;
    }

    public Date getEndTime() {
        return end_time;
    }

    public void setMovieId(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setShowroomId(int showroom_id) {
        this.showroom_id = showroom_id;
    }

    public void setStartTime(Date start_time) {
        this.start_time = start_time;
    }

    public void setEndTime(Date end_time) {
        this.end_time = end_time;
    }
}

