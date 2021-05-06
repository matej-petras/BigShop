package com.shop.rules.additionRules;

import com.shop.products.Product;
import java.util.List;

// Max 10 SIMs in one buy
public class SimLawLimitation implements ShopAdditionRule {
    public static final int LAW_LIMITATION = 10;

    @Override
    public boolean isApplicable(List<Product> productsList, Product product, int incrementCount) {
        return product.getProductParameters().contains("SIM");
    }

    @Override
    public void act(List<Product> productsList, Product product, int incrementCount) throws RuntimeException {
        if (product.getCount() > LAW_LIMITATION) {
            String message = String.format("Law prohibits to have more than %d SIM cards (you have %d)",
                                        LAW_LIMITATION, product.getCount() + incrementCount);
            throw new RuntimeException(message);
        }
    }

    @Override
    public String getRuleMessage() {
        return "";
    }
}
