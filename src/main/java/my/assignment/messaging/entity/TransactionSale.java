package my.assignment.messaging.entity;

import java.util.ArrayList;
import java.util.List;


public class TransactionSale extends Transaction {
	
	private Integer totaleValue;
	private List<TransactionAdjustment> transactionAdjustments = new ArrayList<TransactionAdjustment>();
	
	/**
	 * @return the totaleValue
	 */
	public Integer getTotaleValue() {
		return totaleValue;
	}
	/**
	 * @param totaleValue the totaleValue to set
	 */
	public void setTotaleValue(Integer totaleValue) {
		this.totaleValue = totaleValue;
	}
	public List<TransactionAdjustment> getTransactionAdjustments() {
		return transactionAdjustments;
	}
	public void setTransactionAdjustments(List<TransactionAdjustment> transactionAdjustments) {
		this.transactionAdjustments = transactionAdjustments;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionSale [totaleValue=" + totaleValue
				+ ", getMessageReference()=" + getMessageReference()
				+ ", getProduct()=" + getProduct() + ", getQuantity()="
				+ getQuantity() + ", getValue()=" + getValue()
				+ ", getMessageType()=" + getMessageType() + "]";
	}
	
	
}
