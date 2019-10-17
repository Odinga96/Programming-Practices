package product;

public class Milk extends Product {

    private double quantity;
    private String name;

    public Milk(double price, int code, double quantity, String name) {
        super(price, code);
        this.quantity = quantity;
        this.name = name;
    }


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  super.toString()+"  quantity='  "+ quantity + '\'' + " litres, name='" + name + '\''+"}";
    }
}
