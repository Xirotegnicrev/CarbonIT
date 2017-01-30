package map;

public class Mountain {

	private boolean mountain = false;
	
	public Mountain(){}
	
	public void add(){
		this.mountain= true;
	}
	
	public boolean isEmpty(){
		return !this.mountain;
	}
}
