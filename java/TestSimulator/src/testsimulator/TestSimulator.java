/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.util.Scanner;

/**
 *
 * @author root
 */
public class TestSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        QuestionBank questionBank = new QuestionBank();
        int choice=getSelection();
        
       
       do{   
            while (choice<1 || choice>3) {choice=getSelection();}
        switch(choice){
        case 1:
            System.out.println("\nWelcome to your test.");
           Test test= new Test(5, questionBank);
            if (test.runTest()) {
               test.showTestSummary();
               test.saveTestResult();
            }
            choice=0;
            System.out.println("\n");
        break;
        case 2:
            TestSummary summary=new TestSummary();
            summary.summarisePerformance();
            summary.reportPerformance();
            choice=0;
            System.out.println("\n");
        break;
        
        }
       }while(choice != 3);
       
        System.out.println("\nThank you for using TestSimulator!");
    }
    
    public static int getSelection() {
        System.out.println("Welcome to the TestSimulator program menu.\n" +
                            "Select from one of the following options.");
        
        int choice=0;
        boolean not_valid=true;

        while(not_valid){
        try {
            Scanner scanner=new Scanner(System.in);
            System.out.print("(1) New test.\n" +
                               "(2) Test summary.\n" +
                               "(3) Exit. \n"+
                               "Enter your selection: ");
            choice=scanner.nextInt();

            not_valid=false; 
          } catch (Exception e) {
              System.out.println("Only integer values allowed");
         }
        }
        
        return choice;
    }
    
}
