package com.devpro.android54_day7.Model;

import androidx.annotation.NonNull;

public class CartItem {
    private int id;
    private int imageFood;
    private String foodName;
    private double price;
    private double quantity;


    public CartItem(int id, int imageFood, String foodName, double price) {

        this.id = id;
        this.imageFood = imageFood;
        this.foodName = foodName;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageFood() {
        return imageFood;
    }

    public void setImageFood(int imageFood) {
        this.imageFood = imageFood;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", imageFood=" + imageFood +
                ", foodName='" + foodName + '\'' +
                ", price=" + price +
                '}';
    }
}
