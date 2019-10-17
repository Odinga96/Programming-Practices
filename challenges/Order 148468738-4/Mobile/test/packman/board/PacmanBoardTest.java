/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packman.board;

import pacman.board.BoardItem;
import pacman.board.PacmanBoard;
import pacman.util.Position;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author User
 */
public class PacmanBoardTest {
    PacmanBoard original;
    PacmanBoard boardAfterAddingGhostSpown;
    PacmanBoard boardAfterAddingPacmanSpawn;
    PacmanBoard boadsAfterEatingDots;
    PacmanBoard boardAfterReset;
    
    
    
    @Before
    public void setUp(){
        original = new PacmanBoard(52,32);
        original.setEntry(new Position(1,1), BoardItem.NONE);
        original.setEntry(new Position(2,1), BoardItem.NONE);
        original.setEntry(new Position(1,3), BoardItem.NONE);
        original.setEntry(new Position(4,1), BoardItem.NONE);
        original.setEntry(new Position(7,1), BoardItem.GHOST_SPAWN);
        original.setEntry(new Position(8,1), BoardItem.PACMAN_SPAWN);
        original.setEntry(new Position(12,3), BoardItem.BIG_DOT);
        original.setEntry(new Position(12,3), BoardItem.DOT);
        
        boardAfterAddingGhostSpown = new PacmanBoard(52,32);
        boardAfterAddingGhostSpown.setEntry(new Position(1,1), BoardItem.NONE);
        boardAfterAddingGhostSpown.setEntry(new Position(2,1), BoardItem.NONE);
        boardAfterAddingGhostSpown.setEntry(new Position(1,3), BoardItem.NONE);
        boardAfterAddingGhostSpown.setEntry(new Position(4,1), BoardItem.NONE);
        boardAfterAddingGhostSpown.setEntry(new Position(7,1), BoardItem.NONE);
        boardAfterAddingGhostSpown.setEntry(new Position(8,1), BoardItem.PACMAN_SPAWN);
        boardAfterAddingGhostSpown.setEntry(new Position(9,1), BoardItem.GHOST_SPAWN);
        boardAfterAddingGhostSpown.setEntry(new Position(12,3), BoardItem.BIG_DOT);
        boardAfterAddingGhostSpown.setEntry(new Position(12,3), BoardItem.DOT);
        
        boardAfterAddingPacmanSpawn = new PacmanBoard(52,32);
        boardAfterAddingPacmanSpawn.setEntry(new Position(1,1), BoardItem.NONE);
        boardAfterAddingPacmanSpawn.setEntry(new Position(2,1), BoardItem.NONE);
        boardAfterAddingPacmanSpawn.setEntry(new Position(1,3), BoardItem.NONE);
        boardAfterAddingPacmanSpawn.setEntry(new Position(4,1), BoardItem.NONE);
        boardAfterAddingPacmanSpawn.setEntry(new Position(7,1), BoardItem.GHOST_SPAWN);
        boardAfterAddingPacmanSpawn.setEntry(new Position(8,1), BoardItem.NONE);
        boardAfterAddingPacmanSpawn.setEntry(new Position(9,1), BoardItem.GHOST_SPAWN);
        boardAfterAddingPacmanSpawn.setEntry(new Position(11,1), BoardItem.PACMAN_SPAWN);
        boardAfterAddingPacmanSpawn.setEntry(new Position(12,3), BoardItem.BIG_DOT);
        boardAfterAddingPacmanSpawn.setEntry(new Position(12,4), BoardItem.DOT);
        
        
        boadsAfterEatingDots = new PacmanBoard(52,32);
        boadsAfterEatingDots.setEntry(new Position(1,1), BoardItem.NONE);
        boadsAfterEatingDots.setEntry(new Position(2,1), BoardItem.NONE);
        boadsAfterEatingDots.setEntry(new Position(1,3), BoardItem.NONE);
        boadsAfterEatingDots.setEntry(new Position(4,1), BoardItem.NONE);
        boadsAfterEatingDots.setEntry(new Position(7,1), BoardItem.GHOST_SPAWN);
        boadsAfterEatingDots.setEntry(new Position(8,1), BoardItem.NONE);
        boadsAfterEatingDots.setEntry(new Position(9,1), BoardItem.GHOST_SPAWN);
        boadsAfterEatingDots.setEntry(new Position(11,1), BoardItem.PACMAN_SPAWN);
        boadsAfterEatingDots.setEntry(new Position(12,3), BoardItem.BIG_DOT_SPAWN);
        boadsAfterEatingDots.setEntry(new Position(12,4), BoardItem.NONE);
        
        
        boardAfterReset = new PacmanBoard(52,32);
        boardAfterReset.setEntry(new Position(1,1), BoardItem.DOT);
        boardAfterReset.setEntry(new Position(2,1), BoardItem.DOT);
        boardAfterReset.setEntry(new Position(1,3), BoardItem.DOT);
        boardAfterReset.setEntry(new Position(4,1), BoardItem.DOT);
        boardAfterReset.setEntry(new Position(7,1), BoardItem.GHOST_SPAWN);
        boardAfterReset.setEntry(new Position(8,1), BoardItem.DOT);
        boardAfterReset.setEntry(new Position(9,1), BoardItem.GHOST_SPAWN);
        boardAfterReset.setEntry(new Position(11,1), BoardItem.PACMAN_SPAWN);
        boardAfterReset.setEntry(new Position(12,3), BoardItem.BIG_DOT_SPAWN);
        boardAfterReset.setEntry(new Position(12,3), BoardItem.NONE);
        
        
        
       
        
        
        
    
    }
    @Test
     public void testAddGhostSpawn(){
        original.setEntry(new Position(9,1), BoardItem.GHOST_SPAWN);
        assertTrue(original.equals(boardAfterAddingGhostSpown));
    }
     
     @Test
     public void testAddPacmanSpawn(){
        original.setEntry(new Position(11,1), BoardItem.PACMAN_SPAWN);
        assertTrue(original.equals(boardAfterAddingPacmanSpawn));
    }
     @Test
     public void testEatDots(){
         original.eatDot(new Position(12,3));
         original.eatDot(new Position(12,4));
         assertTrue(original.equals(boadsAfterEatingDots));
     }
     
    @Test
     public void testGetWidth(){
         assertTrue(original.getWidth() == 52);
     }
     
     @Test
     public void testGetHeight(){
         assertTrue(original.getWidth() == 32);
     }
     
     
}


