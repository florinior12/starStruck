package data;

import java.sql.Date;

public class Transaction {
    private int idTransaction;
    private Date transactionDate;
    private int idCustomer;
    private String productType;

    public Transaction(Date transactionDate,  String productType) {
        this.transactionDate = transactionDate;
        this.productType = productType;
    }

    public Transaction(Date transactionDate, String productType, int idCustomer) {
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.productType = productType;
    }

    public Transaction(int idTransaction, Date transactionDate, String productType, int idCustomer) {
        this.idTransaction = idTransaction;
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.productType = productType;
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

    public String getproductType() {
        return productType;
    }

    public void setproductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return idTransaction + "|" + transactionDate + "|" +  idCustomer + "|" + productType;
    }
}
