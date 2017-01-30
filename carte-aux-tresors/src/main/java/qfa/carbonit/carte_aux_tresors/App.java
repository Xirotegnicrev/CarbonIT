package qfa.carbonit.carte_aux_tresors;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.InvalidCoordinates;
import exceptions.InvalidInput;
import io.Input;
import io.Output;
import map.Map;
import thread.act;

/**
 * The Class App.
 */
public class App 
{
	
	/** The player folder path. */
	private static String PLAYER_FOLDER_PATH = ".\\Adventurers";
	
	/** The map folder path. */
	private static String MAP_FOLDER_PATH = ".\\Maps";
	
	/** The output folder path. */
	private static String OUTPUT_FOLDER_PATH = ".\\Output";
	
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws NumberFormatException the number format exception
     * @throws InvalidCoordinates the invalid coordinates
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
    	//Creation du flux de sortie
    	Output.createOut(OUTPUT_FOLDER_PATH);
    	
    	//Simulation GUI
    	List<Adventurer> adventurer = new ArrayList<Adventurer>();
    	Map map = null;
    	
    	List<String> adventurerFiles = new ArrayList<String>();
    	for(File file : Input.getFileList(PLAYER_FOLDER_PATH)) adventurerFiles.add(file.getName());
    	
    	List<String> mapFiles = new ArrayList<String>();
    	for(File file : Input.getFileList(MAP_FOLDER_PATH)) mapFiles.add(file.getName());
    	
    	Scanner sc = new Scanner(System.in);
    	String input = "";
    	
    	
    	/*******************************************************************************************************/
    	System.out.println("Je détecte " + mapFiles.size() + " fichier(s) de carte\n"
    			+ "Laquelle désirez-vous utiliser ? ");
    	
    	while(map==null){
    		for(String possibleMap : mapFiles) System.out.println("   - " + possibleMap);
    		try {
    			map = Input.createMapFromFile(MAP_FOLDER_PATH + "\\" + sc.nextLine());	
    		} catch(InvalidInput e) {
    			System.out.println(e + "\nSorry I haven't been able to create a map from this file, please choose another");
    		} catch(IOException e2) {
    			System.out.println(e2 + "\nSorry, it seems the files you've indicated doesn't exists or is unreadable");
    		}
		}
    	
		
    	
    	/*******************************************************************************************************/
    	System.out.print("Je détecte " + adventurerFiles.size() + " fichier(s) d'aventurier\n"
    			+ "Désirez-vous en utiliser un ? Y/N ");
    	input = sc.nextLine().toUpperCase();
    	
    	while(!input.equals("N")) {
    		if (input.equals("Y")) {
    			System.out.println("lequel ?");
    			
    			for(String adv : adventurerFiles) System.out.println(" - " + adv);
    			
    			try{
					Adventurer adv = Input.createAdventurerFromFile(PLAYER_FOLDER_PATH + "\\" + sc.nextLine());
					adventurer.add(adv);
        		} catch(InvalidInput e) {
        			System.out.println(e + "\nSorry I haven't been able to create an adventurer from this file");
        		} catch(IOException e2) {
        			System.out.println(e2 + "\nSorry, it seems the files you've indicated doesn't exists or is unreadable");
        		}
    		}
    		System.out.print("Desirez-vous en utiliser un autre ? Y/N ");
    		input = sc.nextLine().toUpperCase();
    	}
    	
    	
    	/*******************************************************************************************************/
    	System.out.print("Peut-être désirez-vous intégrer vous même un aventurier ? Y/N ");
    	input = sc.nextLine().toUpperCase();
    	
    	while(!input.equals("N")) {
	    	if (input.equals("Y")) {
				System.out.println("Quelles sont ses instructions ?");
				
				try{
	        		Adventurer adv = Input.createAdventurerFromCommand(sc.nextLine());
	        		adventurer.add(adv);
        		} catch(InvalidInput e) {
        			System.out.println(e + "\nSorry I haven't been able to create an adventurer with these instructions");
        		}
			}
	    	System.out.print("Desirez-vous en intégrer un autre ? Y/N ");
    		input = sc.next().toUpperCase();
    	}
    	
    	//Each adventurer lives his journey
    	for(Adventurer adv : adventurer){
    		try {
				map.validateCoordinates(adv.getPosX(), adv.getPosY());
				Thread t = new Thread(new act(map, adv));
				
				while(!adv.isArrived()) {
	    			t.run();
	    		}
	    		Output.writeReport("--------------------------------------------------------------J'ai atteint la fin de mon voyage\n"
	    				+ adv.report() + "\n\n\n");
			} catch (InvalidCoordinates e) {
				System.out.println(e + "\nIt seems " + adv.getName() + "is unable to enter this map. Mes coordonnées de départ doivent être incorrectes");
			}
    		
    		
		}
		sc.close();
		Output.closeOut();
        System.out.println("END");
    }
}
