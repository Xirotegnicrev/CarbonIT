package map;

public class Tile {
	
	private Treasure treasure = new Treasure();
	private Mountain mountain = new Mountain();
	
	public Tile(){}
	
	public void addMountain(){
		this.mountain.add();
	}
	
	public boolean isCrossable(){
		boolean crossable = this.mountain.isEmpty();
		return crossable;
	}
	
	public void addTreasure(int treasure){
		this.treasure.add(treasure);
	}
	
	public boolean containsTreasure(){
		return !treasure.isEmpty();
	}
	
	public int loot(){
		return treasure.loot();
	}
}
