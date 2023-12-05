package edu.uga.cinemabooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    String cardNumber;
    String expDate;
    String billingState;
    String billingStreet;
    String billingZipCode;
    String billingCity;
    int userID;

    /*
     * Empty construcotr
     */
    public Card() {

    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "YourClassName{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expDate='" + expDate + '\'' +
                ", billingState='" + billingState + '\'' +
                ", billingStreet='" + billingStreet + '\'' +
                ", billingZipCode='" + billingZipCode + '\'' +
                ", billingCity='" + billingCity + '\'' +
                ", userID=" + userID +
                '}';
    }

}
