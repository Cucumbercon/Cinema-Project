package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.entity.User;
import edu.uga.cinemabooking.entity.Card;
import edu.uga.cinemabooking.Decryption;

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
            if (cards.size() != 0) {
            } else {
            cdb.addCard(id);
            cdb.addCard(id);
            cdb.addCard(id);
            cards = cdb.getLoggedInCard(id);
            }

            String jsonUserProfile = objectMapper.writeValueAsString(cards);
            System.out.println(jsonUserProfile);

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
            User user = udb.getLoggedInProfile(id);
            Card card = objectMapper.readValue(data, Card.class);
            // System.out.println(card.toString());
        
            // System.out.println(" \n\n\n"+data);
            cdb.checkNUpdateCard(card.getUserID(), card.getCardNumber(), card.getExpDate(), card.getBillingState(),
                                 card.getBillingStreet(), card.getBillingZipCode(), card.getBillingCity());

            System.out.println(card.toString());


            String fullName = "";
            String email = "";
            String phoneNumber = "";
            String password = "";
            String confirmPassword = "";
            String homeStreet = "";
            String homeCity = "";
            String homeState = "";
            String homeZipCode = "";
            String currentPassword = "";
            JsonNode jsonNode = objectMapper.readTree(data);
            if (jsonNode.get("fullName").asText() != null) {
            fullName = jsonNode.get("fullName").asText();
            if (fullName == null) {
            fullName = user.getFullName();
            }
            }

            if (jsonNode.get("email").asText() != null) {
            email = jsonNode.get("email").asText();
            if (email == null) {
            email = user.getEmail();
            }
            }

            if (jsonNode.get("phoneNumber").asText() != null) {
            phoneNumber = jsonNode.get("phoneNumber").asText();
            if (phoneNumber == null) {
            phoneNumber = user.getPhoneNumber();
            }
            }

            if (jsonNode.get("pass").asText() != null) {
            password = jsonNode.get("pass").asText();
            if (password == null) {
            password = "";
            }
            }

            if (jsonNode.get("passConfirm").asText() != null) {
            confirmPassword = jsonNode.get("passConfirm").asText();
            if (confirmPassword == null) {
            confirmPassword = " ";
            }
            }

            if (jsonNode.get("homeStreet").asText() != null) {
            homeStreet = jsonNode.get("homeStreet").asText();
            if (homeStreet == null) {
            homeStreet = user.getStreet();
            }
            }

            if (jsonNode.get("homeCity").asText() != null) {
            homeCity = jsonNode.get("homeCity").asText();
            if (homeCity == null) {
            homeCity = user.getCity();
            }
            }

            if (jsonNode.get("homeState").asText() != null) {
            homeState = jsonNode.get("homeState").asText();
            if (homeState == null) {
            homeState = user.getState();
            }
            }

            if (jsonNode.get("homeZipCode").asText() != null) {
            homeZipCode = jsonNode.get("homeZipCode").asText();
            if (homeZipCode == null) {
            homeZipCode = user.getZipCode();
            }
            }

            if (jsonNode.get("passCurrent").asText() != null) {
            currentPassword = jsonNode.get("passCurrent").asText();
            if (currentPassword == null) {
            currentPassword = "";
            }
            }

            Decryption decrypt = new Decryption();
            if
            (decrypt.decryptData(currentPassword).equals(decrypt.decryptData(user.getPassword())))
            {
            if (password.equals(confirmPassword)) {

            }
            } else {
            password = user.getPassword();
            }
            boolean subscribe = jsonNode.get("subscribe").asBoolean();
            // System.out.println("Checkpoint 2");
            // System.out.println("Current password: " + currentPassword);
            // System.out.println("Password: " + password);
            // System.out.println("Confirm Password: " + confirmPassword);
            // System.out.println(creditCardNumber);
            // System.out.println("User.password: " + user.getPassword());
            udb.updateUserName(fullName, email);
            udb.updatePassword(password, email);
            udb.updatePhone(phoneNumber, email);
            udb.updateSubscribe(subscribe, email);
            udb.updateHomeCity(homeCity, email);
            udb.updateHomeState(homeState, email);
            udb.updateHomeStreet(homeStreet, email);
            udb.updateHomeZipCode(homeZipCode, email);
            // System.out.println("Checkpoint 3");
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating profile info.");
        } // try

    } // updateUserInfo

    public void getID() {
        try {
            File idstore = new File("idstore.txt");
            Scanner scan = new Scanner(idstore);
            String idString;
            idString = scan.next();
            id = Integer.parseInt(idString);
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read file");
        }
    }

}
