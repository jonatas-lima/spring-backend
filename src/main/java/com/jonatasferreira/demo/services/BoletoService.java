package com.jonatasferreira.demo.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jonatasferreira.demo.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preenchePagamentoBoleto(PagamentoBoleto pagamento, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
	}
}
