package recomendation;

import product.Product;

/**
 * Stores data of the item recomended
 */
public class RecommendedItem {

    private Product product;
    private int freq;

    public RecommendedItem(Product product, int freq) {
        this.product = product;
        this.freq = freq;
    }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }

    public int getFreq() { return freq; }

    public void setFreq(int freq) { this.freq = freq; }
}
