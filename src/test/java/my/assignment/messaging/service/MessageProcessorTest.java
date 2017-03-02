/**
 * 
 */
package my.assignment.messaging.service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import my.assignment.messaging.bo.MessageType;
import my.assignment.messaging.commons.Bootstrap;
import my.assignment.messaging.exception.InvalidMessageFormatException;

/**
 * @author Bhushan
 *
 */
public class MessageProcessorTest {
	
	@Before
	public void init(){
		Bootstrap.start();
	}
	
	@Test
	public void testSingleSaleMessageIdentifyMessageType(){
		MessageProcessor messageProcessor = new MessageProcessor();
		MessageType messageType = messageProcessor.identifyMessageType("apple at 15p");
		assertEquals(MessageType.Type.SingleSale, messageType.getType());
	}
	
	@Test
	public void testMultipleSaleMessageIdentifyMessageType(){
		MessageProcessor messageProcessor = new MessageProcessor();
		MessageType messageType = messageProcessor.identifyMessageType("5 sales of apples at 8p each");
		assertEquals(MessageType.Type.MultipleSale, messageType.getType());
	}
	
	@Test
	public void testAdjustmentSaleMessageIdentifyMessageType(){
		MessageProcessor messageProcessor = new MessageProcessor();
		MessageType messageType = messageProcessor.identifyMessageType("Add 10p apples");
		assertEquals(MessageType.Type.AdjustmentOfSale, messageType.getType());
	}
	
	@Test(expected = InvalidMessageFormatException.class)
	public void testInvalidMessageIdentifyMessageType(){
		MessageProcessor messageProcessor = new MessageProcessor();
		MessageType messageType = messageProcessor.identifyMessageType("sale all apples");
	}

}
