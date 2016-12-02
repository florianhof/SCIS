

package ch.speleo.scis.model.common;

import javax.persistence.*;

/**
 * Class representing an embedded object AddressVisit of Surveillance sheets using Hibernate annotations
 * 
 * @author Julien
 * @version 1.0
 */

@Embeddable // Used instead of @entity for embeddable classes
public class PostalAddress {
	
	@Column(
			name="STREET",
			length=50
			)
	private String street;
	
	@Column(
			name="POSTAL_CODE",
			length=4
			)
	private int postalCode;
	
	@Column(
			name="CITY",
			length=30
			)
	private String city;
	
	@Column(
			name="CANTON",
			length=20
			)
	private String canton;
	
    /**
     * Empty constructor.
     */
    public PostalAddress() { }
	
	public PostalAddress(String street,int postalCode,String city,String canton){
		this.street=street;
		this.postalCode=postalCode;
		this.city=city;
		this.canton=canton;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("street=");
		builder.append(street);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(" city=");
		builder.append(city);
		builder.append(" (canton=)");
		builder.append(canton);
		builder.append("]");
		return builder.toString();
	}

}