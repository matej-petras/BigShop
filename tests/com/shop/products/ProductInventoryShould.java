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

}