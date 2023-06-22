package com.week1.cashregisterpart1;

import java.io.Serializable;

public class PurchasedProduct implements Serializable {
    private String name;
    private int quantity;
    private double price;

    private String timestamp;

    public PurchasedProduct(String name, int quantity, double price, String timestamp) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    public void setPrice(double price) {
        this.price=price;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp=timestamp;
    }
}
