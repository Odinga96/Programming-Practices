package Main;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private int    productID;
    private double productPrice;
    private double productTotal;


    public Product(String productName, int productID, double productPrice, double productTotal) {
        this.productName = productName;
        this.productID = productID;
        this.productPrice = productPrice;
        this.productTotal = productTotal;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(double productTotal) {
        this.productTotal = productTotal;
    }
}
