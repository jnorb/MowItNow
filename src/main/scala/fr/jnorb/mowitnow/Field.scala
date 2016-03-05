package fr.jnorb.mowitnow

import fr.jnorb.mowitnow.exception.MowerAlreadyHereException;

class Field(toprightx: Int, toprighty: Int) {
  
  /**
   * Top-right x and top-right y must be positive
   */
  require(toprightx >= 0, "Top-right x must be >= 0")
  require(toprighty >= 0, "Top-right y must be >= 0")
  
  /**
   * Set of mowers on that field
   */
  var mowers = Set[Mower]()
  
  /**
   * Returns true if pos is inside the field
   * @param pos the position to test
   */
  def hasPosition(pos: Position): Boolean =
  {
    pos.x >= 0 && pos.y >= 0 && pos.x <= toprightx && pos.y <= toprighty
  }
  
  /**
   * Returns true if there is a mower on pos
   * @param pos the position to test
   */
  def hasMowerOn(pos: Position): Boolean =
  {
    !mowers.find {
        m => m.position == pos
    }.isEmpty
  }
  
  /**
   * Returns true if :
   * - pos is inside the field
   * - there is no mower on pos
   */
  def canBeAccessed(pos: Position): Boolean =
  {
    hasPosition(pos) && !hasMowerOn(pos)
  }
  
  /**
   * Add a mower to the current field
   * @param mower the mower to add
   * @throws MowerAlreadyHereException if there is already a mower on the same place on the field
   */
  @throws(classOf[MowerAlreadyHereException])
  def addMower(mower: Mower)
  {
	  if (!hasMowerOn(mower.position)) mowers += mower else throw new MowerAlreadyHereException(mower.position)
  }
  
}