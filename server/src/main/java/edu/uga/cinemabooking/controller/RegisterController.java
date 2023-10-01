package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.entity.User;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @PostMapping("/register")
    public ResponseEntity<String> fetchData(@RequestBody String data) {
        // Logic to fetch data
        ObjectMapper objectMapper = new ObjectMapper();

         try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String first = jsonNode.get("userData").get("first name").asText();
            String last = jsonNode.get("userData").get("last name").asText();
            String email = jsonNode.get("userData").get("email").asText();
            String password = jsonNode.get("userData").get("password").asText();
            String phoneNumber = jsonNode.get("userData").get("phoneNumber").asText();

            // String subCheckBox = jsonNode.get("userData").get("first name").asText();

            User user = new User("1", first, last, email, password, phoneNumber);

            // check DB if the user already exists
            /*
             *  if user already exists:
             *  if (isUsernameExists(userData.getUsername())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
                }

                else {
                    put the user info into db
                }
             * 
             * 
             * 
             * 
             * 
             * 
             * 
             */


        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Registration successful");
    }

}
