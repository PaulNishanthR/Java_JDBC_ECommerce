package com.codewithnishanth.models;

public class Product {
    private int id;
    private Category category;
    private String productName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(int id, Category category, String productName, double price) {
        this.id = id;
        this.category = category;
        this.productName = productName;
        this.price = price;
    }

    private double price;
}
