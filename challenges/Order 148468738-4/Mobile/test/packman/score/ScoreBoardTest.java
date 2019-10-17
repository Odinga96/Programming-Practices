/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packman.score;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import pacman.score.ScoreBoard;

/**
 *
 * @author User
 */
public class ScoreBoardTest {
    private ScoreBoard original ;
    private ScoreBoard orderedByNames;
    private ScoreBoard orderedByScore;
    private ScoreBoard afterAddingOne;
    private ScoreBoard afterAddingMultiple;
    
    @Before
    public void setUp(){
      original = new ScoreBoard();
      
      
      
      orderedByNames = new ScoreBoard();
      orderedByNames.setScore("a",0);
       Map<String, Integer> multiple1 = new HashMap<String, Integer> ();
        multiple1.put("b", 1);
        multiple1.put("c", 2);
        multiple1.put("d", 4);
      orderedByNames.setScore(multiple1);
      
      orderedByScore = new ScoreBoard();
      orderedByScore.setScore("a",0);
       Map<String, Integer> multiple2 = new HashMap<String, Integer> ();
        multiple2.put("b", 1);
        multiple2.put("c", 2);
        multiple2.put("d", 4);
      orderedByScore.setScore(multiple2);
      afterAddingOne = new ScoreBoard();
      afterAddingOne.setScore("a",0);
      
      afterAddingMultiple = new ScoreBoard();
      afterAddingMultiple.setScore("a",0);
      Map<String, Integer> multiple = new HashMap<String, Integer> ();
        multiple.put("b", 1);
        multiple.put("c", 2);
        multiple.put("d", 4);
      afterAddingMultiple.setScore(multiple);
    }
    
    
    @Test
    public void testAdding1(){
        original.setScore("a",0);
        assertTrue(original.equals(afterAddingOne));
    }
    
    
     public void testAddingMultiple(){
        Map<String, Integer> multiple = new HashMap<String, Integer> ();
        multiple.put("b", 1);
        multiple.put("c", 2);
        multiple.put("d", 4);
        original.setScore(multiple);
        assertTrue(original.equals(afterAddingMultiple));
    }
     
     @Test
     public void testIncreamentScore(){
         original.increaseScore(20);
         assertTrue(original.getScore() == 20);
     }
     @Test
     public void TestNames(){
         assertTrue(original.getEntriesByScore().equals(orderedByNames));
     }
     
     @Test
     public void TestScores(){
         assertTrue(original.getEntriesByScore().equals(orderedByNames));
     }
}
