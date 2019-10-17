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
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class TestSummary {
    public  static final String FILENAME_SUMMARY="src/results/test-summary.txt";
    private final  int  TEST_REPORT_FIELDS=4;
    private int[]       chapterQuestionsAnswered;
    private int[]       chapterQuestionsCorrect;

    public TestSummary() {
        this.chapterQuestionsAnswered = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        this.chapterQuestionsCorrect = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    }
    
    
    public void summarisePerformance(){
            ArrayList<String>  tests_files=new ArrayList<>();
        try { 
              File summary_file=new File(FILENAME_SUMMARY);
              BufferedReader  reader=new BufferedReader(new FileReader(summary_file));
            
              String readline;
              while((readline=reader.readLine()) != null){tests_files.add(readline); }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestSummary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tests_files.forEach(e->{
         
            try {   File tests=new File(e);
                    BufferedReader reader=new BufferedReader(new FileReader(tests));
                    
                    String readline;
                    while((readline=reader.readLine()) != null){
                        String[] fields=readline.split(",");
                        
                        
                        
                        if(fields.length == TEST_REPORT_FIELDS){
                            int chapter=Integer.parseInt(fields[1]);
                            char chosenAnswer  =fields[2].charAt(0);
                            char correctAnswer =fields[3].charAt(0);
                            
                            if(chapter==QuestionBank.CHAPTERS[0]){ 
                                chapterQuestionsAnswered[0]++; 
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[0]++; 
                            }
                            else if(chapter == QuestionBank.CHAPTERS[1]){
                                chapterQuestionsAnswered[1]++; 
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[1]++;
                            }
                            else if(chapter == QuestionBank.CHAPTERS[2]){
                                chapterQuestionsAnswered[2]++; 
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[2]++;
                            }
                            else if(chapter == QuestionBank.CHAPTERS[3]){
                                chapterQuestionsAnswered[3]++;
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[3]++;}
                            else if(chapter == QuestionBank.CHAPTERS[4]){
                                chapterQuestionsAnswered[4]++; 
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[4]++;}
                            else if(chapter == QuestionBank.CHAPTERS[5]){
                                chapterQuestionsAnswered[5]++;
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[5]++;}
                            else if(chapter == QuestionBank.CHAPTERS[6]){
                                chapterQuestionsAnswered[6]++;
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[6]++;}
                            else if(chapter == QuestionBank.CHAPTERS[7]){
                                chapterQuestionsAnswered[7]++; 
                                
                                if(chosenAnswer == correctAnswer)
                                chapterQuestionsCorrect[7]++;}
                        }else{
                            System.out.println("The field rows is not equal to four");
                        }
                    
                    }
                    
                    reader.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TestSummary.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TestSummary.class.getName()).log(Level.SEVERE, null, ex);
                }catch(NumberFormatException exception){
                  System.out.println("The chapter field is not numerical");
                }
        });

              
    }
    
    public void reportPerformance(){
        System.out.println("\nPerformance report ...\n\n" +
                           "Chapter\t\tCorrect Answered\t\tPercent");
        System.out.println("---------------------------------------------------------------");
    
        for (int i = 0; i < chapterQuestionsAnswered.length; i++) {
            int answered  =chapterQuestionsAnswered[i];
            int correct   =chapterQuestionsCorrect[i];
            
            if(answered>1){
            double percentageScore=((double)correct/(double)answered)*100;
             DecimalFormat f=new DecimalFormat("##.00");
             
             System.out.printf("%d%"+(17-Integer.toString(QuestionBank.CHAPTERS[i]).length())+"d"
                     + "%"+(17-Integer.toString(correct).length())+"d"
                     + "%"+(20-Integer.toString(answered).length())+"s\n"
                     ,QuestionBank.CHAPTERS[i],correct,answered,f.format(percentageScore));
            }
            
        }
    }
    
    
}
