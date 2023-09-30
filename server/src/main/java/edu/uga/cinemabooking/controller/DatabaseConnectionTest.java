package edu.uga.cinemabooking.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
        String username = "root";
        String password = "uga4050group1";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Database connection successful!");
            // 关闭连接
            connection.close();
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
