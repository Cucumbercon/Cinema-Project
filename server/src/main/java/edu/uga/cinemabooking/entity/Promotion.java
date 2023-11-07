package edu.uga.cinemabooking.entity;

import java.util.Date;

public class Promotion {

    private int id;
    private String promotion_code;
    private String description;
    private double discountAmount;
    private Date startDate;
    private Date endDate;
    private int status;

    // Constructors
    public Promotion() {
    }

    public Promotion(int id, String promotion_code, String description, double discountAmount, Date startDate, Date endDate, int status) {
        this.id = id;
        this.promotion_code = promotion_code;
        this.description = description;
        this.discountAmount = discountAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionCode() {
        return promotion_code;
    }

    public void setPromotionCode(String promotion_code) {
        this.promotion_code = promotion_code;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    // To String method for debugging
    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", promotionCode='"+promotion_code+'\''+
                ", description='" + description + '\'' +
                ", discountAmount=" + discountAmount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
