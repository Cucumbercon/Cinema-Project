package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CardDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    public CardDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * This method will add the card to the userID
     * @param userID userID
     * @param cardNumber card number
     * @param expDate expiration date
     * @param State state
     * @param street street
     * @param zipcode zipcode
     * @param city city
     */
    public void addCard(int userID, String cardNumber, String expDate, String state,
            String street, String zipcode, String city) {

        // reformat the java string to sql's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String sql = "INSERT INTO payment (user_id, card_number, exp_date, zipcode, street, city, state) " +
        "VALUES (?,?,?,?,?,?,?)";        

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, zipcode);
            preparedStatement.setString(5, street);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
