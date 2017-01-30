package qfa.carbonit.carte_aux_tresors;

/**
 * The Class TreasureMap.
 */
public class TreasureMap {

	/** The treasures. */
	private int treasures[][];
	
	/**
	 * Instantiates a new treasure map.
	 * By default, each tile of this map is empty (contains 0 treasures)
	 *
	 * @param mapWidth
	 * 		The map width
	 * @param mapHeight
	 * 		The map height
	 */
	public TreasureMap(int mapWidth, int mapHeight){

		//Initialization the map treasures[][]
		this.treasures = new int[mapWidth][mapHeight];
		
		//Every tiles are set to be empty by default (value = 0)
		for( int i=0 ; i<treasures.length; i++) {
			for( int j=0; j<treasures[i].length; j++ ) {
				treasures[i][j] = 0;
			}
		}
	}

	/**
	 * Check the coordinates of a treasure map. Return a boolean indicating the presence, or not, of a treasure
	 *
	 * @param x
	 * 		X coordinate
	 * @param y
	 * 		Y coordinate
	 * @return TRUE if the map contains at least one treasure at these coordinates, FALSE otherwise
	 */
	public boolean containsTreasure(int x, int y) {
		
		return (this.treasures[x][y] > 1) ;
	}
	
	/**
	 * Indicate the number of treasure(s) situated at the given coordinates
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @return the number of treasure(s)
	 */
	public int getTreasure(int x, int y) {
		
		//Get the number of treasures situated at the specified coordinates (x, y)
		int value = this.treasures[x][y];
		
		//Remove these treasures from the map
		this.treasures[x][y] = 0;
				
		return value;
	}
	
	/**
	 * Adds a treasure at the given coordinates
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @param value
	 * 		Number of treasures to add at the location
	 */
	public void placeTreasure(int x, int y, int value) {
		
		//Add a given number of treasure (value) to the specified coordinates (x, y)
		this.treasures[x][y] = value;
	}
	
}
