package com.shop.products;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Inventory should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductInventoryShould {
    private ProductInventory inventory;

    @BeforeAll
    public void setInventory() {
        this.inventory = new ProductInventory(new ProductsLoader());
    }

    @Test
    @DisplayName("add products by increasing count")
    public void addProductsToInventory(){
        int count = 10;
        int productIndex = 0;
        this.inventory.addMultipleProducts(productIndex, count);
        assertEquals(count, inventory.getProductsList().get(productIndex).getCount());
    }
}