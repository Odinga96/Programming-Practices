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
public class Clyde extends Ghost{
    public Clyde(){
        
    }
    
    public String  getColour(){
       return "#e78c45"; 
    }
    public GhostType  getType(){
        return GhostType.CLYDE;
    }
}
