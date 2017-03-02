/**
 * 
 */
package my.assignment.messaging.commons;

import java.util.UUID;

import my.assignment.messaging.exception.NotPostiveNumberException;

/**
 * @author Bhushan
 *
 */
public class Utility {
	
	public static String generateMessageReference(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * Parses string argument with positive decimal. Throws NumberFormatException if string is not decimal and NotPostiveNumberException if number is not Postive
	 * @return
	 * 
	 */
	public static Integer parsePositiveInteger(String number){
		Integer value = Integer.parseInt(number);
		if(value<=0){
			throw new NotPostiveNumberException();
		}
		return value;
	}
	

}
