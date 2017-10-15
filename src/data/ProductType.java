package data;

public class ProductType {

    private int idProductType;
    private String productType;
    private int stockNumber;
    private float price;

    public ProductType( String productType, float price) {
        this(productType,0,price);
    }

    public ProductType( String productType, int stockNumber, float price) {

        this.productType = productType;
        this.stockNumber = stockNumber;
        this.price = price;
    }

    public ProductType(int idProductType, String productType, int stockNumber, float price) {
        this(productType,stockNumber,price);
        this.idProductType = idProductType;

    }

    public int getIdProductType() {
        return idProductType;
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

    @Override
    public String toString() {
        return idProductType + "|" + productType + "|" + stockNumber + "|" + price;
    }
}
