package com.shop.products;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Product {
    private final String name;
    private final List<String> productParameters;
    private final List<String> appliedRules;
    private float totalPrice;
    private float price;
    private int count;

    public Product(String name, List<String> productParameters, float price) {
        this.name = name;
        this.productParameters = productParameters;
        this.price = price;
        this.appliedRules = new ArrayList<>();
    }

    public List<String> getProductParameters(){
        return this.productParameters;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return this.price;
    }

    public float getTotalPrice() { return this.totalPrice; }


    public void increaseTotalPrice(float price) {
        this.totalPrice += price;
    }

    public void decreaseTotalPrice(float price) {
        if (this.totalPrice - price < 0)
            throw new IllegalArgumentException(
                    String.format("Total price must not be negative. You entered: %.2f", price)
            );
        this.totalPrice -= price;
    }

    public void increaseCount(int countIncrement) {
        this.count += countIncrement;
    }

    public void decreaseCount(int countDecrement) {
        if (this.count - countDecrement < 0)
            throw new IllegalArgumentException(
                    String.format("Count must not be negative. You entered: %d", countDecrement)
            );
        this.count -= countDecrement;
    }

    public void addAppliedRule(String ruleMessage){
        if (! ruleMessage.isEmpty())
            this.appliedRules.add(ruleMessage);
    }

    public void reset(){
        this.totalPrice = 0;
        this.count = 0;
        this.appliedRules.clear();
    }

    @Override
    public Product clone() {
        Product product;
        try {
            product = (Product) super.clone();
        } catch (Exception exception){
            product = new Product(this.name, getProductParameters(), getPrice());
            product.increaseTotalPrice(this.getTotalPrice());
            product.increaseCount(this.getCount());
        }
        return product;
    }

    @Override
    public String toString(){
        String pieces = String.format("%.2f", this.price);
        StringJoiner appliedRules = new StringJoiner(",");
        for (String ruleMessage : this.appliedRules)
            appliedRules.add(ruleMessage);

        return String.format("%-25s   %-6s (%-3d pcs of total %.2f) | %s",
                                this.name, pieces, this.count, this.totalPrice, appliedRules);
    }
}
