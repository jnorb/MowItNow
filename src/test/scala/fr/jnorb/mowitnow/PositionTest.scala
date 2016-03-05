package fr.jnorb.mowitnow;

import org.junit.Test;
import org.junit.Assert._;

class PositionTest {

  /**
   * Test ahead method (for all directions)
   */
	@Test
	def testAhead() {
		assertEquals(Position(0, 0).ahead(Direction.North), Position(0, 1))
		assertEquals(Position(0, 2).ahead(Direction.East), Position(1, 2))
		assertEquals(Position(3, 3).ahead(Direction.South), Position(3, 2))
		assertEquals(Position(4, 2).ahead(Direction.West), Position(3, 2))
	}
	
}
