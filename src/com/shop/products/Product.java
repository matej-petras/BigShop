package com.shop.products;

public class Product {
    private final String name;
    private float price;
    private int count;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0)
            throw new IllegalArgumentException(
                    String.format("Price must not be negative. You entered: %d", price)
            );
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        if (count < 0)
            throw new IllegalArgumentException(
                    String.format("Count must not be negative. You entered: %d", count)
            );
        this.count = count;
    }

    @Override
    public String toString(){
        return String.format("%10s %.2f (%d pcs)", this.name, this.price, this.count);
    }
}
