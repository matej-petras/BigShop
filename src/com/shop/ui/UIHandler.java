package com.shop.ui;

import com.shop.products.Product;
import com.shop.shop.Shop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UIHandler {
    private final Scanner scanner;
    private Shop shop;

    public UIHandler(Shop shop, Scanner scanner) {
        this.scanner = scanner;
        this.shop = shop;
    }

    public void handleUserOptionSelection(){
        final int selectedOption = scanner.nextInt();

        switch (selectedOption) {
            case UserInterface.SELECT_PRODUCT_OPTION_INDEX:
                printOfferedProducts();
                addProductsToShoppingListMenu();
                break;
            case UserInterface.PURCHASE_OPTION_INDEX:
                printReceipt(shop.getReceipt());
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

    private void addProductsToShoppingListMenu() {
        int productIndex = 0;
        int productsAmount = 0;

        try {
            System.out.print("Enter the amount of requested products: ");
            productsAmount = scanner.nextInt();

            System.out.print("Enter index of requested product: ");
            productIndex = scanner.nextInt();
        } catch (Exception exception) {
            System.err.println("Please, enter correct numbers");
        }

        System.out.println("Adding " + productsAmount + " of product " + productIndex + "into inventory");
        try {
            shop.addProductsToCard(productIndex, productsAmount);
        } catch (IndexOutOfBoundsException exception){
            System.err.println("Error: Please, enter correct index of product and amount" + exception.getMessage());
        }
    }

    private void printOfferedProducts(){
        int index;
        List<Product> products = shop.getProducts();

        System.out.println("\n---- Shop Offers  ----");
        for (index = 0; index < products.size(); index++)
            System.out.printf("\t%d: %s%n", index, products.get(index));

        System.out.println();
    }

    private void printReceipt(List<Product> receipt){
        System.out.println("\n=====================");
        System.out.println("------ Your Purchase ----");
        for (Product product : receipt){
            System.out.println("  " + product);
        }
    }
}