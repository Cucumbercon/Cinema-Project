package edu.uga.cinemabooking.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import edu.uga.cinemabooking.DB.MovieDB;

public class DatabaseConnection {

    public static void main(String[] args) {

        Date d=new Date();  
        int id = 2;
        int adult = 0;
        String lang = "English";
        String title = "Saw X";
        double popularity = 10;
        String posterPath = "https://www.google.com.hk/url?sa=i&url=https%3A%2F%2Fwww.fandango.com%2Fsaw-x-2023-232577%2Fmovie-overview&psig=AOvVaw0KmBcolV_CNFpPoIGxDnYm&ust=1696345778113000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCJiMnc7S14EDFQAAAAAdAAAAABAE";
        String backdropPath = "https://pics.filmaffinity.com/saw_10-770317273-large.jpg";
        String releaseDay = "2023";
        int state = 1;
        String category = "Horror";
        String trailerPath = "https://youtu.be/t3PzUo4P21c";
        String synopsis = "Saw X is a 2023 American horror film and the tenth installment in the Saw film series, and serves as both a direct sequel to Saw (2004) and prequel to Saw II (2005).";
        String cast = "John";
        double rating = 9;

        MovieDB mdb = new MovieDB();

        // mdb.addMovie(id, adult, lang, title, popularity, posterPath, backdropPath, releaseDay, state, category,
                    // trailerPath, synopsis, cast, rating);
        mdb.deleteMovie(id);
    }

    // final static String URL =
    // "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    // final static String USERNAME = "root";
    // final static String PASSWORD = "uga4050uga4050_1";

    // public DatabaseConnection() {

    // try {
    // Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    // String query = "SELECT * FROM test";
    // PreparedStatement preparedStatement = connection.prepareStatement(query);
    // ResultSet resultSet = preparedStatement.executeQuery();

    // while (resultSet.next()) {
    // int id = resultSet.getInt("ID");
    // String item = resultSet.getString("item");
    // System.out.println("ID: " + id + ", Item: " + item);
    // }

    // resultSet.close();
    // preparedStatement.close();
    // connection.close();
    // } catch (SQLException e) {
    // System.err.println("Database connection or query failed: " + e.getMessage());
    // }

    // try {
    // Class.forName("com.mysql.jdbc.Driver");
    // Connection connection = DriverManager.getConnection(jdbcUrl, username,
    // password);

    // // 获取数据库元数据
    // DatabaseMetaData metaData = connection.getMetaData();

    // // 指定要查询的表名
    // String tableName = "test";

    // // 获取表的列信息
    // ResultSet columns = metaData.getColumns(null, null, tableName, null);

    // while (columns.next()) {
    // String columnName = columns.getString("COLUMN_NAME");
    // int ordinalPosition = columns.getInt("ORDINAL_POSITION");
    // System.out.println("Column Name: " + columnName + ", Ordinal Position: " +
    // ordinalPosition);
    // }

    // // 关闭连接
    // connection.close();
    // } catch (Exception e) {
    // System.err.println("Database connection or query failed: " + e.getMessage());
    // }

    // try {
    // Class.forName("com.mysql.jdbc.Driver");
    // Connection connection = DriverManager.getConnection(jdbcUrl, username,
    // password);

    // // 指定要插入数据的表名
    // String tableName = "test"; // 替换为实际表名

    // // 插入数据的 SQL 查询
    // String insertSql = "INSERT INTO " + tableName + " (ID, item) VALUES (?,?)";

    // // 创建一个 PreparedStatement 对象
    // PreparedStatement preparedStatement = connection.prepareStatement(insertSql);

    // // 设置要插入的值
    // preparedStatement.setString(1, "2"); // 替换为实际值
    // preparedStatement.setString(2, "item1"); // 替换为实际值

    // // 执行插入操作
    // int rowsAffected = preparedStatement.executeUpdate();
    // System.out.println("Rows affected: " + rowsAffected);

    // // 关闭连接
    // preparedStatement.close();
    // connection.close();
    // } catch (ClassNotFoundException | SQLException e) {
    // System.err.println("Database connection or query failed: " + e.getMessage());
    // }

    // }
}
