package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cinemabooking.Decryption;
import edu.uga.cinemabooking.entity.User;

public class UserDB {

    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;
    Decryption decryption = new Decryption();

    /**
     * Calling this will init the db connection
     */
    public UserDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * This method will add the user to db
     * 
     * @param name        full name
     * @param email       email
     * @param password    password
     * @param phone       phone number
     * @param subscribe   subscribe to promotion
     * @param homeCity    home address city
     * @param homeState   home address state
     * @param homeStreet  home address street
     * @param homeZipCode home address zip code
     * @return the incresment userID
     */
    public int addUser(String name, String email, String password,
            String phone, boolean subscribe, String homeCity,
            String homeState, String homeStreet, String homeZipCode) {

        String sql = "INSERT INTO user (user_name, email, password_hash, phone, subscribe, " +
                "city, state, street, zipcode) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setBoolean(5, subscribe);
            preparedStatement.setString(6, homeCity);
            preparedStatement.setString(7, homeState);
            preparedStatement.setString(8, homeStreet);
            preparedStatement.setString(9, homeZipCode);

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                return (generatedId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    } // addUser

    /**
     * 
     * @param email    email
     * @param password password
     * @return 0 if email is not in db
     *         1 successfully
     *         -1 email and password do not match
     */
    public User loginValidation(String email, String password) {

        String sql = "SELECT * FROM user WHERE email = ?";
        User user = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String dbPassword = resultSet.getString("password_hash");
                if (decryption.decryptData(dbPassword).equals(decryption.decryptData(password))) {
                    user = new User();
                    user.setId(resultSet.getInt("ID"));
                    user.setFullName(resultSet.getString("user_name"));
                    user.setType(resultSet.getInt("type"));
                } else return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return user;
    }

    /**
     * This method will search for all the customers
     * 
     * @return the list of customers
     */
    public List<User> getCustomers() {

        String sql = "SELECT * FROM user WHERE type = ?";
        List<User> users = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 0);
            ResultSet resultSet = preparedStatement.executeQuery();

            users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setFullName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * This method will search for all the admins
     * 
     * @return the list of admins
     */
    public List<User> getAdmins() {

        String sql = "SELECT * FROM user WHERE type = ?";
        List<User> users = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();

            users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setFullName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * This method is used to check if the email is valid
     * 
     * @return true if email already exists
     */
    public boolean emailExist(String email) {

        String sql = "SELECT * FROM user WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
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
    /*
     * Searches user by their ID
     * To be used for edit profile
     */
    public User getLoggedInProfile(int id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setFullName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password"));
                user.setSubscribe(resultSet.getInt("subscribe"));
                user.setStreet(resultSet.getString("street"));
                user.setCity(resultSet.getString("city"));
                user.setState(resultSet.getString("state"));
                user.setZipCode(resultSet.getString("zipcode"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } // try
        return user;
    } // getLoggedInProfile()
}
