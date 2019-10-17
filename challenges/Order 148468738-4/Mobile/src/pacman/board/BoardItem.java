/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.board;

/**
 *
 * @author Joakim 
 */
public enum BoardItem {

    NONE(true,0,'0'),
    WALL(false,0,'X'),
    DOT(true,10,'1'),
    BIG_DOT(true,15,'B'),
    
    BIG_DOT_SPAWN(true,0,'b'),
    GHOST_SPAWN(true, 0, '$'),
    PACMAN_SPAWN(true, 0, 'P');
    
    
    
    private boolean pathable;
    private int score;
    private char key;
    
   
    private BoardItem(boolean pathable, int score, char key){
       this.pathable = pathable;
       this.score = score;
       this.key = key;
    }

    public char getChar(){
        return this.key;
    }
    public static BoardItem getItem(char key){
        switch(key){
            case '0':
            return BoardItem.NONE;
            case 'X':
            return BoardItem.WALL;
            case '1':
            return BoardItem.DOT;
            case 'B':
            return BoardItem.BIG_DOT;
            case 'b':
            return BoardItem.BIG_DOT_SPAWN;
            case '$':
            return BoardItem.GHOST_SPAWN;
            case 'P':
            return PACMAN_SPAWN;
            default:
            return null;
        }
    }
    public boolean getPathable(){
        return this.pathable;
    }

    public int getScore(){
        return this.score;
    }
   
   
}

