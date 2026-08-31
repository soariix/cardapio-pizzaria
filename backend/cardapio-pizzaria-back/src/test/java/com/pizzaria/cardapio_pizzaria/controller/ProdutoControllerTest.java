package com.pizzaria.cardapio_pizzaria.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pizzaria.cardapio.dto.ProdutoDTO;
import com.pizzaria.cardapio_pizzaria.model.Categoria;
import com.pizzaria.cardapio_pizzaria.service.ProdutoService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProdutoService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void deveListarProdutos() throws Exception {
		ProdutoDTO produto = new ProdutoDTO(1L, "Pizza Calabresa", "Descrição",
				new BigDecimal("45.90"), Categoria.PIZZA_SALGADA, null, true);

		when(service.listarTodos()).thenReturn(List.of(produto));

		mockMvc.perform(get("/api/produtos"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].nome").value("Pizza Calabresa"));
	}

	@Test
	void deveRetornarErroAoCriarProdutoSemNome() throws Exception {
		String jsonInvalido = "{\"preco\": 20.0, \"categoria\": \"BEBIDA\"}";

		mockMvc.perform(post("/api/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonInvalido))
			.andExpect(status().isBadRequest());
	}
}