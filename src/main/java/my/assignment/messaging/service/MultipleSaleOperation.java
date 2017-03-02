/**
 * 
 */
package my.assignment.messaging.service;

import my.assignment.messaging.commons.ApplicationCache;
import my.assignment.messaging.commons.Utility;
import my.assignment.messaging.dao.TransactionDao;
import my.assignment.messaging.entity.Transaction;
import my.assignment.messaging.entity.TransactionSale;
import my.assignment.messaging.service.Operation;

/**
 * @author Bhushan
 *
 */
public class MultipleSaleOperation implements Operation {
	
	private TransactionDao applicationDao = new TransactionDao();

	@Override
	public void buildTransactionMessage(String message, Transaction tr) {
		TransactionSale transaction = (TransactionSale) tr;
		String parts[] = message.split(" ");
		transaction.setProduct(ApplicationCache.getProduct(parts[3],true));
		transaction.setQuantity(Utility.parsePositiveInteger(parts[0]) );
		transaction.setValue(Utility.parsePositiveInteger(parts[5].replace("p","")) );
		transaction.setTotaleValue(transaction.getQuantity() * transaction.getValue());
	}

	@Override
	public void perform(Transaction transaction) {
		applicationDao.saveTransaction((TransactionSale) transaction);
	}

	

}
