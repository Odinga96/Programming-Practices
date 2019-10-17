import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class GeographyQuiz {
   private List<String> CountriesAndCapital;

    public GeographyQuiz() {
        CountriesAndCapital = new List<>();
    }

    /**Adds a new country and its capital to the list
     * @param countryAndCity :String separated by comma that has both country and its capital
     */
    public void addCountryAndCity(String countryAndCity){ CountriesAndCapital.add(countryAndCity); }

    /**This methods returns the user response to the play question
     * @param scanner ;;used to read the play option
     * @return ;; returns a string
     */
    public String menu(Scanner scanner){
        System.out.print("Play?\t");
        return scanner.next();
    }

    /** Runs a question and returns the answer to the question
     * @return :: Returns the correct answer to the asked question
     */
    public String question(){

        //index of the question to be asked
        int index = new Random().nextInt(CountriesAndCapital.size());

        /*holds the value 0 or 1
         * 0 => The question to ask is the capital city for a country
         * 1 => The question to ask is the country to which a capital city is located
         */

        int questionToAsk= new Random().nextInt(2);

        //used to check if the question randomized is the current question in the list
        int count=0;

        //points to the first question
        String countryCapital = CountriesAndCapital.first();

        //Check if the question is null
        while (countryCapital != null){

            //check if we found the question and break
            if (count == index)  break;

            //if the current question is not the one being queried we point to the next element
               countryCapital = CountriesAndCapital.next();

               //increment the count value
               count++;
        }


        //take the value of the capital or city and split
        String[] answers=countryCapital.split(",");

        //initialize answer to null
        String answer=null;

        //check if the question to ask was 0
        if (questionToAsk == 0){
            //ask for capital
            System.out.println("\nWhat is the capital of "+answers[0]+" ?");

            //set answer for country
            answer=answers[1];
        }else if (questionToAsk == 1){
            //ask for country
            System.out.println("\nWhat country has "+answers[1]+" as its capital?");
            //answer for countries capital
            answer=answers[0];
        }

        return answer;
    }


    /**
     * This is where the game is played
     * @param scanner //reading answeres
     */
    private void play(Scanner scanner){
        //initialize correct score and questionsAnswered  to 0
        int correctScore=0;
        int questionsAnswerd=0;

        //check if the player wants to play
        while (menu(scanner).equalsIgnoreCase("Yes")){

            //ask question
             String question=question();

             //check if the answer is right
            if (question.equalsIgnoreCase(scanner.next())){
                System.out.print("Correct.  ");

                //increment  the score value
                correctScore++;
            }else{
                //tell user that they input the wrong answer and tell them the wright answer
                System.out.print("Incorrect. The correct answer is  "+question+"  ");
            }
              questionsAnswerd++;
        }

        //used to format score output to 2dp
        DecimalFormat dollar = new DecimalFormat("0.00");

        //compute score
        double score=((double) correctScore/(double) questionsAnswerd)*100;

        //display results
        System.out.println("\n\nGame over.\n" +
                "Game Stats:\n" +
                "Questions played: "+questionsAnswerd+"; Correct answers: "+correctScore+"; Score: "+dollar.format(score)+"%");

    }

    public static void main(String[] args) throws FileNotFoundException {
        //initialize quiz
        GeographyQuiz geographyQuiz=new GeographyQuiz();

        //declare input scanner
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the Country-Capital Quiz");

        //quiz file
        File file = new File("CountryCapitals.txt");

        //open quiz file
        Scanner inputFile = new Scanner(file);

        //quiz fields
        String country,capital;

        //read quiz fields
        while (inputFile.hasNext()) {
            country = inputFile.nextLine();
            capital = inputFile.nextLine();

            //add the country and city values to quiz
            geographyQuiz.addCountryAndCity(country+","+capital);
        }
        //close quiz file
        inputFile.close();

        //play the game
        geographyQuiz.play(keyboard);
    }
}
