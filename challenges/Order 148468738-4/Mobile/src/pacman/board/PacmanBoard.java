/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import pacman.util.Position;

/**
 *
 * @author 
 */
public class PacmanBoard extends Object {
    private int width, height;
    private List<BoardItem> items;
    private Map<Position, BoardItem> itemsPosition;
    public PacmanBoard(int width, int height) throws IllegalArgumentException{
        if(width <=0 || height <=0){
             throw new IllegalArgumentException("Invalid dimentions");
        }
        this.width =width;this.height=height;
        this.items = new ArrayList<BoardItem>();
        itemsPosition = new HashMap<Position, BoardItem>();
    }
    
    public PacmanBoard(PacmanBoard other) throws NullPointerException {
        if(other == null){
            throw new NullPointerException ("No board to copy");
        }
        this.height = other.getHeight();
        this.width = other.getWidth();
        this.items = new ArrayList<BoardItem>();
         itemsPosition = new HashMap<Position, BoardItem>();
    }
    
    public BoardItem  eatDot(Position position){
      if(position == null){
          throw new NullPointerException ("Position can not be null");
      } 
      if(position.getX() > width || position.getY() > height){
          throw new IndexOutOfBoundsException  ("Position is not within the board's dimentions");
      }
      BoardItem  item=  itemsPosition.get(position);
      if(item == BoardItem.DOT ){
          itemsPosition.replace(position, BoardItem.BIG_DOT_SPAWN);
          return item;
      }
      if(item == BoardItem.BIG_DOT  ){
          itemsPosition.replace(position, BoardItem.NONE);
          return item;
      }
      return null;
    }
    
    public BoardItem  getEntry(Position position)throws IndexOutOfBoundsException, NullPointerException{
       if(position == null){
          throw new NullPointerException ("Position can not be null");
      } 
      if(position.getX() > width || position.getY() > height){
          throw new IndexOutOfBoundsException  ("Position is not within the board's dimentions");
      }
      
       Set set = itemsPosition.entrySet();
         Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         if(entry.getKey().equals(position)){
             return (BoardItem)entry.getValue();
         }
      }
        return null;
    }
    
    public Position  getGhostSpawn(){
         Set set = itemsPosition.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         if(entry.getValue() == (BoardItem)BoardItem.GHOST_SPAWN){
             return (Position)entry.getKey();
         }
      }
        return null;
    }
    
    public int  getHeight(){
        return this.height;
    }
    
    public Position  getPacmanSpawn(){
        Set set = itemsPosition.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         if(entry.getValue() == (BoardItem)BoardItem.PACMAN_SPAWN){
             return (Position)entry.getKey();
         }
      }
        return null;
    }
    
    public int  getWidth(){
        return this.width;
    }
    
    public boolean  isEmpty(){
        return items.isEmpty();
    }
    
    public void  reset(){
         Set set = itemsPosition.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         Position position = (Position)entry.getKey();
         BoardItem item = (BoardItem) entry.getValue();
         
         if(item == BoardItem.NONE){
           itemsPosition.replace(position, BoardItem.DOT);
         }
         if(item == BoardItem.BIG_DOT_SPAWN ){
           itemsPosition.replace(position, BoardItem.BIG_DOT);
         }
      }
    }
   public void setEntry​(Position position, BoardItem item) throws IndexOutOfBoundsException, NullPointerException{
      if(position == null){
          throw new NullPointerException ("Position can not be null");
      } 
      if(position.getX() > width || position.getY() > height){
          throw new IndexOutOfBoundsException  ("Position is not within the board's dimentions");
      }
      if(item ==BoardItem.GHOST_SPAWN ){
          Set set = itemsPosition.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         if(entry.getValue() == BoardItem.GHOST_SPAWN){
             entry.setValue(BoardItem.NONE);
         }
      }
        items.add(item);
        itemsPosition.put(position, item);
        return;
      }
      
      if(item ==BoardItem.PACMAN_SPAWN ){
         Set set = itemsPosition.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
         Map.Entry entry = (Map.Entry)iterator.next();
         if(entry.getValue() == BoardItem.PACMAN_SPAWN){
             entry.setValue(BoardItem.NONE);
         }
      }
        items.add(item);
        itemsPosition.put(position, item);
        return;   
      }
      items.add(item);
      itemsPosition.put(position, item);
      
//       Set set = itemsPosition.entrySet();
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()) {
//         Map.Entry entry = (Map.Entry)iterator.next();
//         
//      }
   }
}
