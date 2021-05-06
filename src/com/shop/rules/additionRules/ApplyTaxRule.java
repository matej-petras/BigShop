package com.shop.rules.additionRules;

import com.shop.products.Product;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ApplyTaxRule implements ShopAdditionRule {
    public static final float TAX_PERCENTAGE_AMOUNT = 0.12f;
    private static final List<String> excludedTaxableItems = Stream.of("insurance").collect(Collectors.toList());

    @Override
    public boolean isApplicable(List<Product> productsList, Product product, int incrementCount) {
        return isTaxable(product);
    }

    private boolean isTaxable(Product product){
        return product.getProductParameters().stream()
                .noneMatch(parameter -> excludedTaxableItems.contains(parameter.toLowerCase()));
    }

    @Override
    public void act(List<Product> productsList, Product product, int incrementCount) throws RuntimeException {
        float price = product.getPrice();
        product.increaseTotalPrice(price*TAX_PERCENTAGE_AMOUNT * incrementCount);
    }

    public String getRuleMessage(){
        return String.format("Tax +%.2f%%", TAX_PERCENTAGE_AMOUNT * 100);
    }
}
