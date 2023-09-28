package edu.uga.cinemabooking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class TheController {

    @GetMapping("/data")
    public ResponseEntity<String> fetchData() {
        // Logic to fetch data
        String data = "Hello from Spring!";
        return ResponseEntity.ok(data);
    }

    // Add other endpoints as needed
}
