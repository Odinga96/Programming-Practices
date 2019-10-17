
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pacman.Gost.Blinky;
import pacman.Gost.Clyde;
import pacman.Gost.GhostType;
import pacman.hunter.Hungry;
import pacman.hunter.Hunter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 
 */
public class JdkTest {
    
  private  final Blinky blinky = new Blinky();
  private final Clyde clyde = new Clyde();
  
  private final Hunter hunter = new Hungry();
    
  
  
 @Test
 
 
 public void testBlinky(){
     assertTrue(blinky.getColour().equals("#d54e53")  && blinky.getType() == GhostType.BLINKY);
 }
 
 @Test
 public void testClyde(){
     assertTrue(clyde.getColour().equals("#e78c45")  && clyde.getType() == GhostType.CLYDE);
 }
 
 
 
}
