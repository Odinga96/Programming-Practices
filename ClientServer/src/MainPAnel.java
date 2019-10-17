import javax.swing.*;
import java.awt.*;

class MainPAnel extends Panel {
   private int width;
   private int height;
   public JButton search;
   public JRadioButton client,products;
   public JComboBox<String> fields;

   String[] productsField  ={"Name","Id","Price","Total"};
   String[] clientFields   ={"Name","Address","City","Estate","ZipCode","Id"};

   public JTextField query;
   public JTextArea response;

    public MainPAnel(int width, int height){
        this.width=width;
        this.height=height;


        setLayout(null);
        setSize(this.width,this.height);
        setBackground(Color.ORANGE);

        Label serchFor=new Label("Search for:");
        serchFor.setBounds(20,10,80,40);

        client= new JRadioButton("Get client Data");
        client.setBounds(120,10,150,40);
        client.addActionListener(e -> {
            fields.removeAllItems();
            for (String clientField:clientFields)
                fields.addItem(clientField);
        });
        client.setSelected(true);

        products=new JRadioButton("Get product details");
        products.setBounds(320,10,200,40);
        products.addActionListener(e -> {
            fields.removeAllItems();
            for (String clientField:productsField)
                fields.addItem(clientField);
        });

        Label serchBy=new Label("Search by:");
        serchBy.setBounds(20,65,80,40);

        fields=new JComboBox<>(clientFields);
        fields.setBounds(120,65,200,40);

        search  =new JButton("Search");
        search.setBounds(400,120,200,40);


        ButtonGroup group=new ButtonGroup();
        group.add(client);
        group.add(products);

        Label input=new Label("Enter value:");
        input.setBounds(20,120,80,40);

        query=new JTextField();
        query.setBounds(120,120,250,40);

        Label responseHeader=new Label("Response");
        responseHeader.setBounds(200,180,250,40);

        response=new JTextArea();
        response.setBounds(120,220,450, 270);




        add(responseHeader);
        add(response);
        add(input);
        add(query);
        add(search);
        add(serchFor);
        add(serchBy);
        add(fields);
        add(client);
        add(products);
    }
}
