package exceptions;

@SuppressWarnings("serial")
public class InvalidInput extends Exception {

	public InvalidInput() {
		
		super();
	}
	
	public InvalidInput(String error) {
		
		super(error);
	}
}
