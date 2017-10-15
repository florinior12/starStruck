package data;

public class Coupon {
    private int idCoupon;
    private float discount;
    private int idCustomer;
    private int idProductType;

    public Coupon(float discount, int idCustomer, int idProductType) {
        this.discount = discount;
        this.idCustomer = idCustomer;
        this.idProductType = idProductType;
    }

    public Coupon(int idCoupon, float discount, int idCustomer, int idProductType) {
        this.idCoupon = idCoupon;
        this.discount = discount;
        this.idCustomer = idCustomer;
        this.idProductType = idProductType;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getIdCoupon() {
        return idCoupon;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdProductType() {
        return idProductType;
    }

    @Override
    public String toString() {
        return idCoupon + "|" + discount + "|" + idCustomer + "|" + idProductType;
    }
}
