package edu.uga.cinemabooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.OrderDB;
import edu.uga.cinemabooking.DB.TicketDB;
import edu.uga.cinemabooking.entity.Order;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {

    OrderDB odb = new OrderDB();
    Order order = new Order();
    TicketDB tdb = new TicketDB();

    @PostMapping("/addOrder")
    public ResponseEntity<String> addOrder(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            System.out.println(jsonNode);

            int user_id = jsonNode.get("orderData").get("user_id").asInt();
            double total = jsonNode.get("orderData").get("total").asDouble();
            int ticket_amount = jsonNode.get("orderData").get("ticket_amount").asInt();
            int payment_id = jsonNode.get("orderData").get("payment_id").asInt();
            int promote_id = jsonNode.get("orderData").get("promote_id").asInt();
            String description = jsonNode.get("orderData").get("describe").asText();
            // int schedule_id = jsonNode.get("schedule_id").asInt();

            // List<Integer> ticket_ids = tdb.getTicketIDs(schedule_id);
            // for (int i = 0; i < ticket_ids.size(); i++) {
                odb.addOrder(user_id, payment_id, promote_id, ticket_amount, total, description);
            // }
            

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return ResponseEntity.ok("Add order successful");

    }
}
