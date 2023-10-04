package edu.uga.cinemabooking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.entity.Movie;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GetMovieController {

    MovieDB mdb = new MovieDB();

    @GetMapping("/getAvailableMovie")
    public ResponseEntity<String> receiveAvailable() {
        //System.out.println("ajdgaujhgdhjagdvjhasgdajdhasgjdghjb");

        List<Movie> movies = mdb.getAvailableMovie();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMovies = objectMapper.writeValueAsString(movies);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

    @GetMapping("/getUpComingMovie")
    public ResponseEntity<String> receiveUpComing() {
        List<Movie> movies = mdb.getUpComingMovie();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMovies = objectMapper.writeValueAsString(movies);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }
    @GetMapping("/searchMovieByName")
    public ResponseEntity<String> searchMovieByName(@RequestParam String movieName) {
        System.out.println(movieName);
        List<Movie> movies = mdb.searchMovieByName(movieName); //calling searchMovie
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMovies = objectMapper.writeValueAsString(movies);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }
}
