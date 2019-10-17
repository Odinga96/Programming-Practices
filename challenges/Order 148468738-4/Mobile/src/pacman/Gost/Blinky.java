/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.Gost;

/**
 *
 * @author 
 */
public class Blinky extends Ghost {
    
    public Blinky(){
        
    }
    
    public String getColour(){
        return "#d54e53";
    }
    
    public GhostType getType(){
        return GhostType.BLINKY;
    }
}
