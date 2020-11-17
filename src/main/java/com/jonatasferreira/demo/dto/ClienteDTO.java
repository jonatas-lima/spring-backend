package com.jonatasferreira.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Cliente;

public class ClienteDTO {

	private Integer id;
	
	@NotEmpty(message = Messages.CAMPO_NULO)
	@Length(min = 5, max = 120, message = Messages.TAMANHO_INVALIDO)
	private String nome;
	
	@NotEmpty(message = Messages.CAMPO_NULO)
	@Email(message = Messages.EMAIL_INVALIDO)
	private String email;
	
	public ClienteDTO() {
	}
	
	public ClienteDTO(Cliente obj) {
		setId(obj.getId());
		setNome(obj.getNome());
		setEmail(obj.getEmail());
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
