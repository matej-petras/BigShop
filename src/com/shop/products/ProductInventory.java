package com.shop.products;

import java.util.List;

public class ProductInventory {
    private List<Product> productsList;

    public ProductInventory(ProductsLoader loader){
        this.productsList = loader.getDataAsList();
    }

    public void addMultipleProducts(int productIndex, int countIncrement){
        productsList.get(productIndex).setCount(countIncrement);
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
