package com.jonatasferreira.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.dto.CategoriaDTO;
import com.jonatasferreira.demo.repositories.CategoriaRepository;
import com.jonatasferreira.demo.services.exceptions.DataIntegrityException;
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
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Categoria insert(Categoria obj) {
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		Categoria categoria = find(obj.getId());
		updateData(categoria, obj);
		return repo.save(categoria);
	}
	
	private void updateData(Categoria categoria, Categoria obj) {
		categoria.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(Messages.DATA_INTEGRITY_CATEGORIA);
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		Categoria obj = new Categoria(objDto.getNome());
		obj.setId(objDto.getId());
		return obj;
	}
}
