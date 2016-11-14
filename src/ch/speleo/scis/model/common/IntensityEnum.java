package ch.speleo.scis.model.common;

import org.hibernate.usertype.*;

import ch.speleo.scis.model.common.Codeable;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;

/**
 * For rating
 * @author Julien
 * @version 1.0
 */
public enum IntensityEnum
implements Codeable {

	NONE("0"), 
	VERY_WEAK("1"),
	WEAK("2"),
	MODERATE("3"), 
	HIGH("4"),
	VERY_HIGH("5"),
	OVERWHELMING("6");
	
	public static final String CLASSNAME = "ch.speleo.scis.model.common.IntensityEnum";

	private final String code;
	
	private IntensityEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public static IntensityEnum fromCode(String code) {
		return Codeable.Utils.fromCode(code, values());
	}
	
	
	public static class CodedType extends CodedEnumType implements UserType {
		public static final String CLASSNAME = "ch.speleo.scis.model.common.IntensityEnum.CodedType";
		public Class<? extends Codeable> returnedClass() {
			return IntensityEnum.class;
		}
	}
	
}
