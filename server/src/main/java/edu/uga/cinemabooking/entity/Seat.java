package edu.uga.cinemabooking.entity;

public class Seat {
    int id;
    int ticket_id;
    int showroom_id;
    int column;
    int row;

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for ticket_id
    public int getTicketId() {
        return ticket_id;
    }

    public void setTicketId(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    // Getter and Setter for showroom_id
    public int getShowroomId() {
        return showroom_id;
    }

    public void setShowroomId(int showroom_id) {
        this.showroom_id = showroom_id;
    }

    // Getter and Setter for column
    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    // Getter and Setter for row
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
