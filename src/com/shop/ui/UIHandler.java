package com.shop.ui;

import com.shop.shop.Shop;

import java.util.Scanner;

public class UIHandler {
    private final Scanner scanner;
    private Shop shop;

    public UIHandler(Shop shop, Scanner scanner) {
        this.scanner = scanner;
        this.shop = shop;
    }

    public void HandleUserOptionSelection(){
        final int selectedOption = scanner.nextInt();

        switch (selectedOption) {
            case UserInterface.SELECT_PRODUCT_OPTION_INDEX:
                addProductsToInventory();
                break;
            case UserInterface.PURCHASE_OPTION_INDEX:
                System.out.println("Thank you for your purchase !");
                break;
            case UserInterface.EXIT_OPTION_INDEX:
                System.out.println("...exiting");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown action, please try again");
                break;
        }
    }

    private void addProductsToInventory(){
        System.out.print("Enter index of requested product: ");
        int productIndex = scanner.nextInt();
        System.out.print("Enter the amount of requested products: ");
        int productsAmount = scanner.nextInt();

        System.out.println("Adding " + productsAmount + " of product " + productIndex + "into inventory");
    }
}