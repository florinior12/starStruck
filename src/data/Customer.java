package data;

public class Customer {
    private int idCustomer;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public Customer(int id, String name) {
        this(name);
        this.idCustomer = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
