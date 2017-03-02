/**
 * 
 */
package my.assignment.messaging.exception;

/**
 * @author Bhushan
 *
 */
public class InvalidMessageException extends RuntimeException {
	
	public InvalidMessageException() {
		super("Invalid Message.");
	}

	public InvalidMessageException(String msg) {
		super(msg);
	}

	
}
