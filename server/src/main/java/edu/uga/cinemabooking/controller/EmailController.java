package edu.uga.cinemabooking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmailController {
    MovieDB mdb = new MovieDB();
    private final JavaMailSender emailSender;

    public EmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    private static final String SMTP_SERVER = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SENDER_EMAIL = "sukiqwq@gmail.com";
    private static final String SENDER_PASSWORD = "oqjkkvzusbrgrlsp";

    /**
     * This method is used to get the available movies
     * 
     * @return the list of movies
     */
    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody String data) {
        UserDB udb = new UserDB();
        System.out.println("Received a request to send an email.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            int emailType = jsonNode.get("type").asInt();

            switch (emailType) {
                case 1:
                    // String verifyCode = generateVerificationCode();
                    // Store verifyCode in the database associated with the email
                    // sendEmailMessage(email, "Verification Code", "Your verification code is: " +
                    // verifyCode);
                    // System.out.println("success.\n");
                    if (udb.emailExist(email)) {
                        String verifyCode = generateVerificationCode();
                        // Store verifyCode in the database associated with the email
                        udb.updateVerificationCode(email, verifyCode);
                        sendEmailMessage(email, "Verification Code", "Your verification code is: " + verifyCode);
                    } else {
                        return ResponseEntity.status(400).body("Email does not exist in the database.");
                    }
                    break;
                case 2:
                    sendEmailMessage(email, "Signup Successful", "Congratulations on signing up!");
                    break;
                case 3:
                    sendEmailMessage(email, "Profile Updated", "Your profile has been successfully updated.");
                    break;
                case 4:
                    // promoCodeinfo
                    String promoCodeInfo = jsonNode.has("promoCodeInfo") ? jsonNode.get("promoCodeInfo").asText()
                            : "NONE";

                    List<String> subscribedEmails = udb.getAllSubscribedUserEmails(); // neeed to implement
                    System.out.println(subscribedEmails);
                    for (String subscribedEmail : subscribedEmails) {
                        sendEmailMessage(subscribedEmail, "Promotion",
                                "Here is your Promotion information:" + promoCodeInfo);
                    }
                    break;

            }

            return ResponseEntity.ok("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error from send email.");
        }
    }

    private void sendEmailMessage(String receiverEmail, String subject, String content) {
        final String senderEmail = "sukiqwq@gmail.com";
        final String senderPassword = "oqjkkvzusbrgrlsp";

        // Set mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(properties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set the sender and receiver
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));

            // Set subject and content
            message.setSubject(subject);
            message.setText(content);

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // This ensures the code is always 6 digits
        return String.valueOf(code);
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<String> verifyCode(@RequestBody String data) {
        UserDB udb = new UserDB();
        System.out.println("Received a request to verifyCode.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            String Code = jsonNode.get("code").asText();
            System.out.println(email);
            System.out.println(Code);
            if (udb.isEmailAndCodeMatched(email, Code)) {
                udb.updateIsActivity(email); // set is_activity = 1
                udb.updateVerificationCode(email, "");
                return ResponseEntity.ok("Verification success!");

            } else
                return ResponseEntity.status(500).body("Verification fail.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error from Verification.");
        }
    }

    @PostMapping("/verifyForgotCode")
    public ResponseEntity<String> verifyForgotCode(@RequestBody String data) {
        UserDB udb = new UserDB();
        System.out.println("Received a request to verifyForgotCode.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            String Code = jsonNode.get("verificationCode").asText();
            String password = jsonNode.get("pass").asText();
            System.out.println(email);
            System.out.println(Code);
            if (udb.isEmailAndCodeMatched(email, Code)) {
                udb.updateVerificationCode(email, "");
                udb.updatePassword(password, email);
                return ResponseEntity.ok("Verification success! Password changed.");

            } else
                return ResponseEntity.status(500).body("Verification fail.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error from Verification.");
        }
    }

    @PostMapping("/sendForgotEmail")
    public ResponseEntity<String> sendForgotEmail(@RequestBody String data) {
        UserDB udb = new UserDB();
        System.out.println("Received a request to send an email.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            if (udb.emailExist(email)) {
                String verifyCode = generateVerificationCode();
                // Store verifyCode in the database associated with the email
                udb.updateVerificationCode(email, verifyCode);
                sendEmailMessage(email, "Verification Code", "Your verification code is: " + verifyCode);
            } else {
                return ResponseEntity.status(400).body("Email does not exist in the database.");
            }
            return ResponseEntity.ok("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error from send forgot email.");
        }
    }

    @PostMapping("/sendOrderConfirmation")
    public ResponseEntity<String> sendOrderConfirmation(@RequestBody String data) {
        UserDB udb = new UserDB();
        // System.out.println(movie.toString());
        System.out.println("Received a request to send an email.");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String movieName = jsonNode.get("movie").get("title").asText();
            String userID = jsonNode.get("userID").asText();
            String price = jsonNode.get("price").asText();
            String email = udb.getEmail(Integer.valueOf(userID));
            System.out.println(jsonNode.toString());
            System.out.println(email);
            String emailMessage = String.format("Your Order Confirmation Code: 1000340 \n Ticket: %s \n Cost Total: $%s", movieName, price);

            sendEmailMessage(email, "Order Confirmation", emailMessage);
            System.out.println("done");

            return ResponseEntity.ok(email);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Error from send Order Confirmation.");
        }

    }
}
