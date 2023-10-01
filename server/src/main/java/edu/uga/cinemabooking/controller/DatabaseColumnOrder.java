package edu.uga.cinemabooking.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseColumnOrder {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
        String username = "root";
        String password = "uga4050uga4050_1";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String query = "SELECT * FROM test";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String item = resultSet.getString("item");
                System.out.println("ID: " + id + ", Item: " + item);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("Database connection or query failed: " + e.getMessage());
        }

        // try {
        //     Class.forName("com.mysql.jdbc.Driver");
        //     Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            
        //     // 获取数据库元数据
        //     DatabaseMetaData metaData = connection.getMetaData();
            
        //     // 指定要查询的表名
        //     String tableName = "test";
            
        //     // 获取表的列信息
        //     ResultSet columns = metaData.getColumns(null, null, tableName, null);
            
        //     while (columns.next()) {
        //         String columnName = columns.getString("COLUMN_NAME");
        //         int ordinalPosition = columns.getInt("ORDINAL_POSITION");
        //         System.out.println("Column Name: " + columnName + ", Ordinal Position: " + ordinalPosition);
        //     }
            
        //     // 关闭连接
        //     connection.close();
        // } catch (Exception e) {
        //     System.err.println("Database connection or query failed: " + e.getMessage());
        // }




    //     try {
    //         Class.forName("com.mysql.jdbc.Driver");
    //         Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

    //         // 指定要插入数据的表名
    //         String tableName = "test"; // 替换为实际表名

    //         // 插入数据的 SQL 查询
    //         String insertSql = "INSERT INTO " + tableName + " (ID, item) VALUES (?,?)";

    //         // 创建一个 PreparedStatement 对象
    //         PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

    //         // 设置要插入的值
    //         preparedStatement.setString(1, "2"); // 替换为实际值
    //         preparedStatement.setString(2, "item1"); // 替换为实际值

    //         // 执行插入操作
    //         int rowsAffected = preparedStatement.executeUpdate();
    //         System.out.println("Rows affected: " + rowsAffected);

    //         // 关闭连接
    //         preparedStatement.close();
    //         connection.close();
    //     } catch (ClassNotFoundException | SQLException e) {
    //         System.err.println("Database connection or query failed: " + e.getMessage());
    //     }



    }
}
