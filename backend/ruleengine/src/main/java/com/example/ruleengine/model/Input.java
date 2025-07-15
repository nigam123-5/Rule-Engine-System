package com.example.ruleengine.model;

public class Input {

    private String stockSymbol;
    private String action;
    private double price;
    private int quantity;
    private String userType;
    private String marketCondition;

    // Getters and Setters

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMarketCondition() {
        return marketCondition;
    }

    public void setMarketCondition(String marketCondition) {
        this.marketCondition = marketCondition;
    }
}
