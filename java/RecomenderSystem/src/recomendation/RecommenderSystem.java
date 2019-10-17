package recomendation;

import database.ProductDatabase;
import database.PurchaseHistory;
import product.Product;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Used to recomment products to customers based on their buying behaviour
 */
public class RecommenderSystem {

    /**
     * Stores all previous customer purchase histories
     */
    private ArrayList<PurchaseHistory> purchaseHistories;
    /**
     * Stores the db of all the products on sale
     */
    private ProductDatabase productDB;
    /**
     * Arraylist to be used to store the list of products to recoment
     */
   private  ArrayList<RecommendedItem> recommend;

    /**
     * @param purchaseHistories previous customer purchases
     * @param productDB database of the products on sate
     */
    public RecommenderSystem(ArrayList<PurchaseHistory> purchaseHistories, ProductDatabase productDB) {
        this.purchaseHistories = purchaseHistories;
        this.productDB = productDB;

        recommend=new ArrayList<>();

    }

    /**
     * @return all previous purchases
     */
    public ArrayList<PurchaseHistory> getPurchaseHistories() {
        return purchaseHistories;
    }

    /**
     * This method helps in determining the number of times a product has been bough
     * @param product the product to check for
     * @return the frequency of the product being bought
     */
    public int getProductFreq(Product product){
        final int[] count = {0};

        purchaseHistories.forEach(purchaseHistory -> {
            purchaseHistory.getPurchases().forEach(product1 -> {
                if (product.compareTo(product1) == 0)
                    count[0]++;
            });
        });

        return count[0];
    }

    /**
     * @param product
     */
    public void updateProductFreq(Product product){
        this.productDB.put(product);
    }


    /**
     * @param cart The current customer purchase list
     * @param freq The number of items we want to check with
     * @return a list of items being recommended to use
     */
    public ArrayList<Product> productRecommenderAlgorithm(ArrayList<Product> cart, int freq){
        ArrayList<Product>  recommended=new ArrayList<>();

        cart.forEach(product -> purchaseHistories.forEach(pH ->{
            if (pH.getPurchases().contains(product)){
                 pH.getPurchases().forEach(product1 -> {
                     if (product1.compareTo(product) != 0){
                         int frequency=this.getProductFreq(product1);
                         if (frequency >= freq) {
                                recommend.add(new RecommendedItem(product,frequency));

                             final boolean[] alreadyContained = {false};
                                recommended.forEach(product2 -> {
                                    if (product2.compareTo(product1) == 0)
                                        alreadyContained[0] =true;
                                });

                                if (!alreadyContained[0])
                                    recommended.add(product1);
                            }
                     }
                 });
            }
        }));


        Collections.sort(recommended, new Product.PriceSorter());
        return recommended;
    }
}
