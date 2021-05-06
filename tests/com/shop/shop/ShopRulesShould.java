package com.shop.shop;

import com.shop.products.Product;
import com.shop.rules.additionRules.*;
import com.shop.rules.checkoutRules.InsuranceDiscount;
import com.shop.rules.checkoutRules.ShopCheckoutRule;
import com.shop.dataLoading.IDataLoader;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Shop rules should")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShopRulesShould {
    private Shop shop;
    private IDataLoader<Product> productsLoader;
    private IDataLoader<ShopAdditionRule> additionRules;
    private IDataLoader<ShopCheckoutRule> checkoutRules;

    @BeforeAll
    public void setInventory() {
        productsLoader = new ProductsLoader();
        additionRules = new AdditionRulesLoader();
        checkoutRules = new CheckoutRulesLoader();
    }

    @BeforeEach
    public void resetShop(){
        this.shop = new Shop(this.productsLoader.getDataAsList(),
                             this.additionRules.getDataAsList(),
                             this.checkoutRules.getDataAsList()
                        );
    }

    @Test
    @DisplayName("give one free SIM card to a bought one")
    public void addFreeSimToSimPurchase() {
        int simProductIndex = 0;
        int simCount = 2;
        this.shop.addProductsToCard(simProductIndex, simCount);
        assertEquals(simCount * 2, this.shop.getProductByIndex(simProductIndex).getCount());
    }

    @Test
    @DisplayName("result in an error when extending the SIM card buy limit")
    public void addSimCardsAboveLimitToPurchase(){
        int simProductIndex = 0;
        int simCount = SimLawLimitation.LAW_LIMITATION + 1;
        assertThrows(RuntimeException.class, () -> this.shop.addProductsToCard(simProductIndex, simCount));
    }

    @Test
    @DisplayName("not result in an error when getting close to limit of buying SIM Cards")
    public void addSimCardsAlmostInLimitToPurchase(){
        int simProductIndex = 0;
        int simCount = SimLawLimitation.LAW_LIMITATION/2;   // count in the BOGOF Rule as well
        assertDoesNotThrow(() -> this.shop.addProductsToCard(simProductIndex, simCount));
    }

    @Test
    @DisplayName("apply tax to a taxable product added to inventory")
    public void applyTaxToTaxableProduct(){
        int simProductIndex = 0;
        int simCount = 3;
        float price = this.shop.getProductByIndex(simProductIndex).getPrice();
        float expectedPrice = (price + price * ApplyTaxRule.TAX_PERCENTAGE_AMOUNT) * simCount;

        this.shop.addProductsToCard(simProductIndex, simCount);
        assertEquals(expectedPrice, this.shop.getProductByIndex(simProductIndex).getTotalPrice());
    }

    @Test
    @DisplayName("not apply tax to tax-exculded products")
    public void dontApplyTaxToTaxExcludedProduct(){
        int insuranceProductIndex = 2;
        int insurancesCount = 3;
        float price = this.shop.getProductByIndex(insuranceProductIndex).getPrice();
        float expectedPrice = price * insurancesCount;

        this.shop.addProductsToCard(insuranceProductIndex, insurancesCount);
        assertEquals(expectedPrice, this.shop.getProductByIndex(insuranceProductIndex).getTotalPrice());
    }

    @Test
    @Disabled
    @DisplayName("discount insurance price when headphones are in products list")
    public void discountInsurancePriceWhenBuyingHeadphones(){
        int headphonesIndex = 3;
        int headphonesCount = 2;
        this.shop.addProductsToCard(headphonesIndex, headphonesCount);

        int insuraceIndex = 2;
        int insurancesCount = 1;
        float insurancePrice = shop.getProductByIndex(insuraceIndex).getPrice();
        float expectedPrice = (insurancePrice - insurancePrice* InsuranceDiscount.DISCOUNT_PERCENTAGE_AMOUNT) * insurancesCount;

        this.shop.addProductsToCard(insuraceIndex, insurancesCount);
        assertEquals(expectedPrice, shop.getProductByIndex(insuraceIndex).getTotalPrice());
    }

    // Test class specific data
    private static class ProductsLoader implements IDataLoader<Product> {
        @Override
        public List<Product> getDataAsList() {
            List<String> simParameters = Arrays.asList("TESTING_PARAMETER_X", "TESTING_PARAMETER_Y", "SIM");
            List<String> caseParameters = Arrays.asList("TESTING_PARAMETER_A", "TESTING_PARAMETER_B");
            List<String> phoneInsuranceParameters = Arrays.asList("InsURaNce","TESTING_PARAMETER_2","TESTING_PARAMETER_3");
            List<String> headphonesParameters = Arrays.asList("Headphones","Wireless");

            return Stream.of(
                        new Product("MeowSim 123", simParameters, 20f),
                        new Product("XI APO Case", caseParameters, 10f),
                        new Product("Insurance Oi2", phoneInsuranceParameters, 120f),
                        new Product("S-HeadPHONES m", headphonesParameters, 30f)
                     ).collect(Collectors.toList());
        }
    }

    private static class AdditionRulesLoader implements IDataLoader<ShopAdditionRule> {
        @Override
        public List<ShopAdditionRule> getDataAsList() {
            return Stream.of(
                        new SimBOGOFRule(),
                        new SimLawLimitation(),
                        new ApplyTaxRule()
                    ).collect(Collectors.toList());
        }
    }
    private static class CheckoutRulesLoader implements IDataLoader<ShopCheckoutRule> {
        @Override
        public List<ShopCheckoutRule> getDataAsList() {
            return Stream.of(
                        new InsuranceDiscount()
                    ).collect(Collectors.toList());
        }
    }
}
