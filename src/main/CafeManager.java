package main;

import data.*;

import java.sql.Date;

public class CafeManager {
    private CouponsManager couponsManager;
    private ProductTypeManager productTypeManager;
    private ProductManager productManager;
    private CustomerManager customerManager;
    private TransactionManager transactionManager;

    public CafeManager() {
        couponsManager = new CouponsManager();
        productTypeManager = new ProductTypeManager();
        customerManager = new CustomerManager();
        transactionManager = new TransactionManager();
        productManager = new ProductManager();
    }

    public boolean addProducts(String type, int amount) {
        for (int i = 0;i<amount;i++)
            if(!productManager.add(new Product(new Date(System.currentTimeMillis() + 2*60 * 60 * 24 * 1000), type)))
                return false;
        return true;

    }
}
