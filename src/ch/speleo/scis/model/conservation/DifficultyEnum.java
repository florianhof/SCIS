package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

/**
 * For rating
 * @author Julien
 * @version 1.0
 */
public enum DifficultyEnum
implements Codeable {

	VERY_EASY("0"), 
	EASY("1"),
	MODERATE("2"),
	DIFFICULT("3"), 
	VERY_DIFFICULT("4"),
	ACROBATIC("5");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.DifficultyEnum";

	private final String code;
	
	private DifficultyEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static DifficultyEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
