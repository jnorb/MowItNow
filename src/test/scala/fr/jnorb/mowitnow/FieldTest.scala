package fr.jnorb.mowitnow;

import org.junit.Test;
import org.junit.Assert._;

import fr.jnorb.mowitnow.exception.MowerAlreadyHereException

class FieldTest {

  /**
   * A field with an invalid (negative) top-right x must return IllegalArgumentException
   * because requirement failed
   */
	@Test(expected = classOf[IllegalArgumentException])
	def testFieldWithInvalidTopRightX() {
		val f: Field = new Field(-1, 1)
	}
	
	/**
   * A field with an invalid (negative) top-right y must return IllegalArgumentException
   * because requirement failed
   */
	@Test(expected = classOf[IllegalArgumentException])
	def testFieldWithInvalidTopRightY() {
		val f: Field = new Field(1, -1)
	}
	
	/**
	 * Test hasPosition method
	 */
	@Test
	def testHasPosition() {
	  val f: Field = new Field(5, 5)
	  assertTrue(f.hasPosition(Position(0, 0)))
	  assertTrue(f.hasPosition(Position(2, 2)))
	  assertTrue(f.hasPosition(Position(5, 5)))
	  assertFalse(f.hasPosition(Position(5, 6)))
	  assertFalse(f.hasPosition(Position(6, 5)))
	  assertFalse(f.hasPosition(Position(-1, 0)))
	}
	
	/**
	 * Test addMower method
	 */
	@Test
	def testAddMowerDifferentPositions() {
	  val f: Field = new Field(5, 5)
	  f.addMower(new Mower("mower1", f, Position(1, 2), Direction.North))
	  assertEquals(f.mowers.size, 1)	  
	  f.addMower(new Mower("mower2", f, Position(3, 4), Direction.North))
	  assertEquals(f.mowers.size, 2)
	}
	
	/**
	 * Mowers cannot be initialized on the same position
	 */
	@Test
	def testAddMowerSamePosition() {
	  val f: Field = new Field(5, 5)
	  f.addMower(new Mower("mower1", f, Position(1, 2), Direction.North))
	  assertEquals(f.mowers.size, 1)	  
	  
	  try
	  {
	    f.addMower(new Mower("mower2", f, Position(1, 2), Direction.South))
	    fail()
	  } catch {
	    case e: MowerAlreadyHereException => ()
	  }
	  
	  assertEquals(f.mowers.size, 1)
	}
	
	/**
	 * Test hasMowerOn method
	 */
	@Test
	def testHasMowerOn() {
	  val f: Field = new Field(5, 5)
	  f.addMower(new Mower("mower1", f, Position(1, 2), Direction.North))
	  f.addMower(new Mower("mower2", f, Position(3, 4), Direction.North))
	  
    assertTrue(f.hasMowerOn(Position(1, 2)))
    assertTrue(f.hasMowerOn(Position(3, 4)))
    assertFalse(f.hasMowerOn(Position(2, 3)))
	}

	/**
	 * Test canBeAccessed method
	 */
	@Test
	def testCanBeAccessed() {
	  val f: Field = new Field(5, 5)
	  f.addMower(new Mower("mower1", f, Position(1, 2), Direction.North))
	  f.addMower(new Mower("mower2", f, Position(3, 4), Direction.North))
	  
	  assertTrue(f.canBeAccessed(Position(1, 3)))
	  assertTrue(f.canBeAccessed(Position(1, 4)))
	  assertFalse(f.canBeAccessed(Position(1, 2)))
	  assertFalse(f.canBeAccessed(Position(3, 4)))
	  assertFalse(f.canBeAccessed(Position(6, -1)))
	}

}
