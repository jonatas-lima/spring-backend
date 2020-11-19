package com.jonatasferreira.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jonatasferreira.demo.domain.Categoria;
import com.jonatasferreira.demo.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	@Transactional(readOnly = true)
	public Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nomeCategoria, List<Categoria> categorias, Pageable pageRequest);
	
}
