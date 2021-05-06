package com.shop.shop;

import com.shop.products.Product;
import com.shop.shop.rules.IAdditionRule;
import com.shop.shop.rules.SimBOGOFRule;
import com.shop.utils.IDataLoader;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Inventory should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ShopShould {
    private Shop shop;
    private ProductsLoader productLoader;
    private RulesLoader rulesLoader;

    @BeforeAll
    public void setInventory() {
        productLoader = new ProductsLoader();
        rulesLoader = new RulesLoader();
    }

    @BeforeEach
    public void resetShop(){
        this.shop = new Shop(productLoader.getDataAsList(), rulesLoader.getDataAsList());
    }

    @Test
    @Disabled
    @DisplayName("be able to add products of one kind")
    public void addProductsOfOneKind() {
        int productIndex = 0;
        int productCount = 3;
        this.shop.requestAddition(productIndex, productCount);
        assertEquals(productCount, shop.getProductByIndex(productIndex).getCount());
    }

    @Test
    @DisplayName("increase the price of a product when adding it in")
    public void increasePriceOfAddedProduct(){
        int productIndex = 0;
        int productCount = 1;
        this.shop.requestAddition(productIndex, productCount);
        Product product = this.shop.getProductByIndex(productIndex);

        assertEquals(product.getPrice(), product.getTotalPrice());
    }

    @Test
    @DisplayName("increase the price of a multiple added products accordingly")
    public void increasePriceOfMultipleAddedProduct(){
        int productIndex = 0;
        int productCount = 3;
        this.shop.requestAddition(productIndex, productCount);
        Product product = this.shop.getProductByIndex(productIndex);

        assertEquals(product.getPrice() * productCount, product.getTotalPrice());
    }

    // Test class specific data
    private static class ProductsLoader implements IDataLoader<Product> {
        @Override
        public List<Product> getDataAsList() {
            List<String> simParameters = Arrays.asList("TESTING_PARAMETER_X", "TESTING_PARAMETER_Y", "Z");
            List<String> caseParameters = Arrays.asList("TESTING_PARAMETER_A", "SIM");
            List<String> phoneInsuranceParameters = Arrays.asList("TESTING_PARAMETER_1","TESTING_PARAMETER_2","TESTING_PARAMETER_3");

            return Stream.of(
                    new Product("MeowSim 123", simParameters, 20f),
                    new Product("XI APO Case", caseParameters, 10f),
                    new Product("Insurance Oi2", phoneInsuranceParameters, 120f)
            ).collect(Collectors.toList());
        }
    }

    private static class RulesLoader implements IDataLoader<IAdditionRule> {
        @Override
        public List<IAdditionRule> getDataAsList() {
            return Stream.of(
                    new SimBOGOFRule()
            ).collect(Collectors.toList());
        }
    }
}