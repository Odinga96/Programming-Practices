/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.util;

import javafx.geometry.Pos;

/**
 *
 * @author 
 */
public class Position {
    private int x,y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;

    }

    public double distance​(Position other){
        return Math.sqrt((this.x - other.getX())*(this.x - other.getX())+(this.y - other.getY())*(this.y - other.getY()));
    }

    public Position add​(Position other){
        return new Position((this.x + other.getX()), (this.y + other.getY()));
    }

    public Position multiply​(int factor){
        return new Position(this.x*factor, this.y*factor);
    }

    public boolean equals​(Object other){
        return this.x == ((Position) other).getX() && this.y == ((Position) other).getY();
    }

    public int hashCode(){
        return   Integer.parseInt(""+this.x+""+this.y);
    }
}
