package fr.jnorb.mowitnow

/**
 * Available commands for a mower
 */
object MowerCommand extends Enumeration {
  val Ahead = Value("A")
  val Left = Value("G")
  val Right = Value("D")
}