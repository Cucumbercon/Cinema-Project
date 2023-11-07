package edu.uga.cinemabooking.DB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import edu.uga.cinemabooking.entity.Promotion;

public class PromoDB {

    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    public PromoDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPromotion(String promotion_code, String description, double discount_amount, Date startDate, Date endDate) {
        String sql = "INSERT INTO promotion (promotion_code, description, discount_amount, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, promotion_code);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, discount_amount);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            preparedStatement.setInt(6, 0); // Assuming status 0 as default for new entries
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Promotion> getAllPromotions() {
        // update the vilid date
        updatePromotionStatus(new Date(System.currentTimeMillis()));
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT * FROM promotion";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Promotion promotion = new Promotion();
                promotion.setId(resultSet.getInt("id"));
                promotion.setPromotionCode(resultSet.getString("promotion_code"));
                promotion.setDescription(resultSet.getString("description"));
                promotion.setDiscountAmount(resultSet.getDouble("discount_amount"));
                promotion.setStartDate(resultSet.getDate("start_date"));
                promotion.setEndDate(resultSet.getDate("end_date"));
                promotion.setStatus(resultSet.getInt("status"));
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }

    public void updatePromotion(int id, String promotion_code, String description, double discount_amount, Date startDate, Date endDate, int status) {
        String sql = "UPDATE promotion SET promotion_code = ?, description = ?, discount_amount = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, promotion_code);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, discount_amount);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            preparedStatement.setInt(6, status);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePromotion(int id) {
        String sql = "DELETE FROM promotion WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePromotionStatus(Date inputDate) {
        String procedureCall = "{call UpdatePromotionStatus(?)}";

        try (CallableStatement callableStatement = connection.prepareCall(procedureCall)) {
            callableStatement.setDate(1, inputDate);
            callableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
