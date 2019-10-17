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
abstract class Question {
    
    private final String quesstionID;
    private final int    chapterNumber;
    private final String questionText;

    public Question(String quesstionID, int chapterNumber, String questionText) {
        this.quesstionID = quesstionID;
        this.chapterNumber = chapterNumber;
        this.questionText = questionText;
    }
    
    abstract boolean isAnswerCorrect();

    public String getQuesstionID() {
        return quesstionID;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public String getQuestionText() {
        return questionText;
    }
    
       
}
