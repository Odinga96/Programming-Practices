/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.score;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author User
 */
public class ScoreBoard {
    private Map<String, Integer> entities;
    private int score = 0;
    
    public ScoreBoard(){
        this.score =0;
        this.entities = new HashMap<String, Integer> ();
    }
    
    public void setScore​(String name, int score){
        entities.putIfAbsent(name, score);
    }
    public void setScore(Map<String, Integer> scores){
        entities.putAll(scores);
    }
    public int getScore(){
        return score;
    }
    
    public void increaseScore​(int additional){
        if(additional > 0){
            score+= additional;
        }
    }
    
    public void reset(){
       this.score = 0; 
    }
    
    public List<String> getEntriesByScore(){
        List<String> list = new ArrayList<String>();
         Set set = entities.entrySet();
         Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         list.add(entry.getKey()+": "+entry.getValue());
      }
        return list;
    }
    
    
}
