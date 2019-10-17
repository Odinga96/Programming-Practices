/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.hunter;
/**
 *
 * @author 
 */
public class Phil extends Hunter{
    public Phil(){
        super();
    }
    public Phil(Hunter original){
        super(original);
    }
    
    @Override
    public boolean isSpecialActive(){
        return false;
    }
    
   
}
