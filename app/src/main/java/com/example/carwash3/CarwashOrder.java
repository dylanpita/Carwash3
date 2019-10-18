package com.example.carwash3;

public class CarwashOrder {
    final int EXTERIOR_ONLY = 5;
    final int INTERIOR_EXTERIOR = 10;

    private int amount;
    private double price;

    public CarwashOrder() {
        this.amount = 1;
        this.price = EXTERIOR_ONLY;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getTotalPrice(){
        double discount = 0;
        if (amount >= 12)
            discount = 25;
        return (price * amount) * ((100 - discount) / 100);
    }
}
