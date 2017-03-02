/**
 * 
 */
package my.assignment.messaging.dao;

import java.util.ArrayList;
import java.util.List;

import my.assignment.messaging.entity.Product;
import my.assignment.messaging.entity.TransactionAdjustment;
import my.assignment.messaging.entity.TransactionSale;

/**
 * @author Bhushan
 *
 *	This class is dao layer for transactions. Since transaction details needs to be maintained at memory level, details is maintained in same class.
 */
public class TransactionDao {
	
	private static List<TransactionSale> transactions = new ArrayList<TransactionSale> ();
	private static List<TransactionAdjustment> transactionAdjustments = new ArrayList<TransactionAdjustment> ();

	public void saveTransaction(TransactionSale t){
		transactions.add(t);
	}
	
	public List<TransactionSale> getAllTransactions(){
		return new ArrayList<TransactionSale> (transactions);
	}
	
	public List<TransactionSale> getTransactionsForProduct(Product product){
		List<TransactionSale> productTransactions = new ArrayList<TransactionSale> ();
		for (TransactionSale transactionSale : transactions) {
			if(transactionSale.getProduct().equals( product)){
				productTransactions.add(transactionSale);
			}
		}
		return productTransactions;
	}
	
	public void saveTransactionAdjustment(TransactionAdjustment t){
		transactionAdjustments.add(t);
	}
	
	public List<TransactionAdjustment> getAllTransactionAdjustments(){
		return new ArrayList<TransactionAdjustment> (transactionAdjustments);
	}
}
