package edu.uga.cinemabooking.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.uga.cinemabooking.DB.CardDB;
import edu.uga.cinemabooking.DB.UserDB;
import edu.uga.cinemabooking.DB.PromoDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.uga.cinemabooking.entity.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PromotionController {

    PromoDB pdb = new PromoDB();
    

    @GetMapping("/promotions")
    public List<Promotion> getAllPromotions() {
        System.out.println("recieved get promotionlist requestion.");
        List<Promotion> promotionlists = pdb.getAllPromotions();
        System.out.println(promotionlists);
        return promotionlists;
    }
}
