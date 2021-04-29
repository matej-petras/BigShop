package com.shop.ui;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {
    private static final int SELECT_PRODUCT_OPTION_INDEX = 1;
    private static final int PURCHASE_OPTION_INDEX = 2;
    private static final int EXIT_OPTION_INDEX = 3;

    private final Scanner scanner;

    public UserInterface(){
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printShopMenu();
            printControlOptions();
            handleUserInput();
        }
    }

    private void handleUserInput(){
        final int selectedOption = scanner.nextInt();

        switch (selectedOption) {
            case SELECT_PRODUCT_OPTION_INDEX:
                addProductsToInventory();
                break;
            case PURCHASE_OPTION_INDEX:
                System.out.println("Thank you for your purchase !");
                break;
            case EXIT_OPTION_INDEX:
                System.out.println("...exiting");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown action, please try again");
                break;
        }

        delayRefresh();
    }

    private void addProductsToInventory(){
        System.out.print("Enter index of requested product: ");
        int productIndex = scanner.nextInt();
        System.out.print("Enter the amount of requested products: ");
        int productsAmount = scanner.nextInt();

        System.out.println("Adding " + productsAmount + " of product " + productIndex + "into inventory");
    }

    private void printShopMenu() {
        System.out.println("=== SHOP ===");
        System.out.println("\tSIM" + 20f);
        System.out.println("\tPhone Case" + 10f);
        System.out.println("\tPhone Insurance (2 years)" + 120f);
        System.out.println("\tWired Headphones" + 30f);
        System.out.println("\tWireless Headphones" + 50f);
        System.out.println();
    }

    private void printControlOptions() {
        System.out.println("Select an Option:");
        System.out.println("\t" + SELECT_PRODUCT_OPTION_INDEX + ": Select Products");
        System.out.println("\t" + PURCHASE_OPTION_INDEX + ": Make a Purchase");
        System.out.println("\t" + PURCHASE_OPTION_INDEX + ": Exit");
    }

    // UI delay to simulate loading
    private void delayRefresh(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException e) {
            System.err.println("Sleep interrupted !");
        }
    }
}
