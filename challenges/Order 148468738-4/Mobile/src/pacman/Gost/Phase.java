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
public enum Phase{
    CHASE(20),SCATTER(10),FRIGHTENED(30);
    private int duration;
    private Phase(int duration){
        this.duration = duration;
    }
    
    public int getDurtion(){
        return this.duration;
    }
}
