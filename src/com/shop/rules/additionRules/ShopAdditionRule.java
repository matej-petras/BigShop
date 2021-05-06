package com.shop.rules.additionRules;

import com.shop.products.Product;

import java.util.List;

public interface ShopAdditionRule {
    boolean isApplicable(List<Product> productsList, Product product, int incrementCount);
    void act(List<Product> productsList, Product product, int incrementCount) throws RuntimeException;
    String getRuleMessage();
}
