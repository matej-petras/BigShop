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
        this.price = price;
        if (this.price < 0)
            this.price = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        if (this.count < 0)
            this.count = 0;
    }

    @Override
    public String toString(){
        return String.format("%10s %.2f (%d pcs)", this.name, this.price, this.count);
    }
}
