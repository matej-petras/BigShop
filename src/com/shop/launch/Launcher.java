package com.shop.launch;

import com.shop.products.ProductInventory;
import com.shop.products.ProductsLoader;
import com.shop.shop.Shop;
import com.shop.ui.UIHandler;
import com.shop.ui.UserInterface;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        ProductsLoader productsLoader = new ProductsLoader();
        Shop shop = new Shop(new ProductInventory(productsLoader));
        UIHandler userInputHandler = new UIHandler(shop, new Scanner(System.in));
        UserInterface userInterface = new UserInterface(userInputHandler);

        userInterface.run();
    }
}
