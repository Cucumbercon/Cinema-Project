package edu.uga.cinemabooking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.entity.User;
import edu.uga.cinemabooking.entity.Card;
import edu.uga.cinemabooking.Decryption;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import edu.uga.cinemabooking.DB.OrderDB;
import edu.uga.cinemabooking.DB.TicketDB;
import edu.uga.cinemabooking.entity.Order;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderHistoryController {

    OrderDB odb = new OrderDB();
    TicketDB tdb = new TicketDB();

    @GetMapping("/orderHistory")
    public ResponseEntity<String> displayOrder(@RequestParam int userId) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Order> orders = null;
        List<OrderHistory> orderHistories = null;
        try {
            orders = odb.getAllOrders(userId);
            for (int i = 0; i < orders.size(); i++) {
                OrderHistory orderHistory = new OrderHistory();
                Order order = orders.get(i);
                orderHistory.bookingNum = 1;
                orderHistory.ticketNum = 1;
                orderHistory.date = order.getOrderTime();
                orderHistory.seats = "";
                orderHistory.amountSpent = order.getTotal();
                orderHistories.add(orderHistory);
            }
            String jsonOrderHistory = objectMapper.writeValueAsString(orderHistories);
            return ResponseEntity.ok(jsonOrderHistory);

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return ResponseEntity.status(500).body("Error getting order history.");
        }

    }

    public class OrderHistory {
        String movieTitle;
        int bookingNum;
        int ticketNum;
        String date;
        String seats;
        double amountSpent;
    }
}
