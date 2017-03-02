/**
 * 
 */
package my.assignment.messaging.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Bhushan
 *
 */
public class TransactionAdjustment extends Transaction {
	
	public enum Operator {
		
		add,subtract,multiply

	}
	
	private Operator operator;
	private List<TransactionSale> transactionSales = new ArrayList<TransactionSale>();

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<TransactionSale> getTransactionSales() {
		return transactionSales;
	}

	public void setTransactionSales(List<TransactionSale> transactionSales) {
		this.transactionSales = transactionSales;
	}
	
}
