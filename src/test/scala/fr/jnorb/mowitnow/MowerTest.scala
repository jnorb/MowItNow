package fr.jnorb.mowitnow;

import org.junit.Test;
import org.junit.Assert._;

class MowerTest {

  /**
   * Mower cannot be initialized outside the associated field
   */
	@Test(expected = classOf[IllegalArgumentException])
	def testMowerNotInField() {
		val f: Field = new Field(5, 5)
		val m1: Mower = new Mower("mower1", f, Position(5, 6), Direction.North)
	}
	
	/**
	 * Test if mowers have been updated after calling addToField
	 */
	@Test
	def testAddToField() {
	  val f: Field = new Field(5, 5)
		val m1: Mower = new Mower("mower1", f, Position(3, 2), Direction.North)
	  m1.addToField()
	  assertTrue(f.mowers.exists { m => m == m1 })
	}
	
	/**
	 * Test all commands (Left, Right, Ahead)
	 */
	@Test
	def ApplyMowerCommand() {
	  val f: Field = new Field(5, 5)
		val m1: Mower = new Mower("mower1", f, Position(5, 4), Direction.North)
	  m1.addToField()
	  
	  m1.applyMowerCommand(MowerCommand.Left)
	  assertEquals(m1.direction, Direction.West)
	  
	  m1.applyMowerCommand(MowerCommand.Right)
	  assertEquals(m1.direction, Direction.North)
	  
	  m1.applyMowerCommand(MowerCommand.Ahead)
	  assertEquals(m1.position, Position(5, 5))
	  
	}
	
	/**
	 * Test if mower stays if the top-right limit is reached
	 */
	@Test
	def testMowerCommandTopRightLimit() {
	  val f: Field = new Field(5, 5)
		val m1: Mower = new Mower("mower1", f, Position(5, 5), Direction.North)
	  m1.addToField()
	  
	  m1.applyMowerCommand(MowerCommand.Ahead)
	  // m1 must have not moved because it reaches the upper limit of the field
	  assertEquals(m1.position, Position(5, 5))
	  
	  m1.applyMowerCommand(MowerCommand.Right)
	  m1.applyMowerCommand(MowerCommand.Ahead)
	  // m1 must have not moved because it reaches the right limit of the field
	  assertEquals(m1.position, Position(5, 5))
	}
	
	/**
	 * Test if mower stays if the bottom-left limit is reached
	 */
	@Test
	def testMowerCommandBottomLeftLimit() {
	  val f: Field = new Field(5, 5)
		val m1: Mower = new Mower("mower1", f, Position(0, 0), Direction.South)
	  m1.addToField()
	  
	  m1.applyMowerCommand(MowerCommand.Ahead)
	  // m1 must have not moved because it reaches the lower limit of the field
	  assertEquals(m1.position, Position(0, 0))
	  
	  m1.applyMowerCommand(MowerCommand.Right)
	  m1.applyMowerCommand(MowerCommand.Ahead)
	  // m1 must have not moved because it reaches the left limit of the field
	  assertEquals(m1.position, Position(0, 0))
	}
	
	/**
	 * Test from wording :
	 * 1 2 N => 1 3 N
	 * 3 3 E => 5 1 E
	 */
	@Test
	def test2MowersFromWording() {
	  
    val field = new Field(5, 5)    
    val mower1 = new Mower("mower1", field, Position(1, 2), Direction.North);
    mower1.addToField()
    
    mower1.applyMowerCommand(MowerCommand.Left);
    mower1.applyMowerCommand(MowerCommand.Ahead);
    mower1.applyMowerCommand(MowerCommand.Left);
    mower1.applyMowerCommand(MowerCommand.Ahead);
    mower1.applyMowerCommand(MowerCommand.Left);
    mower1.applyMowerCommand(MowerCommand.Ahead);
    mower1.applyMowerCommand(MowerCommand.Left);
    mower1.applyMowerCommand(MowerCommand.Ahead);
    mower1.applyMowerCommand(MowerCommand.Ahead);
    
    val mower2 = new Mower("mower2", field, Position(3, 3), Direction.East);
    mower2.addToField()
    
    mower2.applyMowerCommand(MowerCommand.Ahead);
    mower2.applyMowerCommand(MowerCommand.Ahead);
    mower2.applyMowerCommand(MowerCommand.Right);
    mower2.applyMowerCommand(MowerCommand.Ahead);
    mower2.applyMowerCommand(MowerCommand.Ahead);
    mower2.applyMowerCommand(MowerCommand.Right);
    mower2.applyMowerCommand(MowerCommand.Ahead);
    mower2.applyMowerCommand(MowerCommand.Right);
    mower2.applyMowerCommand(MowerCommand.Right);
    mower2.applyMowerCommand(MowerCommand.Ahead);
    
    assertEquals(mower1.toString, "1 3 N")
    assertEquals(mower2.toString, "5 1 E")
	}
	
	/**
	 * Test mowers collision
	 */
	@Test
	def testMower2BlocksMower1() {
	  
    val field = new Field(5, 5)    
    val mower1 = new Mower("mower1", field, Position(1, 2), Direction.North)
    mower1.addToField()
    val mower2 = new Mower("mower2", field, Position(1, 3), Direction.East)
    mower2.addToField()
    
    mower1.applyMowerCommand(MowerCommand.Ahead)
    
    // mower1 must have not moved, because mower2 blocks mower1 on North direction
    assertEquals(mower1.position, Position(1, 2))
	}
	
}
