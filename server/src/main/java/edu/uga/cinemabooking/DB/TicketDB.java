package edu.uga.cinemabooking.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.uga.cinemabooking.entity.Ticket;

public class TicketDB {
    final static String URL = "jdbc:mysql://sg-cdb-kpa6dm3n.sql.tencentcdb.com:63965/ebooking";
    final static String USERNAME = "root";
    final static String PASSWORD = "uga4050uga4050_1";
    Connection connection = null;
    

    /**
     * Calling this will init the connection to db
     */
    public TicketDB() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            // TODO: handle exceptio
        }
    }

    public int addTicket(int scheduleID, int seatID, int orderID) {

        String sql = "INSERT INTO ticket (schedule_id, seat_id, order_id) " +
                "VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, scheduleID);
            preparedStatement.setInt(2, seatID);
            preparedStatement.setInt(3, orderID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findMaxId();
    }

    public int findMaxId() {
        String sql = "SELECT MAX(ID) as max_id FROM ticket";
        int maxId = -1; // default value if no rows are found
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
    
            if (resultSet.next()) {
                if (Integer.valueOf(resultSet.getInt("max_id")) == null) {
                    maxId = 1;
                } else {
                    maxId = resultSet.getInt("max_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return maxId;
    }

    public List<Ticket> getTickets() {
        String sql = "SELECT * FROM ticket";
        List<Ticket> tickets = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            tickets = new ArrayList<>();

            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setScheduleId(resultSet.getInt("schedule_id"));
                ticket.setState(edu.uga.cinemabooking.entity.Ticket.State.occupied);
                ticket.setSeatId(resultSet.getInt("seat_id"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Integer> getTicketIDs(int schedule_id) {
        String sql = "SELECT * FROM ticket WHERE schedule_id = ?";
        List<Integer> ticketIDs = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, schedule_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ticketIDs = new ArrayList<>();
            while (resultSet.next()) {
                int ticketID;
                ticketID = resultSet.getInt("ID");
                ticketIDs.add(ticketID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketIDs;
    }

    public void addSeatID(int ticket_id, int seat_id) {

        String sql = "INSERT INTO ticket (seat_id) WHERE ID = " + ticket_id +
                " VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, seat_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
