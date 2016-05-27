package bbs.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import bbs.validator.annotation.RefuseBlank;

public class RefuseBlankValidator implements ConstraintValidator<RefuseBlank, String>  {

	public void initialize(RefuseBlank constraintAnnotation) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isNotBlank(value);
	}

}
