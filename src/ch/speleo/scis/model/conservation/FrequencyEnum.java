package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

/**
 * For rating
 * @author Julien
 * @version 1.0
 */
public enum FrequencyEnum
implements Codeable {

	EXCEPTIONAL("0"), 
	VERY_RARE("1"),
	RARE("2"),
	FREQUENT("3"), 
	VERY_FREQUENT("4");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.FrequencyEnum";

	private final String code;
	
	private FrequencyEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static FrequencyEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
