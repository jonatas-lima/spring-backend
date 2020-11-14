package com.jonatasferreira.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Pedido;
import com.jonatasferreira.demo.repositories.PedidoRepository;
import com.jonatasferreira.demo.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format(Messages.OBJECT_NOT_FOUND_D_S, id, Categoria.class.getName()))
		);
	}
}
