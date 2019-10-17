package product;

public class Phone extends Product {

    private String name;
    private String color;
    private double screen_size;


    public Phone(double price, int code, String name, String color, double screen_size) {
        super(price, code);
        this.name = name;
        this.color = color;
        this.screen_size = screen_size;
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public double getScreen_size() {
        return screen_size;
    }

    @Override
    public int compareTo(Product o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString() +
                "  name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", screen_size=" + screen_size +
                '}';
    }
}
