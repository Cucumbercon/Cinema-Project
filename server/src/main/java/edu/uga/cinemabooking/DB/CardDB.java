package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import edu.uga.cinemabooking.Decryption;
import edu.uga.cinemabooking.entity.User;
import edu.uga.cinemabooking.entity.Card;

import java.util.ArrayList;
import java.util.List;


public class CardDB {

    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Decryption decryption = new Decryption();
    Connection connection = null;

    /**
     * Calling this will init the connection to db
     */
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
        expDate = decryption.decryptData(expDate);

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


    } // addCard()

    /*
     * Searches user by their ID
     * To be used for edit profile
     */
    public List<Card> getLoggedInCard(int id) {
        List<Card> cards = new ArrayList<>();
        String sql = "SELECT * FROM payment WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card();
                card.setCardNumber(resultSet.getString("card_number"));
                card.setExpDate(resultSet.getString("exp_date"));
                card.setBillingZipCode(resultSet.getString("zipcode"));
                card.setBillingStreet(resultSet.getString("street"));
                card.setBillingCity(resultSet.getString("city"));
                card.setBillingState(resultSet.getString("state"));

                cards.add(card);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } // try
        return cards;
    } // getLoggedInCard()

    public void updateInfo(int userID, String cardNumber, String expDate, String state,
            String street, String zipcode, String city) {

        // reformat the java string to sql's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        expDate = decryption.decryptData(expDate);

        try {
            utilDate = sdf.parse(expDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String sql = "UPDATE payment SET (user_id, card_number, exp_date, zipcode, street, city, state) VALUES (?,?,?,?,?,?,?) WHERE user_id = " + userID;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
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
