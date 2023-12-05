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
import java.sql.Time;
import java.util.Date;

import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.Schedule;
import edu.uga.cinemabooking.entity.Showroom;

public class ScheduleDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
    public ScheduleDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public void addSchedule(int movie_id, int showroom_id, String start_time, String end_time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilStart = null;
        java.util.Date utilEnd = null;
        try {
            utilStart = sdf.parse(start_time);
            utilEnd = sdf.parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate1 = new java.sql.Date(utilStart.getTime());
        java.sql.Date sqlDate2 = new java.sql.Date(utilEnd.getTime());

        String sql = "INSERT INTO schedule (movie_id, showroom_id, start_time, end_time) " +
                "VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movie_id);
            preparedStatement.setInt(2, showroom_id);
            preparedStatement.setDate(3, sqlDate1);
            preparedStatement.setDate(4, sqlDate2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Schedule> getSchedules(int movie_id) {

        String sql = "SELECT * FROM schedule WHERE movie_id = ?";
        List<Schedule> schedules = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(0, movie_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            schedules = new ArrayList<>();

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setMovieId(resultSet.getInt("id"));
                schedule.setShowroomId(resultSet.getInt("showroom_id"));
                schedule.setStartTime(resultSet.getString("start_time"));
                schedule.setEndTime(resultSet.getString("end_time"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(movies);
        return schedules;

    }

    public boolean checkOverlapSchedule(String input_time, int movie_id) {
        List<Schedule> schedules = getSchedules(movie_id);
        if (schedules == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            try {
                Date parsedStart = sdf.parse(schedule.getStartTime());
                Date parsedEnd = sdf.parse(schedule.getEndTime());
                Date parsedInput = sdf.parse(input_time);
                Time startTime = new Time(parsedStart.getTime());
                Time endTime = new Time(parsedEnd.getTime());
                Time inputTime = new Time(parsedInput.getTime());
                if (!inputTime.before(startTime) && !inputTime.after(endTime)) {

                } else {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
