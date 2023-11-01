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
            Card card;
            if (cards.size() != 0) {
                card = cards.get(0);
            } else {
                card = new Card();
            }
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
            User user = udb.getLoggedInProfile(id);
            List<Card> cards = cdb.getLoggedInCard(id);
            Card card;
            if (cards.size() != 0) {
                card = cards.get(0);
            } else {
                cdb.addCard(id);
                cards = cdb.getLoggedInCard(id);
                card = cards.get(0);
            }
            System.out.println("Checkpoint 1");
            String fullName = "";
            String email = "";
            String phoneNumber = "";
            String creditCardNumber = "";
            String expirationDate = "";
            String password = "";
            String confirmPassword = "";
            String street = "";
            String city = "";
            String state = "";
            String zipCode = "";
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

            if (jsonNode.get("creditCardNumber").asText() != null) {
                creditCardNumber = jsonNode.get("creditCardNumber").asText();
                if (creditCardNumber == null) {
                    creditCardNumber = card.getCardNumber();
                }
            }

            if (jsonNode.get("expirationDate").asText() != null) {
                expirationDate = jsonNode.get("expirationDate").asText();
                if (expirationDate == null) {
                    expirationDate = "1900-01-01";
                } else if (expirationDate.equals("")) {
                    expirationDate = "1900-01-01";
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

            if (jsonNode.get("street").asText() != null) {
                street = jsonNode.get("street").asText();
                if (street == null) {
                    street = card.getBillingStreet();
                }
            }

            if (jsonNode.get("city").asText() != null) {
                city = jsonNode.get("city").asText();
                if (city == null) {
                    city = card.getBillingCity();
                }
            }

            if (jsonNode.get("state").asText() != null) {
                state = jsonNode.get("state").asText();
                if (state == null) {
                    state = card.getBillingState();
                }
            }

            if (jsonNode.get("zipCode").asText() != null) {
                zipCode = jsonNode.get("zipCode").asText();
                if (zipCode == null) {
                    zipCode = card.getBillingZipCode();
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

            if (currentPassword.equals(user.getPassword())) {
                if (password.equals(confirmPassword)) {

                }
            } else {
                password = user.getPassword();
            }
            boolean subscribe = jsonNode.get("subscribe").asBoolean();
            System.out.println("Checkpoint 2");
            udb.updateInfo(fullName, email, password, phoneNumber, subscribe, homeCity, homeState, homeStreet, homeZipCode);
            cdb.updateInfo(id, creditCardNumber, expirationDate, zipCode, street, city, state);
            System.out.println("Checkpoint 3");
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
