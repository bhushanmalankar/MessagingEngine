/**
 * 
 */
package my.assignment.messaging.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import my.assignment.messaging.bo.MessageType;
import my.assignment.messaging.bo.MessageType.Type;
import my.assignment.messaging.entity.Product;
import my.assignment.messaging.exception.InvalidProductTypeException;

/**
 * @author Bhushan
 *
 */
public class ApplicationCache {

	private static Map<String,Product> products;
	private static Set<MessageType> messageTypes;
	
	
	
	
	public void init(){
		initialiseProducts();
		initialiseMessageType();
		
	}

	public static Map<String,Product> getProducts(){
		return products;
	}
	public static Set<MessageType> getMessageTypes(){		
		return messageTypes;
	}

	/**
	 * 
	 */
	private void initialiseProducts() {
		products = new HashMap<String,Product>();
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("products.txt");

			// load a properties file
			prop.load(input);
			for (Entry<Object, Object> product : prop.entrySet()) {
				products.put(product.getKey().toString(),new Product(product.getKey().toString(), product.getValue().toString()));
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
	
	
	private void initialiseMessageType() {
		messageTypes = new HashSet<MessageType>();
		
		Pattern msgPattern = Pattern.compile("^("+getProductTypePattern()+") at \\d+p",Pattern.CASE_INSENSITIVE);
		messageTypes.add(new MessageType(Type.SingleSale, msgPattern));
		if(Bootstrap.debug){
			System.out.println(Type.SingleSale+":"+msgPattern.pattern());
		}
		
		msgPattern = Pattern.compile("^\\d+ sales of ("+getProductPluralPattern()+") at \\d+p each",Pattern.CASE_INSENSITIVE);
		messageTypes.add(new MessageType(Type.MultipleSale, msgPattern));
		if(Bootstrap.debug){
			System.out.println(Type.MultipleSale+":"+msgPattern.pattern());
		}
		
		msgPattern = Pattern.compile("^(Add|Subtract|Multiply) \\d+p ("+getProductPluralPattern()+")",Pattern.CASE_INSENSITIVE);
		messageTypes.add(new MessageType(Type.AdjustmentOfSale, msgPattern));
		if(Bootstrap.debug){
			System.out.println(Type.AdjustmentOfSale+":"+msgPattern.pattern());
		}
		
	}



	private String getProductTypePattern() {
		StringBuilder typeStr = new StringBuilder();
		for (Product product : products.values()) {
			typeStr.append(product.getType()).append('|');
		}
		return typeStr.deleteCharAt(typeStr.length()-1).toString();
	}
	
	private String getProductPluralPattern() {
		StringBuilder pluralStr = new StringBuilder();
		for (Product product : products.values()) {
			pluralStr.append(product.getTypePlural()).append('|');
		}
		return pluralStr.deleteCharAt(pluralStr.length()-1).toString();
	}
	
	public static Product getProduct(String productType, boolean isPlural){
		Product product = null;
		if(isPlural){ // get product from plural form
			for (Product p : products.values()) {
				if(p.getTypePlural().equalsIgnoreCase(productType.toLowerCase())){
					product = p;
					break;
				}
			}
			
		}else{ // get product from name
			product = products.get(productType.toLowerCase());
		}
		
		if(product==null){
			System.out.println("Product type not registered!! "+productType);
			throw new InvalidProductTypeException();
		}
		return product;
	}
	
}
