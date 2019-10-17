/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.util;

/**
 *
 * @author 
 */
public enum Direction {
    LEFT(-1,0), RIGHT(1,0), UP(0,-1), DOWN(0,1);
    private int x,y;
    private Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Position offset(){
        return new Position(x,y);
    }
    
    public Direction opposite(){
       if(this.equals(Direction.DOWN)){
           return Direction.UP;
       }
       if(this.equals(Direction.UP)){
           return Direction.DOWN;
       }
       
       if(this.equals(Direction.LEFT)){
           return Direction.RIGHT;
       }
       if(this.equals(Direction.RIGHT)){
           return Direction.LEFT;
       }
       return null;
    }
    
}
