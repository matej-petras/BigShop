package com.shop.rules.checkoutRules;

import com.shop.products.Product;

import java.util.List;

public interface ShopCheckoutRule {
    boolean isApplicable(List<Product> productsList);
    void act(List<Product> productsList) throws RuntimeException;
    String getRuleMessage();
}
