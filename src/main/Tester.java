package main;

import data.*;

import java.io.IOException;
import java.util.Scanner;

public class Tester {
    static void waitForEnter() {
        System.out.println("Press ENTER key to continue.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int input;
        int input2;
        Scanner scanner = new Scanner(System.in);
        CafeManager cafeManager = new CafeManager();
        System.out.println("Welcome to starStruck cafe!");
            do {
                System.out.println("What do you wish to do?");
                System.out.println("1. Add new products/update stock");
                System.out.println("2. Add or remove a loyal customer and their coupon");
                System.out.println("3. Add a transaction");
                System.out.println("4. Show sale history");
                System.out.println("5. Show product types with 0 stock or with expired products");
                System.out.println("6. Cheapest/most expensive 3 products a customer can buy");
                System.out.println("0. Exit");
                input = scanner.nextInt();
                switch (input){
                    case 1:

                        System.out.println("1. Add new products");
                        System.out.println("2. Update stock");
                        System.out.println("0. Exit");
                        input2 = scanner.nextInt();
                        switch (input2) {

                            case 1:
                                System.out.println("Product type:");
                                String type = scanner.next();
                                System.out.println("Amount:");
                                int amount = scanner.nextInt();
                                if (cafeManager.addProducts(type,amount))
                                    System.out.println("Successfully added products!");
                                else
                                    System.out.println("Failed to add products!");

                                waitForEnter();
                                break;
                            case 2:
                                cafeManager.updateStock();
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Plese select a valid operation");
                                break;
                        }

                        break;
                    case 2:
                        System.out.println("1. Add new loyal customer");
                        System.out.println("2. Add coupon to a loyal customer");
                        System.out.println("0. Exit");
                        input2 = scanner.nextInt();
                        switch (input2) {
                            case 1:
                                System.out.println("Name:");
                                String name = scanner.next();
                                if (cafeManager.addCustomer(name)) {
                                    System.out.println("Successfully added customer!");
                                    System.out.println("ID:" + cafeManager.getIdOfLastCustomer());

                                } else
                                    System.out.println("Failed to add customer!");
                                waitForEnter();
                                break;
                            case 2:
                                System.out.println("What is the coupon for? (the product type)");
                                String type = scanner.next();
                                System.out.println("How much is the discount?");
                                float discount = scanner.nextFloat();
                                System.out.println("What is the ID of the customer?");
                                int id = scanner.nextInt();
                                cafeManager.addCoupon(type,discount,id);
                                waitForEnter();
                        }
                        break;
                    case 3:
                        System.out.println("What do you want to buy?");
                        String type = scanner.next();
                        System.out.println("Enter your customer ID(enter 1 if you don't have any)");
                        int id = scanner.nextInt();
                        cafeManager.addTransaction(type,id);
                        waitForEnter();
                        break;
                    case 4:
                        System.out.println("For how many days?");
                        int days = scanner.nextInt();
                        System.out.println("Sales history for last " + days + " days");
                        cafeManager.showHistory(days);
                        waitForEnter();
                        break;
                    case 5:
                        System.out.println("Product types with 0 stock:");
                        cafeManager.showZeroStock();
                        System.out.println("Product types with expired products:");
                        cafeManager.showExpired();
                        waitForEnter();
                        break;

                    case 6:
                        System.out.println("Customer ID:");
                        int idCustomer = scanner.nextInt();
                        cafeManager.showCheapest(idCustomer);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Please enter a valid operation");
                        break;
                }


            } while (input!=0);



    }//end main


}
