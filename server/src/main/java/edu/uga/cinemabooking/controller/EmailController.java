package edu.uga.cinemabooking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.MovieDB;
import edu.uga.cinemabooking.entity.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
     * @return the list of movies
     */
    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            String email = jsonNode.get("email").asText();
            int emailType = jsonNode.get("type").asInt();

            switch (emailType) {
                case 1:
                    String verifyCode = generateVerificationCode();
                    // Store verifyCode in the database associated with the email
                    sendEmailMessage(email, "Verification Code", "Your verification code is: " + verifyCode);
                    System.out.println("success.");
                    break;
                case 2:
                    sendEmailMessage(email, "Signup Successful", "Congratulations on signing up!");
                    break;
                case 3:
                    sendEmailMessage(email, "Profile Updated", "Your profile has been successfully updated.");
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
                new javax.mail.Authenticator() {
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
            throw new RuntimeException(e);
        }
    }

private String generateVerificationCode() {
    Random random = new Random();
    int code = random.nextInt(900000) + 100000; // This ensures the code is always 6 digits
    return String.valueOf(code);
}




}

