package com.jonatasferreira.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jonatasferreira.demo.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		List<Categoria> list = new ArrayList<>();
		
		list.add(new Categoria("domestico"));
		list.add(new Categoria("escritorio"));
		list.add(new Categoria("informatica"));
		
		return list;
	}
}
