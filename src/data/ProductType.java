package data;

public class ProductType {

    private int idProductType;
    private String productType;
    private int stockNumber;
    private float price;

    public ProductType(int idProductType, String productType, int stockNumber, float price) {
        this.idProductType = idProductType;
        this.productType = productType;
        this.stockNumber = stockNumber;
        this.price = price;
    }

    public int getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(int idProductType) {
        this.idProductType = idProductType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }




}
