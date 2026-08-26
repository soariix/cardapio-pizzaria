package com.pizzaria.cardapio.dto;

import java.math.BigDecimal;

import com.pizzaria.cardapio_pizzaria.model.Categoria;

public record ProdutoDTO(
		Long id, 
		String nome,
		String descricao,
		BigDecimal preco,
		Categoria categoria,
		String imagemUrl,
		Boolean disponivel
) {}