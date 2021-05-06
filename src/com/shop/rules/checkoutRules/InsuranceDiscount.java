package com.shop.rules.checkoutRules;

import com.shop.products.Product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsuranceDiscount implements ShopCheckoutRule {
    public static final float DISCOUNT_PERCENTAGE_AMOUNT = 0.2f;
    private static final List<String> discountableProducts = Stream.of("insurance").collect(Collectors.toList());
    private static final List<String> discountParameterCondition = Stream.of("headphones").collect(Collectors.toList());
    private String message;

    @Override
    public boolean isApplicable(List<Product> productsList) {
        for (Product productInList : productsList) {
           if (productInList.getProductParameters().stream()
                   .anyMatch(parameter -> discountParameterCondition.contains(parameter.toLowerCase()))) {
               System.out.println("APPLICABLE");
               return true;
           }
        }
        System.out.println("NOT APPLICABLE");
        return false;
    }

    @Override
    public void act(List<Product> productsList) throws RuntimeException {
        int counter = 0;
        for (Product productInList : productsList) {
            if (productInList.getProductParameters().stream()
                    .anyMatch(parameter -> discountableProducts.contains(parameter.toLowerCase()))){
                float priceDiscount = productInList.getPrice() * productInList.getCount() * DISCOUNT_PERCENTAGE_AMOUNT ;
                productInList.decreaseTotalPrice(priceDiscount);
                counter++;
            }
        }
        this.message = String.format("%.2f%% discount %d times", DISCOUNT_PERCENTAGE_AMOUNT*100, counter);
        System.out.println(message);
    }

    public String getRuleMessage(){
        return this.message;
    }
}
