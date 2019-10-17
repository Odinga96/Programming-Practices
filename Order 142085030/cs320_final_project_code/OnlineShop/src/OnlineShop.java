//Full name:
//
//Tutorial Group:
//Declaration:


import java.util.Scanner;

public class OnlineShop {

    public static void main(String[] args) {

        //Array to contain the list of items
        String[] items=new String[100];

        //Quantity
        int[] quantity=new int[100];

        //prices
        double[]  price=new double[100];

        //Scanner for picking input
        Scanner input=new Scanner(System.in);

//        Welcome note
        System.out.println("Welcome to ABC online service");
        System.out.println("-----------------------------");

//        Prompting for the 3 items to be added
        System.out.println("Enter 3 items to be sold");


        //Count helps in keeping track of the items input
        int count=0;

        //pick 3 items
        while (count<3){
            System.out.print((count+1)+".\t");
            items[count++]=input.nextLine();
        }

        System.out.println("\n\n");

//        reset count
        count=0;

//        Pick the prices
        while(count<3){
            System.out.print("Enter the quantities and price of "+items[count]+":");
            quantity[count]=input.nextInt();
            price[count++]=input.nextDouble();
        }

        System.out.println("\n\n");

        System.out.println("Summary of items");
        System.out.println("----------------");
        System.out.println();

        System.out.printf("%s%50s%30s\n","Item","Quantity","Price");
        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");

        count=0;


        //displaying data
        while (count<3){
            int spacing1=50-items[count].length();
            int spacing2=40;
            System.out.printf("%s%"+spacing1+"d%"+spacing2+"f\n",items[count],quantity[count],price[count++]);
        }

        System.out.println("\n");

        //swaping the first and second item
//            swapping items
        String tempItem=items[0];

           //change item 0 to item 1
        items[0]=items[1];

           //change item 1 to value of item 0
        items[1]=tempItem;


//        swaping quantity
        int  tempQuantity=quantity[0];

        //change quantity 0 to quantity 1
        quantity[0]=quantity[1];

        //change quantity 0 to quantity 1
        quantity[1]=tempQuantity;


//        swaping prices

        double  tempPrice=price[0];

        //change quantity 0 to quantity 1
        price[0]=price[1];

        //change quantity 0 to quantity 1
        price[1]=tempPrice;



        System.out.println("Summary of items after swaps");
        System.out.println("----------------------------");

        //re displaying the data

        System.out.printf("%s%50s%30s\n","Item","Quantity","Price");
        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");

        //reseting count
        count=0;

        while (count<3){
            int spacing1=50-items[count].length();
            int spacing2=40;
            System.out.printf("%s%"+spacing1+"d%"+spacing2+"f\n",items[count],quantity[count],price[count++]);
        }


        System.out.println("\n\n");
        //Customers placing order
        count=0;

        System.out.println("Please place your order");
        System.out.println("-----------------------");

        int[] order=new int[100];

        while (count<3){
            System.out.print("No of  "+items[count]+":  ");
            order[count]=input.nextInt();
            quantity[count]-=order[count++];
        }

        System.out.println("\n\n");





        //Displaying customer items
        System.out.println("Summary of your order");
        System.out.println("---------------------");
        System.out.println();


        //displaying data
        System.out.printf("%s%50s%30s\n","Item","Quantity","Cost");
        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");

        //reseting count
        count=0;

        double subtatal=0;
        while (count<3){
            int spacing1=50-items[count].length();
            int spacing2=40;
            System.out.printf("%s%"+spacing1+"d%"+spacing2+"f\n",items[count],order[count],(price[count]*order[count]));

             subtatal+=(price[count]*order[count++]);
        }

        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");

        double GST   =(7.0/100.0)*subtatal;
        double total =subtatal+GST;

        System.out.printf("%s%80f\n","Subtotal:",subtatal);
        System.out.printf("%s%80f\n","GST (7%):",GST);
        System.out.printf("%s%80s\n","Total cost:",total);
        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");
        System.out.println("\n");


        System.out.println("Balance report");
        System.out.println("--------------");



        //re displaying the data

        System.out.printf("%s%50s%30s\n","Item","Quantity","Price");
        System.out.println("-----------------------------------------------" +
                "---------------------------------------------");

        System.out.println();
        //reseting count
        count=0;

        while (count<3){
            int spacing1=50-items[count].length();
            int spacing2=40;
            System.out.printf("%s%"+spacing1+"d%"+spacing2+"f\n",items[count],quantity[count],price[count++]);
        }

    }
}
