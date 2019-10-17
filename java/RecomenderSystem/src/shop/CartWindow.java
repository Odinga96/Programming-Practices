package shop;

import product.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CartWindow extends JPanel {
    private ArrayList<Product> cart;

    private JList<String> cartArea;
    private JTextArea  mycart;

    CartWindow(ArrayList<Product> cart, int width, int height, int x, int y) {
        this.cart = cart;

        setLayout(null);
        setBounds(x, y, width, height);
        setBackground(Color.yellow);

        JLabel title=new JLabel("Your Cart", SwingConstants.CENTER);
        title.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        title.setBounds(10,10,this.getWidth()-20,50);
        this.add(title);
    }

    public void populateCart(){
//        final String[] items = new String[this.cart.size()];
//        this.cart.forEach(product -> items[this.cart.indexOf(product)] =product.toString()+"\n");
//
////        if (items.length>0)
////        System.out.println(items[0]);
//
////        if (this.cartArea !=null) this.remove(this.cartArea);
//
//        cartArea=new JList<>(items);
//        cartArea.setBounds(10,55,this.getWidth(),this.getHeight()/2);
//        this.add(this.cartArea);



    }


}
