package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.ScheduleDB;
import edu.uga.cinemabooking.DB.ShowroomDB;
import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.Showroom;

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
    MovieDB mdb = new MovieDB();
    
    @PostMapping("/scheduleMovie")
    public ResponseEntity<String> fetchData(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            System.out.println(data);
            JsonNode jsonNode = objectMapper.readTree(data);

            // Gets the movie name and uses it to get movieID
            String movie_name = jsonNode.get("selectedMovie").asText();
            Movie movie = mdb.getMovieID(movie_name);
            int movie_id = movie.getId();
            // Create a showroom
            shdb.addShowroom(63, jsonNode.get("startTime").asText(), jsonNode.get("selectedMovie").asText());

            // Gets the movie name and uses it to get showroomID
            Showroom showroom = shdb.getShowroomID(movie_name);
            int showroom_id = showroom.getId();

            String start_date = jsonNode.get("startTime").asText();
            String end_date = jsonNode.get("endTime").asText();

            sdb.addSchedule(movie_id, showroom_id, start_date, end_date);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Schedule movies successful");

    }
}
