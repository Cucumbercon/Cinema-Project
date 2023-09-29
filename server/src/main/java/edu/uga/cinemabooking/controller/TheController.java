package edu.uga.cinemabooking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TheController {

    //*****************************retrieve message*********************************//
    @GetMapping("/data")
    public ResponseEntity<String> fetchData() {
        // Logic to fetch data
        String data = "Hello from Spring!";
        return ResponseEntity.ok(data);
    }

    //*****************************send message**************************************//
    @PostMapping("/send-message")
    public ResponseEntity<String> receiveMessage(@RequestBody String message) {
        // Process the received message here
        System.out.println("Received message from React: " + message);
        return ResponseEntity.ok("Message received successfully!");
    }


    // Add other endpoints as needed
}
