package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.DB.UserDB;

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
    CardDB cdb = new CardDB();

    /**
     * This method is used for signup function
     * 
     * @param data the data from frontend
     * @return status code
     */
    @PostMapping("/register")
    public ResponseEntity<String> fetchData(@RequestBody String data) {
        // Logic to fetch data
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();

            // if email is valid
            if (!udb.emailExist(email)) {

                String fullName = jsonNode.get("fullName").asText();
                String password = jsonNode.get("password").asText();
                String phoneNumber = jsonNode.get("phoneNumber").asText();
                String homeCity = jsonNode.get("homeCity").asText();
                String homeState = jsonNode.get("homeState").asText();
                String homeStreet = jsonNode.get("homeStreet").asText();
                String homeZipCode = jsonNode.get("homeZipCode").asText();
                boolean subscribe = jsonNode.get("subscribe").asBoolean();
                boolean includeCreditCardInfo = jsonNode.get("includeCreditCardInfo").asBoolean();

                int id = udb.addUser(fullName, email, password, phoneNumber, subscribe,
                        homeCity, homeState, homeStreet, homeZipCode);

                if (includeCreditCardInfo) {
                    String creditCardNumber = jsonNode.get("creditCardNumber").asText();
                    String expDate = jsonNode.get("formatDate").asText();
                    String city = jsonNode.get("city").asText();
                    String state = jsonNode.get("state").asText();
                    String street = jsonNode.get("street").asText();
                    String zipCode = jsonNode.get("zipCode").asText();
                    cdb.addCard(id, creditCardNumber, expDate, state, street, zipCode, city);
                }

            } else {
                // if email already exists
                System.out.println("Register fail: Email already exists");
                return new ResponseEntity<>("Email already exists", HttpStatus.NOT_ACCEPTABLE);
            }

            // System.out.println(includeCreditCardInfo);

            // if something wrongs going on
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Register fail: Something wrong");
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }
        // if register successfully
        return ResponseEntity.ok("Registration successful");
    }

}
