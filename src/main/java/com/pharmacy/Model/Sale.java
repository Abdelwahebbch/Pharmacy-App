package com.pharmacy.Model;

import java.sql.Date;

public class Sale {
    private String id;
    private String name;
    private String category;
    private int quantity;
    private double unitPrice;
    private double total;
    private Date date;

    // public Sale(String id, String name, int quantity, double price, Date date) {
    // this.id = id;
    // this.name = name;
    // this.quantity = quantity;
    // this.price = price;
    // this.date = date;
    // }

    public Sale(String id, String name, String category, int quantity, double price, Date date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = price;
        this.date = date;
    }

    public Sale(String name, String category, int quantity, double unitPrice, double total, Date date) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.date = date;
    }

    public Sale(String id, String name, String category, int quantity, double unitPrice, double total, Date date) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = total;
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double price) {
        this.unitPrice = price;
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
