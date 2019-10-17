import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        System.out.println("Welcome to the game of sticks!");
        Scanner scanner=new Scanner(System.in);

        int total_sticks=0;

        while (total_sticks<10 || total_sticks>100){
            System.out.print("How many sticks are there on the table initially (10-100)?");
            total_sticks=scanner.nextInt();
        }

        Sticks sticks=new Sticks(total_sticks);

        int option=0;

        while (option<1 || option>3) {
            System.out.println("Options:\n\tPlay Against a friend  (1)" +
                    "\n\tPlay against the computer  (2)" +
                    "\n\tPlay against the trained computer (3)\n" +
                    "Which options do you like (1-3)?");
            option=scanner.nextInt();
        }


        if (option==1)
            sticks.playAgainstFriend(scanner);
        else if(option==2)
            sticks.playAgainstComputer(scanner);
        else
            sticks.trainedAI(scanner);
    }
}
