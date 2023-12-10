package edu.uga.cinemabooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private int user_id;
    private int payment_id;
    private int ticket_id;
    private int promote_id;
    private int ticket_amount;
    private double total;
    private String order_time;
    private String describe;

    public Order() {

    }

    // Getter and setter methods for user_id
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    // Getter and setter methods for payment_id
    public int getPaymentId() {
        return payment_id;
    }

    public void setPaymentId(int payment_id) {
        this.payment_id = payment_id;
    }

    // Getter and setter methods for ticket_id
    public int getTicketId() {
        return ticket_id;
    }

    public void setTicketId(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    // Getter and setter methods for promote_id
    public int getPromoteId() {
        return promote_id;
    }

    public void setPromoteId(int promote_id) {
        this.promote_id = promote_id;
    }

    // Getter and setter methods for ticket_amount
    public int getTicketAmount() {
        return ticket_amount;
    }

    public void setTicketAmount(int ticket_amount) {
        this.ticket_amount = ticket_amount;
    }

    // Getter and setter methods for total
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Getter and setter methods for order_time
    public String getOrderTime() {
        return order_time;
    }

    public void setOrderTime(String order_time) {
        this.order_time = order_time;
    }

    // Getter and setter methods for describe
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user_id=" + user_id +
                ", payment_id=" + payment_id +
                ", ticket_id=" + ticket_id +
                ", promote_id=" + promote_id +
                ", ticket_amount=" + ticket_amount +
                ", total=" + total +
                ", order_time='" + order_time + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}