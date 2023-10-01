package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.entity.User;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
            String first = jsonNode.get("first name").asText();
            String last = jsonNode.get("last name").asText();
            String email = jsonNode.get("email").asText();
            String password = jsonNode.get("password").asText();
            String phoneNumber = jsonNode.get("phoneNumber").asText();
            String subCheckBox = jsonNode.get("first name").asText();

            User user = new User("1", first, last, email, password, phoneNumber);
            System.out.println(user.toString());

            // if (existingUser != null) {
            //     return new ResponseEntity<>("Username already exists", HttpStatus.BAD_REQUEST);
            // } else {
            //     // 在这里执行用户注册逻辑
            //     // ...

            //     // 返回成功的响应
            //     return new ResponseEntity<>("Registration successful", HttpStatus.OK);
            // }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);
        }





        return ResponseEntity.ok(data);
    }

}
