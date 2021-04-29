package com.shop.products;

import com.shop.products.productRules.IAdditionRule;

import java.util.List;

public class Product {
    private final String name;
    private final List<String> productParameters;
    private float totalPrice;
    private float price;
    private int count;

    public Product(String name, List<String> productParameters, float price) {
        this.name = name;
        this.productParameters = productParameters;
        this.price = price;
    }

    public List<String> getProductParameters(){
        return this.productParameters;
    }

    public float getPrice() {
        return this.price;
    }

    public float getTotalPrice() { return this.totalPrice; }

    public void decreaseTotalPrice(float price) {
        if (this.totalPrice - price < 0)
            throw new IllegalArgumentException(
                    String.format("Total price must not be negative. You entered: %.2f", price)
            );
        this.totalPrice -= price;
    }

    public void increaseTotalPrice(float price) {
        this.totalPrice += price;
    }

    public int getCount() {
        return count;
    }

    public void decreaseCount(int countDecrement) {
        if (this.count - countDecrement < 0)
            throw new IllegalArgumentException(
                    String.format("Count must not be negative. You entered: %d", countDecrement)
            );
        this.count -= countDecrement;
    }

    public void increaseCount(int countIncrement) {
        this.count += countIncrement;
    }

    @Override
    public String toString(){
        return String.format("%10s %.2f (%d pcs of total %.2f)", this.name, this.price, this.count, this.totalPrice);
    }
}
