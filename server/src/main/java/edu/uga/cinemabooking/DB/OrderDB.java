package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.List;

import edu.uga.cinemabooking.entity.Schedule;
import edu.uga.cinemabooking.entity.Order;

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

    public void addOrder(int user_id, int payment_id, int promote_id, int ticket_amount,
            double total, String describe) {

        String sql = "INSERT INTO `order` (user_id, payment_id, promote_id, ticket_amount, total, order_time, discribe) "
                +
                "VALUES (?,?,?,?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parsableString = sdf.format(Calendar.getInstance().getTime());
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(parsableString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, payment_id);
            preparedStatement.setInt(3, promote_id);
            preparedStatement.setDouble(4, ticket_amount);
            preparedStatement.setDouble(5, total);
            preparedStatement.setTimestamp(6, sqlDate);
            preparedStatement.setString(7, describe);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addOrderHelper(double total, String describe) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String parsableString = sdf.format(Calendar.getInstance().getTime());
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(parsableString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
        String sql = "INSERT INTO order (total, order_time, discribe) WHERE ID = " + findMaxId() + " VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, total);
            preparedStatement.setTimestamp(2, sqlDate);
            preparedStatement.setString(3, describe);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int findMaxId() {
        String sql = "SELECT MAX(ID) as max_id FROM order";
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

    public List<Order> getAllOrders(int user_id) {
        String sql = "SELECT * FROM order WHERE user_id = ?";
        List<Order> orders = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            orders = new ArrayList<>();

            while (resultSet.next()) {
                Order order = new Order();
                order.setUserId(resultSet.getInt("user_id"));
                order.setPaymentId(resultSet.getInt("payment_id"));
                order.setPromoteId(resultSet.getInt("promote_id"));
                order.setTicketAmount(resultSet.getInt("ticket_amount"));
                order.setTotal(resultSet.getDouble("total"));
                order.setOrderTime(resultSet.getString("order_time"));
                order.setDescribe(resultSet.getString("discribe"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
