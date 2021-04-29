package com.shop.shop;

import com.shop.products.Product;
import com.shop.products.productRules.IAdditionRule;
import com.shop.products.productRules.SimBOGOFRule;
import com.shop.utils.IDataLoader;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Shop rules should apply so that")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShopRulesShould {
    private Shop shop;
    private IDataLoader<Product> productsLoader;
    private IDataLoader<IAdditionRule> rulesLoader;

    @BeforeAll
    public void setInventory() {
        productsLoader = new ProductsLoader();
        rulesLoader = new RulesLoader();
    }

    @BeforeEach
    public void resetShop(){
        this.shop = new Shop(this.productsLoader.getDataAsList(), this.rulesLoader.getDataAsList());
    }

    @Test
    @DisplayName("each SIM card gives the buyer a new free one")
    public void addFreeSimToSimPurchase(){
        int simProductIndex = 0;
        int simCount = 2;
        this.shop.requestAddition(simProductIndex, simCount);
        assertEquals(simCount * 2, this.shop.getProductByIndex(simProductIndex).getCount());
    }


    // Test class specific data
    private static class ProductsLoader implements IDataLoader<Product> {
        @Override
        public List<Product> getDataAsList() {
            List<String> simParameters = Arrays.asList("TESTING_PARAMETER_X", "TESTING_PARAMETER_Y", "SIM");
            List<String> caseParameters = Arrays.asList("TESTING_PARAMETER_A", "TESTING_PARAMETER_B");
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
