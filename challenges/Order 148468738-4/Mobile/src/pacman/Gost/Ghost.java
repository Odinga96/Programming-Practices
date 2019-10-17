/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.Gost;

import pacman.game.Entity;
import pacman.game.Moveable;
import pacman.util.Direction;
import pacman.util.Position;

/**
 *
 * @author 
 */
public abstract class Ghost extends Entity{
    private boolean alive;
    private Phase phase;
    private int duration;
    private Position position;
    private Direction direction ;
    
    public Ghost(){
        
        this.alive = true;
        this.phase = Phase.SCATTER;
        this.duration = Phase.SCATTER.getDurtion();
        this.position = new Position(0,0);
        this.direction = Direction.UP;
        
    }
   public void setPhase​(Phase newPhase, int duration){
       if( newPhase != null ){
           this.phase = newPhase;
       }
       if(duration > 0){
           this.duration = duration;return;
       }
       this.duration = 0;
   }
   
   public Phase getPhase(){
       return this.phase;
   }
   public String phaseInfo(){
       return ""+this.phase+":"+this.phase.getDurtion();
   }
   public void kill(){
       this.alive = false;
   }
   
   public boolean isDead(){
       return !alive;
   }
   
   public void reset(){
        this.alive = true;
        this.phase = Phase.SCATTER;
        this.duration = Phase.SCATTER.getDurtion();
        this.position = new Position(0,0);
        this.direction = Direction.UP;
   }
   
   public abstract String getColour();
   public abstract GhostType getType();
       
   
}
