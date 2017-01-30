package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import exceptions.InvalidCoordinates;
import exceptions.InvalidInput;
import map.Map;
import qfa.carbonit.carte_aux_tresors.Adventurer;

/**
 * The Class Input.
 */
public class Input {
	
    /**
     * Return a list of the files contained in a folder
     *
     * @param url 
     * 		The name of the folder to iterate through
     * @return 
     * 		List of files
     * @throws IOException
     * 		Signals that an I/O exception has occurred
     */
    public static List<File> getFileList(String url) throws IOException {
    	
    	//Generate the path to the file with the URL
		Path path = Paths.get(url);
		
		//Check if the folder exists
		if (!Files.exists(path)) throw new IOException("Le dossier indiqué n'existe pas : " + path);

    	File folder = new File(path.toString());
    	
    	//Generate the list
    	List<File> fileList = new ArrayList<File>();
    	
    	//Fill the list with the files contained in the folder and his sub-folders
    	fileList = iterateThroughFolder(fileList, folder);
        
        return fileList;
    }
	
	/**
	 * Returns the content of a file.
	 *
	 * @param url
	 * 		URL of the file to get data from.
	 * @return
	 * 		List of the line(s) written in the file
	 * @throws IOException
	 * 		Signals that an I/O exception has occurred
	 */
	public static List<String> getFileContent(String url) throws IOException {
		
		//Generate the path to the file with the URL
		Path path = Paths.get(url);
		
		//Check for some errors that may happen 
		if (!Files.exists(path)) throw new IOException("Le fichier indiqué n'existe pas");
		if (!Files.isReadable(path)) throw new IOException("Le fichier indiqué n'est pas lisible");
		
		//Create a list of the line(s) contained in the file
		List<String> fileContent;
		fileContent = Files.readAllLines(path);
		
		return fileContent;
	}
	
	/**
	 * Creates a Map from a file.
	 *
	 * @param url
	 * 		The URL of the file to get the command from
	 * @return
	 * 		The Map object
	 * @throws
	 * 		InvalidCoordinates the invalid input
	 * @throws
	 * 		IOException Signals that an I/O exception has occurred.
	 * @throws InvalidInput 
	 */
	public static Map createMapFromFile(String url) throws IOException, InvalidInput {
		
		//Get the list of commands contained in the file
		List<String> commands = getFileContent(url);
		
		//Create the Map
		Map map = createMapFromCommand(commands);
		
		return map;
	}
	
	/**
	 * Creates a Map from a command.
	 *
	 * @param commands
	 * 		The command to use to create the adventurer
	 * @return
	 * 		The adventurer object
	 * @throws InvalidInput 
	 */
	public static Map createMapFromCommand(List<String> commands) throws InvalidInput {
		
		//Is the command valid ?
		validateMapCommands(commands);
    	
		//Breakdown of the first command into different instructions
		String[] instructions = commands.get(0).split(" ");
		int width = Integer.parseInt(instructions[1]);
		int height = Integer.parseInt(instructions[2]);
		
		//Creation of an empty map with the given dimensions
		Map map = new Map(width ,height);
		commands.remove(0);
  
		//Iteration through all the remaining commands contained in the file
    	for (String nextCommand : commands) {
    		
    		try {
    		
    		//Breakdown of the command into different instructions
    		instructions = nextCommand.split(" ");
    		int posX = Integer.parseInt(instructions[1].split("-")[0]);
    		int posY = Integer.parseInt(instructions[1].split("-")[1]);
    		
    		//If the instruction is to add a treasure
    		//   - Get the number of treasures
    		//   - Place the treasure
    		if (instructions[0].equalsIgnoreCase("T")){
    			int value = Integer.parseInt(instructions[2]);

						map.addTreasure(posX, posY, value);
	    		}
	    		
	    		//If the instruction is to add a mountain
	    		//   - Place the mountain
	    		if (instructions[0].equalsIgnoreCase("M")) {
		    			map.placeMountain(posX, posY);
	    		}
	    	} catch (InvalidCoordinates e) {
	    		System.out.println(e);
	    	}
    	}

		return map;
	}
	
	/**
	 * Creates an adventurer from a file.
	 *
	 * @param url
	 * 		The URL of the file to get the command from
	 * @return
	 * 		The adventurer object
	 * @throws
	 * 		InvalidCoordinates the invalid input
	 * @throws
	 * 		IOException Signals that an I/O exception has occurred.
	 * @throws InvalidInput 
	 */
	public static Adventurer createAdventurerFromFile(String url) throws IOException, InvalidInput {
		
		//Get the list of commands contained in the file
		List<String> commands = getFileContent(url);
		
		//Create the Adventurer
    	Adventurer adventurer = createAdventurerFromCommand(commands.get(0));
    	
		return adventurer;
	}
	
	/**
	 * Creates an adventurer from a command.
	 *
	 * @param command
	 * 		The command to use to create the adventurer
	 * @return
	 * 		The adventurer object
	 * @throws InvalidInput 
	 */
	public static Adventurer createAdventurerFromCommand(String command) throws InvalidInput {

		//Is the command valid ?
		validateAdventurerCommand(command);
    	
		//Breakdown of the command into different instructions
    	String[] instructions = command.split(" ");
    	
    	String name = instructions[0];
    	int initX = Integer.parseInt(instructions[1].split("-")[0]);
    	int initY = Integer.parseInt(instructions[1].split("-")[1]);
    	String orientation = instructions[2];
    	List<Character> moves = new ArrayList<Character>();
    	for(char c : instructions[3].toCharArray()) moves.add(c);
    	
    	//Creation of the new adventurer
    	Adventurer adventurer = new Adventurer(name, initX, initY, orientation, moves);
		
		return adventurer;
	}

	/**
	 * Iterate through the files of a folder to return a list of its files
	 *
	 * @param fileList
	 * 		The list where the function stores the files it finds
	 * @param folder
	 * 		The URL of the file to get the command from
	 * @return
	 * 		The list of files the folder contains
	 */
    private static List<File> iterateThroughFolder(List<File> fileList, File folder) {
    	
    	//Create a list of the files contained in the folder
    	File[] files = folder.listFiles();
    	
    	//Iterate through the files
        for (File file : files) {
        	//If the file isn't a folder, add it to the list.
        	//Otherwise, call this function to iterate through this folder
            if (file.isDirectory()) iterateThroughFolder(fileList, file);
            else fileList.add(file);
        }
    	
    	return fileList;
    }
	
	/**
	 * Validate an adventurer-creating command.
	 * The validation is based on the conformity of the command to a pre-decided Pattern
	 *
	 * @param command
	 * 		The command to validate
	 * @throws InvalidInput 
	 */
	private static void validateAdventurerCommand(String command) throws InvalidInput {
		
		//Creation of the pattern used to validate the command.
		Pattern adventurerPattern = Pattern.compile("^([a-z]|[A-Z]|[0-9]|[0-9])* [0-9]+\\-[0-9]+ [N/S/E/O/n/s/e/o] [A/G/D/a/g/d]*");
		
		//Creation of a matcher with the pattern
		Matcher adventurerMatcher = adventurerPattern.matcher(command);
		
		//Return the value of the matcher
		//TRUE if the command has been validated, FALSE otherwise
		if(!adventurerMatcher.matches()) throw new InvalidInput("Cannot create an Adventurer with this command : " + command);
	}
	
	/**
	 * Validate an map-creating command.
	 *
	 * @param fileContent
	 * 		The command to validate
	 * 		The first String contained in the list must the map-creating instruction
	 * 		The following Strings can  either add a treasure or a mountain
	 * @return
	 * 		TRUE if the command has been validated, FALSE if that's not the case.
	 */
	private static void validateMapCommands(List<String> fileContent) throws InvalidInput {
		
		//Creation of the pattern used to validate the first line.
		Pattern mapPatternFirstLine = Pattern.compile("^(C [0-9]+ [0-9]+){1}$");
		
		//Creation of the pattern used to validate the next lines.
		Pattern mapPatternNextLines = Pattern.compile("^((T [0-9]+\\-[0-9]+ [0-9]+)*|(M [0-9]+\\-[0-9]+)*)*$");
		
		//Creation of a matcher with the first pattern
		Matcher mapMatcher = mapPatternFirstLine.matcher(fileContent.get(0));
		
		//If one the lines is not validated, the 'validation' variable change definitely to FALSE
		if(!mapMatcher.matches()) throw new InvalidInput("Cannot create a Map with this command : " + fileContent.get(0));
		for (int i=1; i<fileContent.size(); i++) {
			mapMatcher = mapPatternNextLines.matcher(fileContent.get(i));
			if(!mapMatcher.matches()) throw new InvalidInput("Cannot create a Map with this command : " + fileContent.get(i));;
		}
	}
}


