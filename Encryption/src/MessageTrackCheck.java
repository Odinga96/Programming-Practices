/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class MessageTrackCheck {
    private int runningSum;
    public MessageTrackCheck(Integer offset)
    {
        this.runningSum =offset;
    }
    
    public void add(Integer n)
    {
        //PRE: -
        //POST: Adds n to the running sum
        this.runningSum +=n; 
    }
    
    public char check(){
        //PRE: -
        //POST: Returns the character that corresponds to the running sum mod 26;
        //0...25 correspond to a...z
        int val = this.runningSum % 26;
        int newval = val + 97; //Add 97 (which is the int representation -1 of 'a') to get the char representation
        char rep = (char)newval; //Convert to get the char representation which is obviously a small letter
        return rep;
    }
    
    public void reset(Integer offset)
    {
        //PRE: -
        //POST: Re-initialises the running sum to the given offset
        this.runningSum = offset;
    }
    
    
    
}
