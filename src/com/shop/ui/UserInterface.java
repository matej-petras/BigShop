package com.shop.ui;

import com.shop.products.Product;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {
    public static final int SELECT_PRODUCT_OPTION_INDEX = 1;
    public static final int PURCHASE_OPTION_INDEX = 2;
    public static final int EXIT_OPTION_INDEX = 3;

    private UIHandler uiHandler;

    public UserInterface(UIHandler uiHandler){
        this.uiHandler = uiHandler;
    }

    public void run() {
        while (true) {
            printShopMenu();
            printControlOptions();
            handleUserInput();
        }
    }

    private void handleUserInput(){

        delayRefresh();
    }

    private void printShopMenu() {
        int index = 0;

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
