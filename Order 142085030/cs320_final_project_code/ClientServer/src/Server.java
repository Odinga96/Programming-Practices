import Main.Client;
import Main.Product;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static ArrayList<Product> productsList =new ArrayList<>();
    public static ArrayList<Client>  clientList   =new ArrayList<>();


    public static void loadData(String datafile){
        File data=new File(datafile);

        try {
            Scanner scanner=new Scanner(data);

            while (scanner.hasNextLine()){
                String input=scanner.nextLine();

                if ( !input.isEmpty() ){
                    char   first=input.charAt(0);

                    if(first != '#'){
                        String[] field=input.split(",");

                        if(datafile.equals("products.txt")){
                            Product product=new
                                    Product(
                                            field[0],Integer.parseInt(field[1].trim()),Double.parseDouble(field[2].trim()),
                                             Double.parseDouble(field[3].trim()));
                            productsList.add(product);


                        }else if(datafile.equals("client.txt")){
                            Client client
                                    =new Client(field[0],field[1],field[2],field[3],
                                    Integer.parseInt(field[4].trim()),
                                    Integer.parseInt(field[5].trim()));
                            clientList.add(client);

                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<Client> getClientData(String searchBy, String searchString){
        ArrayList<Client> satisfiedRequest=new ArrayList<>();
        for (Client client:clientList) {
            if (searchBy.equalsIgnoreCase("Name") && client.getClientName().equals(searchString))
                satisfiedRequest.add(client);
            if (searchBy.equalsIgnoreCase("Address") && client.getClientAddress().equals(searchString))
                satisfiedRequest.add(client);
            if (searchBy.equalsIgnoreCase("City") && client.getClientCity().equals(searchString))
                satisfiedRequest.add(client);
            if (searchBy.equalsIgnoreCase("Estate") && client.getClientEstate().equals(searchString))
                satisfiedRequest.add(client);
            if (searchBy.equalsIgnoreCase("ZipCode") && client.getClientZipCode()==Integer.parseInt(searchString))
                satisfiedRequest.add(client);
            if (searchBy.equalsIgnoreCase("ID") && client.getClientID() == Integer.parseInt(searchString))
                satisfiedRequest.add(client);

        }

        return satisfiedRequest;
    }

    public static ArrayList<Product> getProductDetails(String searchBy, String searchString){
        ArrayList<Product> satisfiedRequest=new ArrayList<>();
        for (Product product:productsList) {
            if (searchBy.equalsIgnoreCase("Name") && product.getProductName().equals(searchString))
                satisfiedRequest.add(product);
            if (searchBy.equalsIgnoreCase("ID") && product.getProductID() == Integer.parseInt(searchString))
                satisfiedRequest.add(product);
            if (searchBy.equalsIgnoreCase("price") && product.getProductPrice() == Double.parseDouble(searchString))
                satisfiedRequest.add(product);
            if (searchBy.equalsIgnoreCase("Total") && product.getProductTotal() == Double.parseDouble(searchString))
                satisfiedRequest.add(product);


        }

        return satisfiedRequest;
    }




    public static void main(String [] args){
       loadData("client.txt");
        loadData("products.txt");

        int port = 5000;

        try{

            ServerSocket ss = new ServerSocket(port);

            do{

                try{


                    final Socket client = ss.accept();
                    final ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                    InputStreamReader reader=new InputStreamReader(client.getInputStream());
                    DataInputStream datain=new DataInputStream(client.getInputStream());

                    final BufferedReader in = new BufferedReader(reader);

                    new Thread(() -> {
                        try{
                          while(true){

//                              System.out.println(datain.readUTF());
                              String[] request=datain.readUTF().split(",");
                               System.out.println(request[0]);
                              if (request[0].equalsIgnoreCase("client"))
                                  out.writeObject(getClientData(request[1],request[2]));
                              else if(request[0].equalsIgnoreCase("product"))
                                  out.writeObject(getProductDetails(request[1],request[2]));

                          }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }).start();
                }catch(Exception e){e.printStackTrace();}
            }while (true);
        }catch(Exception e){e.printStackTrace();}
    }
}
