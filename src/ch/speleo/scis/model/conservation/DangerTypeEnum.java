package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

public enum DangerTypeEnum
implements Codeable {

	AVALANCHE("A"), 
	ROCK_FALL("R"),
	SLIPPING("S"),
	FLOOD("F");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.DangerTypeEnum";
	public static final int CODE_LENGTH = 1;

	private final String code;
	
	private DangerTypeEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static DangerTypeEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}
