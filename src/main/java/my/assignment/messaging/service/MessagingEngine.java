/**
 * 
 */
package my.assignment.messaging.service;

import my.assignment.messaging.bo.MessageType;
import my.assignment.messaging.commons.Bootstrap;
import my.assignment.messaging.dto.MessageResponse;
import my.assignment.messaging.dto.MessageResponse.Status;
import my.assignment.messaging.entity.Transaction;
import my.assignment.messaging.exception.InvalidMessageException;

/**
 * This service would be hosted for third party to send Messages
 * @author Bhushan
 * 
 */
public class MessagingEngine {

	
	

	protected static int messageCounter = 0;
	private MessageProcessor messageProcessor = new MessageProcessor();
	TransactionReport transactionReport = new TransactionReport();
	

	/**
	 * This api accepts message string. It processes message based on format.
	 * It return MessageResponse, which contains <code>status</code> ( SUCCESS , ERROR ,  HALT),
	 *	<code>messageReference</code> for the message which is unique reference number
	 * and <code>errorInfo</code> which contains error info in case of status is ERROR
	 * 
	 * @param message
	 * @return MessageResponse
	 */
	public MessageResponse sendMessage(String message) {
		MessageResponse messageResponse = null;
		if (messageCounter > 50) {
			// discard messages
			return new MessageResponse(Status.HALT, "",
					"Application is paused currently");
		}
		messageCounter++;
		
		try{
			MessageType messageType = messageProcessor.identifyMessageType(message);
	
			Transaction transaction = messageProcessor.processMessage(message, messageType);
	
			transactionReport.generateReport(messageCounter);
			
			messageResponse = new MessageResponse(Status.SUCCESS, transaction.getMessageReference() );
			
		}catch(InvalidMessageException ie){
			messageResponse = new MessageResponse(Status.ERROR,"",ie.getMessage() ); 
			if(Bootstrap.debug){
				ie.printStackTrace();
			}
		}catch (Exception e) {
			messageResponse = new MessageResponse(Status.ERROR,"","Error while processing message" );
			if(Bootstrap.debug){
				e.printStackTrace();
			}
		}

		return messageResponse;
	}
	
	public static void resetMessageCounter(){
		messageCounter = 0;
	}

}
