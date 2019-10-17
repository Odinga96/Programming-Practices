
package garbogame;

import garbogame.garbo.Garbo;

import java.util.Scanner;


public class GarboGame {

    public static void main(String[] args) {
        Garbo garbo=new Garbo();
        Scanner input=new Scanner(System.in);


        int playerCount=0;   //counts the players as they are being keyed in


        //Makes sure that user enters two players as needed
        while(playerCount<2) {
            String playerName;

            System.out.println("Enter player " + ++playerCount);

            if ((playerName=input.nextLine()).length()>0)
                garbo.createPlayer(playerName);
            else
                playerCount--;
        }

        //Create all the 32 cards
             garbo.createCards();
        //Shuffle the cards
             garbo.shuffleCards();

             //Play
             garbo.play();

    }
}
