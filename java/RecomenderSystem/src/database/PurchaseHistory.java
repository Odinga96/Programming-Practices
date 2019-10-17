package database;

import product.Product;

import java.util.ArrayList;

/**
 * This Class is used to mange the customer purchase history
 */
public class PurchaseHistory {
    /**
     * Stores the codes of the products the customer purchased
     */
    private ArrayList<Product>  purchases;


    /**
     * @param purchases is a list of the product customer purchased
     */
    public PurchaseHistory(ArrayList<Product> purchases) {
        this.purchases = purchases;
    }


    /**
     * @return returns a purxhase info
     */
    public ArrayList<Product> getPurchases() { return purchases; }

    @Override
    public String toString() {
        return super.toString();
    }
}
