package com.jonatasferreira.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.jonatasferreira.demo.constants.Messages;


public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = Messages.CAMPO_NULO)
	@Email(message = Messages.EMAIL_INVALIDO)
	private String email;
	
	public EmailDTO() {
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
