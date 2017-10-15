package data;

import main.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class TransactionManager {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public TransactionManager() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public boolean add(Transaction transaction) {
        transaction.getTransactionDate().compareTo(transaction.getProduct().getExpireDate());
        try {
            String query = "INSERT INTO transactions (transaction_date, customers_id_customer, products_id_product) VALUES (?,?,?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1,transaction.getTransactionDate());

            preparedStatement.executeUpdate();
            System.out.println(transaction.getCustomer().getName() + " bought " + transaction.getProduct().getProductType());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }



    public void delete(Customer customer) {
        try {
            String query = "DELETE FROM customers WHERE id_customer = " + customer.getIdCustomer();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            System.out.println(customer.getName() + " deleted succesfully from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Customer get(int id) {
        try {
            String query = "SELECT  * FROM customers WHERE id_customer=" + id;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return new Customer(resultSet.getInt("id_customer"),resultSet.getString("name"));
            } else {
                System.out.println("ID " + id + " not found !");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public ArrayList get(String name) {
        try {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            String query = "SELECT  * FROM customers WHERE name = '" + name + "'";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt("id_customer"),resultSet.getString("name")));
            }
            return customers;
        }catch (SQLException e) {
            System.out.println(name + " not found !");
            //e.printStackTrace();
        }
        finally {
            close();
        }
        return null;
    }

    public ArrayList getAll() {
        try {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            String query = "SELECT  * FROM products";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(new Customer(resultSet.getInt("id_customer"),resultSet.getString("name")));
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    private void close() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
