package com.jonatasferreira.demo.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.jonatasferreira.demo.domain.enums.StatusPagamento;

@Entity
public class PagamentoBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;

	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoBoleto() {
	}
	
	public PagamentoBoleto(StatusPagamento status, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(status, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}
	
}
