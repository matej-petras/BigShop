package com.shop.products;

import java.util.List;

public class ProductInventory {
    private List<Product> productsList;

    public ProductInventory(ProductsLoader loader){
        this.productsList = loader.getDataAsList();
    }

    public Product getProductWithIndex(int productIndex){
        return productsList.get(productIndex);
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
