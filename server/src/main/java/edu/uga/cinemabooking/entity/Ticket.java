package edu.uga.cinemabooking.entity;

public class Ticket {
    int id;
    int schedule_id;
    public enum State {
        available, reserved, occupied, expired
    }
    State state;
    int seat_id;

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for schedule_id
    public int getScheduleId() {
        return schedule_id;
    }

    public void setScheduleId(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    // Getter and Setter for state
    public State getState() {
        return state;
    }

    public void setState(State available) {
        this.state = available;
    }

    // Getter and Setter for seat_id
    public int getSeatId() {
        return seat_id;
    }

    public void setSeatId(int seat_id) {
        this.seat_id = seat_id;
    }
}
