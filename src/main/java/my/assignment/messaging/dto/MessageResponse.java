/**
 * 
 */
package my.assignment.messaging.dto;

/**
 * 
 * 
 * @author Bhushan
 *
 */
public class MessageResponse {
	
	
	public enum Status { SUCCESS , ERROR ,  HALT};
	

	public MessageResponse(){
	}
	
	public MessageResponse(Status status, String messageReference) {
		this.status = status;
		this.messageReference = messageReference;
	}

	public MessageResponse(Status status, String messageReference,
			String errorInfo) {
		this.status = status;
		this.messageReference = messageReference;
		this.errorInfo = errorInfo;
	}

	private Status status;
	
	/**
	 * messageReference is unique reference to the message which will be shared with client application for future reference.
	 */
	private String messageReference;
	
	/**
	 * This field will be only poulated when there is any error while processing
	 */
	private String errorInfo;

	

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the messageReference
	 */
	public String getMessageReference() {
		return messageReference;
	}

	/**
	 * @param messageReference the messageReference to set
	 */
	public void setMessageReference(String messageReference) {
		this.messageReference = messageReference;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo() {
		return errorInfo;
	}

	/**
	 * @param errorInfo the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageResponse [status=" + status + ", messageReference="
				+ messageReference + ", errorInfo=" + errorInfo + "]";
	}

	
}
