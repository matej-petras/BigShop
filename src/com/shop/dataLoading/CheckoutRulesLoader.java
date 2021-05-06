package com.shop.dataLoading;

import com.shop.rules.checkoutRules.InsuranceDiscount;
import com.shop.rules.checkoutRules.ShopCheckoutRule;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckoutRulesLoader implements IDataLoader<ShopCheckoutRule> {
    @Override
    public List<ShopCheckoutRule> getDataAsList() {
        return Stream.of(
                    new InsuranceDiscount()
                ).collect(Collectors.toList());
    }
}
