/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.game;

import pacman.util.Direction;
import pacman.util.Position;

/**
 *
 * @author 
 */
public  abstract class  Entity extends Object implements Moveable {
    private Position position;
    private Direction direction;
    
    public Entity(){
        this.position = new Position(0,0);
        this.direction = Direction.UP;
    }
    public Entity(Position position , Direction direction){
         this.position = position;
         this.direction = direction;
    }
    @Override
    public Direction getDirection(){
        return this.direction;
    }
    @Override
    public Position getPosition(){
        return this.position;
    }
    
    @Override
    public void setPosition(Position position){
        this.position = position;
    }
    @Override
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
}
