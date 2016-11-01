package ch.speleo.scis.model.common;

import org.hibernate.usertype.UserType;

import ch.speleo.scis.model.common.Codeable;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;

/**
 * Categories importance of heritage 
 * @author Julien
 * @version 1.0
 */
public enum ImportanceEnum 
implements Codeable {

	LOCAL("0"), 
	REGIONAL("1"),
	CANTONAL("2"),
	NATIONAL("3"), 
	INTERNATIONAL("4");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.common.ImportanceEnum";

	private final String code;
	
	private ImportanceEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static ImportanceEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
	
	public static class CodedType extends CodedEnumType implements UserType {
		public static final String CLASSNAME = "ch.speleo.scis.model.common.ImportanceEnum.CodedType";
		public Class<? extends Codeable> returnedClass() {
			return ImportanceEnum.class;
		}
	}
	
}
