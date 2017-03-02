/**
 * 
 */
package my.assignment.messaging.service;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import my.assignment.messaging.commons.Bootstrap;
import my.assignment.messaging.dto.MessageResponse;

/**
 * @author Bhushan
 * 
 */
public class MessagingEngineTest {
	
	
	@Before
	public void init(){
		Bootstrap.start();
	}
	
	@Test
	public void testSingleSaleMessageSendMessage(){
		MessagingEngine messagingEngine = new MessagingEngine();
		MessageResponse messageResponse = messagingEngine.sendMessage("apple at 15p");
		assertEquals(MessageResponse.Status.SUCCESS, messageResponse.getStatus());
	}
	
	@Test
	public void testMultipleSaleMessageSendMessage(){
		MessagingEngine messagingEngine = new MessagingEngine();
		
		MessageResponse messageResponse = messagingEngine.sendMessage("5 sales of apples at 8p each");
		assertEquals(MessageResponse.Status.SUCCESS, messageResponse.getStatus());
	}
	
	@Test
	public void testAdjustmentSaleMessageSendMessage(){
		MessagingEngine messagingEngine = new MessagingEngine();
		MessageResponse messageResponse = messagingEngine.sendMessage("5 sales of apples at 8p each");
		assertEquals(MessageResponse.Status.SUCCESS, messageResponse.getStatus());
	}
	
	@Test
	public void testInvalidMessageSendMessage(){
		MessagingEngine messagingEngine = new MessagingEngine();
		MessageResponse messageResponse = messagingEngine.sendMessage("sale all products");
		assertEquals(MessageResponse.Status.ERROR, messageResponse.getStatus());
	}
	
	@Test
	public void testMessageHaltAfterFiftySendMessage(){
		MessagingEngine messagingEngine = new MessagingEngine();
		MessageResponse messageResponse = null;
		//Engine is supposed to accept 50 messages, after that it will halt
		for(int i=0;i<=51;i++){
			 messageResponse = messagingEngine.sendMessage("apple at 15p");
		}
		
		assertEquals(MessageResponse.Status.HALT, messageResponse.getStatus());
	}
	
	
	
	

	public static void main(String[] args) {
		Bootstrap.start();
		MessagingEngine engine = new MessagingEngine();
		BufferedReader br = null;
		String file = "";

		if (args.length > 0) {
			file = args[0];
		} else {
			file = "sample.txt";
		}

		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {

				MessageResponse response = engine.sendMessage(line);
				if(Bootstrap.debug){
					System.out.println(response);
				}
				if(MessageResponse.Status.ERROR.equals(response.getStatus())){
					System.out.println("Message:" +line+" >> "+response);
				}
			}
		} catch (IOException e) {
			if(Bootstrap.debug){
			System.out.println("Error while reading file. " + file);
			e.printStackTrace();
			}
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}
}
