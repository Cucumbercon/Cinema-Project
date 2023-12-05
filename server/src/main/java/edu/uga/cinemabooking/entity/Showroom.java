package edu.uga.cinemabooking.entity;

public class Showroom {
    int id;
    int seat_amount;
    String information;
    String name;

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for seat_amount
    public int getSeatAmount() {
        return seat_amount;
    }

    public void setSeatAmount(int seat_amount) {
        this.seat_amount = seat_amount;
    }

    // Getter and Setter for information
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
