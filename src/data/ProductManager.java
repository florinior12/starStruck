package data;

import main.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductManager {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ProductManager() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(Product product) {

        try {
            String query = "INSERT INTO products (expire_date, product_types_type) VALUES (?,?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1,product.getExpireDate());
            preparedStatement.setString(2,product.getProductType());

            preparedStatement.executeUpdate();
            System.out.println(product.getProductType()+ " added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }



    public void delete(Product product) {
        try {
            String query = "DELETE FROM products WHERE id_product = " + product.getIdProduct();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            System.out.println(product.getProductType() + " deleted succesfully from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public Product get(int id) {
        try {
            String query = "SELECT  * FROM products WHERE id_product=" + id;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("ID " + id + " not found !");
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_product") + "|" + resultSet.getDate("expire_date") + "|" + resultSet.getString("product_types_type") );
            }
            return new Product(resultSet.getInt("id_product"), resultSet.getDate("expire_date"), resultSet.getString("product_types_type"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    public void get(String type) {
        try {
            String query = "SELECT  * FROM products WHERE product_types_type = '" + type + "'";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_product") + "|" + resultSet.getDate("expire_date") + "|" + resultSet.getString("product_types_type") );
            }
        }catch (SQLException e) {
            System.out.println(type + " not found !");
            //e.printStackTrace();
        }
        finally {
            close();
        }
    }

    public void getAll() {
        try {
            String query = "SELECT  * FROM products";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_product") + "|" + resultSet.getDate("expire_date") + "|" + resultSet.getString("product_types_type") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
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
