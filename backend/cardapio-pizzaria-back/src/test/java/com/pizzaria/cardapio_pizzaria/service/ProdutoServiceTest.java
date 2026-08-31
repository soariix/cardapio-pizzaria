package com.pizzaria.cardapio_pizzaria.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pizzaria.cardapio.dto.ProdutoDTO;
import com.pizzaria.cardapio_pizzaria.exception.ProdutoNaoEncontradoException;
import com.pizzaria.cardapio_pizzaria.model.Categoria;
import com.pizzaria.cardapio_pizzaria.model.Produto;
import com.pizzaria.cardapio_pizzaria.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

	@Mock
	private ProdutoRepository repository;

	@InjectMocks
	private ProdutoService service;

	private Produto produtoExemplo;

	@BeforeEach
	void setUp() {
		produtoExemplo = new Produto();
		produtoExemplo.setId(1L);
		produtoExemplo.setNome("Pizza Calabresa");
		produtoExemplo.setPreco(new BigDecimal("45.90"));
		produtoExemplo.setCategoria(Categoria.PIZZA_SALGADA);
		produtoExemplo.setDisponivel(true);
	}

	@Test
	void deveBuscarProdutoPorIdComSucesso() {
		when(repository.findById(1L)).thenReturn(Optional.of(produtoExemplo));

		ProdutoDTO resultado = service.buscarPorId(1L);

		assertThat(resultado.nome()).isEqualTo("Pizza Calabresa");
		verify(repository, times(1)).findById(1L);
	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExiste() {
		when(repository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(ProdutoNaoEncontradoException.class, () -> service.buscarPorId(99L));
	}

	@Test
	void deveCriarProdutoComSucesso() {
		ProdutoDTO dto = new ProdutoDTO(null, "Pizza Marguerita", "Molho e manjericão",
				new BigDecimal("42.00"), Categoria.PIZZA_SALGADA, null, true);

		when(repository.save(any(Produto.class))).thenReturn(produtoExemplo);

		ProdutoDTO resultado = service.criar(dto);

		assertThat(resultado).isNotNull();
		verify(repository, times(1)).save(any(Produto.class));
	}

	@Test
	void deveLancarExcecaoAoDeletarProdutoInexistente() {
		when(repository.existsById(50L)).thenReturn(false);

		assertThrows(ProdutoNaoEncontradoException.class, () -> service.deletar(50L));
		verify(repository, never()).deleteById(anyLong());
	}
}