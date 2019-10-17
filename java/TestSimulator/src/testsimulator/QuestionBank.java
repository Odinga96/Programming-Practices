/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class QuestionBank implements QuestionFinder{
    public static final int[] CHAPTERS={8,9,10,11,13,14,15,16};
    private final  String MULTIPLE_CHOICE_FILE="src/multiple-choice-questions.csv";
    private final  String TRUE_FALSE_FILE="src/true-false-questions.csv";
    
    private final  int MULTIPLE_CHOICE_FIELDS=8;
    private final  int TRUE_FALSE_FIELDS=4;
        
    private ArrayList<Question> questions;

    public QuestionBank() {
        questions=new ArrayList<>();
        loadMultipleChoiceQuestions();
        loadTrueFalseChoiceQuestions();
        System.out.println("loaded "+getLenth()+"  questions from the question bank.\n\n");
    }
    
    public int getLenth(){return questions.size();}
    
    public Question getQuestion(int index){return this.questions.get(index);}
    
    public void loadMultipleChoiceQuestions(){
        File questionsFile=new File(MULTIPLE_CHOICE_FILE);
        try {
            FileReader fileReader;
            fileReader = new FileReader(questionsFile);
            
            BufferedReader  reader=new BufferedReader(fileReader);
            
            String readline;
              while((readline=reader.readLine()) != null){
                  String fields[] =readline.split(",");
                  if(fields.length == MULTIPLE_CHOICE_FIELDS){
                      String ID=fields[0];
                      int chapterNum=Integer.parseInt(fields[1]);
                      String question=fields[2];
                      
                      String answer1=fields[3];
                      String answer2=fields[4];
                      String answer3=fields[5];
                      String answer4=fields[6];
                      
                      char correctAnswer=fields[7].charAt(0);
                      
                      boolean allowedChapter=false;
                      for (int i = 0; i < CHAPTERS.length; i++) {
                          if(CHAPTERS[i] == chapterNum){
                              allowedChapter=true;
                              break;
                          }
                      }
                      
                       if (allowedChapter) {
                           if (correctAnswer == 'a' || correctAnswer == 'b' || correctAnswer == 'c' || correctAnswer == 'd') {
                                if(!containsQuestion(ID, questions)){
                                    Question mcq=new MultipleChoiceQuestion(ID, chapterNum, question, answer1, answer2, answer3, answer4, correctAnswer);
                                    this.questions.add(mcq);
                                    
                                }else{
                                    System.out.println("Dublicate question ID found");
                                }
                                
                           }else{
                               System.out.println("The correct answer : "+correctAnswer+" is not one of the allowed answers i.e a,b,c,d");
                           }
                          
                      }else{
                          System.out.println("Chapeter number "+chapterNum+"is not allowed as part of the chapters");
                      }
                      
                  }else{
                      System.out.println("Field row has incorrect number of fields");
                  }
            }
                    
            } catch (FileNotFoundException ex) {
            System.out.println("Could not open "+MULTIPLE_CHOICE_FILE+" Please make aure the file exists"); 
            } catch (IOException ex) {
            Logger.getLogger(QuestionBank.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException formatException){
               System.out.println("Chapter number has to be numeric");
               System.exit(0);
            }
        
    }
    
    public void loadTrueFalseChoiceQuestions(){
        File questionsFile=new File(TRUE_FALSE_FILE);
        try {
            FileReader fileReader;
            fileReader = new FileReader(questionsFile);
            
            BufferedReader  reader=new BufferedReader(fileReader);
            
            String readline;
              while((readline=reader.readLine()) != null){
                  String fields[] =readline.split(",");
                  if(fields.length == TRUE_FALSE_FIELDS){
                      String ID=fields[0];
                      int chapterNum=Integer.parseInt(fields[1]);
                      String question=fields[2];
                      
                  
                      
                      String correctAnswer=fields[3];
                      
                      boolean allowedChapter=false;
                      for (int i = 0; i < CHAPTERS.length; i++) {
                          if(CHAPTERS[i] == chapterNum){
                              allowedChapter=true;
                              break;
                          }
                      }
                      
                       if (allowedChapter) {
                           if (correctAnswer.equalsIgnoreCase("true") || correctAnswer.equalsIgnoreCase("false")) {
                                if(!containsQuestion(ID, questions)){
                                    Question truefalsequestion=new TrueFalseQuestion(ID, chapterNum, question, Boolean.parseBoolean(correctAnswer));
                                    this.questions.add(truefalsequestion);
                                    
                                }else{
                                    System.out.println("Dublicate question ID found");
                                    System.exit(0);
                                }
                                
                           }else{
                               System.out.println("The correct answer : "+correctAnswer+" is not one of the allowed answers i.e true,false");
                               System.exit(0);
                           }
                          
                      }else{
                          System.out.println("Chapter number "+chapterNum+"is not allowed as part of the chapters");
                          System.exit(0);
                      }
                      

                      
                  }else{
                      System.out.println("Field row has incorrect number of fields");
                      System.exit(0);
                  }
            }
                    
            } catch (FileNotFoundException ex) {
            System.out.println("Could not open "+TRUE_FALSE_FILE+" Please make aure the file exists"); 
            } catch (IOException ex) {
            Logger.getLogger(QuestionBank.class.getName()).log(Level.SEVERE, null, ex);
            } catch(NumberFormatException formatException){
               System.out.println("Chapter number has to be numeric");
               System.exit(0);
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
