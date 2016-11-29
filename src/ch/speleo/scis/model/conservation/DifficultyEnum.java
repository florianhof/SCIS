package ch.speleo.scis.model.conservation;

import org.hibernate.usertype.*;

import ch.speleo.scis.model.common.Codeable;
import ch.speleo.scis.model.common.Codeable.*;
import ch.speleo.scis.persistence.typemapping.CodedEnumType;

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
	
	
	public static class CodedType extends CodedEnumType implements UserType {
		public static final String CLASSNAME = "ch.speleo.scis.model.conservation.DifficultyEnum.CodedType";
		public Class<? extends Codeable> returnedClass() {
			return DifficultyEnum.class;
		}
	}
	
}
