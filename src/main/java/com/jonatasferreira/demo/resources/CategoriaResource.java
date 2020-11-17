package com.jonatasferreira.demo.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonatasferreira.demo.constants.ApiEndpoints;
import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.services.CategoriaService;

@RestController
@RequestMapping(value = ApiEndpoints.ENDPOINT_CATEGORIAS)
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findById(@PathVariable(required = true) Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody(required = true) Categoria obj) {
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
