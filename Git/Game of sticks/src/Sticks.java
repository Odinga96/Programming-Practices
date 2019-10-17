import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Sticks {


    private int no_of_sticks;

    Sticks(int no_of_sticks) {
        this.no_of_sticks = no_of_sticks;
    }

    public void playAgainstFriend(Scanner scanner){
        System.out.print("Enter name of player 1:\n");
        String player1=scanner.next();


        System.out.print("Enter name of player 2:\n");
        String player2=scanner.next();

        int num_of_picks=0;
        int turn=0;

        while (no_of_sticks>0) {


            if (turn==0){

                while (num_of_picks<1 || num_of_picks>3) {
                    System.out.println("\n\nThere are "+no_of_sticks+" sticks remaining on the board");

                    System.out.print(player1 + ": How many sticks do you take (1-3)?");

                    num_of_picks = scanner.nextInt();

                    if (num_of_picks > 0 && num_of_picks < 4) {

                        no_of_sticks -= num_of_picks;

                    } else
                        System.out.println("you can only pick in the range (1-3)");

                    System.out.println(no_of_sticks);
                    if (no_of_sticks <= 0)
                        System.out.println(player1 + " , you loose");
                }
                turn = 1;
                num_of_picks=0;

            }else{
                while (num_of_picks<1 || num_of_picks>3) {
                    System.out.println("\n\nThere are "+no_of_sticks+" remaining on the board");
                    System.out.println(player2 + ":\t How many sticks do you take (1-3)?");

                    num_of_picks = scanner.nextInt();

                    if (num_of_picks > 0 && num_of_picks < 4) {
                        no_of_sticks -= num_of_picks;
                    } else
                        System.out.println("you can only pick in the range (1-3)");

                    if (no_of_sticks <= 0)
                        System.out.println(player2 + " , you loose");
                }

                turn = 0;
                num_of_picks=0;
            }
        }

    }

    public void playAgainstComputer(Scanner scanner){

        Hat[] AIHats=new Hat[no_of_sticks];
        int  initial_sticks=no_of_sticks;

        //put the balls into the hats for A1

        for (int i=0; i<AIHats.length; i++) {
            AIHats[i]=new Hat();

            AIHats[i].hats.add(new Ball(1));
            AIHats[i].hats.add(new Ball(2));
            AIHats[i].hats.add(new Ball(3));

            AIHats[i].setChosenBall(new Ball(0));

        }

        //Random to be used by the AI
        Random random=new Random();


        System.out.print("Enter your name:\n");
        String player=scanner.next();

        boolean play_again;

        do {

            int num_of_picks = 0;
            int turn = 0;

            while (no_of_sticks > 0) {


                if (turn == 0) {

                    while (num_of_picks < 1 || num_of_picks > 3) {
                        System.out.println("\n\nThere are " + no_of_sticks + " sticks remaining on the board");

                        System.out.print(player + ": How many sticks do you take (1-3)?");

                        num_of_picks = scanner.nextInt();

                        if (num_of_picks > 0 && num_of_picks < 4) {

                            no_of_sticks -= num_of_picks;

                        } else
                            System.out.println("you can only pick in the range (1-3)");


                        if (no_of_sticks <= 0) {
                            System.out.println(player + " , you loose");

                            //Ai adjusts its  hats

                            int index = 0;
                            for (Hat hat : AIHats) {

                                if (hat.getChosenBall().getValue() !=0) {
                                    //Add two balls to the hat
                                    AIHats[index].hats.add(hat.getChosenBall());
                                    AIHats[index].hats.add(hat.getChosenBall());

                                    //reset the chosen ball to zero
                                    AIHats[index].setChosenBall(new Ball(0));
                                }
                                index++;

                            }
                        }
                    }
                    turn = 1;
                    num_of_picks = 0;

                } else {
                    System.out.println("\n\nThere are " + no_of_sticks + " sticks remaining on the board");

                    Hat hat = AIHats[no_of_sticks];

                    //pick a random ball

                    Ball chosen=hat.hats.get(random.nextInt(hat.hats.size()));

                    num_of_picks = chosen.getValue();

                    //Add the picked value to chosen ball and remove it from the list of balls
                    AIHats[no_of_sticks].setChosenBall(chosen);

                    //remove from balls list
                    AIHats[no_of_sticks].hats.remove(chosen);


                    System.out.println("AI selects " + num_of_picks);


                    no_of_sticks -= num_of_picks;


                    if (no_of_sticks <= 0) {
                        System.out.println("AI ,  looses");

                        //remove balls from hat if Ai looses
                        int index = 0;
                        for (Hat hat1 : AIHats) {

                            if (hat1.getChosenBall().getValue() !=0) {

                                LinkedList<Integer> values=new LinkedList<>();

                                for (Ball ball:hat1.hats){
                                    values.add(ball.getValue());
                                }

                                if (!(values.contains(hat1.getChosenBall().getValue()))) {

//                                    System.out.println(AIHats[index].hats.size());
                                    AIHats[index].hats.add(hat1.getChosenBall());

//                                    System.out.println(AIHats[index].hats.size());
                                }

                                //reset the chosen ball to zero
                                AIHats[index].setChosenBall(new Ball(0));
                            }
                            index++;
                        }

                    }


                    turn = 0;
                    num_of_picks = 0;
                }
            }

            System.out.println("Play again (1 = yes, 0 no)");
            play_again= scanner.nextInt() == 1;
            no_of_sticks=initial_sticks;

        }while (play_again);
    }


    //AI plays Against AI
    public void trainedAI(Scanner scanner){

        System.out.println("Training AI please wait ....");

        int  winnerA=0,winnerB=0;

        //Hats for the first AI
        Hat[] AIHats1=new Hat[no_of_sticks];

        //Hats for the second AI
        Hat[] AIHats2=new Hat[no_of_sticks];

        int  initial_sticks=no_of_sticks;

        //Random to be used by the AI
        Random random=new Random();

        for (int i=0; i<AIHats1.length; i++) {
            AIHats1[i]=new Hat();

            AIHats1[i].hats.add(new Ball(1));
            AIHats1[i].hats.add(new Ball(2));
            AIHats1[i].hats.add(new Ball(3));

            AIHats1[i].setChosenBall(new Ball(0));

        }

        for (int i=0; i<AIHats2.length; i++) {
            AIHats2[i] = new Hat();

            AIHats2[i].hats.add(new Ball(1));
            AIHats2[i].hats.add(new Ball(2));
            AIHats2[i].hats.add(new Ball(3));

            AIHats2[i].setChosenBall(new Ball(0));
        }


            int num_of_picks = 0;
            int turn = 0;

            int training_count=0;
            do {


                while (no_of_sticks > 0) {

                    if (turn == 0) {
//                        System.out.println("There are " + no_of_sticks + " sticks remaining on the board");

                        Hat hat = AIHats1[no_of_sticks-1];

                        //pick a random ball

                        Ball chosen = hat.hats.get(random.nextInt(hat.hats.size()));

                        num_of_picks = chosen.getValue();

                        //Add the picked value to chosen ball and remove it from the list of balls
                        AIHats1[no_of_sticks-1].setChosenBall(chosen);

                        //remove from balls list
                        AIHats1[no_of_sticks-1].hats.remove(chosen);


//                        System.out.println("AI selects " + num_of_picks);


                        no_of_sticks -= num_of_picks;


                        if (no_of_sticks <= 0) {
//                            System.out.println("AI ,  looses");

                            winnerB++;

                            //Add the values of A12 back to
                            int i = 0;
                            for (Hat hat2 : AIHats2) {

                                if (hat2.getChosenBall().getValue() != 0) {
                                    //Add two balls to the hat
                                    AIHats2[i].hats.add(hat2.getChosenBall());
                                    AIHats2[i].hats.add(hat2.getChosenBall());

                                    //reset the chosen ball to zero
                                    AIHats2[i].setChosenBall(new Ball(0));
                                }
                                i++;

                            }


                            //remove balls from hat if Ai looses
                            int index = 0;
                            for (Hat hat1 : AIHats1) {

                                if (hat1.getChosenBall().getValue() != 0) {


                                    LinkedList<Integer> values = new LinkedList<>();

                                    for (Ball ball : hat1.hats) { values.add(ball.getValue()); }


                                    if (!(values.contains(hat1.getChosenBall().getValue()))) {

//                                    System.out.println(AIHats[index].hats.size());
                                        AIHats1[index].hats.add(hat1.getChosenBall());

//                                    System.out.println(AIHats[index].hats.size());
                                    }

                                    //reset the chosen ball to zero
                                    AIHats1[index].setChosenBall(new Ball(0));
                                }
                                index++;
                            }

                        }


                        turn = 1;
                    } else {
//                        System.out.println("There are " + no_of_sticks + " sticks remaining on the board");

                        Hat hat = AIHats2[no_of_sticks-1];

                        //pick a random ball

                        Ball chosen = hat.hats.get(random.nextInt(hat.hats.size()));

                        num_of_picks = chosen.getValue();

                        //Add the picked value to chosen ball and remove it from the list of balls
                        AIHats2[no_of_sticks-1].setChosenBall(chosen);

                        //remove from balls list
                        AIHats2[no_of_sticks-1].hats.remove(chosen);


//                        System.out.println("A2 selects " + num_of_picks);


                        no_of_sticks -= num_of_picks;


                        if (no_of_sticks <= 0) {
//                            System.out.println("A2 ,  looses");

                            winnerA++;


                            //Add the values of A11 back to
                            int i = 0;
                            for (Hat hat2 : AIHats1) {

                                if (hat2.getChosenBall().getValue() != 0) {
                                    //Add two balls to the hat
                                    AIHats1[i].hats.add(hat2.getChosenBall());
                                    AIHats1[i].hats.add(hat2.getChosenBall());

                                    //reset the chosen ball to zero

                                    AIHats1[i].setChosenBall(new Ball(0));
                                }
                                i++;
                            }


                            //remove balls from hat if Ai looses
                            int index = 0;
                            for (Hat hat1 : AIHats2) {

                                if (hat1.getChosenBall().getValue() != 0) {

                                    LinkedList<Integer> values = new LinkedList<>();

                                    for (Ball ball : hat1.hats) {
                                        values.add(ball.getValue());
                                    }

                                    if (!(values.contains(hat1.getChosenBall().getValue()))) {

//                                    System.out.println(AIHats[index].hats.size());
                                        AIHats2[index].hats.add(hat1.getChosenBall());

//                                    System.out.println(AIHats[index].hats.size());
                                    }

                                    //reset the chosen ball to zero
                                    AIHats2[index].setChosenBall(new Ball(0));
                                }
                                index++;
                            }

                        }


                        turn = 0;
                    }

                }

                no_of_sticks=initial_sticks;
                training_count++;


            }while (training_count<100000);


             System.out.println("Training is complete: ");
        System.out.println(no_of_sticks);

             System.out.println("AI one: "+winnerA+"\nAI two: "+winnerB);
            //finished training AI let human play

               playWithTrainedAI(scanner,(winnerA>=winnerB)?AIHats1:AIHats2);
    }


    private void playWithTrainedAI(Scanner scanner, Hat[] chosenAI){

        int  initial_sticks=no_of_sticks;

        //put the balls into the hats for A1

        for (int i = 0; i< chosenAI.length; i++) {
            chosenAI[i]=new Hat();

            chosenAI[i].hats.add(new Ball(1));
            chosenAI[i].hats.add(new Ball(2));
            chosenAI[i].hats.add(new Ball(3));

            chosenAI[i].setChosenBall(new Ball(0));

        }

        //Random to be used by the AI
        Random random=new Random();


        System.out.print("Enter your name:\n");
        String player=scanner.next();

        boolean play_again;

        do {

            int num_of_picks = 0;
            int turn = 0;

            while (no_of_sticks > 0) {


                if (turn == 0) {

                    while (num_of_picks < 1 || num_of_picks > 3) {
                        System.out.println("\n\nThere are " + no_of_sticks + " sticks remaining on the board");

                        System.out.print(player + ": How many sticks do you take (1-3)?");

                        num_of_picks = scanner.nextInt();

                        if (num_of_picks > 0 && num_of_picks < 4) {

                            no_of_sticks -= num_of_picks;

                        } else
                            System.out.println("you can only pick in the range (1-3)");


                        if (no_of_sticks <= 0) {
                            System.out.println(player + " , you loose");

                            //Ai adjusts its  hats

                            int index = 0;
                            for (Hat hat : chosenAI) {

                                if (hat.getChosenBall().getValue() !=0) {
                                    //Add two balls to the hat
                                    chosenAI[index].hats.add(hat.getChosenBall());
                                    chosenAI[index].hats.add(hat.getChosenBall());

                                    //reset the chosen ball to zero
                                    chosenAI[index].setChosenBall(new Ball(0));
                                }
                                index++;

                            }
                        }
                    }
                    turn = 1;
                    num_of_picks = 0;

                } else {
                    System.out.println("\n\nThere are " + no_of_sticks + " sticks remaining on the board");

                    Hat hat = chosenAI[no_of_sticks];

                    //pick a random ball

                    Ball chosen=hat.hats.get(random.nextInt(hat.hats.size()));

                    num_of_picks = chosen.getValue();

                    //Add the picked value to chosen ball and remove it from the list of balls
                    chosenAI[no_of_sticks].setChosenBall(chosen);

                    //remove from balls list
                    chosenAI[no_of_sticks].hats.remove(chosen);


                    System.out.println("AI selects " + num_of_picks);


                    no_of_sticks -= num_of_picks;


                    if (no_of_sticks <= 0) {
                        System.out.println("AI ,  looses");

                        //remove balls from hat if Ai looses
                        int index = 0;
                        for (Hat hat1 : chosenAI) {

                            if (hat1.getChosenBall().getValue() !=0) {

                                LinkedList<Integer> values=new LinkedList<>();

                                for (Ball ball:hat1.hats){
                                    values.add(ball.getValue());
                                }

                                if (!(values.contains(hat1.getChosenBall().getValue()))) {

//                                    System.out.println(AIHats[index].hats.size());
                                    chosenAI[index].hats.add(hat1.getChosenBall());

//                                    System.out.println(AIHats[index].hats.size());
                                }

                                //reset the chosen ball to zero
                                chosenAI[index].setChosenBall(new Ball(0));
                            }
                            index++;
                        }

                    }


                    turn = 0;
                    num_of_picks = 0;
                }
            }

            System.out.println("Play again (1 = yes, 0 no)");
            play_again= scanner.nextInt() == 1;
            no_of_sticks=initial_sticks;

        }while (play_again);
    }
}
