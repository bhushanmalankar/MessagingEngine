/**
 * 
 */
package my.assignment.messaging.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.assignment.messaging.dao.TransactionDao;
import my.assignment.messaging.entity.Product;
import my.assignment.messaging.entity.TransactionAdjustment;
import my.assignment.messaging.entity.TransactionSale;

/**
 * @author Bhushan
 *
 */
public class TransactionReport {
	
	private TransactionDao applicationDao = new TransactionDao();
	
	
	/**
	 * 
	 */
	protected void generateReport(Integer messageCounter) {
		if(messageCounter%10==0){
			
			saleReport();
		}
		if(messageCounter==50){
			//	log that application is pausing, stop accepting new messages and log a report of the adjustments that have been made to each sale type while the application was running.
			System.out.println("\nApplication is pausing!!");
			adjustmentReport();
		}
	}
	
	public void saleReport(){
		
		System.out.println("\n----------------Report of Sale-----------------");
		List<TransactionSale> sales = applicationDao.getAllTransactions();
		Map<String,TransactionSale> saleReport = new HashMap<String,TransactionSale>();
		
		for (TransactionSale tr : sales) {
			TransactionSale productSummary = null;
			if(saleReport.containsKey(tr.getProduct().getType())){
				productSummary = saleReport.get(tr.getProduct().getType());
			}else{
				productSummary = new TransactionSale();
				productSummary.setQuantity(0);
				productSummary.setTotaleValue(0);
				productSummary.setProduct(new Product(tr.getProduct().getType()));
				saleReport.put(tr.getProduct().getType(), productSummary);
			}
			
			productSummary.setQuantity(productSummary.getQuantity()+tr.getQuantity());
			productSummary.setTotaleValue(productSummary.getTotaleValue()+tr.getTotaleValue());
			
		}
		
		//String formatter can be used for better formatting
		System.out.println("Product Type \t|| Total Quantity \t|| Total Value");
		for (TransactionSale productSummary : saleReport.values()) {
			System.out.println(productSummary.getProduct().getType()+" \t\t|| "+productSummary.getQuantity()+" \t\t\t|| "+productSummary.getTotaleValue());
		}
		
		System.out.println("----------------End of Report of Sale-----------------");
	}
	
	
	public void adjustmentReport(){
		
		System.out.println("\n----------------Report of Sale Adjustments-----------------");
		List<TransactionAdjustment> salesAdjustments = applicationDao.getAllTransactionAdjustments();
		
		//String formatter can be used for better formatting
		System.out.println("Product Type \t|| Operation \t||  Value");
		for (TransactionAdjustment adjustments : salesAdjustments) {
			System.out.println(adjustments.getProduct().getType()+" \t\t|| "+adjustments.getOperator()+" \t\t|| " +adjustments.getValue());
		}
		System.out.println("----------------End of Report of Sale Adjustments-----------------");
	}

}
