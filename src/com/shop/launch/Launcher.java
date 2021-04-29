package com.shop.launch;

import com.shop.products.ProductsLoader;
import com.shop.products.productRules.IAdditionRule;
import com.shop.shop.RulesLoader;
import com.shop.shop.Shop;
import com.shop.ui.UIHandler;
import com.shop.ui.UserInterface;
import com.shop.utils.IDataLoader;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        ProductsLoader productsLoader = new ProductsLoader();
        RulesLoader additionRuleLoader = new RulesLoader();

        Shop shop = new Shop(productsLoader.getDataAsList(), additionRuleLoader.getDataAsList());
        UIHandler userInputHandler = new UIHandler(shop, new Scanner(System.in));
        UserInterface userInterface = new UserInterface(userInputHandler);

        userInterface.run();
    }
}