package data;

import main.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public CustomerManager() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(Customer customer) {

        try {
            String query = "INSERT INTO customers (name) VALUES (?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,customer.getName());

            preparedStatement.executeUpdate();
            System.out.println(customer.getName()+ " added successfully!");
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

    public ArrayList<Customer> get(String name) {
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

    public ArrayList<Customer> getAll() {
        try {
            ArrayList<Customer> customers = new ArrayList<Customer>();
            String query = "SELECT  * FROM customers";
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
