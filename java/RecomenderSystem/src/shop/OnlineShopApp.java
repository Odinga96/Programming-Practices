package shop;

import database.ProductDatabase;
import database.PurchaseHistory;
import product.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OnlineShopApp {

    public static ProductDatabase generateSampleDatabase(){
        ProductDatabase productDatabase=new ProductDatabase();
        productDatabase.put(new Phone(1000,123,"Iphone 11","Black",5));
        productDatabase.put(new Milk(50,187,40,"Lato"));
        productDatabase.put(new Shoes(200,199,"Nike",33,"Male","Black"));
        productDatabase.put(new Bed(3000,200,6,"King Makers"));
        productDatabase.put(new Laptop(700,865,"Elite Booke 840","4GB","core i7"));

        return productDatabase;
    }


    public static ArrayList<PurchaseHistory> readPurchaseHistoryData(ProductDatabase pb, String
            filename) throws ProductNotFoundException, IOException,NumberFormatException{

        Scanner scanner=new Scanner(new File(filename));

        ArrayList<Product>  productsBought=new ArrayList<>();
        ArrayList<PurchaseHistory>  purchaseHistories=new ArrayList<>();
        boolean readAlready=false;

        while (scanner.hasNext()){
            String read = scanner.nextLine();


            if(read.length() == 3){
                readAlready=true;
               Product product=pb.get(Integer.parseInt(read));
               productsBought.add(product);
            }else if (read.length() == 1 && readAlready){
                purchaseHistories.add(new PurchaseHistory(productsBought));
                productsBought=new ArrayList<>();
            }
        }
        purchaseHistories.add(new PurchaseHistory(productsBought));


        return purchaseHistories;
    }

    public static int shop(OnlineShop shop, Scanner scanner){
        System.out.println("\nPlease select a product");

        shop.getProductListing().forEach(product -> System.out.println(product.toString()));

        return scanner.nextInt();
    }


    public static void displayCart(OnlineShop  shop){
        System.out.println("Here is your cart");

        shop.getShoppingCart().forEach(product -> System.out.println(product.toString()));
        System.out.println("The total amount you need to pay is: "+shop.amountOwing());
    }

    private static int displayRecommendations(OnlineShop shop, Scanner scanner){
        System.out.println("The following are products recommended for you");

        shop.getRecommendedProducts().forEach(product -> System.out.println(product.toString()));

        return scanner.nextInt();
    }

    public static int menu(Scanner scanner){

        System.out.println("Select an option\n" +
                "1:\tShop\n" +
                "2:\tView cart\n" +
                "3:\tPay \n" +
                "4:\tQuit");

        return scanner.nextInt();
    }



    private static class ProductNotFoundException extends Exception {

        public ProductNotFoundException() {
        }

        public ProductNotFoundException(String message) {
            super(message);
        }

        public ProductNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public ProductNotFoundException(Throwable cause) {
            super(cause);
        }

        public ProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        @Override
        public String toString() {
            return "The mentioned product is not available";
        }
    }


    public static void main(String[] args) throws IOException, ProductNotFoundException {
        ProductDatabase        database=generateSampleDatabase();
        ArrayList<PurchaseHistory>  purchaseHistories  =readPurchaseHistoryData(database,"src/database/purchaseHistoryupdated.txt");

        OnlineShop shop=new OnlineShop(database, purchaseHistories);

        new ShopGUI(shop, database);

    }
}
