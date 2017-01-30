package map;

public class Treasure {
	
	private int treasure = 0;
	
	public Treasure(){}
	
	public void add(int treasure){
		this.treasure += treasure;
	}
	
	public boolean isEmpty(){
		return (this.treasure == 0) ? true : false;
	}
	
	public int loot(){
		
		return this.treasure;
	}
}
