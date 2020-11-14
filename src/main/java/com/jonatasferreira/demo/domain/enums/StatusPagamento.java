package com.jonatasferreira.demo.domain.enums;

public enum StatusPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private Integer codigo;
	private String descricao;
	
	private StatusPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static StatusPagamento toEnum(Integer codigo) {
		if (codigo == null)
			return null;
		
		for (StatusPagamento status : StatusPagamento.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		
		throw new IllegalArgumentException();
	}
}
