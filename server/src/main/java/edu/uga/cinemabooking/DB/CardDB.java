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
     * 
     * @param userID     userID
     * @param cardNumber card number
     * @param expDate    expiration date
     * @param State      state
     * @param street     street
     * @param zipcode    zipcode
     * @param city       city
     */
    public void addCard(int userID, String cardNumber, String expDate, String state,
            String street, String zipcode, String city) {

        String sql = "INSERT INTO payment (user_id, card_number, exp_date, zipcode, street, city, state) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.setString(3, expDate);
            preparedStatement.setString(4, zipcode);
            preparedStatement.setString(5, street);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.executeUpdate();
            //System.out.println("addcard");
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
                card.setPaymentID(resultSet.getInt("ID"));
                card.setCardNumber(decryption.decryptData(resultSet.getString("card_number")));
                card.setExpDate(resultSet.getString("exp_date"));
                card.setBillingZipCode(resultSet.getString("zipcode"));
                card.setBillingStreet(resultSet.getString("street"));
                card.setBillingCity(resultSet.getString("city"));
                card.setBillingState(resultSet.getString("state"));

                cards.add(card);
            }
            // Card card = new Card();
            // card.setUserID(24);
            // card.setCardNumber("1234567891234569");
            // cards.add(card);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } // try
        return cards;
    } // getLoggedInCard()

    public void updateInfo(int paymentID, String cardNumber, String expDate, String state,
                       String street, String zipcode, String city) {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    java.util.Date utilDate = null;

    try {
        utilDate = sdf.parse(expDate);
    } catch (ParseException e) {
        e.printStackTrace();
        return; 
    }

    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    String sql = "UPDATE payment SET card_number = ?, exp_date = ?, zipcode = ?, street = ?, city = ?, state = ? WHERE ID = ?";

    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setString(1, cardNumber);
        preparedStatement.setDate(2, sqlDate);
        preparedStatement.setString(3, zipcode);
        preparedStatement.setString(4, street);
        preparedStatement.setString(5, city);
        preparedStatement.setString(6, state);
        preparedStatement.setInt(7, paymentID); // 
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void addCard(int userID) {
        String sql = "INSERT INTO payment (user_id) " +
                "VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    } // addCard()

    public void checkNUpdateCard(int userID, String cardNumber, String expDate, String state,
            String street, String zipcode, String city) {
        String selectSQL = "SELECT * FROM payment WHERE user_id = ?";
        String updateSQL = "UPDATE payment SET exp_date = ?, state = ?, street = ?, zipcode = ?, city = ? WHERE user_id = ? AND card_number = ?";

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL);
                PreparedStatement updateStatement = connection.prepareStatement(updateSQL)) {
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            int result = 0;
            while (resultSet.next()) {

                if (decryption.decryptData(resultSet.getString("card_number"))
                        .equals(decryption.decryptData(cardNumber))) {
                    updateStatement.setString(1, expDate);
                    updateStatement.setString(2, state);
                    updateStatement.setString(3, street);
                    updateStatement.setString(4, zipcode);
                    updateStatement.setString(5, city);
                    updateStatement.setInt(6, userID);
                    updateStatement.setString(7, resultSet.getString("card_number"));

                    updateStatement.executeUpdate();
                    result++;
                    break;
                }
            }
            if (result == 0) {
                addCard(userID, cardNumber, expDate, state, street, zipcode, city);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateCreditCard(String input, int id) {
        String sql = "UPDATE payment SET card_number = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateExpDate(String input, int id) {
        String sql = "UPDATE payment SET exp_date = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateZipCode(String input, int id) {
        String sql = "UPDATE payment SET zipcode = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateStreet(String input, int id) {
        String sql = "UPDATE payment SET street = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateCity(String input, int id) {
        String sql = "UPDATE payment SET city = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateState(String input, int id) {
        String sql = "UPDATE payment SET state = ? WHERE user_id = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
