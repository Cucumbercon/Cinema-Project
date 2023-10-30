package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.entity.User;
import edu.uga.cinemabooking.entity.Card;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EditProfileController {

    UserDB udb = new UserDB();
    CardDB cdb = new CardDB();
    SigninController signin = new SigninController();

    /**
     * 
     */
    @GetMapping("/loadprofile")
    public ResponseEntity<String> getUserInfo() {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        List<Card> cards = null;

        try {
            user = udb.getLoggedInProfile(signin.getID());
            cards = cdb.getLoggedInCard(signin.getID());
            String jsonUserProfile = objectMapper.writeValueAsString(user);
            return ResponseEntity.ok(jsonUserProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting profile info.");
        }

    }

    @PostMapping("/updateprofile")
    public ResponseEntity<String> updateUserInfo(@RequestBody String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String fullName = jsonNode.get("fullName").asText();
            String phoneNumber = jsonNode.get("phoneNumber").asText();
            String subscribe = jsonNode.get("subscribe").asText();
            String creditCardNumber = jsonNode.get("creditCardNumber").asText();
            String expirationDate = jsonNode.get("expirationDate").asText();
            String password = jsonNode.get("password").asText();
            String confirmPassword = jsonNode.get("confirmPassword").asText();

            udb.updateInfo(signin.getID(), fullName, subscribe, confirmPassword, phoneNumber, false, creditCardNumber, expirationDate, password, confirmPassword);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating profile info.");
        } // try
        
    }  // updateUserInfo

}
