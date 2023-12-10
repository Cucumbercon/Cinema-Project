package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.ScheduleDB;
import edu.uga.cinemabooking.DB.ShowroomDB;
import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.Schedule;
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
            int movie_id = jsonNode.get("selectedMovie").asInt();
            System.out.println("Checkpoint 1" + movie_id);

           
            String date = jsonNode.get("date").asText();
            String start_date = date + " " + jsonNode.get("startTime").asText() + ":00";
            String end_date = date + " " + jsonNode.get("endTime").asText() + ":00";
            System.out.println("Checkpoint 2" + start_date + "\n" + end_date);


            if (sdb.checkOverlapScheduleMovie(start_date, movie_id) || sdb.checkOverlapScheduleMovie(end_date, movie_id)) {
                System.out.println("Overlaps.");
            } else {
                // Create a showroom
             // Gets the movie name and uses it to get showroomID
            //Showroom showroom = shdb.getShowroomID(movie_id);
            Random random = new Random();
            int showroom_id = random.nextInt(8) + 1;
            
            System.out.println("Checkpoint 3" + start_date + "\n" + end_date);
            sdb.addSchedule(movie_id, showroom_id, start_date, end_date);
            }
            

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Schedule movies successful");

    }

    @GetMapping("/getschedule")
    public ResponseEntity<String> getUserInfo(@RequestParam String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Schedule> schedule = null;

        try {
            int movie_id = Integer.parseInt(data);
            schedule = sdb.getScheduleMovie(movie_id);
            String jsonUserProfile = objectMapper.writeValueAsString(schedule);

            return ResponseEntity.ok(jsonUserProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting schedule info.");
        }

    }

}
