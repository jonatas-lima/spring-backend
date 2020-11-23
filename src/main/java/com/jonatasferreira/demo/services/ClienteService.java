package com.jonatasferreira.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jonatasferreira.demo.constants.Messages;
import com.jonatasferreira.demo.domain.Cidade;
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.domain.Endereco;
import com.jonatasferreira.demo.domain.enums.TipoCliente;
import com.jonatasferreira.demo.dto.ClienteDTO;
import com.jonatasferreira.demo.dto.ClienteNewDTO;
import com.jonatasferreira.demo.repositories.ClienteRepository;
import com.jonatasferreira.demo.repositories.EnderecoRepository;
import com.jonatasferreira.demo.services.exceptions.DataIntegrityException;
import com.jonatasferreira.demo.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepositorio;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException(String.format(Messages.OBJECT_NOT_FOUND_D_S, id, Cliente.class.getName()))
		);
	}
	
	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = clienteRepo.save(obj);
		enderecoRepositorio.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente cliente = find(obj.getId());
		updateData(cliente, obj);
		return clienteRepo.save(cliente);
	}
	
	private void updateData(Cliente cliente, Cliente obj) {
		cliente.setNome(obj.getNome());
		cliente.setEmail(obj.getEmail());
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(Messages.DATA_INTEGRITY_CLIENTE);
		}
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		Cliente cli = new Cliente(objDto.getNome(), objDto.getEmail(), null, null);
		cli.setId(objDto.getId());
		return cli;
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		cli.setId(null);
		
		Cidade cid = new Cidade(null, null);
		cid.setId(objDto.getCidadeId());
		
		Endereco end = new Endereco(objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cid, cli);
		cli.adicionaEnderecos(end);
		cli.adicionaTelefones(objDto.getTelefone1());
		
		if (objDto.getTelefone2() != null) {
			cli.adicionaTelefones(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cli.adicionaTelefones(objDto.getTelefone3());
		}
		
		return cli; 
	}
}
