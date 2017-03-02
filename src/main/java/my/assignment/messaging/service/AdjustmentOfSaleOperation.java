/**
 * 
 */
package my.assignment.messaging.service;

import java.util.List;

import my.assignment.messaging.commons.ApplicationCache;
import my.assignment.messaging.commons.Utility;
import my.assignment.messaging.dao.TransactionDao;
import my.assignment.messaging.entity.Transaction;
import my.assignment.messaging.entity.TransactionAdjustment;
import my.assignment.messaging.entity.TransactionAdjustment.Operator;
import my.assignment.messaging.entity.TransactionSale;

/**
 * @author Bhushan
 *
 */
public class AdjustmentOfSaleOperation implements Operation {

	private TransactionDao applicationDao = new TransactionDao();
	
	@Override
	public void buildTransactionMessage(String message, Transaction t) {
		TransactionAdjustment transaction= (TransactionAdjustment) t;
		String parts[] = message.split(" ");
		transaction.setProduct(ApplicationCache.getProduct(parts[2],true));
		transaction.setOperator(Operator.valueOf(parts[0].toLowerCase()));
		transaction.setValue(Utility.parsePositiveInteger(parts[1].replace("p","")) );
	
		
	}

	@Override
	public void perform(Transaction t) {
		TransactionAdjustment adjustment = (TransactionAdjustment) t;
		
		List<TransactionSale> sales = applicationDao.getTransactionsForProduct(adjustment.getProduct());

		switch (adjustment.getOperator()) {
		case add:
			for (TransactionSale transactionSale : sales) {
				transactionSale.setTotaleValue((transactionSale.getValue()+adjustment.getValue())*transactionSale.getQuantity());
				transactionSale.getTransactionAdjustments().add(adjustment);
			}
			break;
		case subtract:
			for (TransactionSale transactionSale : sales) {
				// Need to check if we want to set value to zero if it goes negative
				transactionSale.setTotaleValue((transactionSale.getValue()-adjustment.getValue())*transactionSale.getQuantity());
				transactionSale.getTransactionAdjustments().add(adjustment);
			}
			break;
		case multiply:
			for (TransactionSale transactionSale : sales) {
				transactionSale.setTotaleValue((transactionSale.getValue()*adjustment.getValue())*transactionSale.getQuantity());
				transactionSale.getTransactionAdjustments().add(adjustment);
			}
		default:
			break;
		}
		adjustment.setTransactionSales(sales);
		
		applicationDao.saveTransactionAdjustment(adjustment);
	}


}
