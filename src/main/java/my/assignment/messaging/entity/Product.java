/**
 * 
 */
package my.assignment.messaging.entity;

/**
 * @author Bhushan
 * 
 */
public class Product {

	
	public Product(String type, String typePlural){
		this.type = type;
		this.typePlural = typePlural;
	}
	
	public Product(String type){
		this.type = type;
	}
	/**
	 * Type of product. This will be unique.
	 */
	private String type;

	/**
	 * Maintaining plural form of type can be avoided by integrating dictionary
	 * api to find out plural form of product type.
	 */
	private String typePlural;

	public String getType() {
		return type;
	}

	public void setType(String name) {
		this.type = name;
	}

	public String getTypePlural() {
		return typePlural;
	}

	public void setTypePlural(String namePlural) {
		this.typePlural = namePlural;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Product)) {
			return false;
		}

		Product product = (Product) o;

		return product.type.equals(type);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + type.hashCode();
		return result;
	}

}
