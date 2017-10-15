package main;

import data.*;

import java.sql.Date;
import java.util.*;

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

    public void updateStock() {
        for (ProductType productType : productTypeManager.getAll()) {
            productType.setStockNumber(productManager.get(productType.getProductType()).size());
            productTypeManager.update(productType);
        }
    }

    public boolean addCustomer(String name) {
        if (customerManager.add(new Customer(name)))
            return true;
        else return false;
    }

    public int getIdOfLastCustomer() {
        ArrayList<Customer> customers = customerManager.getAll();
        return customers.get(customers.size()-1).getIdCustomer();
    }

    public boolean addCoupon(String type, float discount, int idCustomer) {
        if (couponsManager.add(new Coupon(discount,idCustomer,productTypeManager.get(type).getIdProductType())))
            return true;
        else return false;
    }

    public boolean addTransaction(String type, int id) {
        ArrayList<Product> products = productManager.get(type);
        if (products.size()==0) {
            System.out.println("No products of type " + type);
            return false;
        }
        Date currentDate = new Date(System.currentTimeMillis());
        for(Product product : products) {   //look in products of the same type
            if (product.getExpireDate().compareTo(currentDate) > -1)    //check if it's not expired

                if (transactionManager.add(new Transaction(currentDate, type, id))) {
                    productManager.delete(product);
                    updateStock();
                    return true;
                }
                else {
                    System.out.println("Could not add transaction!");
                    return false;
                }

        }
        //if the for loop ended without any return, then all products are expired
        System.out.println("All products of type " + type + " are expired!");
        return false;

    }

    public void showHistory (int days) {
        for (Transaction transaction : transactionManager.getAll()) {
            if (transaction.getTransactionDate().compareTo(new Date(System.currentTimeMillis() - days * 60 * 60 * 24 * 1000)) > -1)
                System.out.println(transaction);
        }
    }

    public void showZeroStock() {
        if (productTypeManager.getByStock(0)!=null)
            for (ProductType productType : productTypeManager.getByStock(0)) {
                System.out.println(productType);
            }
    }

    public void showExpired() {
        Date currentDate = new Date(System.currentTimeMillis());
        ArrayList<ProductType> expiredTypes = new ArrayList<>();

        for (ProductType productType : productTypeManager.getAll()) {
            for (Product product : productManager.get(productType.getProductType())) {
                if (product.getExpireDate().compareTo(currentDate) == -1)    //check if it's expired
                    if (!expiredTypes.contains(productType))
                        expiredTypes.add(productType);

            }
        }

        for (ProductType productType:expiredTypes)
            System.out.println(productType);
    }

    public void showCheapest(int idCustomer) {
        ArrayList<ProductType> productTypes = productTypeManager.getAll();
        ArrayList<Coupon> coupons = couponsManager.getByForeign(idCustomer,true);// get customer's coupons
        Coupon goodCoupon;
        for (ProductType productType : productTypes) {
            goodCoupon = null;
            for (Coupon coupon : coupons) {
                if (coupon.getIdProductType()==productType.getIdProductType()) {
                    goodCoupon = coupon;
                }
            }

            if (goodCoupon!=null)
                productType.setPrice(productType.getPrice()*goodCoupon.getDiscount()/100);
        }
        Collections.sort(productTypes, new Comparator<ProductType>() {
            @Override
            public int compare(ProductType productType, ProductType t1) {
                return productType.getPrice().compareTo(t1.getPrice());
            }
        });
        System.out.println("Cheapest 3 products:");
        for (int i = 0;i<3;i++){
            System.out.println(productTypes.get(i));
        }
        Collections.sort(productTypes, new Comparator<ProductType>() {
            @Override
            public int compare(ProductType productType, ProductType t1) {
                return t1.getPrice().compareTo(productType.getPrice());
            }
        });
        System.out.println("Most expensive 3 products:");
        for (int i = 0;i<3;i++){
            System.out.println(productTypes.get(i));
        }
    }






}
