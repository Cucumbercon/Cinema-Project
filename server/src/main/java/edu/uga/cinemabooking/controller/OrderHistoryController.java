package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.MovieInfoDB;
import edu.uga.cinemabooking.DB.OrderDB;
import edu.uga.cinemabooking.entity.MovieInfo;
import edu.uga.cinemabooking.entity.Order;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderHistoryController {

    OrderDB odb = new OrderDB();
    MovieInfoDB mdb = new MovieInfoDB();
    @GetMapping("/orderHistory")
    public ResponseEntity<String> displayOrder(@RequestParam int userId) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Order> orders = odb.getAllOrders(userId);
            String jsonOrders = objectMapper.writeValueAsString(orders);
            System.out.println(jsonOrders);
            return ResponseEntity.ok(jsonOrders);
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return ResponseEntity.status(500).body("Error getting order history.");
        }
    }
    @GetMapping("/getMovieInfoForOrder")
    public ResponseEntity<String> getMovieInfoForOrder(@RequestParam int orderId) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MovieInfo movieInfo = mdb.getMovieScheduleInfoByOrderId(orderId);
            if (movieInfo != null) {
                String jsonMovieInfo = objectMapper.writeValueAsString(movieInfo);
                return ResponseEntity.ok(jsonMovieInfo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return ResponseEntity.status(500).body("Error getting movie info for order.");
        }
    }
}
