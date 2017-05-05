package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

/**
 * For rating
 * @author Julien
 * @version 1.0
 */
public enum FrequencyEnum
implements Codeable {

	RARE("0"),
	LOW("2"),
	MIDDLE("4"), 
	OFTEN("6");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.FrequencyEnum";
	public static final int CODE_LENGTH = 1;

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
