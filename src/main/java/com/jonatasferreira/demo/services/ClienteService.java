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
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.dto.ClienteDTO;
import com.jonatasferreira.demo.repositories.ClienteRepository;
import com.jonatasferreira.demo.services.exceptions.DataIntegrityException;
import com.jonatasferreira.demo.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format(Messages.OBJECT_NOT_FOUND_D_S, id, Cliente.class.getName()))
		);
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente insert(Cliente obj) {
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		updateData(cliente, obj);
		return repo.save(cliente);
	}
	
	private void updateData(Cliente cliente, Cliente obj) {
		cliente.setNome(obj.getNome());
		cliente.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(Messages.DATA_INTEGRITY_CLIENTE);
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		Cliente obj = new Cliente(objDto.getNome(), objDto.getEmail(), null, null);
		return obj;
	}
}
