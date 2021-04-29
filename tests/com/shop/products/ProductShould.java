package com.shop.products;

import org.junit.jupiter.api.*;

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
        this.product = new Product("product1", initialPrice);
    }

    @Test
    @DisplayName("not decrease the product price below zero")
    public void decreasePriceNotBelowZero(){
        product.setPrice( - 12.2f );
        assertEquals(0.0f, product.getPrice());
    }

    @Test
    @DisplayName("not decrease the product count below zero")
    public void decreaseCountNotBelowZero(){
        product.setCount( -5 );
        assertEquals(0, product.getCount());
    }
}