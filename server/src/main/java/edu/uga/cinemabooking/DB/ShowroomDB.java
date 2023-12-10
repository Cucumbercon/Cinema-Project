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
import edu.uga.cinemabooking.entity.Schedule;
import edu.uga.cinemabooking.entity.Showroom;

public class ShowroomDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
    public ShowroomDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public void addShowroom(int seat_amount, String information, String name) {

        String sql = "INSERT INTO showroom (seat_amount, information, name) " +
                "VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, seat_amount);
            preparedStatement.setString(2, information);
            preparedStatement.setString(3, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Showroom getShowroomID(int movie_id) {

        String sql = "SELECT * FROM showroom WHERE name = ?";
        Showroom showroom = new Showroom();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movie_id);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                showroom.setId(resultSet.getInt("id"));
                showroom.setSeatAmount(resultSet.getInt("seat_amount"));
                showroom.setInformation(resultSet.getString("information"));
                showroom.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return showroom;

    }
    
    public boolean startTimeExist(String information) {

        String sql = "SELECT * FROM showroom WHERE information = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, information);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);

        }

        return false;
    }
}
