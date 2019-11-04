package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;
import com.example.demo.model.ProdutoVenda;
import com.example.demo.repository.ProdutoVendaRepository;

@Service
public class ProdutoVendaService {
	
	@Autowired
	private ProdutoVendaRepository repository;
	
	public ProdutoVenda salvarProdutoVenda(ProdutoVenda  produtoVenda){
		return repository.save(produtoVenda);
	}
	
	public ProdutoVenda findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public ProdutoVenda buscarPorListaDeProdutos(List<Produto> produtos){
		return repository.findByProdutos(produtos);
	}
	
	public List<ProdutoVenda> findAll(){
		return repository.findAll();
	}
 	
	

}
