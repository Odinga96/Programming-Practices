import java.util.Scanner;
import java.util.ArrayList;

public class midterm {
    public static void main(String[] args) {
        
        // A custom array that stores task (class) into the list
        ArrayList <task> toDoList = new <task> ArrayList();
        String userInput;
        
        //Prints out the menu
        menu();
        
        Scanner user = new Scanner(System.in);
        userInput = user.nextLine();
        
        /* The start of the menu through the use of a switch-case statements */
        
        while (!"E".equals(userInput)) {
            switch(userInput) {
                case "A":
                    menuOptionA(toDoList, user); //runs method menuOptionA 
                    break;
                case "R":
                    menuOptionR(toDoList); //runs method menuOptionR
                    break;
                case "D":
                    menuOptionD(toDoList); //runs method menuOptionD
                    break;
                case "L":
                    menuOptionL(toDoList); // runs method menuOptionL
                    break;
                default:
                    System.out.println("Invalid input\n"); //If the user decides to input anything else, it will return this
                    break;  
            }
            menu();
            userInput = user.nextLine(); //Loop continues and asks for the user input again
           
        }
       
        if ("E".equals(userInput)) {
            exitProgram(); //If E is hit as an input, program calls method exitProgram() and quits
        }  
    }
   
    static void menu() {
        
        //Prints out the menu for the user to see
        
        System.out.println(" Welcome to your To Do list ");
        System.out.println(" Enter A to add an item to your list                                              ");
        System.out.println(" Enter R to remove an item from your list                                         ");
        System.out.println(" Enter D if you would like to view the contents of an item within your list       ");
        System.out.println(" Enter L to show your list                                                        ");
        System.out.println(" Enter E to exit the program                                                      ");
        System.out.println("\n");
    }
   
    static void exitProgram() {
        
        //Exits the program
        
        System.out.println("Program is exiting...");
        System.exit(0);
    }
   
    static void menuOptionA (ArrayList <task> toDoList, Scanner user) {
       
        /*Method that runs when the user inputs "A"
        Creates a new task called "userAdd"
        Asks the user for the following inputs and stores info on the item
        */
        
        task userAdd = new task();
        String userCheck;  
        String userMenuA;
        Scanner userMenuOption = new Scanner(System.in);
        
        listMenuA();
        userMenuA = userMenuOption.nextLine();
        
        //Menu A within the main menu
        //Allows the user to add a title, description, date, and mark the task as complete
        while (!"0".equals(userMenuA)) {
            switch (userMenuA) {  
                case "1":
                    System.out.println("Print out the title of your task");
                    userAdd.title = user.nextLine(); //asks user for the title
                    break;
                case "2":
                    System.out.println("Print out the description");
                    userAdd.description = user.nextLine(); //asks the user for the decription
                    break;
                case "3":
                    System.out.println("Print out the date (ex: 3.20.19 or March 20 2019)");
                    userAdd.date = user.nextLine(); //asks the user for the date
                    break;
                case "4":
                    System.out.println("Is the task complete? (Input 'Complete' or 'Not Complete')");
                    userCheck = user.nextLine(); //asks the user if the task is complete
                    
                    if (userCheck.equals ("Complete") || userCheck.equals ("Not Complete")) { //If the user inputs anything else, a default message will be added to the complete section of the task
                        userAdd.complete = userCheck;
                    }
                    else {
                        System.out.println("Invalid input");
                    }
                    
                    break;
                default: 
                    System.out.println("Invalid input");
                    break;
            }
            
            listMenuA();
            userMenuA = userMenuOption.nextLine();
            
        }
        toDoList.add(userAdd);
        
        System.out.println("");
    }
    
    static void menuOptionR(ArrayList <task> toDoList) {
        
        /*Method that runs when user inputs "R"
        Deletes a task based on the user's choice
        */
        
        Scanner userMenuR = new Scanner(System.in);
        int userChoiceRemove;
        
        list(toDoList);
        
        System.out.println("Print out the number of the task to be removed");
        
        userChoiceRemove = userMenuR.nextInt(); 
        if (userChoiceRemove <= toDoList.size()-1 && toDoList.size()>0) { //if the user decides to choose something other then what is provided, they will run into an error
            toDoList.remove(userChoiceRemove);
            System.out.println("Task was removed");
        }
        else {
            System.out.println("Task does not exist");
        }

        System.out.println("");
    }
    
    static void menuOptionD(ArrayList <task> toDoList) {
        
        /*Method that runs when user inputs "D"
        Returns the title, description, task completion, and date of the task
        */
        
        Scanner userMenuD = new Scanner(System.in);
        int userChoiceSelect;
        
        list(toDoList);
        
        System.out.println("Print out the number of the task for more contents");
        
        userChoiceSelect = userMenuD.nextInt();
        
        if (userChoiceSelect <= toDoList.size()-1 && toDoList.size()>0) {
            System.out.println("Title of task: " + toDoList.get(userChoiceSelect).title); //prints out title of the chosen task
            System.out.println("Description of task: " + toDoList.get(userChoiceSelect).description); //prints out the decription of the chosen task
            System.out.println("Date of when the task was created: " + toDoList.get(userChoiceSelect).date); //prints out the date of the chosen task
            System.out.println("Task complete?: " + toDoList.get(userChoiceSelect).complete); //prints weather or not the task is complete
        }
        else {
            System.out.println("Task does not exist");
        }
        
        System.out.println("");
    }
    
    static void menuOptionL (ArrayList <task> toDoList) {
        /*Runs when user inputs "L"
        Prints out the list
        */
        
        list(toDoList);
        System.out.println("");
}
    
    static void list(ArrayList <task> toDoList) {
        
        /*Method that prints out the list*/
        
        System.out.println("Current List: ");
        
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(i + ") " + toDoList.get(i).title + " (" + toDoList.get(i).complete + ")");
        }
}
    static void listMenuA() {
        //prints out the menu within Option A
        System.out.println("");
        System.out.println("-Select an option-");
        System.out.println("0) Exit");
        System.out.println("1) Add a title");
        System.out.println("2) Add a description");
        System.out.println("3) Add a date");
        System.out.println("4) Mark as complete");
        System.out.println("");
    }
   
    public static class task {
    /* A task class */    
    String title = new String(); //holds the title of the task
    String description = new String(); //holds the description of the task
    String date = new String(); //holds the date of the task
    String complete = new String(); //holds info on weather the task is complete
    
    }
}