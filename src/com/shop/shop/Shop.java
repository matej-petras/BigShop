package com.shop.shop;

import com.shop.products.Product;
import com.shop.rules.additionRules.ShopAdditionRule;
import com.shop.rules.checkoutRules.ShopCheckoutRule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Shop {
    private final List<Product> offeredProducts = new ArrayList<>();
    private final List<ShopAdditionRule> additionRules = new ArrayList<>();
    private final List<ShopCheckoutRule> checkoutRules = new ArrayList<>();

    public Shop(List<Product> offeredProducts){
        this.offeredProducts.addAll(offeredProducts);
    }

    public Shop(List<Product> offeredProducts,
                List<ShopAdditionRule> additionRules,
                List<ShopCheckoutRule> checkoutRules){
        this.offeredProducts.addAll(offeredProducts);
        this.additionRules.addAll(additionRules);
        this.checkoutRules.addAll(checkoutRules);
    }

    public void addProductsToCard(int productIndex, int incrementCount) throws RuntimeException {
        Product targetProduct = this.offeredProducts.get(productIndex);

        for(ShopAdditionRule rule : this.additionRules)
            if (rule.isApplicable(offeredProducts, targetProduct, incrementCount)) {
                rule.act(offeredProducts, targetProduct, incrementCount);
                targetProduct.addAppliedRule(rule.getRuleMessage());
            }

        targetProduct.increaseCount(incrementCount);
        targetProduct.increaseTotalPrice(targetProduct.getPrice() * incrementCount);
        
        for (ShopCheckoutRule rule : this.checkoutRules)
            if (rule.isApplicable(offeredProducts))
                rule.act(offeredProducts);
    }

    public List<Product> getReceipt(){
        List<Product> receiptContent = offeredProducts.stream()
                .filter(product -> product.getCount() > 0).collect(Collectors.toList());
        for (Product product : offeredProducts)
            product.reset();

        return receiptContent;
    }

    public List<Product> getProducts(){ return offeredProducts; }
    public Product getProductByIndex(int productIndex){
        return this.offeredProducts.get(productIndex);
    }
}