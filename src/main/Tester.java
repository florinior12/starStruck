package main;

import data.ProductType;
import data.ProductTypeManager;

import java.sql.Connection;
import java.sql.SQLException;

public class Tester {

    public static void main(String[] args) {
        try {

            ProductTypeManager productTypeManager = new ProductTypeManager();
            productTypeManager.get(24);
        } catch (Exception e) {
            e.printStackTrace();
        }


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
