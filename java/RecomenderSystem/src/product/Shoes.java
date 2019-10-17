package product;

public class Shoes extends Product {
    private String brand;
    private int size;
    private String sex;
    private String color;


    public Shoes(double price, int code, String brand, int size, String sex, String color) {
        super(price, code);
        this.brand = brand;
        this.size = size;
        this.sex = sex;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public String getSex() {
        return sex;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString() +
                "  " +
                "  brand='" + brand + '\'' +
                ", size=" + size +
                ", sex='" + sex + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public int compareTo(Product product) {
        return super.compareTo(product);
    }
}
