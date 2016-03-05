package fr.jnorb.mowitnow.io

import fr.jnorb.mowitnow.Field
import fr.jnorb.mowitnow.Mower
import fr.jnorb.mowitnow.MowerCommand

/**
 * Trait extended by all input formats
 */
trait InputMowItNow {
  
  /**
   * Returns a tuple containing :
   * - a field
   * - an ordered list of mowers, with their associated commands
   * 
   * This method must be overrode by all possible input formats
   */
  def parse(): (Field, List[(Mower, List[MowerCommand.Value])])
  
}