package com.jonatasferreira.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jonatasferreira.demo.constants.ApiEndpoints;
import com.jonatasferreira.demo.domain.Cliente;
import com.jonatasferreira.demo.services.ClienteService;

@RestController
@RequestMapping(value = ApiEndpoints.ENDPOINT_CLIENTES)
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findById(@PathVariable(required = true) Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
