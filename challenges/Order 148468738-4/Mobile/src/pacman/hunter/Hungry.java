/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.hunter;

import pacman.Gost.Ghost;

/**
 *
 * @author 
 */
public class Hungry extends Hunter {

   public Hungry(){
      super(); 
   }
    public Hungry(Hunter original){
       super(original);
   }
    
    public void hit(Ghost ghost){
       if(this.isSpecialActive()){
         if(this.getPosition().equals(ghost.getPosition())){
             ghost.kill();return;
         }  
       }
       super.hit(ghost);
    }
    
}
