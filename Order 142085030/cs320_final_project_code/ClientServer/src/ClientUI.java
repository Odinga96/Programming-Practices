import Main.Client;
import Main.Product;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientUI extends JFrame implements Serializable{

   private static MainPAnel pAnel=new MainPAnel(610,550);



    ClientUI() {
        super("Client");
        setLayout(null);
        setVisible(true);
        setSize(610,550);
        add(pAnel);

    }

    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(ClientUI::new);

        try {

            Socket socket = new Socket("localhost", 5000);

            DataOutputStream cOut = new DataOutputStream(socket.getOutputStream());
            ObjectInputStream objectIn=new ObjectInputStream(socket.getInputStream());


            pAnel.search.addActionListener(e -> {
                String type    =pAnel.client.isSelected()?"client":"product";
                String searchBy= (String) pAnel.fields.getSelectedItem();
                String value   =  pAnel.query.getText();
                String request =type+","+searchBy+","+value;

                try {
                    cOut.writeUTF(request);
                    cOut.flush();




                    String results="";
                    if (type.equals("client")){
                        ArrayList<Client> clist= (ArrayList<Client>) objectIn.readObject();

                        for (Client client: clist) {

                            results+="Name:\t"+client.getClientName()+"" +
                                    "\nAddress:\t" +client.getClientAddress()+
                                    "\nCity:\t" +client.getClientCity()+
                                    "\nEstate:\t" +client.getClientEstate()+
                                    "\nZip code:'t" +client.getClientZipCode()+
                                    "\nId:\t\t"+client.getClientID()+"\n\n";

                        }

                    }else{

                        ArrayList<Product> clist= (ArrayList<Product>) objectIn.readObject();
                        for (Product product: clist) {

                            results+="Name:\t"+product.getProductName()+"" +
                                    "\nID:\t" +product.getProductID()+
                                    "\nPrice:\t" +product.getProductPrice()+
                                    "\nTotal:\t" +product.getProductTotal()+"\n\n";

                        }
                        System.out.println(results);
                    }

                    pAnel.response.setText(results);


                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }


            });


        }catch (Exception e){e.printStackTrace();}
    }


}
