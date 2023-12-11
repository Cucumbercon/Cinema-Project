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
    public ResponseEntity<String> displayOrder(@RequestParam int userID) {

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        List<Order> orders = null;
        try {
            

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return ResponseEntity.ok("Add order successful");

    }
}
