package map;

import exceptions.InvalidCoordinates;

/* TODO
 * exceptions.InvalidCoordinates: Coordinates beyond the limit of the map => indicate coordinates
 * Synchronisation des thread ?
 */


/**
 * The Class Map.
 */
public class Map {

	private Tile[][] map;
	
	/**
	 * Instantiates a new map.
	 *
	 * @param width
	 * 		Width of the map
	 * @param height
	 * 		Height of the map
	 */
	public Map(int width, int height) {
		
		map = new Tile[width][height];
		
		//Each tile of the map set with default value
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				map[i][j] = new Tile();
			}
		}
	}
	
	/**
	 * Place a mountain on a tile.
	 *
	 * @param x
	 * 		the X coordinate
	 * @param y
	 * 		the Y coordinate
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public void placeMountain(int posX, int posY) throws InvalidCoordinates {
		
		this.validateCoordinates(posX, posY);
		map[posX][posY].addMountain();
	}
	
	/**
	 * Checks if a tile is occupied by a mountain.
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @return
	 * 		TRUE, if a mountain is present at this location, FALSE otherwise
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public boolean isCrossable(int posX, int posY) throws InvalidCoordinates {
		
		this.validateCoordinates(posX, posY);
		return map[posX][posY].isCrossable();
	}
	
	/**
	 * Add a treasure on a tile
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @param value
	 * 		the value
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public void addTreasure(int posX, int posY, int treasure) throws InvalidCoordinates {
		
		this.validateCoordinates(posX, posY);
		map[posX][posY].addTreasure(treasure);
	}
	
	/**
	 * Checks if a tile contains a treasure.
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @return
	 * 		TRUE, if a treasure is present at this location, FALSE otherwise
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public boolean containsTreasure(int posX, int posY) throws InvalidCoordinates {

		this.validateCoordinates(posX, posY);
		return map[posX][posY].containsTreasure();
	}
	
	/**
	 * Gets the treasure of a specific tile.
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @return
	 * 		The list of treasures located there
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public int loot(int posX, int posY) throws InvalidCoordinates {
		this.validateCoordinates(posX, posY);
		return map[posX][posY].loot();
	}
	
	/**
	 * Validate coordinates.
	 *
	 * @param x
	 * 		The X coordinate
	 * @param y
	 * 		The Y coordinate
	 * @throws InvalidCoordinates
	 * 		Coordinates beyond the limits of the map
	 */
	public void validateCoordinates(int posX, int posY) throws InvalidCoordinates {
		if(posX<0 || posY<0 || posX>=map.length || posY>=map[0].length) throw new InvalidCoordinates("Coordinates beyond the limit of the map");
	}
}
