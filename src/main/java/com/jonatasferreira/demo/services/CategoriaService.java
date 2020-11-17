package com.jonatasferreira.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.repositories.CategoriaRepository;
import com.jonatasferreira.demo.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format(Messages.OBJECT_NOT_FOUND_D_S, id, Categoria.class.getName()))
		);
	}
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
}
