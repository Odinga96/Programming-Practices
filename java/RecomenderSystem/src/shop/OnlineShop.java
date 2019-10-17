package shop;

import database.ProductDatabase;
import database.PurchaseHistory;
import payement.PaymentSystem;
import product.Product;
import recomendation.RecommenderSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * This is our shop class
 */
public class OnlineShop  implements PaymentSystem {

    private ProductDatabase productDB;
    private ArrayList<Product> cart;
    private RecommenderSystem recommender;


    /**
     * @param productDB is the Database of the available products
     * @param purchaseHistories contains all the previous buys by customers
     */
    public OnlineShop(ProductDatabase productDB, ArrayList<PurchaseHistory> purchaseHistories) {
        this.productDB    = productDB;
        this.recommender  = new RecommenderSystem(purchaseHistories, this.productDB);
        this.cart         = new ArrayList<>();
    }


    public ArrayList<Product> getRecommendedProducts(){
        return recommender.productRecommenderAlgorithm(this.cart, 2);
    }

    public boolean addToCart(Product  product){
        if (!cart.contains(product)){
            cart.add(product);
            return true;
        }

        return false;
    }
    public ArrayList<Product> getProductListing(){
        return this.productDB.getProducts();
    }

    public ArrayList<Product> getShoppingCart(){
        Collections.sort(this.cart, new Product.PriceSorter());
        return this.cart; }

    public double amountOwing(){

        final double[] amount = {0};

        this.cart.forEach(product -> amount[0] +=product.getPrice());

        return amount[0];
    }

    public void completeTransaction(){
        if (this.cart.size()>0)
         recommender.getPurchaseHistories().add(new PurchaseHistory(cart));


        File file=new File("src/database/purchaseHistoryupdated.txt");
        try {
            PrintWriter writer=new PrintWriter(file);


            recommender.getPurchaseHistories().forEach(purchaseHistory -> {
                writer.println(new Random().nextInt(9));
                purchaseHistory.getPurchases().forEach(product -> writer.println(product.getCode()));
            });


            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
