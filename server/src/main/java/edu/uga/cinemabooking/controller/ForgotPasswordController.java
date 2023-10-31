package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.entity.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ForgotPasswordController {

    UserDB udb = new UserDB();

    /**
     * 
     */
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> fetchData(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            if (udb.emailExist(email)) {
                String password = jsonNode.get("newPassword").asText();
                String confirmPassword = jsonNode.get("confirmNewPassword").asText();
                if (password.equals(confirmPassword)) {
                    udb.changePassword(email, confirmPassword);
                } else {
                    return ResponseEntity.status(501).body("Passwords much match.");
                }
            } else {
                return ResponseEntity.status(502).body("Email does not exist.");
            }
            return ResponseEntity.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error occured.");
        } 

    }

}
