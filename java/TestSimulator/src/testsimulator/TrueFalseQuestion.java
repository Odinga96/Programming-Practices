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
public class TrueFalseQuestion extends Question{
    private boolean correctAnswer;
    private boolean chosenAnswer;

    public TrueFalseQuestion(String quesstionID, int chapterNumber, String questionText, boolean correctAnswer) {
        super(quesstionID, chapterNumber, questionText);
        this.correctAnswer = correctAnswer;
        this.chosenAnswer=true;
    }

    @Override
    boolean isAnswerCorrect() {return getChosenAnswer()== getChosenAnswer();}

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(boolean chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
        
}
