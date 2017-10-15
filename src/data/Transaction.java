package data;

import java.sql.Date;

public class Transaction {
    private int idTransaction;
    private Customer customer;
    private Product product;
    private Date transactionDate;
    //below fields are used for when we obtain a transaction from TransactionManager and we can access id's only
    //we will return the id's to the main class to get the customer and product objects
    private int idCustomer;
    private int idProduct;

    public Transaction(int idTransaction, Date transactionDate, int idCustomer, int idProduct) {
        this.idTransaction = idTransaction;
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public Transaction(Customer customer, Product product, Date transactionDate) {
        this.customer = customer;
        this.product = product;
        this.transactionDate = transactionDate;
    }

    public Transaction(int idTransaction, Customer customer, Product product, Date transactionDate) {
        this(customer, product, transactionDate);
        this.idTransaction = idTransaction;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
}
