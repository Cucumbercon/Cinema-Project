package edu.uga.cinemabooking.controller;

import java.util.ArrayList;
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
import edu.uga.cinemabooking.DB.SeatDB;
import edu.uga.cinemabooking.DB.TicketDB;
import edu.uga.cinemabooking.entity.Order;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class OrderController {

    OrderDB odb = new OrderDB();
    SeatDB sdb = new SeatDB();
    Order order = new Order();
    TicketDB tdb = new TicketDB();

    @PostMapping("/addOrder")
    public ResponseEntity<Integer> addOrder(@RequestBody String data) {

        int orderID = 0;

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
            orderID = odb.addOrder(user_id, payment_id, promote_id, ticket_amount, total, description);
            // }

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }

        return ResponseEntity.ok(orderID);

    }

    @PostMapping("/addTicket")
    public ResponseEntity<List<Integer>> addTicket(@RequestBody String data) {

        List<Integer> seatID = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            int selectedShowRoomId = jsonNode.get("ticketData").get("selectedShowRoomId").asInt();
            int orderID = jsonNode.get("ticketData").get("orderID").asInt();
            int scheduleID = jsonNode.get("ticketData").get("scheduleId").asInt();
            JsonNode selectedSeatsNode = jsonNode.get("ticketData").get("selectedSeats");
            if (selectedSeatsNode.isArray()) {
                for (JsonNode seatNode : selectedSeatsNode) {
                    String selectedSeat = seatNode.asText();
                    System.out.println(selectedSeat);
                    seatID.add(sdb.assignSeat(selectedShowRoomId, selectedSeat));
                }
            } else {
                String selectedSeat = selectedSeatsNode.asText();
                sdb.assignSeat(selectedShowRoomId, selectedSeat);
                    seatID.add(sdb.assignSeat(selectedShowRoomId, selectedSeat));
            }

            for (int i = 0; i < seatID.size(); i++) {
                tdb.addTicket(scheduleID, seatID.get(i), orderID);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ResponseEntity.ok(seatID);

    }
}
