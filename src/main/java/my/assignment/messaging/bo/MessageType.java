/**
 * 
 */
package my.assignment.messaging.bo;

import java.util.regex.Pattern;

/**
 * @author Bhushan
 *
 */
public class MessageType {

	public enum Type {
		SingleSale, // e.g. apple at 15p
		MultipleSale, // e.g. 5 sales of apples at 8p each
		AdjustmentOfSale // e.g. Add 100p apples
	};

	public MessageType(Type type, Pattern msgPattern){
		this.type = type;
		this.msgPattern = msgPattern;
	}
	
	private Type type;
	private Pattern msgPattern;

	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Pattern getMsgPattern() {
		return msgPattern;
	}
	public void setMsgPattern(Pattern msgPattern) {
		this.msgPattern = msgPattern;
	}
	

	
}
