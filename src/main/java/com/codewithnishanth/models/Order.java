package com.codewithnishanth.models;

import java.util.Date;

public class Order {
    private int id;
    private User user;
    private Date date;

    private String status;
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order(int id, User user, Date date, String status, Product product) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.status = status;
        this.product = product;
    }
}
