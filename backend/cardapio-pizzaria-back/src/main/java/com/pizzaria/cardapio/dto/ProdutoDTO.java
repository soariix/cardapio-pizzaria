package com.pizzaria.cardapio.dto;

import java.math.BigDecimal;

import com.pizzaria.cardapio_pizzaria.model.Categoria;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
		Long id,
		@NotBlank(message = "Nome é obrigatório") String nome,
		String descricao,
		@NotNull(message = "Preço é obrigatório")
		@DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero") BigDecimal preco,
		@NotNull(message = "Categoria é obrigatória") Categoria categoria,
		String imagemUrl,
		Boolean disponivel
) {}