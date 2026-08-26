package com.pizzaria.cardapio_pizzaria.controller;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pizzaria.cardapio_pizzaria.service.ProdutoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pizzaria.cardapio.dto.ProdutoDTO;
import com.pizzaria.cardapio_pizzaria.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private final ProdutoService service;
	
	public ProdutoController(ProdutoService service) {
		this.service = service;
	}
	
	@GetMapping
	public List<ProdutoDTO> listar() {
		return service.listarTodos();
	}
	
	@GetMapping("/{id}")
	public ProdutoDTO buscar(@PathVariable Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO dto) {
		ProdutoDTO criado = service.criar(dto);
		return ResponseEntity.status(201).body(criado);
	}
	
	@PutMapping("/{id}")
	public ProdutoDTO atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
		return service.atualizar(id, dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
