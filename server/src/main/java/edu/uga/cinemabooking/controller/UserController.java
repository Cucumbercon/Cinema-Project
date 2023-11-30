package edu.uga.cinemabooking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.entity.Movie;
import edu.uga.cinemabooking.entity.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {
    
    UserDB udb = new UserDB();
    
    /**
     * This method is used to get the all the users
     * @return the list of movies
     */
    @GetMapping("/getUsers")
    public ResponseEntity<String> getUsers() {

        List<User> user = udb.getUsers();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMovies = objectMapper.writeValueAsString(user);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

    /**
     * This method is used to delete the user
     * @return the list of movies
     */
    @GetMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {

        List<User> user = udb.deleteUser();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonMovies = objectMapper.writeValueAsString(user);
            return ResponseEntity.ok(jsonMovies);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }
    }

}
