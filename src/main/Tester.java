package main;

import data.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
                System.out.println("5. Show product types with a ceratin stock or with expired products");
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
                                System.out.println("Product type:");
                                type = scanner.next();
                                System.out.println("New stock:");
                                int stock = scanner.nextInt();
                                cafeManager.addProducts(type,stock);
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
                        cafeManager.showHistory(days);
                        waitForEnter();
                        break;
                    case 5:

                        break;

                    case 6:

                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Please enter a valid operation");
                        break;
                }


            } while (input!=0);

            /*//productTypeManager.get(24);
            ProductManager productManager = new ProductManager();
            //productManager.add(new Product(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000), "Coffee"));
            //productManager.add(new Product(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000), "Cheesecake"));
            //productManager.add(new Product(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000), "Juice"));

            for (ProductType product : productTypeManager.getByStock(15)) {
                System.out.println(product);
            }
            //System.out.println(productTypeManager.getById(2));



            //products = productManager.get("Cheesecake");


            //customerManager.delete(customerManager.get(2));

            for (Customer product : customerManager.getAll()) {
                System.out.println(product);
            }
            //TransactionManager transactionManager = new TransactionManager();
            //transactionManager.add(new Transaction(customerManager.get(1),productManager.get(9),new Date(System.currentTimeMillis())));
            //Transaction toFind;
            //toFind = transactionManager.getTransaction(4);
            //oFind.setCustomer(customerManager.get(toFind.getIdCustomer()));
            //toFind.setProduct(productManager.get(toFind.getIdProduct()));
            //System.out.println(toFind);*/



        // the mysql insert statement
            /*String query = " insert into product_types (type, stock_number, price)"
                    + " values (?, ?, ?)";*/
            /*UPDATE
            String query = "update product_types set stock_number = ? where type = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1,26);
            preparedStatement.setString(2,"coffee");
            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();*/

            /*
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1,"Cheesecake");
            preparedStmt.setInt(2,15);
            preparedStmt.setFloat(3,2);

            preparedStmt.execute();

            preparedStmt.setString(1,"Juice");
            preparedStmt.setInt(2,15);
            preparedStmt.setFloat(3,2);

            preparedStmt.execute();

            preparedStmt.close();
            conn.close();
             */

            /*
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();*/

    }//end main


}
