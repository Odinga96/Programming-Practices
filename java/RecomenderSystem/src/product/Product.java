package product;

import java.util.Comparator;

/**
 * This is class declaration of a product
 */
public abstract class Product implements Comparable<Product>{


    private double price;
    private int code;

    /**
     * @param price price of the product
     * @param code the unique number to identify the product
     */
    public Product(double price, int code) {
        this.price = price;
        this.code = code;
    }

    /**
     * @return returns the price of a product
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    /**
     * @param product the object to compare with our object
     * @return returns a negative 0 or positive number
     */
    @Override
    public int compareTo(Product product) { return product.getCode()- this.getCode(); }

    /**
     * @return prints out the object
     */
    @Override
    public String toString() { return code+ ":  {Type:"+this.getClass().getSimpleName()+"  price= "+price+"  "; }


    public static class PriceSorter implements Comparator<Product>{
        @Override
        public int compare(Product product, Product t1) {
            return (int)(t1.getPrice()-product.getPrice());
        }
    }
}
