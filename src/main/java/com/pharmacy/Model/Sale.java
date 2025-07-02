package com.pharmacy.Model;

import java.sql.Date;

public class Sale {
    private String id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private Date date;

    // public Sale(String id, String name, int quantity, double price, Date date) {
    //     this.id = id;
    //     this.name = name;
    //     this.quantity = quantity;
    //     this.price = price;
    //     this.date = date;
    // }

    public Sale(String id, String name, String category, int quantity, double price, Date date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
