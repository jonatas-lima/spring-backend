package com.jonatasferreira.demo.domain.enums;

import com.jonatasferreira.demo.constants.Messages;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int codigo;
	private String descricao;
	
	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getCodigo() == codigo) {
				return perfil;
			}
		}
		
		throw new IllegalArgumentException(Messages.TIPO_INVALIDO);
	}
}
