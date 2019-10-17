/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.hunter;

import pacman.Gost.Ghost;
import pacman.Gost.Phase;
import pacman.game.Entity;
import pacman.util.Direction;
import pacman.util.Position;

/**
 *
 * @author 
 */
public abstract class Hunter extends Entity {
    
    private boolean alive;
    private boolean specialActive;
    private int specialDuration;
    private boolean specialUsed;
    public Hunter(){
        this.alive = true;
        this.specialUsed = false;
        this.setDirection(Direction.UP);
        this.setPosition(new Position(0,0));
        this.specialActive = false;
        
    }
    public Hunter(Hunter original){
        this.alive = !original.isDead();
        this.specialActive = original.isSpecialActive();
        this.specialDuration =original.getSpecialDurationRemaining();
        this.setDirection(original.getDirection());
        this.setPosition(original.getPosition());
    }
    
    public void activateSpecial(int duration) throws Exception{
        if(!this.specialActive){
            this.specialActive = true;
            
            if(duration > 0){
                this.specialDuration = duration;
            }
        }
        throw new Exception("Lacking implementation");
    }
    
    public int getSpecialDurationRemaining(){
        return this.specialDuration;
    }
    
    public void hit​(Ghost ghost) throws NullPointerException{
        if(ghost == null){
            throw new NullPointerException("No ghost to hit");
        }
        if(this.getPosition().equals(ghost.getPosition())){
            if(ghost.getPhase() == Phase.FRIGHTENED){
                ghost.kill();
                return;
            }
            this.alive = false;
            
        }
    }
    
    public boolean isDead(){
        return !alive;
    }
    
   public boolean isSpecialActive(){
      return specialActive; 
   }
   
   public void reset(){
        this.alive = true;
        this.specialUsed = false;
        this.setDirection(Direction.UP);
        this.setPosition(new Position(0,0));
        this.specialActive = false;  
   }
   
}
