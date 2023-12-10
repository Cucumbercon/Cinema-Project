package edu.uga.cinemabooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.OrderDB;
import edu.uga.cinemabooking.entity.Order;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {

    OrderDB odb = new OrderDB();
    Order order = new Order();

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            System.out.println(jsonNode);
            int id = jsonNode.get("orderData").get("user_id").asInt();
            double total = jsonNode.get("orderData").get("total").asDouble();
            int ticket_amount = jsonNode.get("orderData").get("ticket_amount").asInt();

            odb.addOrder(id, 0, 0, 0, ticket_amount, total, "");

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return ResponseEntity.ok("Add order successful");

    }
}
