package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

/**
 * For rating
 * @author Julien
 * @version 1.0
 */
public enum RatingEnum
implements Codeable {

	NONE("0"), 
	VERY_WEAK("1"),
	WEAK("2"),
	MODERATE("3"), 
	HIGH("4"),
	VERY_HIGH("5"),
	OVERWHELMING("6");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.RatingEnum";

	private final String code;
	
	private RatingEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static RatingEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
