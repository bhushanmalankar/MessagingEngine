package my.assignment.messaging.service;

import my.assignment.messaging.entity.Transaction;

public interface Operation {
	
	/**
	 * This method parses message based on MessageType format and filled the corresponding transaction object
	 * @param transaction
	 *  
	 */
	void buildTransactionMessage(String message, Transaction transaction);
	
	/**
	 * This method perform operation specific to MessageType.
	 * 
	 * @param transaction
	 */
	void perform(Transaction transaction);

}
