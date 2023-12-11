// package edu.uga.cinemabooking;

// import edu.uga.cinemabooking.controller.*;

// import org.springframework.web.bind.annotation.RestController;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;


// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @RestController
// @RequestMapping("/api")
// @CrossOrigin(origins = "*")
// public class Facade {

    // AddMovieController
    @PostMapping("/addMovies")
    public void fetchData(@RequestBody String data) {
        AddMovieController.fetchData(data);
    }
    // EditProfileController
    @GetMapping("/loadprofiles")
    public void getUserInfo(@RequestParam int userId) {
        EditProfileController.getUserInfo(userId);
    }

    @GetMapping("/loadprofilecard")
    public void getUserCard(@RequestParam int userId) {
        EditProfileController.getUserCard(userId);
    }

    @PostMapping("/updateprofile")
    public void updateUserInfo(@RequestBody String data) {
        EditProfileController.updateUserInfo(data);
    }
    // EmailController

    @PostMapping("/sendEmail")
    public void sendEmail(@RequestBody String data) {
        EmailController.sendEmail(data);
    }

    @PostMapping("/verifyCode")
    public void verifyCode(@RequestBody String data) {
        EmailController.verifyCode(data);
    }

    @PostMapping("/verifyForgotCode")
    public void verifyForgotCode(@RequestBody String data) {
        EmailController.verifyForgotCode(data);
    }

    @PostMapping("/sendForgotEmail")
    public void sendForgotEmail(@RequestBody String data) {
        EmailController.sendForgotEmail(data);
    }

    @PostMapping("/sendOrderConfirmation")
    public void sendOrderConfirmation(@RequestBody String data) {
        EmailController.sendOrderConfirmation(data);
    }

    // ForgotPasswordController
    @PostMapping("/forgotpassword")
    public void fetchDataForgot(@RequestBody String data) {
        ForgotPasswordController.fetchData(data);
    }

    // GetMovieController
    @GetMapping("/getAvailableMovie")
    public void receiveAvailable() {
        GetMovieController.receiveAvailable();
    }

    @GetMapping("/getUpComingMovie")
    public void receiveUpComing() {
        GetMovieController.receiveUpComing();
    }

    @GetMapping("/searchMovieByName")
    public void searchMovieByName(@RequestParam String movieName) {
        GetMovieController.searchMovieByName(movieName);
    }

    @GetMapping("/getArchiveMovie")
    public void getArchiveMovie() {
        GetMovieController.getArchiveMovie();
    }

// }
