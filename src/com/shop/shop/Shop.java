package com.shop.shop;

import com.shop.products.Product;

import java.util.List;

public class Shop {
    private List<Product> productsList;

    public Shop(List<Product> productsList){
        this.productsList = productsList;
    }

    public void addProductsToInventory(int productIndex, int productCount){
        Product targetProduct = this.productsList.get(productIndex);
        targetProduct.increaseCount(productCount);
        targetProduct.increaseTotalPrice(targetProduct.getPrice() * productCount);
    }

    public Product getProductByIndex(int productIndex){
        return this.productsList.get(productIndex);
    }
}