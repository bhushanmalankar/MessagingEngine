/**
 * 
 */
package my.assignment.messaging.service;

import my.assignment.messaging.bo.MessageType;
import my.assignment.messaging.exception.InvalidMessageFormatException;

/**
 * @author Bhushan
 *
 */
public class OperationFactory {
	
	private static final SingleSaleOperation singleSaleOperation = new SingleSaleOperation();
	private static final MultipleSaleOperation multipleSaleOperation = new MultipleSaleOperation();
	private static final AdjustmentOfSaleOperation adjustmentOfSaleOperation = new AdjustmentOfSaleOperation();

	public static Operation getMessageOperation(MessageType messageType) {
		switch (messageType.getType()) {

		case SingleSale: {
			return singleSaleOperation;
		}

		case MultipleSale: {
			return multipleSaleOperation;
		}

		case AdjustmentOfSale: {
			return adjustmentOfSaleOperation;
		}

		default:
			// This case should not occur as we have already got messageType
			// from message.
			throw new InvalidMessageFormatException();

		}

	}

}
