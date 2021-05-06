package com.shop.shop;

import com.shop.products.Product;
import com.shop.rules.additionRules.ShopAdditionRule;
import com.shop.rules.checkoutRules.ShopCheckoutRule;

import java.util.ArrayList;
import java.util.List;

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
            if(rule.isApplicable(offeredProducts, targetProduct, incrementCount))
                rule.act(offeredProducts, targetProduct, incrementCount);

        targetProduct.increaseCount(incrementCount);
        targetProduct.increaseTotalPrice(targetProduct.getPrice() * incrementCount);
    }

    public void checkout(){
        System.out.println("Purchase Performed");
        clearProducts();
    }

    public Product getProductByIndex(int productIndex){
        return this.offeredProducts.get(productIndex);
    }

    private void clearProducts(){
        for (Product product : offeredProducts)
            product.reset();
    }
}