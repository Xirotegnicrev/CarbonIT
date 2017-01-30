package qfa.carbonit.carte_aux_tresors;

/**
 * The Class MountainMap.
 */
public class MountainMap {

	/** The mountains. */
	private boolean mountains[][];
	
	/**
	 * Instantiates a new mountain map.
	 * By default, each tile of this map is a empty (does not contains a mountain)
	 *
	 * @param mapWidth
	 * 		The map width
	 * @param mapHeight
	 * 		The map height
	 */
	public MountainMap(int mapWidth, int mapHeight){

		//Initialization of the map mountains[][]
		this.mountains = new boolean[mapWidth][mapHeight];
		
		//Every tiles are set to be land by default (value = FALSE)
		for( int i=0 ; i<this.mountains.length; i++) {
			for( int j=0; j<this.mountains[i].length; j++ ) {
				this.mountains[i][j] = false;
			}
		}
	}
	
	/**
	 * Check the coordinates of a mountain map. Return a boolean indicating the presence, or not, of a mountain
	 *
	 * @param x
	 * 		X coordinate
	 * @param y
	 * 		Y coordinate
	 * @return TRUE if the map contains a mountains at these coordinates, FALSE otherwise
	 */
	public boolean containsMountain(int x, int y) {
		
		return this.mountains[x][y];
	}
	
	/**
	 * Adds a mountain at the given coordinates
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 */
	public void placeMountain(int x, int y) {
		
		//set the specified coordinates as containing a mountain
		this.mountains[x][y] = true;
	}
}
