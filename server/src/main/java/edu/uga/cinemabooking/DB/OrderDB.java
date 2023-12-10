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

public class OrderDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
    public TicketDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public void addSeat(int ticket_id, int showroom_id, int column, int row) {

        String sql = "INSERT INTO seat (ticket_id, showroom_id, column, row) " +
                "VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ticket_id);
            preparedStatement.setInt(2, showroom_id);
            preparedStatement.setInt(3, column);
            preparedStatement.setInt(4, row);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
