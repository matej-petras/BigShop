package com.shop.products;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductShould {
    private float initialPrice;
    private Product product;

    @BeforeAll
    private void setInitialPrice(){
        this.initialPrice = 15.2f;
    }

    @BeforeEach
    public void setProduct(){
        this.product = new Product("product1", Arrays.asList("NA"), initialPrice);
    }

    @Test
    @DisplayName("not allow the product total price to be a negative number")
    public void notAllowNegativeTotalPrice(){
        assertThrows(IllegalArgumentException.class, () -> this.product.decreaseTotalPrice(1000f));
    }

    @Test
    @DisplayName("not allow the product count to be a negative number")
    public void notAllowNegativeCount(){
        assertThrows(IllegalArgumentException.class, () -> this.product.decreaseCount(5));
    }
}