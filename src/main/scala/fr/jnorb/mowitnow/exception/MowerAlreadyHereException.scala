package fr.jnorb.mowitnow.exception

import fr.jnorb.mowitnow.Position

case class MowerAlreadyHereException(pos: Position) extends Exception