package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.entity.User;
import edu.uga.cinemabooking.entity.Card;

import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner;

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
    int id;
    SigninController signin = new SigninController();

    /**
     * 
     */
    @GetMapping("/loadprofile")
    public ResponseEntity<String> getUserInfo() {
        getID();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        List<Card> cards = null;

        try {
            user = udb.getLoggedInProfile(id);
            cards = cdb.getLoggedInCard(id);
            String jsonUserProfile = objectMapper.writeValueAsString(user);

            return ResponseEntity.ok(jsonUserProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting profile info.");
        }

    }

    /**
     * 
     */
    @GetMapping("/loadprofilecard")
    public ResponseEntity<String> getUserCard() {
        getID();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        List<Card> cards = null;

        try {
            user = udb.getLoggedInProfile(id);
            cards = cdb.getLoggedInCard(id);
            Card card = cards.get(0);
            String jsonUserProfile = objectMapper.writeValueAsString(card);
            System.out.println("ran through here");

            return ResponseEntity.ok(jsonUserProfile);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error getting profile info.");
        }

    }

    @PostMapping("/updateprofile")
    public ResponseEntity<String> updateUserInfo(@RequestBody String data) {
        getID();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String fullName = jsonNode.get("fullName").asText();
            String email = jsonNode.get("email").asText();
            String phoneNumber = jsonNode.get("phoneNumber").asText();
            boolean subscribe = jsonNode.get("subscribe").asBoolean();
            String creditCardNumber = jsonNode.get("creditCardNumber").asText();
            String expirationDate = jsonNode.get("expirationDate").asText();
            String password = jsonNode.get("password").asText();
            String confirmPassword = jsonNode.get("confirmPassword").asText();
            String street = jsonNode.get("street").asText();
            String city = jsonNode.get("city").asText();
            String state = jsonNode.get("state").asText();
            String zipCode = jsonNode.get("zipCode").asText();
            String homeStreet = jsonNode.get("homeStreet").asText();
            String homeCity = jsonNode.get("homeCity").asText();
            String homeState = jsonNode.get("homeState").asText();
            String homeZipCode = jsonNode.get("homeZipCode").asText();

            String[] nullBeGone = {fullName, email, phoneNumber, creditCardNumber, expirationDate, password, confirmPassword
            , street, city, state, zipCode, homeStreet, homeCity, homeState, homeZipCode};

            //for (String s : nullBeGone) {
            //    if (s == null) {
            //        s = "";
            //    }
            //} // for

            udb.updateInfo(fullName, email, password, phoneNumber, subscribe, homeCity, homeState, homeStreet, homeZipCode);
            cdb.updateInfo(id, creditCardNumber, expirationDate, zipCode, street, city, state);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating profile info.");
        } // try
        
    }  // updateUserInfo

    public void getID() {
        try {
            File idstore = new File("idstore.txt");
            Scanner scan = new Scanner(idstore);
            String idString;
            idString = scan.next();
            id = Integer.parseInt(idString);
            scan.close();
        } catch (FileNotFoundException e)  {
            System.out.println("Cannot read file");
        }
    }

}
