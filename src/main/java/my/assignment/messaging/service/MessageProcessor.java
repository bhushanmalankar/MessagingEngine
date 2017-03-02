/**
 * 
 */
package my.assignment.messaging.service;

import java.sql.Timestamp;
import java.util.Set;
import java.util.regex.Matcher;

import my.assignment.messaging.bo.MessageType;
import my.assignment.messaging.bo.MessageType.Type;
import my.assignment.messaging.commons.ApplicationCache;
import my.assignment.messaging.commons.Bootstrap;
import my.assignment.messaging.commons.Utility;
import my.assignment.messaging.entity.Transaction;
import my.assignment.messaging.entity.TransactionAdjustment;
import my.assignment.messaging.entity.TransactionSale;
import my.assignment.messaging.exception.InvalidMessageFormatException;

/**
 * @author Bhushan
 *
 */
public class MessageProcessor {
	
	
	/**
	 * This method accepts message and process the sale information according to it's message type.
	 * It returns transaction related to corresponding sale.
	 * 
	 * @param message
	 * @param messageType
	 * @return Transaction
	 */
	protected Transaction processMessage(
			String message, MessageType messageType) {
		
		Transaction transaction = buildTransaction(message,messageType);
		
		Operation operation = OperationFactory.getMessageOperation(messageType);
		
		operation.buildTransactionMessage(message, transaction);
		
		operation.perform(transaction);
		
		
		
		return transaction;
	}
	
	

	
	/**
	 * This method build and return transaction object based on messageType.
	 * 
	 * @param message
	 * @param messageType
	 * @return Transaction
	 */
	protected Transaction buildTransaction(String message,
			MessageType messageType) {
		Transaction transaction = null;
		if(Type.AdjustmentOfSale.equals(messageType.getType())){
			transaction = new TransactionAdjustment();
		}else{
			transaction = new TransactionSale();
		}
		
		transaction.setMessage(message);
		transaction.setMessageType(messageType);
		transaction.setMessageReference(Utility.generateMessageReference());
		transaction.setCreatedOn(new Timestamp( System.currentTimeMillis()));
		transaction.setModifiedOn(transaction.getModifiedOn());
		return transaction;
	}

	/**
	 * This method identifies MessageType based on pattern matched. It returns first messageType which matches with message.
	 * If no messageType pattern is matched, method returns null.
	 * @param message
	 * @return MessageType
	 */
	protected MessageType identifyMessageType(String message) {
		 Set<MessageType> messageTypes = ApplicationCache.getMessageTypes();
			for (MessageType messageType : messageTypes) {
				Matcher matcher = messageType.getMsgPattern().matcher(message);
				if (matcher.matches()) {
					if(Bootstrap.debug){
						System.out.println("match found with pattern: " + messageType.getMsgPattern() + " : message > " + message);
					}
					return messageType;
				}
			}
			if(Bootstrap.debug){
				System.out.println("!!!match NOT found!! : message > " + message);
			}
		throw new InvalidMessageFormatException();
	}
	
	
	

}
