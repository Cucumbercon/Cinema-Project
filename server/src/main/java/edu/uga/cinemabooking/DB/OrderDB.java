package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public OrderDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public void addOrder(int user_id, int payment_id, int ticket_id, int promote_id, int ticket_amount,
    double total, String describe) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parsableString = sdf.format(Calendar.getInstance().getTime());
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(parsableString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());

        String sql = "INSERT INTO order (user_id, payment_id, ticket_id, promote_id, ticket_amount, total, order_time, discribe) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, payment_id);
            preparedStatement.setInt(3, ticket_id);
            preparedStatement.setInt(4, promote_id);
            preparedStatement.setInt(5, ticket_amount);
            preparedStatement.setDouble(6, total);
            preparedStatement.setTimestamp(7, sqlDate);
            preparedStatement.setString(8, describe);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
