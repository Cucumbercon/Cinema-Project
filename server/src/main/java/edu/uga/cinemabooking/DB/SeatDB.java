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

import edu.uga.cinemabooking.entity.Schedule;

public class SeatDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
    public SeatDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public int addSeat(int ticket_id, int showroom_id, int column, String row) {

        String sql = "INSERT INTO seat (ticket_id, showroom_id, column, row_alphabet) " +
                "VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ticket_id);
            preparedStatement.setInt(2, showroom_id);
            preparedStatement.setInt(3, column);
            preparedStatement.setString(4, row);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findMaxId();
    }

    public int findMaxId() {
        String sql = "SELECT MAX(ID) as max_id FROM seat";
        int maxId = -1; // default value if no rows are found
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                if (Integer.valueOf(resultSet.getInt("max_id")) == null) {
                    maxId = 1;
                } else {
                    maxId = resultSet.getInt("max_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return maxId;
    }
}
