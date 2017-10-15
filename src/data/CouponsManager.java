package data;

import main.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CouponsManager {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public CouponsManager() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public boolean add(Coupon coupon) {

            try {
                String query = "INSERT INTO coupons (discount, customers_id_customer, product_types_id_types) VALUES (?,?,?)";
                connection = getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setFloat(1, coupon.getDiscount());
                preparedStatement.setInt(2, coupon.getIdCustomer());
                preparedStatement.setInt(3, coupon.getIdProductType());

                preparedStatement.executeUpdate();

                return true;
            } catch (SQLException e) {
                //e.printStackTrace();
                return false;
            } finally {
                close();
            }


    }



    public Coupon get(int id) {
        try {
            String query = "SELECT  * FROM coupons WHERE id_coupon=" + id;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return new Coupon(resultSet.getInt("id_coupon"),resultSet.getFloat("discount"),resultSet.getInt("customers_id_customer"), resultSet.getInt("products_id_product"));
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

    public ArrayList<Coupon> getByForeign(int id, boolean getByCustomer) {
        try {
            ArrayList<Coupon> coupons = new ArrayList<Coupon>();
            String getBy;
            if (getByCustomer)
                getBy = "customers_id_customer";
            else
                getBy = "product_types_id_types";

            String query = "SELECT  * FROM coupons WHERE " + getBy +" = '" + id + "'";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt("id_coupon"),resultSet.getFloat("discount"),resultSet.getInt("customers_id_customer"), resultSet.getInt("product_types_id_types")));
            }
            return coupons;
        }catch (SQLException e) {
            System.out.println(id + " not found !");
            //e.printStackTrace();
        }
        finally {
            close();
        }
        return null;
    }

    public ArrayList<Coupon> getAll() {
        try {
            ArrayList<Coupon> coupons = new ArrayList<Coupon>();
            String query = "SELECT  * FROM coupons";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                coupons.add(new Coupon(resultSet.getInt("id_coupon"),resultSet.getFloat("discount"),resultSet.getInt("customers_id_customer"), resultSet.getInt("product_types_id_types")));
            }
            return coupons;
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
