package com.shop.shop;

import com.shop.products.Product;
import com.shop.shop.rules.IAdditionRule;

import java.util.List;

public class Shop {
    private final List<Product> productsList;
    private final List<IAdditionRule> additionRules;

    public Shop(List<Product> productsList, List<IAdditionRule> rules){
        this.productsList = productsList;
        this.additionRules = rules;
    }

    public List<IAdditionRule>  getRules() {
        return this.additionRules;
    }
    public void addAdditionRule(IAdditionRule rule){
        this.additionRules.add(rule);
    }

    public void requestAddition(int productIndex, int incrementCount) throws RuntimeException {
        Product targetProduct = this.productsList.get(productIndex);
        //boolean anyRulesTriggered = false;

        for(IAdditionRule rule : this.additionRules)
            if(rule.isApplicable(productsList, targetProduct, incrementCount)) {
                rule.act(productsList, targetProduct, incrementCount);
                //anyRulesTriggered = true;
            }

        addProductsToInventory(targetProduct, incrementCount);
    }

    private void addProductsToInventory(Product targetProduct, int incrementCount){
        targetProduct.increaseCount(incrementCount);
        targetProduct.increaseTotalPrice(targetProduct.getPrice() * incrementCount);
    }

    public Product getProductByIndex(int productIndex){
        return this.productsList.get(productIndex);
    }
}