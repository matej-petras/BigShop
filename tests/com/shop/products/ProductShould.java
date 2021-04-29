package com.shop.products;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product should")
class ProductShould {
    private float initialPrice;
    private Product product;

    @BeforeAll
    private void setInitialPrice(){
        this.initialPrice = 15.2f;
    }

    @BeforeEach
    private void setProduct(){
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