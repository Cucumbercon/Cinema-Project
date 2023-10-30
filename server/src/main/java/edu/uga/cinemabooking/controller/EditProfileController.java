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
    @GetMapping("/updateprofile")
    public ResponseEntity<String> updateInfo() {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        List<Card> cards = null;

        try {
            user = udb.getLoggedInProfile(signin.getID());
            cards = cdb.getLoggedInCard(signin.getID());
            String jsonUserProfile = objectMapper.writeValueAsString(user);
            return ResponseEntity.ok(jsonUserProfile);

            // if something wrongs going on
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error converting movies to JSON");
        }

    }

}
