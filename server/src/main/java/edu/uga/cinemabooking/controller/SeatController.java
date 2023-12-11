package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.ScheduleDB;
import edu.uga.cinemabooking.DB.SeatDB;
import edu.uga.cinemabooking.DB.ShowroomDB;
import edu.uga.cinemabooking.DB.TicketDB;
import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.Schedule;
import edu.uga.cinemabooking.entity.Seat;
import edu.uga.cinemabooking.entity.Showroom;
import edu.uga.cinemabooking.entity.Ticket;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SeatController {

    SeatDB seatdb = new SeatDB();
    ShowroomDB shdb = new ShowroomDB();
    MovieDB mdb = new MovieDB();
    TicketDB tdb = new TicketDB();

    @PostMapping("/addSeats")
    public ResponseEntity<String> addSeats(@RequestBody String data) {

        List<String> seats = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);

            seats = objectMapper.convertValue(jsonNode.get("seat"), List.class);
            int showroom_id = jsonNode.get("showroom_id").asInt();
            int schedule_id = jsonNode.get("schedule_id").asInt();
            int numCounter = seats.size();

            List<String> rowAlphabet = null;
            List<Integer> columns = null;
            List<Integer> ticket_ids = null;
            for (String seat : seats) {
                String alphabet = seat.replaceAll("[^A-Za-z]", "");
                int num = Integer.parseInt(seat.replaceAll("[^0-9]", ""));
                rowAlphabet.add(alphabet);
                columns.add(num);
                ticket_ids.add(tdb.addTicket(schedule_id, edu.uga.cinemabooking.entity.Ticket.State.occupied));
            }
            for (int i = 0; i < numCounter; i++) {
                int seat_id = seatdb.addSeat(ticket_ids.get(i), showroom_id, columns.get(i), rowAlphabet.get(i));
                tdb.addSeatID(ticket_ids.get(i), seat_id);
            }
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

}
