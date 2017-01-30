package qfa.carbonit.carte_aux_tresors;

/**
 * The Class AdventurerMap.
 */
public class AdventurerMap {

	/** The adventurers. */
	private boolean adventurers[][];
	
	/**
	 * Instantiates a new adventurer map.
	 *
	 * @param mapWidth
	 * 		the map width
	 * @param mapHeight
	 * 		the map height
	 */
	public AdventurerMap(int mapWidth, int mapHeight){
		 
		//Initialization of the map adventurers[][]
		this.adventurers = new boolean[mapWidth][mapHeight];
		
		//Every tiles are set to be empty by default (value = FALSE)
		for( int i=0 ; i<adventurers.length; i++) {
			for( int j=0; j<adventurers[i].length; j++ ) {
				adventurers[i][j] = false;
			}
		}
	}
	
	/**
	 * Check the coordinates of a adventurer map. Return a boolean indicating the presence, or not, of a adventurer
	 *
	 * @param x
	 * 		X coordinate
	 * @param y
	 * 		Y coordinate
	 * @return TRUE if the map contains an adventurer at these coordinates, FALSE otherwise
	 */
	public boolean containsAdventurer(int x, int y) {
		
		return adventurers[x][y];
	}
}
