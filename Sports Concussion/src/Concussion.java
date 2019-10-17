import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Concussion {


    private static List<Data> previous =new LinkedList<>();


    public static void main(String[] args) {
        System.out.println("\n\t\t\t\t\t\t\t\t\tWelcome to Sport Concussion Assessment System ");

         Concussion d=new Concussion();


         //Load the previous history to memory
        File file=new File("data.txt");

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            previous =(LinkedList<Data>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        d.displayPickedMenu();


    }


    private void readPlayerSymptoms(){
        //remove first symptom if history equals five

        System.out.println(previous.size());
        if (previous.size() == 5)
            previous.remove(0);



        try (Scanner scanner = new Scanner(new File("questions.txt"))) {


            int c=1;

            List<Symptom> symptoms=new LinkedList<>();

            while (scanner.hasNextLine()) {
                String symptom=scanner.nextLine();


                boolean check=true;
                int score = 0;

                if (c<23) {
                    while (check) {

                        System.out.print(c + ":\tplease enter your " + symptom  + " score " + String.format("%"+(79-symptom.length())+"s","(none(0),mid(1-2), moderate(3-4)" +
                                ",& severe(5-6)):"));
                        score = new Scanner(System.in).nextInt();
                        System.out.println();

                        if (score > -1 && score < 7)
                            check = false;
                    }

                    if (score > 0) {
                        symptoms.add(new Symptom(symptom, score));
                    }
                }else {
                    System.out.println(symptom + "\n\t\t\t\t1: yes" +
                            "\n\t\t\t\t2: no");
                    new Scanner(System.in).nextInt();
                }

                c++;

            }

            previous.add(new Data(symptoms));
            save();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    private void displayPickedMenu(){

        int picked_value=0;
        while (picked_value<1 || picked_value>4) {
            System.out.println("Please select one of the options \n\n" +
                    "\t\t 1. Enter Symptoms\n" +
                    "\t\t 2. Display Symptoms Summary\n" +
                    "\t\t 3. I am I at Risk?\n" +
                    "\t\t 4. Exit");

            picked_value=new Scanner(System.in).nextInt();
            System.out.println();
        }


        if (picked_value == 1) {
            readPlayerSymptoms();
            displayPickedMenu();
        }

        else if (picked_value == 2) {
            displaySummary();
            displayPickedMenu();
        }
        else if (picked_value == 3) {
            showRisk();
        }
        else
            System.exit(1);


    }


    private void save(){
        File file=new File("data.txt");

        try {
            FileOutputStream fileOut = new FileOutputStream(file,false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);


            objectOutputStream.writeObject(previous);


            objectOutputStream.close();
            fileOut.close();



        } catch (IOException e ) {
            e.printStackTrace();
        }


    }



    private void displaySummary(){

        for (int i = 0; i < previous.size(); i++) {
            Data playerHistory= previous.get(i);
            int severity=0;
            int diffrence=0;

            for (Symptom symptom:playerHistory.getSymptoms()) {
                severity+=symptom.getScore();
            }


            if (i>0){
                int previous = Concussion.previous.get(i-1).getSymptoms().size();
                int current  =playerHistory.getSymptoms().size();
                diffrence=Math.abs(previous-current);
            }

            String overalRating= diffrence < 3 && severity < 10 ?"No difference": diffrence < 3 ?"Unsure":
                    severity >= 15 ?"Very different":"";

            String summary=String.format("\n\t\t\t*********** Game   "+(i+1)+"  summary ***********" +
                               "\n\n\t\t Total Symptoms: %20s"+
                               "\n\t\t Symptom severity score: %12s" +
                               "\n\t\t Overall rating: %24s\n",playerHistory.getSymptoms().size(),severity,overalRating);

            System.out.println(summary);


        }
    }


    private void showRisk(){


        int total_games_stored= previous.size();

        if (total_games_stored>0)
        for (int i = total_games_stored-1; i >total_games_stored-2 ; i--) {


            Data history= previous.get(i);
            int severity=0;
            int disparity=0;

            for (Symptom symptom:history.getSymptoms()) {
                severity+=symptom.getScore();
            }


            if (i>0){
                int previous = Concussion.previous.get(i-1).getSymptoms().size();
                int current  =history.getSymptoms().size();
                disparity=Math.abs(previous-current);
            }

            String overalRating= disparity < 3 && severity < 10 ?"No difference": disparity < 3 ?"Unsure":
                    severity >= 15 ?"Very different":"";


            System.out.println(overalRating.equals("No difference") ? "green":
                    overalRating.equals("Unsure") ? "yellow":"red");
        }
    }


}
