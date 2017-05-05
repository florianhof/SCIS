package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

public enum VisitorTypeEnum
implements Codeable {

	CAVERS("C"), 
	HIKERS("H"),
	YOUNG_GROUP("Y"),
	UNKNOWN("?");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.VisitorTypeEnum";
	public static final int CODE_LENGTH = 1;

	private final String code;
	
	private VisitorTypeEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static VisitorTypeEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
