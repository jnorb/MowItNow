package fr.jnorb.mowitnow;

import org.junit.Test;
import org.junit.Assert._;

class DirectionTest {

  /**
   * Test left method (only 4 possibilities)
   */
	@Test
	def testLeft() {
		assertEquals(Direction.left(Direction.East), Direction.North)
		assertEquals(Direction.left(Direction.South), Direction.East)
		assertEquals(Direction.left(Direction.West), Direction.South)
		assertEquals(Direction.left(Direction.North), Direction.West)
	}
	
	/**
   * Test right method (only 4 possibilities)
   */
	@Test
	def testRight() {
		assertEquals(Direction.right(Direction.East), Direction.South)
		assertEquals(Direction.right(Direction.South), Direction.West)
		assertEquals(Direction.right(Direction.West), Direction.North)
		assertEquals(Direction.right(Direction.North), Direction.East)
	}

}
