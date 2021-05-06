package com.shop.launch;

import com.shop.products.ProductsLoader;
import com.shop.utils.AdditionRulesLoader;
import com.shop.shop.Shop;
import com.shop.ui.UIHandler;
import com.shop.ui.UserInterface;
import com.shop.utils.CheckoutRulesLoader;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        ProductsLoader productsLoader = new ProductsLoader();
        AdditionRulesLoader additionRuleLoader = new AdditionRulesLoader();
        CheckoutRulesLoader checkoutRuleLoader = new CheckoutRulesLoader();

        Shop shop = new Shop(productsLoader.getDataAsList(),
                             additionRuleLoader.getDataAsList(),
                             checkoutRuleLoader.getDataAsList());
        UIHandler userInputHandler = new UIHandler(shop, new Scanner(System.in));
        UserInterface userInterface = new UserInterface(userInputHandler);

        userInterface.run();
    }
}