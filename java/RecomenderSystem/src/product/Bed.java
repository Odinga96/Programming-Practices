package product;

public class Bed extends Product {

    private int size;
    private String manufacturer;


    public Bed(double price, int code, int size, String manufacturer) {
        super(price, code);
        this.size = size;
        this.manufacturer = manufacturer;
    }

    public int getSize() {
        return size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int compareTo(Product o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString() +
                "  size=" + size +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
