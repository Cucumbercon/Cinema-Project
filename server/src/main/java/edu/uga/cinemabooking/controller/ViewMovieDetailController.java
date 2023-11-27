package edu.uga.cinemabooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.entity.Movie;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ViewMovieDetailController {
    MovieDB mdb = new MovieDB();

    /**
     * This method is used to get the available movies
     * 
     * @return the list of movies
     */
    @GetMapping("/ViewMovieDetail")
    public ResponseEntity<String> viewMovieDetail(int id) {
        Movie movie = new Movie();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            movie = mdb.getMovieDetail(id);
            String jsonMovie = objectMapper.writeValueAsString(movie);
            return ResponseEntity.ok(jsonMovie);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Get movie details failed");
        }
    }

}
