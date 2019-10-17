/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;

/**
 *
 * @author user
 */
public class NodeTransitionFunction {
    public NodeTransitionFunction(Integer ext, Integer KVal)
    {
        //CONSTRUCTOR: Sets the class to calculate f(x) = (x ^ exp) mod KVal
        this.exp = ext;
        this.KVal = KVal;
    }
    private Integer exp, KVal;
    public Integer apply(Integer val){
        //PRE: -
        //POST: Implements f(val)
        //note f(x) = x^exp mod k
        Double retval = Math.pow(val,this.exp) % this.KVal;
        return retval.intValue();
    }
    
    public BigInteger apply(BigInteger val)
    {
    	
        BigInteger retval = BigInteger.valueOf((int)Math.pow(val.intValue(),this.exp) % this.KVal);
        
        return retval;
    }
}
