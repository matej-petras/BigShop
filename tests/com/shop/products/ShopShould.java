package com.shop.products;

import com.shop.shop.Shop;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Inventory should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopShould {
    private Shop shop;
    private ProductsLoader loader;

    @BeforeAll
    public void setInventory() {
        loader = new ProductsLoader();
    }

    @BeforeEach
    public void resetShop(){
        this.shop = new Shop(this.loader.getDataAsList());
    }

    @Test
    @DisplayName("be able to add products of one kind")
    public void addProductsOfOneKind(){
        int productIndex = 0;
        int productCount = 3;
        this.shop.addProductsToInventory(productIndex, productCount);
        assertEquals(productCount, shop.getProductByIndex(productIndex).getCount());
    }

    @Test
    @DisplayName("increase the price of a product when adding it in")
    public void increasePriceOfAddedProduct(){
        int productIndex = 0;
        int productCount = 1;
        this.shop.addProductsToInventory(productIndex, productCount);
        Product product = this.shop.getProductByIndex(productIndex);

        assertEquals(product.getPrice(), product.getTotalPrice());
    }

    @Test
    @DisplayName("increase the price of a multiple added products accordingly")
    public void increasePriceOfMultipleAddedProduct(){
        int productIndex = 0;
        int productCount = 3;
        this.shop.addProductsToInventory(productIndex, productCount);
        Product product = this.shop.getProductByIndex(productIndex);

        assertEquals(product.getPrice() * productCount, product.getTotalPrice());
    }
}