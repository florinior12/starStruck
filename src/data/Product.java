package data;


import java.sql.Date;

public class Product {

    private int idProduct;
    private Date expireDate;
    private String productType;
    
    public Product( Date expireDate, String productType) {
        this.expireDate = expireDate;
        this.productType = productType;
    }
    public Product( int idProduct, Date expireDate, String productType) {
        this(expireDate, productType);
        this.idProduct = idProduct;

    }


    public int getIdProduct() {
        return idProduct;
    }


    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getProductType() {
        return productType;
    }

    public void setproductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return idProduct + "|" + expireDate + "|" + productType;
    }
}
