package com.jonatasferreira.demo.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jonatasferreira.demo.domain.enums.StatusPagamento;

@Entity
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numParcelas;
	
	public PagamentoCartao() {
	}

	public PagamentoCartao(StatusPagamento status, Pedido pedido, Integer numParcelas) {
		super(status, pedido);
		this.numParcelas = numParcelas;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}
	
}
