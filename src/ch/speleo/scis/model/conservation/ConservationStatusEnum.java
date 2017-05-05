package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

public enum ConservationStatusEnum
implements Codeable {

	CATASTROPHIC("1"), 
	BAD("2"),
	AVERAGE("4"),
	GOOD("5");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.ConservationStatusEnum";
	public static final int CODE_LENGTH = 1;

	private final String code;
	
	private ConservationStatusEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static ConservationStatusEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
