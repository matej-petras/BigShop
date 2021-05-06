package com.shop.shop.rules;

import com.shop.products.Product;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO - finish this condition (need to check 'isApplicable' and implement 'act')
public class InsuranceDiscount implements IAdditionRule {
    public static final float DISCOUNT_PERCENTAGE_AMOUNT = 0.2f;
    private static final List<String> discountParameterCondition =
            Stream.of("headphones").collect(Collectors.toList());

    @Override
    public boolean isApplicable(List<Product> productsList, Product product, int incrementCount) {
        if (! isProductOfInsuranceType(product))
            return false;

        for (Product productInList : productsList) {
            boolean isDiscountable = productInList
                                        .getProductParameters()
                                        .stream()
                                        .anyMatch(parameter -> discountParameterCondition.contains(parameter));
            if (isDiscountable)
                return true;
        }

        return false;
    }

    private boolean isProductOfInsuranceType(Product product){
        return product.getProductParameters().stream().anyMatch(parameter -> parameter.equalsIgnoreCase("insurance"));
    }

    @Override
    public void act(List<Product> productsList, Product product, int incrementCount) throws RuntimeException {

    }
}
