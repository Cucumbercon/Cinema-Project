
package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SearchMovieController {
    MovieDB mdb = new MovieDB();

    @PostMapping("/setSearchTerm")
    public ResponseEntity<String> getSearchData(@RequestBody String search) {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] resultsFromSearch = null;
        try {
            JsonNode jsonNode = objectMapper.readTree(search);

            String searchTermTitle = jsonNode.get("text").asText();
            resultsFromSearch = mdb.searchMovie(searchTermTitle);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("No data found", HttpStatus.BAD_REQUEST);
        } // try
        return ResponseEntity.ok(resultsFromSearch.toString());
        
    }
}
