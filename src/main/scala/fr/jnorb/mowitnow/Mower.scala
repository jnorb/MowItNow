package fr.jnorb.mowitnow;

import Direction._;

/**
 * Represents a mower with :
 * - a name
 * - an initial position
 * - a initial direction
 */
class Mower(name: String, field: Field, initialPosition: Position, initialDirection: Direction.Value) {
  
  require(field.hasPosition(initialPosition), "Mower must be on a position contained in the field")
  
  // mutable values because position and direction can change according to MowerCommands
  var position = initialPosition;
  var direction = initialDirection;
  
  def addToField() {
    field.addMower(this)
  }
  
  /**
   * Apply a command on a mower
   * @param cmd the command to apply
   */
  def applyMowerCommand(cmd: MowerCommand.Value) {

    
    cmd match {
      case MowerCommand.Left => direction = Direction.left(direction)
      case MowerCommand.Right => direction = Direction.right(direction)
      case MowerCommand.Ahead =>
        val newPosition = position.ahead(direction);
        // only move mower (change position) if newPosition can be accessed
        if(field.canBeAccessed(newPosition)) {
          position = newPosition
        }
    }
    
  }
  
  /**
   * Overrides toString() method
   * Result looks like 1 2 N
   */
  override def toString(): String =
//  name + " " + position + " " + direction;
    position + " " + direction;

}
