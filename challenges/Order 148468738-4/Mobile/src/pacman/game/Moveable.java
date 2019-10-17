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
 * @author KEVIN
 */
public interface Moveable {
    
  Direction  getDirection();
  Position getPosition();
  void setDirection(Direction direction);
  void setPosition(Position position);
}
