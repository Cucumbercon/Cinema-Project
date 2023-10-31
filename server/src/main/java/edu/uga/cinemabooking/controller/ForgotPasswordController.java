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
    int loggedID;

    /**
     * This method is to validate the login
     * 
     * @param data login email and password
     * @return user
     */
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> fetchData(@RequestBody String data) {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {

            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            String password = jsonNode.get("pass").asText();

            if (udb.emailExist(email)) {
                user = udb.loginValidation(email, password);
                int userID = user.getId();
                if (user != null) {
                    String jsonUser = objectMapper.writeValueAsString(user);
                    System.out.println("logged in");
                    System.out.println(jsonUser);
                    setID(userID);
                    return ResponseEntity.ok(jsonUser);
                } else {
                    System.out.println("logged fail");
                    return new ResponseEntity<>("Email and/or password do not match", HttpStatus.NOT_ACCEPTABLE);
                }
            } else {
                // return 406 if email or password do not matc
                System.out.println("logged fail");
                return new ResponseEntity<>("Email and/or password do not match", HttpStatus.NOT_ACCEPTABLE);

            }

            // if something wrongs going on
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }

    } // fetchData

    public void setID(int id) {
        loggedID = id;
    } // setID

    public int getID() {
        return loggedID;
    } // getID

}
