/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class Test implements QuestionFinder{
    
    private final ArrayList<Question> questions=new ArrayList<>();

    public Test(int numQuestions, QuestionBank questionBank) {selectQuestions(numQuestions, questionBank); }
    
    public int getLenth(){return this.questions.size();}
    
    private void selectQuestions(int numQuestions, QuestionBank questionBank){
        if(numQuestions > questionBank.getLenth()){
            System.out.println("The question bank does not have "+numQuestions+" questions. "+
                               "The test will have "+questionBank.getLenth()+" questions in stead.");
            numQuestions=questionBank.getLenth();
        }else{System.out.println("The test will have "+numQuestions+" questions.");}
        
        System.out.println("You may hit 'q' to quit the test any time, but progress or results will not be saved.\n" +
                           "Starting your test now ...\n\n");
        
        while(getLenth() < numQuestions){
            Question question=questionBank.getQuestion(new Random().nextInt(questionBank.getLenth()));
          if(!containsQuestion(question.getQuesstionID(), this.questions)){
              this.questions.add(question);
          }
        }
      
    }
    
    
    public boolean runTest(){
        Scanner s=new Scanner(System.in);
        String[] options={"(a)","(b)","(c)","(d)"};
        
        char input=' ';
        int index=0;
        
          while(index< questions.size()){
            System.out.println("Question "+(index+1)+":");
            
         if(this.questions.get(index) instanceof MultipleChoiceQuestion){
             
                
             MultipleChoiceQuestion question=(MultipleChoiceQuestion)questions.get(index);
             
             boolean valid_choice=false;
             while(!valid_choice){
             
             System.out.println(questions.get(index).getQuestionText());
             String[] anwerOptions=question.getAnswers();
             
             for (int i = 0; i < anwerOptions.length; i++) 
                 System.out.println(options[i]+"\t"+anwerOptions[i]);
                 
             System.out.print("Enter your answer: ");
             
                 input=s.next().charAt(0);
                 valid_choice=input =='a' || input == 'b'|| input == 'c' || input =='d';
                 
                 if(input != 'q') question.setChosenAnswer(input);
                 else break;  
                 
                 System.out.println();         
             }
             if( question.isAnswerCorrect()) System.out.println("Feedback: Correct!");
                 else System.out.println("Feedback: Incorrect. Correct answer was ("+question.getCorrectAnswer()+")");
             
             System.out.println();
             index++;
             
         }else if(this.questions.get(index) instanceof TrueFalseQuestion){
               
             TrueFalseQuestion question=(TrueFalseQuestion)questions.get(index);
             boolean valid_choice=false;
             while(!valid_choice){
             
             System.out.println(question.getQuestionText());
             String[] anwerOptions={"True", "False"};
             
             for (int i = 0; i < anwerOptions.length; i++) 
                 System.out.println(options[i]+"\t"+anwerOptions[i]);
                 
             System.out.print("Enter your answer: ");
                 input=s.next().charAt(0);
                 valid_choice=input == 'a' || input =='b';
                 
                 if(input != 'q') {
                     if(input == 'a')question.setChosenAnswer(true);
                     else question.setChosenAnswer(false);
                 }else break;
                 
                 System.out.println();     
             }
             
             if( question.isAnswerCorrect())System.out.println("Feedback: Correct!");            
             else{ if(input == 'a') 
                      System.out.println("Feedback: Incorrect. Correct answer was (b)");
                   else 
                       System.out.println("Feedback: Incorrect. Correct answer was (a)");
                 }
             
             System.out.println();
             index++;
         }
        }
          
          System.out.println("Test is finished.");
        
        return input != 'q';
    }
    
    public void showTestSummary(){
        
        int scored=0;
        for(Question q: this.questions){if(q.isAnswerCorrect()) scored++; }
        
        double percentageScore=((double)scored/(double)getLenth())*100;
        DecimalFormat f=new DecimalFormat("##.00");
        System.out.println("\nYou answered "+scored+" questions correctly out of "+getLenth()+
                           "\nYour score was "+f.format(percentageScore)+"%.");
    
    }
    
    public void saveTestResult(){
        LocalDateTime dateTime=LocalDateTime.now();
        DateTimeFormatter  dateformatter=DateTimeFormatter.ofPattern("yyMMdd-HHmmss");
        
        String new_test_file      ="src/results/"+dateTime+".txt";
        String tests_history_file ="src/results/test-summary.txt";
        
        File result      =new File(new_test_file);
        File test_summary=new File(tests_history_file);
        try {
            
            BufferedWriter writer  = new BufferedWriter(new FileWriter(result));
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(test_summary,true));
            
            questions.forEach(e->{
            StringBuilder builder=new StringBuilder();
            builder.append(e.getQuesstionID())
                    .append(",")
                    .append(e.getChapterNumber())
                    .append(",");
            
            if(e instanceof MultipleChoiceQuestion){
            builder.append(((MultipleChoiceQuestion)e).getCorrectAnswer())
                    .append(",")
                    .append(((MultipleChoiceQuestion)e).getChosenAnswer())
                    .append("\n");
            }
            else{
              builder.append(((TrueFalseQuestion)e).getCorrectAnswer())
                      .append(",")
                      .append(((TrueFalseQuestion)e).getChosenAnswer())
                      .append("\n");
            }
            
                try {
                    writer.write(String.valueOf(builder));
                } catch (IOException ex) {
                    Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                }
            
      
            });
            
           
            writer1.write(new_test_file);
            writer1.newLine();
            
            writer.close();
            writer1.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    

    @Override
    public boolean containsQuestion(String questionID, ArrayList<Question> questions) {
   boolean contains=false;
        
        for(Question q: questions){
          if(q.getQuesstionID().equalsIgnoreCase(questionID)){
              contains=true;
              break;
          }
        }
        
     return contains;
    }
    
    
    
}
