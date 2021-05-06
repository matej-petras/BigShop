package com.shop.shop.rules;

import com.shop.products.Product;
import java.util.List;

// BOGOF - buy one get one for free
public class SimBOGOFRule implements IAdditionRule {
    @Override
    public boolean isApplicable(List<Product> productsList, Product product, int incrementCount) {
        return product.getProductParameters().contains("SIM");
    }

    @Override
    public void act(List<Product> productsList, Product product, int incrementCount) {
        product.increaseCount(incrementCount);
    }
}
