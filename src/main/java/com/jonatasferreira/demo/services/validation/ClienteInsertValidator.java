package com.jonatasferreira.demo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.enums.TipoCliente;
import com.jonatasferreira.demo.dto.ClienteNewDTO;
import com.jonatasferreira.demo.resources.exceptions.FieldMessage;
import com.jonatasferreira.demo.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Override
	public void initialize(ClienteInsert ann) {
	}
	
	@Override
	public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (value.getTipo() == null) {
			list.add(new FieldMessage("tipo", Messages.TIPO_NULO));
		}
		else if (value.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", Messages.CPF_INVALIDO));
		}
		else if (value.getTipo().equals(TipoCliente.PESSOA_JURIDCA.getCodigo()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", Messages.CNPJ_INVALIDO));
		} 
		else if (!(value.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo()) || value.getTipo().equals(TipoCliente.PESSOA_JURIDCA.getCodigo()))) {
			list.add(new FieldMessage("tipo", Messages.TIPO_INVALIDO));
		}
		
		list.forEach(e -> {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		});
		
		return list.isEmpty();
	}

}
