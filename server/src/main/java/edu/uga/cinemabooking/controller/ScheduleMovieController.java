package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.ScheduleDB;
import edu.uga.cinemabooking.DB.ShowroomDB;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ScheduleMovieController {

    ScheduleDB sdb = new ScheduleDB();
    ShowroomDB shdb = new ShowroomDB();
    
    @PostMapping("/scheduleMovie")
    public ResponseEntity<String> fetchData(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            System.out.println(data);
            JsonNode jsonNode = objectMapper.readTree(data);
            shdb.addShowroom(63, jsonNode.get("startTime").asText(), jsonNode.get("selectedMovie").asText());
            int movie_id = jsonNode.get("selectedMovie").asInt();
            int showroom_id = 1;
            String start_date = jsonNode.get("startTime").asText();
            String end_date = jsonNode.get("endTime").asText();

            sdb.addSchedule(movie_id, showroom_id, start_date, end_date);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Added movies successful");

    }
}
