package fr.jnorb.mowitnow

import fr.jnorb.mowitnow.exception.MowerAlreadyHereException
import fr.jnorb.mowitnow.io.InputFileFormat
import fr.jnorb.mowitnow.io.InputMowItNow

object MowItNowApp extends App {
  
  if(args.size != 1)
  {
    println("One parameter is needed : <input file name>")
    sys.exit(-1)
  }
  
  val input: InputMowItNow = new InputFileFormat(args(0))
  val fieldAndMowersWithCommands = try {
    input.parse();
  } catch {
    case fnfe: java.io.FileNotFoundException => { println("Input file does not exist"); sys.exit(-1) }
    case _: Throwable => { println("Parsing of input file failed, check if its format is valid"); sys.exit(-1) } 
  }
  
  // get field
  val field: Field = fieldAndMowersWithCommands._1
  // get mowers with commands
  val mowersWithCommands: List[(Mower, List[MowerCommand.Value])] = fieldAndMowersWithCommands._2
  
  // for each mower (with commands)
  for(mowerWithCommands <- mowersWithCommands) {
    
    // get mower
    val mower = mowerWithCommands._1
    
    try
    {
      // add mower to field
      // can throw MowerAlreadyHereException
      mower.addToField()
      
      // if no exception has been thrown, apply commands sequentially
      for(command <- mowerWithCommands._2) {
        mower.applyMowerCommand(command)
      }
      
      // print mower
      println(mower)
    }
    catch
    {
      case e: MowerAlreadyHereException =>
        println("Cannot init mower, because there is already one in position "+e.pos);
//      ()
    }

  }
  
  
  


}