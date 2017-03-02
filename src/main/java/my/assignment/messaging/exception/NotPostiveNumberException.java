package my.assignment.messaging.exception;

public class NotPostiveNumberException extends InvalidMessageException {

	public NotPostiveNumberException() {
		super("Number is zero or negative.");
	}
	

}
