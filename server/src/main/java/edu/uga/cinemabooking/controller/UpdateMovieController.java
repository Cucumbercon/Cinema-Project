package edu.uga.cinemabooking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.entity.Movie;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UpdateMovieController {

    MovieDB mdb = new MovieDB();

    /**
     * This method is used to update movies
     * @return the list of movies
     */
    @PostMapping("/updateMovieInfo")
    public ResponseEntity<String> updateMovieInfo(@RequestBody String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            Movie movie = new Movie();
            movie.setId(jsonNode.get("id").asInt());
            //System.out.println(id);
            movie.setLanguage(jsonNode.get("language").asText());
            //System.out.println(language);
            movie.setTitle(jsonNode.get("title").asText());
            //System.out.println(title);
            movie.setState(jsonNode.get("state").asInt());
            //System.out.println(state);
            movie.setPopularity(jsonNode.get("popularity").asDouble());
            //System.out.println(popularity);
            movie.setPosterPath(jsonNode.get("posterPath").asText());
            //System.out.println(poster_path);
            movie.setBackdropPath(jsonNode.get("backdropPath").asText());
            //System.out.println(backdrop_path);
            movie.setDate(jsonNode.get("date").asText());
            //System.out.println(release_day);
            movie.setCategory(jsonNode.get("category").asText());
            //System.out.println(category);
            movie.setTrailerPath(jsonNode.get("trailerPath").asText());
            //System.out.println(trailer_path);
            movie.setSynopsis(jsonNode.get("synopsis").asText());
            //System.out.println(synopsis);
            movie.setCast(jsonNode.get("cast").asText());
            //System.out.println(cast);
            movie.setRating(jsonNode.get("rating").asDouble()); 
            //System.out.println(rating);
            movie.setDirector(jsonNode.get("director").asText());
            //System.out.println(director);
            movie.setProducer(jsonNode.get("producer").asText());
            //System.out.println(producer);
            movie.setDuration(jsonNode.get("duration").asInt());
            //System.out.println(duration);
            mdb.updateMovieInfo(movie);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

}
