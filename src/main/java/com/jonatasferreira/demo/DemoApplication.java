package com.jonatasferreira.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Produto;
import com.jonatasferreira.demo.repositories.CategoriaRepository;
import com.jonatasferreira.demo.repositories.ProdutoRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria("Informática");
		Categoria c2 = new Categoria("Escritório");
		
		Produto p1 = new Produto("Computador", 2000.0);
		Produto p2 = new Produto("Impressora", 800.0);
		Produto p3 = new Produto("Mouse", 80.0);
		
		c1.adicionaProdutos(Arrays.asList(p1, p2, p3));
		c2.adicionaProdutos(Arrays.asList(p2));
		
		p1.adicionaCategorias(Arrays.asList(c1));
		p2.adicionaCategorias(Arrays.asList(c1, c2));
		p3.adicionaCategorias(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
