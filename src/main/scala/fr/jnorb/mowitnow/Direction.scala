package fr.jnorb.mowitnow

/**
 * Enumeration representing available directions
 * Order is important, because :
 * 		| Left(Direction(i)) = Direction((i - 1) % nbDirections)
 * 		| Right(Direction(i)) = Direction((i + 1) % nbDirections)
 * So we can easily add new directions (for example NE, between N and E)
 */
object Direction extends Enumeration {
  
  val North = Value("N")
//val NorthEast = Value("NE")
  val East = Value("E")
  val South = Value("S")
  val West = Value("W")

  /**
   * Returns left direction for d
   * @param d the current direction
   */
  def left(d: Direction.Value): Direction.Value = {
    val leftDirection = (d.id - 1) % Direction.maxId;
    if(leftDirection < 0) Direction(Direction.maxId + leftDirection) else Direction(leftDirection)        
  }

  /**
   * Returns right direction for d
   * @param d the current direction
   */
  def right(d: Direction.Value): Direction.Value = {
    Direction((d.id + 1) % Direction.maxId)
  }
  
}
