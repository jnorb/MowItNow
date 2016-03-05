package fr.jnorb.mowitnow.io

import scala.io.Source
import scala.collection.mutable.ListBuffer
import fr.jnorb.mowitnow.{Direction, Field, Mower, MowerCommand, Position}

/**
 * Read a file using format :
 * 5 5
 * 1 2 N
 * GAGAGAGAA
 * 3 3 E
 * AADAADADDA
 */
class InputFileFormat(filename: String) extends InputMowItNow {
  
  override def parse(): (Field, List[(Mower, List[MowerCommand.Value])]) =
  {
    var listMowersWithCommands = new ListBuffer[(Mower, List[MowerCommand.Value])]()
    
    // read file
    val iterator = Source.fromFile(filename).getLines()
    
    // first line defines field config (toprightx toprighty)
    val fieldCfg = iterator.next().split(" ").map(_.toInt)
    val field = new Field(fieldCfg(0), fieldCfg(1))
    
    var cpt = 0;
    
    // next lines defines mowers
    for(line <- iterator)
    {
      // first line represents initial position and initial direction...
      val mowerCfg = line.split(" ")
      
      val initialx = mowerCfg(0).toInt
      val initialy = mowerCfg(1).toInt
      val initialdir = Direction withName mowerCfg(2)
      
      // initialize this mower
      val mower = new Mower("mower"+cpt, field, Position(initialx, initialy), initialdir)
      
      // next line lists commands to apply (each character represents a command)
      val mowerCommands = iterator.next().map(_.toString)
      var listMowerCommands = new ListBuffer[MowerCommand.Value]()
      
      for(cmd <- mowerCommands)
      {
//        mower.applyMowerCommand(MowerCommand withName cmd)
        listMowerCommands += MowerCommand withName cmd
      }
      
      listMowersWithCommands += ((mower, listMowerCommands.toList))
      
      cpt = cpt + 1
    }
    
    (field, listMowersWithCommands.toList)
  }
  
}