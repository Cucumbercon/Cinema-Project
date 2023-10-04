package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;

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
public class AddMovieController {

    MovieDB mdb = new MovieDB();

    @PostMapping("/addMovie")
    public ResponseEntity<String> fetchData(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            System.out.println(data);

            JsonNode jsonNode = objectMapper.readTree(data);
            String title = jsonNode.get("title").asText();
            String language = jsonNode.get("language").asText();
            double popularity = jsonNode.get("popularity").asDouble();
            String posterPath = jsonNode.get("poster_path").asText();
            String backdropPath = jsonNode.get("backdrop_path").asText();
            String releaseDay = jsonNode.get("release_day").asText();
            int state = jsonNode.get("state").asInt();
            String category = jsonNode.get("category").asText();
            String trailerPath = jsonNode.get("trailer_path").asText();
            String synopsis = jsonNode.get("synopsis").asText();
            String cast = jsonNode.get("cast").asText();
            String director = jsonNode.get("director").asText();
            String producer = jsonNode.get("producer").asText();
            double rating = jsonNode.get("rating").asDouble();

            mdb.addMovie(language, title, popularity, posterPath, backdropPath, releaseDay, state, category, trailerPath, synopsis, cast, rating, director, producer);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Registration successful");

    }
}
