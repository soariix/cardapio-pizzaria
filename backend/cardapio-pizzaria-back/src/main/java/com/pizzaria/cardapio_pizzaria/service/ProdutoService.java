package com.pizzaria.cardapio_pizzaria.service;

import com.pizzaria.cardapio.dto.ProdutoDTO;
import com.pizzaria.cardapio_pizzaria.exception.ProdutoNaoEncontradoException;
import com.pizzaria.cardapio_pizzaria.model.Produto;
import com.pizzaria.cardapio_pizzaria.repository.ProdutoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
	
	private final ProdutoRepository repository;
	
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public List<ProdutoDTO> listarTodos() {
		return repository.findAll().stream()
				.map(this::toDTO)
				.toList();
	}
	
	public ProdutoDTO buscarPorId(Long id) {
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		return toDTO(produto);
	}
	
	public ProdutoDTO criar(ProdutoDTO dto) {
		Produto produto = toEntity(dto);
		return toDTO(repository.save(produto));
	}
	
	public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
		Produto produto = repository.findById(id)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(id));
		
		produto.setNome(dto.nome());
		produto.setDescricao(dto.descricao());
		produto.setPreco(dto.preco());
		produto.setCategoria(dto.categoria());
		produto.setImagemUrl(dto.imagemUrl());
		produto.setDisponivel(dto.disponivel());
		
		return toDTO(repository.save(produto));
	}
	
	public void deletar(long id) {
		if (!repository.existsById(id)) {
			throw new ProdutoNaoEncontradoException(id);
		}
		repository.deleteById(id);
	}
	
	private ProdutoDTO toDTO(Produto p) {
		return new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(),
				p.getPreco(), p.getCategoria(), p.getImagemUrl(), p.getDisponivel());
	}
	
	private Produto toEntity(ProdutoDTO dto) {
		Produto p = new Produto();
		p.setNome(dto.nome());
		p.setDescricao(dto.descricao());
		p.setPreco(dto.preco());
		p.setCategoria(dto.categoria());
		p.setImagemUrl(dto.imagemUrl());
		p.setDisponivel(dto.disponivel() != null ? dto.disponivel() : true);
		return p;
	}
}
