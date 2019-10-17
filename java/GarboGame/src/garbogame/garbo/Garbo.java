package garbogame.garbo;

import garbogame.card.Card;
import garbogame.card.Suit;
import garbogame.player.Player;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Garbo {
    private Player player1;
    private Player player2;

    private Card[][] table;
    private LinkedList<Card> cards;
    private Stack<Card> shuffledCards;

    private Card[][] level2table;


    private int count=0;
    public void createPlayer(String name){

        if (count==0) {
            player1 = new Player(null,0);
            player1.setName(name);
            count++;
        } else{
            player2=new Player(null,0);
            player2.setName(name);
        }
    }

    public void createCards(){
        String[] cardNames={"Jack","King","Seven","Eight","Nine","Ten","Ace","Queen"};
        String[] cardSuits={"spades","hearts","clubs","diamonds"};

        //Create all the suits and add them to a list
        int rank=0;
        LinkedList<Suit> suitsList=new LinkedList<>();
        for (String suit:cardSuits) {
            suitsList.add(new Suit(suit,++rank));
        }

        //create all the cards and add them to a list
        cards=new LinkedList<>();
        for (String cardName:cardNames) {
            rank++;

            //loops through the list assigning 4 suits per card
            for (Suit suit:suitsList) {
                cards.add(new Card(rank,suit,cardName));
            }

        }

        //Used to display all the cards and their rank and specific suit

//        for (Card card:cards) {
//            System.out.println(card.getName()+"\t"+card.getRank()+"\t"+card.getSuit().getName()+"\t"+card.getSuit().getRank());
//            System.out.println(card.getName()+" of "+card.getSuit().getName());
//        }


    }

    public void shuffleCards(){
        //This method will help with reordering the cards
        //We generate random values and assign them as indices wich we will use to get the specified card from the initial list

        Random random=new Random();
        LinkedList<Integer> list=new LinkedList<>(); //will contain the generated random numbers between 0-31
        for (int i = 0; i < 32; i++) {
            int num=random.nextInt(32);
            if (!(list.contains(num))) {
                list.add(num);

            }else
                i--;
            //reduce the index if the number generated is already in the list

        }


//We now put the cards in a stack. Only the top most card can be dealt
        shuffledCards=new Stack<>();
        for (int index:list) {
            shuffledCards.push(cards.get(index));
        }

        //prints shuffled cards
//        int i=0;
//        for (Card card:shuffledCards) {
//            System.out.println(++i +"\t"+card.getName()+" of "+card.getSuit().getName());
//        }

    }

    private int dealing_count=0;
    private void dealings(Scanner scanner){
        //prompt for player to deal cards to
        int count=1;

        //Dealing of cards can only be stopped once all the two players have received the needed amount of cards
        while(count<2) {
            System.out.println("Enter:\n\t1 to deal cards to:\t" + player1.getName() + "\n\t2 to deal cards to:\t" + player2.getName());
            int choosePlayer=scanner.nextInt();

            //If dealing is to be done to the firsts player
            if (choosePlayer == 1) {
                System.out.println("enter number of cards to deal to " + player1.getName());

                int cards = scanner.nextInt();  //Read the number of cards to deal to a player

                //In case of first dealing
                if ((dealing_count + 1) == 1){
                    //Only 4 cards can be assigned to in the first dealing
                        if (player1.getCards().size()<5 && cards<5-player1.getCards().size()) {

                            for (int i=0; i<cards; i++)
                                player1.getCards().add(shuffledCards.pop());

                            //check if both players have 4 cards if not we continue asking the player to deal
                            if (player1.getCards().size()==4 && player2.getCards().size()==4)
                                count++;
                        } else  //display warning if cards more than 4
                            System.out.println(player1.getName()+" has "+player1.getCards().size()+" cards you can only deal "+(4-player1.getCards().size()));
                }

                          //In case of second dealing
                else if ((dealing_count + 1) == 2){
                    if (player1.getCards().size()<9 && cards<9-player1.getCards().size()){

                        for (int i=0; i<cards; i++)
                            player1.getCards().add(shuffledCards.pop());

                        //check if both players have 8 cards
                        if (player1.getCards().size()==8 && player2.getCards().size()==8)
                            count++;
                    }
                    else
                        System.out.println(player1.getName()+" has "+player1.getCards().size()+" cards you can only deal "+(8-player1.getCards().size()));
                }

                   //In case of third dealing
                else if((dealing_count + 1) == 3) {
                    if (player1.getCards().size() < 9 && cards < 9 - player1.getCards().size()) {
                        for (int i = 0; i < cards; i++)
                            player1.getCards().add(shuffledCards.pop());

                        //check if both players have 8 cards
                        if (player1.getCards().size() == 8 && player2.getCards().size() == 8)
                            count++;
                    } else
                        System.out.println(player1.getName() + " has " + player1.getCards().size() + " cards you can only deal " + (8 - player1.getCards().size()));
                }



                //If dealing is to be done to the second player
            }else if (choosePlayer == 2) {
                System.out.println("enter number of cards to deal to " + player2.getName());

                int cards = scanner.nextInt();

                //First dealing
                if ((dealing_count + 1) == 1) {
                    if (player2.getCards().size() < 5 && cards < 5 - player2.getCards().size()) {
                        for (int i = 0; i < cards; i++)
                            player2.getCards().add(shuffledCards.pop());

                        //check if both players have 4 cards
                        if (player1.getCards().size() == 4 && player2.getCards().size() == 4)
                            count++;

                    } else
                        System.out.println(player1.getName() + " has " + player2.getCards().size() + " cards you can only deal " + (4 - player2.getCards().size()));


                    //Second dealing
                } else if ((dealing_count + 1) == 2) {
                    if (player2.getCards().size() < 9 && cards < 9 - player2.getCards().size()) {
                        for (int i = 0; i < cards; i++)
                            player2.getCards().add(shuffledCards.pop());

                        //check if both players have 8 cards
                        if (player1.getCards().size() == 8 && player2.getCards().size() == 8)
                            count++;

                    } else
                        System.out.println(player2.getName() + " has " + player2.getCards().size() + " cards you can only deal " + (8 - player2.getCards().size()));


                    //Third dealing
                } else if ((dealing_count + 1) == 3) {
                    if (player2.getCards().size() < 9 && cards < 9 - player2.getCards().size()) {
                        for (int i = 0; i < cards; i++)
                            player2.getCards().add(shuffledCards.pop());

                        //check if both players have 8 cards
                        if (player1.getCards().size() == 8 && player2.getCards().size() == 8)
                            count++;
                    } else
                        System.out.println(player2.getName() + " has " + player1.getCards().size() + " cards you can only deal " + (8 - player1.getCards().size()));
                }

            }
        }

        dealing_count++;
    }

    private int round=0,level=1;

    private void controlGame(Player player){

        Scanner input=new Scanner(System.in);
        int index=-1;
        //print the cards of the player to enable them choose one from the dealt
        while(index==-1) {
            System.out.println("Player turn:__________ "+player.getName()+"___________\n\n\tChoose one from the cards you have below:\n\n");
            for (Card card : player.getCards())
                System.out.println("\t\t"+ ++index + ": " + card.getName() + " of " + card.getSuit().getName());

            int choice = input.nextInt();
            index=(choice>=0 && choice < player.getCards().size())?choice:-1;
        }
        System.out.println("\n");

        System.out.println("Choose the cell you want to place the card");
        System.out.println("0\t\t\t\t\t\t\t\t\t1\t\t\t\t\t\t\t\t\t2\t\t\t\t\t\t\t\t\t3\t\t\t\t\t\t\t\t\t\t\t\t");

        for (int i = 0; i <4 ; i++) {

            System.out.print(i+"\t\t");
            for (int j = 0; j < 4; j++) {
                String empty="empty";
                if (table[j][i]==null) {
                    if (j !=0)
                    System.out.printf("%" + "40s", empty);
                    else
                        System.out.print(empty);
                }
                else {
                    String indicate_Use=(level==2 && level2table[j][i] !=null)?"(used)":"";
                    String name=table[j][i].getName()+" of " + table[j][i].getSuit().getName()+" "+indicate_Use;
                    if (j !=0)
                    System.out.printf("%" + (40 - table[j][i].getName().length()) + "s ",name );
                    else
                        System.out.print(name);
                }
            }

            System.out.println();
        }

        boolean placed=false;

        while (!placed) {
            //insert the column
            System.out.println("select column");
            int column = input.nextInt();

            //insert row
            System.out.println("select row");
            int row = input.nextInt();

            //placing cards in the table
            //check if round is the first round
//            if (level == 1) {
                if (++round == 1) {
                    table[column][row] = player.getCards().remove(index);
                    placed=true;
                }
                //if not the first round then we can't place the card wherever we like but do according to the list
                else if (table[column][row] == null || (level2table[column][row] == null && level==2)){
                    if (row != 0 && row != 3 && column != 0 && column != 3) {

                        //places the cards only at the grids that are not in at the edges of the table
                        if (table[column - 1][row - 1] != null || table[column][row - 1] != null || table[column + 1][row - 1] != null
                                || table[column - 1][row] != null || table[column + 1][row] != null
                                || table[column - 1][row + 1] != null || table[column][row + 1] != null || table[column + 1][row + 1] != null) {

                            acceptCard(player,index,column,row);
                            placed=true;
                        }
                        //place Cards to squares at the edge of the table
                    } else {
                        if (row == 0 && (column > 0 && column < 3)) {
                            if (table[column - 1][row] != null || table[column + 1][row] != null
                                    || table[column - 1][row + 1] != null || table[column + 1][row + 1] != null
                                    || table[column][row + 1] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }

                        } else if (row == 3 && (column > 0 && column < 3)) {
                            if (table[column - 1][row] != null || table[column + 1][row] != null
                                    || table[column - 1][row - 1] != null || table[column + 1][row - 1] != null
                                    || table[column][row - 1] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                        } else if (column == 0 && (row > 0 && row < 3)) {
                            if (table[column][row - 1] != null || table[column][row + 1] != null
                                    || table[column + 1][row - 1] != null || table[column + 1][row + 1] != null
                                    || table[column + 1][row] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                        } else if (column == 3 && (row > 0 && row < 3)) {
                            if (table[column][row - 1] != null || table[column][row + 1] != null
                                    || table[column - 1][row - 1] != null || table[column - 1][row + 1] != null
                                    || table[column - 1][row] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                        } else if (column == 0 && row == 0){
                            if (table[column][row + 1] != null || table[column + 1][row] != null || table[column + 1][row + 1] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                        } else if (column == 3 && row == 0){
                            if ((table[column][row + 1] != null || table[column - 1][row] != null || table[column - 1][row + 1] != null)) {
                                acceptCard(player,index,column,row);
                                placed=true;

                                }
                        }else if (column == 0 && row == 3) {
                            if ((table[column][row - 1] != null || table[column + 1][row] != null || table[column + 1][row - 1] != null)) {
                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                        }else if (column == 3 && row == 3) {
                            if (table[column][row - 1] != null || table[column - 1][row] != null || table[column - 1][row - 1] != null) {

                                acceptCard(player,index,column,row);
                                placed=true;
                            }
                                    }

                    }

                }else
                    System.out.println("The cell is occupied");
//            }

        }




    }

    public void play(){

        //Initialize the grid to null values
        table=new Card[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < 4; j++) {
                table[j][i]=null;
            }
        }

        //Initialize the grid to null values
        level2table=new Card[4][4];
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j < 4; j++) {
                level2table[j][i]=null;
            }
        }

        int togglePlayer=0;

        while (level<3){



            if (player1.getCards().size()==0 && level==1)
                dealings(new Scanner(System.in));

            if (togglePlayer==0) {
                controlGame(player2);
                togglePlayer=1;
            }else {
                controlGame(player1);
                togglePlayer=0;
            }

            if ((dealing_count==2 || dealing_count==3) &&
                    (player1.getCards().size()==4 || player1.getCards().size() ==0 ) &&
                    (player2.getCards().size()==4 || player2.getCards().size() ==0 )){


                if (level != 2 || (player2.getCards().size() == 0 && player1.getCards().size() == 0)) {
                    System.out.println("\n\n");
                    System.out.println("\t-------------------------------RESULTS-------------------------------");
                    System.out.println("\t"+player1.getName()+" Scored : "+player1.getScore()+"\n\t"+player2.getName()+" sored: "+player2.getScore());

                    level++;
                    if (level !=3) {
                        System.out.println("\n\n");
                        System.out.println("\t-------------------------------LEVEL   :" + level + "-------------------------------");

                        if (level == 2)
                            dealings(new Scanner(System.in));

                        if (player2.getScore() > player1.getScore())
                            togglePlayer = 0;
                        else if (player1.getScore() > player2.getScore())
                            togglePlayer = 1;
                    }

                    if (level==3){
                        System.out.println();

                        String winner="DRAW";
                        if (player1.getScore() != player2.getScore())
                        winner=(player1.getScore()>player2.getScore())?player1.getName():player2.getName();

                        System.out.println("*****************************************");
                        System.out.println("**WINNER: ****      "+winner.toUpperCase()+"         **");
                        System.out.println("*****************************************");
                    }

                }
            }
        }
    }

    private void calculateScore(int column,int row,Player player){

        //Calculate row forward
        for (int i = row; i < 3; i++) {
            if (table[column][i+1] !=null && table[column][i+1].getName().equals(table[column][row].getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getRank());
            }

        }

        //Calculate row backward
        for (int i = row; i >0; i--) {
            if (table[column][i-1] !=null && table[column][i-1].getName().equals(table[column][row].getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getRank());
            }
        }

        //Calculate columns forward
        for (int i = column; i < 3; i++) {
            if (table[i+1][row] !=null && table[i+1][row].getName().equals(table[column][row].getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getRank());
            }
        }

        //Calculate columns backward
        for (int i = column; i >0; i--) {
            if (table[i-1][row] !=null && table[i-1][row].getName().equals(table[column][row].getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getRank());
            }
        }



        if (row != 0 && row != 3 && column != 0 && column != 3) {

            //places the cards only at the grids that are not in at the edges of the table
            if (table[column - 1][row - 1] != null && table[column - 1][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }


            if (table[column][row - 1] != null && table[column][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }


            if (table[column + 1][row - 1] != null  && table[column + 1][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }

            if (table[column - 1][row] != null && table[column - 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                            player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                        else
                            player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }

            if(table[column + 1][row] != null && table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                   if (player==player1)
                      player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                     else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }


            if (table[column - 1][row + 1] != null && table[column - 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }


            if(table[column][row + 1] != null && table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }



            if(table[column + 1][row + 1] != null  && table[column + 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                if (player==player1)
                    player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                else
                    player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
            }
            //place Cards to squares at the edge of the table
        } else {
            if (row == 0 && (column > 0 && column < 3)) {

                if(table[column - 1][row]     != null &&  table[column - 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row]     != null &&  table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row + 1] != null &&  table[column - 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row + 1] != null &&  table[column + 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column][row + 1]     != null &&  table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            } else if (row == 3 && (column > 0 && column < 3)) {

                if(table[column - 1][row]     != null &&  table[column - 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row]     != null &&  table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row - 1] != null &&  table[column - 1][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row - 1] != null &&  table[column + 1][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column][row - 1]     != null &&  table[column][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }


            } else if (column == 0 && (row > 0 && row < 3)) {
                if(table[column][row - 1]     != null &&  table[column][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column][row + 1]     != null &&  table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row - 1] != null &&  table[column + 1][row-1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row + 1] != null &&  table[column + 1][row+1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row]     != null &&  table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            } else if (column == 3 && (row > 0 && row < 3)) {
                if(table[column][row - 1]     != null  &&  table[column][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column][row + 1]     != null  &&  table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row - 1] != null  &&  table[column - 1][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row + 1] != null  &&  table[column - 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row]     != null  &&  table[column - 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            } else if (column == 0 && row == 0){
                if(table[column][row + 1]     != null  &&  table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row]     != null  &&  table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row + 1] != null  &&  table[column + 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            } else if (column == 3 && row == 0){
                if(table[column][row + 1]     != null  &&  table[column][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row]     != null  &&  table[column - 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row + 1] != null  &&  table[column - 1][row + 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            }else if (column == 0 && row == 3) {
                if(table[column][row - 1]     != null  &&  table[column][row - 1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row]     != null  &&  table[column + 1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column + 1][row - 1] != null  &&  table[column + 1][row -1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            }else if (column == 3 && row == 3) {
                if(table[column][row - 1]     != null  &&  table[column][row -1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row]     != null  &&  table[column -1][row].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }
                if(table[column - 1][row - 1] != null  &&  table[column -1][row -1].getSuit().getName().equals(table[column][row].getSuit().getName())){
                    if (player==player1)
                        player2.setScore(player2.getScore()+table[column][row].getSuit().getRank());
                    else
                        player1.setScore(player1.getScore()+table[column][row].getSuit().getRank());
                }

            }

        }

    }

    private void imposePenalty(Player player,int penalty){
        if (player==player1){
            player2.setScore(player2.getScore()+penalty);
        }else
            player1.setScore(player1.getScore() +penalty);
    }

    private void acceptCard(Player player,int index, int column, int row){
        Card card=player.getCards().remove(index);

        if (level==2) {
            level2table[column][row] = card;
            if (table[column][row].getName().equals(card.getName()))
                imposePenalty(player,table[column][row].getRank()*10);
        }
        table[column][row] = card;
        calculateScore(column,  row, player);
    }

}
