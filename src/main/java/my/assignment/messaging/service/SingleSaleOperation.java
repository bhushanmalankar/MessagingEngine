/**
 * 
 */
package my.assignment.messaging.service;

import my.assignment.messaging.commons.ApplicationCache;
import my.assignment.messaging.commons.Utility;
import my.assignment.messaging.dao.TransactionDao;
import my.assignment.messaging.entity.Transaction;
import my.assignment.messaging.entity.TransactionSale;

/**
 * @author Bhushan
 *
 */
public class SingleSaleOperation implements Operation {

	private TransactionDao applicationDao = new TransactionDao();
	
	@Override
	public void buildTransactionMessage(String message, Transaction tr) {
		TransactionSale transaction = (TransactionSale) tr;
		String parts[] = message.split(" ");
		transaction.setProduct(ApplicationCache.getProduct(parts[0],false) );
		transaction.setQuantity(1);
		transaction.setValue(Utility.parsePositiveInteger(parts[2].replace("p","")) );
		transaction.setTotaleValue(transaction.getValue());
	}

	@Override
	public void perform(Transaction transaction) {
		applicationDao.saveTransaction((TransactionSale) transaction);
	}

	

}
