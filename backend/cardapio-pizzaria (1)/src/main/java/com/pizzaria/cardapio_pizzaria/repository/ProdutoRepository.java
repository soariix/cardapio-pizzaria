package com.pizzaria.cardapio_pizzaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzaria.cardapio_pizzaria.model.Categoria;
import com.pizzaria.cardapio_pizzaria.model.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	List<Produto> findByCategoria(Categoria categoria);
	List<Produto> findByDisponivelTrue();
}
