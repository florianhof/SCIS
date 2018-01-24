package ch.speleo.scis.model.conservation;

import ch.speleo.scis.model.common.Codeable;

/**
 * Categories importance of heritage 
 * @author Julien
 * @version 1.0
 */
public enum SignificanceEnum 
implements Codeable {

	LOCAL("L"), 
	REGIONAL("R"),
	CANTONAL("C"),
	NATIONAL("N"), 
	INTERNATIONAL("I");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.conservation.SignificanceEnum";

	private final String code;
	
	private SignificanceEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static SignificanceEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
}