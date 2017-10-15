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

            try {
                String query;

                query = "INSERT INTO transactions (transaction_date, product_type, customers_id_customer ) VALUES (?,?,?)";
                connection = getConnection();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDate(1, transaction.getTransactionDate());
                preparedStatement.setString(2, transaction.getproductType());
                preparedStatement.setInt(3, transaction.getIdCustomer());

                preparedStatement.executeUpdate();
                System.out.println( transaction.getIdCustomer() + " bought " + transaction.getproductType());
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                close();
            }


        return false;

    }



    public Transaction getTransaction(int id) {
        try {
            String query = "SELECT  * FROM transactions WHERE id_transaction=" + id;
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                return new Transaction(resultSet.getInt("id_transaction"),resultSet.getDate("transaction_date"),resultSet.getString("product_type"), resultSet.getInt("customers_id_customer"));
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

    public ArrayList<Transaction> getByForeign(int id, boolean getByCustomer) {
        try {
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            String getBy;
            if (getByCustomer)
                getBy = "customers_id_customer";
            else
                getBy = "products_id_product";

            String query = "SELECT  * FROM transactions WHERE " + getBy +" = '" + id + "'";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(new Transaction(resultSet.getInt("id_transaction"),resultSet.getDate("transaction_date"),resultSet.getString("product_type"),resultSet.getInt("customers_id_customer")));
            }
            return transactions;
        }catch (SQLException e) {
            System.out.println(id + " not found !");
            //e.printStackTrace();
        }
        finally {
            close();
        }
        return null;
    }

    public ArrayList<Transaction> getAll() {
        try {
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            String query = "SELECT  * FROM transactions";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                transactions.add(new Transaction(resultSet.getInt("id_transaction"),resultSet.getDate("transaction_date"),resultSet.getString("product_type"),resultSet.getInt("customers_id_customer")));
            }
            return transactions;
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
