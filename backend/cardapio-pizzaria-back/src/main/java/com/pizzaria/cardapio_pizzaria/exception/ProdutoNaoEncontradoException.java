package com.pizzaria.cardapio_pizzaria.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
	public ProdutoNaoEncontradoException(Long id) {
		super("Produto não encontrado com id: " + id);
	}
}
