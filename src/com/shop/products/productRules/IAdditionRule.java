package com.shop.products.productRules;

import com.shop.products.Product;
import java.util.List;

public interface IAdditionRule {
    boolean isApplicable(List<Product> productsList, Product product, int incrementCount);
    void act(List<Product> productsList, Product product, int incrementCount);
}
