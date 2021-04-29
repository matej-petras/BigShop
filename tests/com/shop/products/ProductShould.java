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
    @DisplayName("not allow the product price to be a negative number")
    public void notAllowNegativePrice(){
        assertThrows(IllegalArgumentException.class, () -> product.setPrice( - 12.2f ));
    }

    @Test
    @DisplayName("not allow the product count to be a negative number")
    public void notAllowNegativeCount(){
        assertThrows(IllegalArgumentException.class, () -> product.setCount( -5 ));
    }
}