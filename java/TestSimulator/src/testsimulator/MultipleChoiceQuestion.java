/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsimulator;

/**
 *
 * @author root
 */
public class MultipleChoiceQuestion extends Question{
    
    private String[] answers=new String[4];
    private char chosenAnswer;
    private char correctAnswer;

    public MultipleChoiceQuestion(String quesstionID, int chapterNumber, String questionText,
            String answer1, String answer2, String answer3, String answer4, char correctAnswer) {
        super(quesstionID, chapterNumber, questionText);
        
        this.chosenAnswer='a';
        this.answers[0]=answer1;
        this.answers[1]=answer2;
        this.answers[2]=answer3;
        this.answers[3]=answer4;
        
        this.correctAnswer=correctAnswer;
    }

    @Override
    boolean isAnswerCorrect() {return getChosenAnswer() == getCorrectAnswer();}

    public String[] getAnswers() {
        return answers;
    }

    public char getChosenAnswer() {
        return chosenAnswer;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public void setChosenAnswer(char chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
        
}
