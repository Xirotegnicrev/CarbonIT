package qfa.carbonit.carte_aux_tresors;

import java.util.List;
import exceptions.InvalidCoordinates;
import map.Map;

/* TODO
 * getNextMove() => handle exception
 * Synchronisation des thread ?
 */

/**
 * The Class Adventurer.
 */
public class Adventurer {
	
	/** Name of the adventurer */
	private String name;
	
	/** tile the Adventurer is currently on */
	private int posX, posY;
	
	/** Direction the adventurer is facing */
	private String facingDirection;
	
	/** Number of treasures currently in the adventurer's possession */
	private int inventory;
	
	/** Adventurer's remaining moves */
	private List<Character> moves;
	
	
	/**
	 * Instantiates a new adventurer.
	 *
	 * @param name
	 * 		Name of the adventurer
	 * @param posX
	 * 		X coordinates of the adventurer
	 * @param posY
	 * 		Y coordinates of the adventurer
	 * @param facingDirection
	 * 		Direction the adventurer is facing
	 * @param moves
	 * 		Adventurer's remaining moves
	 * @throws InvalidCoordinates 
	 */
	public Adventurer(String name, int posX, int posY, String facingDirection, List<Character> moves){
		
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.facingDirection = facingDirection;
		this.inventory = 0;
		this.moves = moves;		
	}
	
	/**
	 * Gets the name of the adventurer
	 *
	 * @return
	 * 		the name of the adventurer
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the longitude of the adventurer.
	 *
	 * @return
	 * 		The X coordinate of the adventurer
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Gets the latitude  of the adventurer
	 *
	 * @return
	 * 		The Y coordinate of the adventurer
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * Place the adventurer on a map at the given coordinates
	 *
	 * @param x
	 * 		X coordinate where to place the adventurer
	 * @param y
	 * 		Y coordinate where to place the adventurer
	 */
	public void placeAt(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Gets the facing direction of the adventurer
	 *
	 * @return
	 * 		The direction the adventure is facing
	 */
	public String getFacingDirection() {
		return facingDirection;
	}

	/**
	 * Gets the amount of treasure the adventurer has collected
	 *
	 * @return
	 * 		Number of treasure(s)
	 */
	public int getInventory() {
		return inventory;
	}
	
	/**
	 * Adds a certain amount of treasure(s) to an adventurer's inventory
	 *
	 * @param amount
	 * 		Number of treasure(s) to add
	 */
	public void addTreasure(int amount) {
		this.inventory =+ amount;
	}
	
	public char getNextMove() {
		return this.moves.get(0);
	}
	
	public String advance(Map map){
		
		this.moves.remove(0);
		
		try {
			if (facingDirection.equals("N")) {
				if (map.isCrossable(posX, posY-1)){
					posY--;
					return this.name + " avance vers le nord.";
				}
			}
			
			if (facingDirection.equals("E")) {
				if (map.isCrossable(posX+1, posY)){;
					posX++;
					return this.name + " avance vers l'est.";
				}
			}
			
			if (facingDirection.equals("S")) {
				if (map.isCrossable(posX, posY+1)){
					posY++;
					return this.name + " avance vers le sud.";
				}
			}
			
			if (facingDirection.equals("O") ) {
				if (map.isCrossable(posX-1, posY)) {
					posX--;
					return this.name + " avance vers l'ouest.";
				}
			}
		} catch (InvalidCoordinates e) {
			System.out.println(e);
		}
		
		return ""; 	
	}
	
	public String rotateLeft(){
		
		this.moves.remove(0);
		
		if (this.facingDirection.equals("E")) {
			this.facingDirection = "N";
			return this.name + " se tourne vers le nord.";
		}
		
		else if (this.facingDirection.equals("N")) {
			this.facingDirection = "O";
			return this.name + " se tourne vers l'ouest.";
		}
		
		else if (this.facingDirection.equals("O")) {
			this.facingDirection = "S";
			return this.name + " se tourne vers le sud.";
		}
		
		else {
			this.facingDirection = "E";
			return this.name + " se tourne vers l'est.";
		}
	}
	
	public String rotateRight(){
		
		this.moves.remove(0);
		
		if (this.facingDirection.equals("O")) {
			this.facingDirection = "N";
			return this.name + " se tourne vers le nord.";
		}
		
		else if (this.facingDirection.equals("S")) {
			this.facingDirection = "O";
			return this.name + " se tourne vers l'ouest.";
		}
		
		else if (this.facingDirection.equals("E")) {
			this.facingDirection = "S";
			return this.name + " se tourne vers le sud.";
		}
		
		else {
			this.facingDirection = "E";
			return this.name + " se tourne vers l'est.";
		}
	}
	
	/**
	 * Check if the adventurer has arrived at the end of his journey
	 *
	 * @return
	 * 		TRUE if there is no step remaining, FALSE otherwise
	 */
	public boolean isArrived() {
		return moves.isEmpty();
	}
	
	/**
	 * Get a report of the adventurer position and inventory
	 *
	 * @return
	 * 		the report
	 */
	public String report() {
		String msg = this.name.toUpperCase() + " se situe en " + this.posX + ", " + this.posY + "." + 
				"\n" + this.name.toUpperCase() + " a rammassé " + this.inventory + " trésors.";
		return msg;
	}
}
