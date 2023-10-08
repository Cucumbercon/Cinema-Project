package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.entity.User;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    UserDB udb = new UserDB();

    @PostMapping("/register")
    public ResponseEntity<String> fetchData(@RequestBody String data) {
        // Logic to fetch data
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String fullName = jsonNode.get("fullName").asText();
            String email = jsonNode.get("email").asText();
            String password = jsonNode.get("password").asText();
            String phoneNumber = jsonNode.get("phoneNumber").asText();
            boolean subscribe = jsonNode.get("subscribe").asBoolean();
            boolean includeCreditCardInfo = jsonNode.get("includeCreditCardInfo").asBoolean();
            String creditCardNumber = jsonNode.get("creditCardNumber").asText();
            String expirationDate = jsonNode.get("expirationDate").asText();
            String city = jsonNode.get("city").asText();
            String state = jsonNode.get("state").asText();
            String street = jsonNode.get("street").asText();
            String zipCode = jsonNode.get("zipCode").asText();

            int id = udb.addUser(fullName, email, password, phoneNumber, subscribe);
            // System.out.println(includeCreditCardInfo);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Registration successful");
    }

}
