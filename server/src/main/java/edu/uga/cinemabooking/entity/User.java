package edu.uga.cinemabooking.entity;

public class User {

    int id;
    int type;
    String fullName;
    String email;
    String password;
    String phoneNumber;
    String creditCardNumber;
    String expirationDate;
    String state;
    String street;
    String city;
    int subscribe;
    String zipCode;

    // empty contrusctor
    public User() {

    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get method for phone number
     * 
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", subscribe='" + subscribe + '\'' +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // /**
    // * This method is to change the user's password.
    // *
    // * @param curPassword current password
    // * @param newPassword new password
    // * @return if successed
    // */
    // public Boolean setPassword(String curPassword, String newPassword) {
    // if (this.password.equals(curPassword)) {
    // this.password = newPassword;
    // return true;
    // }
    // return false;
    // }

}
