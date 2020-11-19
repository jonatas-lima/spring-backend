package com.jonatasferreira.demo.services.validation;

import java.util.List;

import javax.validation.ConstraintValidatorContext;

import com.jonatasferreira.demo.resources.exceptions.FieldMessage;

public class AttachErrorMessages {

	protected static void attachMessages(List<FieldMessage> list, ConstraintValidatorContext context) {
		list.forEach(e -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		});
	}
}
