package com.shop.rules.checkoutRules;

import com.shop.products.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsuranceDiscount implements ShopCheckoutRule {
    public static final float DISCOUNT_PERCENTAGE_AMOUNT = 0.2f;
    private static final List<String> discountParameterCondition = Stream.of("headphones").collect(Collectors.toList());
    private final List<Product> applicableProducts;
    private String message;

    public InsuranceDiscount(){
        applicableProducts = new ArrayList<>();
    }

    @Override
    public boolean isApplicable(List<Product> productsList) {
        applicableProducts.clear();

        for (Product productInList : productsList) {
           if (productInList.getProductParameters().stream()
                   .anyMatch(parameter -> discountParameterCondition.contains(parameter)))
               this.applicableProducts.add(productInList);
        }
        return (! this.applicableProducts.isEmpty());
    }

    @Override
    public void act(List<Product> productsList) throws RuntimeException {
        int counter = 0;
        for (Product applicableProduct : this.applicableProducts) {
            float priceDiscount = applicableProduct.getPrice() * DISCOUNT_PERCENTAGE_AMOUNT;
            applicableProduct.decreaseTotalPrice(applicableProduct.getPrice() - priceDiscount);
            counter++;
        }
        this.message = String.format("%.2f%% discount %d times", DISCOUNT_PERCENTAGE_AMOUNT*100, counter);
    }

    public String getRuleMessage(){
        return this.message;
    }
}
