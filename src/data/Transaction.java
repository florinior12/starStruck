package data;

import java.sql.Date;

public class Transaction {
    private int idTransaction;
    private Customer customer;
    private Product product;
    private Date transactionDate;


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
}
