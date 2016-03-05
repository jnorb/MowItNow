package fr.jnorb.mowitnow

/**
 * Represents a position, with x and y
 * Making a case class avoids to write new Position(x, y) for initializing
 */
case class Position(x: Int, y: Int) {

  /**
   * Returns a new position ahead, according to the direction parameter
   * @param direction the current direction
   */
  def ahead(direction: Direction.Value): Position =
  {
    direction match {
      case Direction.North => Position(x, y + 1)
      case Direction.East => Position(x + 1, y)
      case Direction.South => Position(x, y - 1)
      case Direction.West => Position(x - 1, y)
    }
  }
  
  /**
   * Returns string representation of this position
   */
  override def toString(): String = x + " " + y 
  
}