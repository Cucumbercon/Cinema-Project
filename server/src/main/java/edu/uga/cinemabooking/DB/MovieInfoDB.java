package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.List;
import edu.uga.cinemabooking.entity.MovieInfo;

public class MovieInfoDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;

    public MovieInfoDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public MovieInfo getMovieScheduleInfoByOrderId(int orderId) {
        String sql = "SELECT m.title, s.start_time FROM Ticket t JOIN Schedule s ON t.schedule_id = s.id JOIN Movie m ON s.movie_id = m.id WHERE t.order_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String movieTitle = resultSet.getString("title");
                    Timestamp startTime = resultSet.getTimestamp("start_time");
                    String formattedStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
                    return new MovieInfo(movieTitle, formattedStartTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 错误处理
        }
        return null; // 或者返回一个指示错误的对象
    }
}
