package data;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import main.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeManager {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ProductTypeManager() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public void add(ProductType productType) {

        try {
            String query = "INSERT INTO product_types (type, stock_number, price) VALUES (?,?,?)";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,productType.getProductType());
            preparedStatement.setInt(2,productType.getStockNumber());
            preparedStatement.setFloat(3,productType.getPrice());
            preparedStatement.executeUpdate();
            System.out.println(productType.getProductType() + " added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

    }

    public void update(ProductType productType) {
        try {
            String query = "UPDATE product_types SET stock_number = ? WHERE id_types = " + productType.getIdProductType();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,productType.getStockNumber());

            preparedStatement.executeUpdate();
            System.out.println(productType.getProductType() + " updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public void delete(ProductType productType) {
        try {
            String query = "DELETE FROM product_types WHERE id_types = " + productType.getIdProductType();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
            System.out.println(productType.getProductType() + " deleted succesfully from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public ProductType getById(int id) {
        try {
            String query = "SELECT  * FROM product_types WHERE id_types=" + id;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("ID " + id + " not found !");
                return null;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_types") + "|" + resultSet.getString("type") + "|" + resultSet.getInt("stock_number")  + "|" + resultSet.getFloat("price") );
                return new ProductType(resultSet.getInt("id_types"), resultSet.getString("type"), resultSet.getInt("stock_number"), resultSet.getFloat("price") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;

    }

    public ProductType getByStock(int stock) {
        try {
            String query = "SELECT  * FROM product_types WHERE stock_number=" + stock;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("ID " +   " not found !");
                return null;
            }
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_types") + "|" + resultSet.getString("type") + "|" + resultSet.getInt("stock_number")  + "|" + resultSet.getFloat("price") );
                return new ProductType(resultSet.getInt("id_types"), resultSet.getString("type"), resultSet.getInt("stock_number"), resultSet.getFloat("price") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return null;

    }

    public void get(String type) {
        try {
            String query = "SELECT  * FROM product_types WHERE type = '" + type + "'";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_types") + "|" + resultSet.getString("type") + "|" + resultSet.getInt("stock_number")  + "|" + resultSet.getFloat("price") );
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
            String query = "SELECT  * FROM product_types";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id_types") + "|" + resultSet.getString("type") + "|" + resultSet.getInt("stock_number")  + "|" + resultSet.getFloat("price") );
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
