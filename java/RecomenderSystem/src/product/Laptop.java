package product;

public class Laptop  extends Product{
    private String name;
    private String memory;
    private String processor;


    public Laptop(double price, int code, String name, String memory, String processor) {
        super(price, code);
        this.name = name;
        this.memory = memory;
        this.processor = processor;
    }


    public String getName() {
        return name;
    }

    public String getMemory() {
        return memory;
    }

    public String getProcessor() {
        return processor;
    }

    @Override
    public int compareTo(Product o) {
        return super.compareTo(o);
    }

    @Override
    public String toString() {
        return super.toString() +
                "  name='" + name + '\'' +
                ", memory='" + memory + '\'' +
                ", processor='" + processor + '\'' +
                '}';
    }
}
