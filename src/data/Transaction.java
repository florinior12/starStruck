package data;

import java.sql.Date;

public class Transaction {
    private int idTransaction;
    private Date transactionDate;
    private int idCustomer;
    private int idProduct;

    public Transaction(Date transactionDate, int idCustomer, int idProduct) {
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }

    public Transaction(int idTransaction, Date transactionDate, int idCustomer, int idProduct) {
        this.idTransaction = idTransaction;
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.idProduct = idProduct;
    }


    public int getIdTransaction() {
        return idTransaction;
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

    @Override
    public String toString() {
        return idTransaction + "|" + transactionDate + "|" +  idCustomer + "|" + idProduct;
    }
}
