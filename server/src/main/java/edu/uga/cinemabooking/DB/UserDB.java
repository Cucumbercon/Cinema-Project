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
            String homeState, String homeStreet, String homeZipCode, int type) {

        String sql = "INSERT INTO user (user_name, email, password_hash, phone, subscribe, " +
                "city, state, street, zipcode, type) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            preparedStatement.setInt(10, type);

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
                System.out.println("activity: " + resultSet.getInt("is_activity"));
                if (resultSet.getInt("is_activity") == 0) {
                    user = new User();
                    user.setFullName("notActivity");
                    return user;
                }

                String dbPassword = resultSet.getString("password_hash");
                if (decryption.decryptData(dbPassword).equals(decryption.decryptData(password))) {
                    user = new User();
                    user.setId(resultSet.getInt("ID"));
                    user.setFullName(resultSet.getString("user_name"));
                    user.setType(resultSet.getInt("type"));
                } else
                    return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return user;
    }

    /**
     * This method will search for all the users
     * 
     * @return the list of users
     */
    public List<User> getUsers() {

        String sql = "SELECT * FROM user";
        List<User> users = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            users = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User();
                user.setFullName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setType(resultSet.getInt("type"));
                user.setIs_activity(resultSet.getInt("is_activity"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
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
                user.setFullName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone"));
                user.setPassword(resultSet.getString("password_hash"));
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

    public void updateInfo(String name, String email, String password,
            String phone, boolean subscribe, String homeCity,
            String homeState, String homeStreet, String homeZipCode) {
        String sql = "UPDATE user SET (user_name, email, password_hash, phone, subscribe, city, state, street, zipcode) VALUES (?,?,?,?,?,?,?,?,?) WHERE email = "
                + email;
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a given email and verification code match in the database.
     *
     * @param email      The email to be checked.
     * @param verifyCode The verification code to be matched.
     * @return true if there's a match, false otherwise.
     */
    public boolean isEmailAndCodeMatched(String email, String verifyCode) {
        String sql = "SELECT * FROM user WHERE email = ? AND verify_code = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, verifyCode);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true; // Matching email and verify_code found
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this exception or propagating it further.
        }
        return false;
    }// not test

    public boolean updateVerificationCode(String email, String code) {
        String sql = "UPDATE user SET verify_code = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, email);
            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0; // returns true if at least one row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return false; // returns false if there was an error
        }
    }

    public void updateIsActivity(String email) {
        String sql = "UPDATE user SET is_activity = 1 WHERE email = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this exception or propagating it further.
        }
    }

    public String getEmail(int id) {
        String sql = "SELECT email FROM user WHERE ID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging this exception or propagating it further.
        }
        return null;

    }

    public List<User> deletUsers(int id) {

        List<User> users = new ArrayList<>();

        return users;

    }

    // public void getUserInfo(int id) {
    // String sql = "SELECT id FROM user WHERE ID = ?";
    // User user = null;

    // try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
    // {
    // preparedStatement.setInt(1, id);
    // ResultSet resultSet = preparedStatement.executeQuery();
    // if (resultSet.next()) {
    // user = new User();
    // user.setFullName(resultSet.getString("name"));
    // user.setEmail(resultSet.getString("email"));
    // user.setPhoneNumber(resultSet.getString("phone"));
    // user.setPassword(resultSet.getString("password"));
    // user.setSubscribe(resultSet.getInt("subscribe"));
    // user.setStreet(resultSet.getString("street"));
    // user.setCity(resultSet.getString("city"));
    // user.setState(resultSet.getString("state"));
    // user.setZipCode(resultSet.getString("zipcode"));
    // }

    // preparedStatement.executeUpdate();

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public void changePassword(String email, String password) {
        String sql = "UPDATE user SET password_hash = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } // try
    }

    public void updateUserEmail(String input, int id) {
        String sql = "UPDATE user SET email = ? WHERE id = ?";
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

    public void updateUserName(String input, String email) {
        String sql = "UPDATE user SET user_name = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updatePassword(String input, String email) {
        String sql = "UPDATE user SET password_hash = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updatePhone(String input, String email) {
        String sql = "UPDATE user SET phone = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateSubscribe(boolean input, String email) {
        String sql = "UPDATE user SET subscribe = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateHomeCity(String input, String email) {
        String sql = "UPDATE user SET city = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateHomeState(String input, String email) {
        String sql = "UPDATE user SET state = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateHomeStreet(String input, String email) {
        String sql = "UPDATE user SET street = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void updateHomeZipCode(String input, String email) {
        String sql = "UPDATE user SET zipcode = ? WHERE email = ?";
        // System.out.println("update verify code");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, input);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public List<String> getAllSubscribedUserEmails() {
        List<String> emails = new ArrayList<>();
        String sql = "SELECT email FROM user WHERE subscribe = 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emails;
    }

}