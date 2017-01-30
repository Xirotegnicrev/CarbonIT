package exceptions;

@SuppressWarnings("serial")
public class InvalidCoordinates extends Exception {
	
	public InvalidCoordinates() {
		
		super();
	}
	
	public InvalidCoordinates(String error) {
		
		super(error);
	}
}
