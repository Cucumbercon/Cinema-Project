package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.ScheduleDB;
import edu.uga.cinemabooking.DB.SeatDB;
import edu.uga.cinemabooking.DB.Showr
import edu.uga.cinemabooking.DB.ShowroomDB;
import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.Schedule;
import edu.uga.cinemabooking.entity.Seat;
import edu.uga.cinemabooking.entity.Showroom;

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

    @PostMapping("/addSeats")
    public ResponseEntity<String> addSeats(@RequestBody String data) {

        List<String> seats = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String jsonMovies = objectMapper.writeValueAsString(movies);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

}
