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
        java.sql.Timestamp sqlDate1 = new java.sql.Timestamp(utilStart.getTime());
        java.sql.Timestamp sqlDate2 = new java.sql.Timestamp(utilEnd.getTime());

        String sql = "INSERT INTO schedule (ID, movie_id, showroom_id, start_time, end_time) " +
                "VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, findMaxId() + 1);
            preparedStatement.setInt(2, movie_id);
            preparedStatement.setInt(3, showroom_id);
            preparedStatement.setTimestamp(4, sqlDate1);
            preparedStatement.setTimestamp(5, sqlDate2);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Schedule> getSchedulesFromMovie(int movie_id) {

        String sql = "SELECT * FROM schedule WHERE movie_id = ?";
        List<Schedule> schedules = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movie_id);
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

    public boolean checkOverlapScheduleMovie(String input_time, int movie_id) {
        List<Schedule> schedules = getSchedules();
        if (schedules == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < schedules.size(); i++) {
            Schedule schedule = schedules.get(i);
            try {
                System.out.println("Check DB1" + schedule.getStartTime());
                System.out.println("Check DB2" + schedule.getEndTime());
                Date parsedStart = sdf.parse(schedule.getStartTime());
                Date parsedEnd = sdf.parse(schedule.getEndTime());
                Date parsedInput = sdf.parse(input_time);
                Time startTime = new Time(parsedStart.getTime());
                Time endTime = new Time(parsedEnd.getTime());
                Time inputTime = new Time(parsedInput.getTime());
                if (inputTime.after(startTime) && inputTime.before(endTime)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean movieExists() {
        return false;
    }

    public List<Schedule> getScheduleMovie(int movie_id) {
        List<Schedule> schedule = null;
        String sql = "SELECT * FROM schedule WHERE movie_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movie_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            schedule = new ArrayList<>();
            while (resultSet.next()) {
                Schedule schedules = new Schedule();
                schedules = new Schedule();
                schedules.setScheduleId(resultSet.getInt("ID"));
                schedules.setMovieId(resultSet.getInt("movie_id"));
                schedules.setShowroomId(resultSet.getInt("showroom_id"));
                schedules.setStartTime(resultSet.getString("start_time"));
                schedules.setEndTime(resultSet.getString("end_time"));

                schedule.add(schedules);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } // try
        return schedule;
    } // getLoggedInProfile()

    public void convertDate() {

    }

    public List<Schedule> getSchedules() {

        String sql = "SELECT * FROM schedule";
        List<Schedule> schedules = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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

    public int findMaxId() {
        String sql = "SELECT MAX(ID) as max_id FROM schedule";
        int maxId = -1; // default value if no rows are found
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return maxId;
    }
}
