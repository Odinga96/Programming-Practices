package shop;

import database.ProductDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopGUI extends JFrame {
    private JTextArea cart;
    private JTextArea products;

    private JLabel productsTittle;

    private JTextField amountOwning;

    private OnlineShop onlineShop;
    private static final int width=1200;
    private static final int height=700;

    public ShopGUI(OnlineShop shop, ProductDatabase database){
        this.onlineShop=shop;


        JPanel mainWindow = new JPanel();
        mainWindow.setLayout(null);
        mainWindow.setSize(width,height);
        mainWindow.setBackground(Color.CYAN);

        JPanel titlePanel=new JPanel();
        titlePanel.setBounds( 10,3, mainWindow.getWidth()-30,100);
        mainWindow.add(titlePanel);
        JLabel title=new JLabel("Online Shopping Store");
        title.setBounds(15,10,titlePanel.getWidth(),titlePanel.getHeight());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Sans Serif", Font.BOLD, 60));
        titlePanel.add(title);


        JPanel cartWindow = new JPanel();
        cartWindow.setLayout(null);
        cartWindow.setBounds(mainWindow.getWidth()/2,105,(mainWindow.getWidth()/2)-20,(mainWindow.getHeight())-170);
        mainWindow.add(cartWindow);


        JPanel productsPanel = new JPanel();
        productsPanel.setBounds(10,105,(mainWindow.getWidth()/2)-20,(mainWindow.getHeight()-170));
        productsPanel.setLayout(null);
        mainWindow.add(productsPanel);

        productsTittle=new JLabel("Available Products", SwingConstants.CENTER);
        productsTittle.setFont(new Font("Sans Serif", Font.BOLD, 20));

        productsTittle.setBounds(10,10, productsPanel.getWidth()-20,50);
        productsPanel.add(productsTittle);

        products =new JTextArea(10, 10);
        products.setBounds(10,60,(productsPanel.getWidth())-20,(int)(productsPanel.getHeight()/1.6));
        products.setEditable(false);
        productsPanel.add(products);

        setProducts();


        JLabel product_code_l=new JLabel("Enter product code", SwingConstants.LEFT);
        product_code_l.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        product_code_l.setBounds(10,(int)(productsPanel.getHeight()/1.5)+60,200,50);
        productsPanel.add(product_code_l);

        JTextField productCode=new JTextField();
        productCode.setBounds((productsPanel.getWidth()-(productsPanel.getWidth())/3)-20,(int)(productsPanel.getHeight()/1.5)+60,(productsPanel.getWidth())/3,50);
        productsPanel.add(productCode);


        //Button to add to cart
        JButton add_To_Cart=new JButton("Add to cart");
        add_To_Cart.setBounds((productsPanel.getWidth()-(productsPanel.getWidth())/3)-60,(int)(productsPanel.getHeight()/1.5)+120,((productsPanel.getWidth())/3)+40,50);
        productsPanel.add(add_To_Cart);
        add_To_Cart.addActionListener(actionEvent -> {


            if (productCode.getText().length() == 3) {
                try {
                    int code=Integer.parseInt(productCode.getText());
                    shop.addToCart(database.get(code));
                    productCode.setText("");
                    setProducts();
                    setCart();

                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
                System.out.println("The String input is correct");
            }else
                System.out.println("Please input a correct string");
        });


        JButton quit=new JButton("Quit");
        quit.setBounds(20,(int)(productsPanel.getHeight()/1.5)+120,((productsPanel.getWidth())/3)+40,50);
        productsPanel.add(quit);
        quit.addActionListener(actionEvent -> { System.exit(0); });



        JLabel carttitle=new JLabel("Your Cart", SwingConstants.CENTER);
        carttitle.setFont(new Font("Sans Serif", Font.BOLD, 20));

        carttitle.setBounds(10,10, cartWindow.getWidth()-20,50);
        cartWindow.add(carttitle);


        cart =new JTextArea(10, 10);
        cart.setBounds(10,60,(cartWindow.getWidth())-20,(int)(cartWindow.getHeight()/1.6));
        cart.setEditable(false);
        cartWindow.add(cart);


        JLabel amountOwningLabel=new JLabel("Total amount:", SwingConstants.LEFT);
        amountOwningLabel.setFont(new Font("Sans Serif", Font.PLAIN, 16));

        amountOwningLabel.setBounds(10,(int)(cartWindow.getHeight()/1.5)+60,200,50);
        cartWindow.add(amountOwningLabel);


        amountOwning=new JTextField();
        amountOwning.setBounds((cartWindow.getWidth()-(cartWindow.getWidth())/3)-20,(int)(cartWindow.getHeight()/1.5)+60,(cartWindow.getWidth())/3,50);
        amountOwning.setText("0.00");
        amountOwning.setEditable(false);
        cartWindow.add(amountOwning);


        JButton pay=new JButton("Pay");
        pay.setBounds((cartWindow.getWidth()-(cartWindow.getWidth())/3)-60,(int)(cartWindow.getHeight()/1.5)+120,((cartWindow.getWidth())/3)+40,50);
        cartWindow.add(pay);
        pay.addActionListener(actionEvent -> {
            shop.completeTransaction();
            System.exit(0);
        });


        this.getContentPane().add(mainWindow, BorderLayout.CENTER);
        this.setSize(width,height);
        this.setResizable(false);
        this.setVisible(true);
    }


    public void setProducts(){
        products.setText("Code");
       if (onlineShop.getShoppingCart().size() == 0){
           onlineShop.getProductListing().forEach(product -> products.append("\n"+product.toString()));
       }else {
           onlineShop.getRecommendedProducts().forEach(product -> products.append("\n"+product.toString()));
           productsTittle.setText("Recommended products");
       }
    }


    public void setCart(){
        cart.setText("");
            onlineShop.getShoppingCart().forEach(product -> cart.append("\n"+product.toString()));

            amountOwning.setText(onlineShop.amountOwing()+"");
    }


}
