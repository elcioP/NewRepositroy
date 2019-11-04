package com.example.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.carrinho.Venda;
import com.example.demo.model.Produto;
import com.example.demo.model.ProdutoVenda;
import com.example.demo.repository.ProdutoService;
import com.example.demo.service.ProdutoVendaService;
import com.example.demo.service.VendaService;

public class FecharVendaTests extends ExemploApplicationTests {
	private static final Logger LOG = LoggerFactory.getLogger(FecharVendaTests.class);
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private ProdutoVendaService produtoVendaService;
	
	private Produto produto;
	private Venda venda;
	
	@Before
	public void montarEntidade(){
		produto = new Produto();
		produto.setNome("limpa vidros");
		produto.setPreco(new BigDecimal(2.00));
		
	}
	
	
	@Test
	public void test_buscar_produto(){
		Produto produtoSalvo =	produtoService.save(produto);
		 Produto produtoChamado = vendaService.chamarProduto(produtoSalvo.getId());
		 
		 Assert.assertEquals(produtoSalvo, produtoChamado);
	}
	
	
	@Test
	public void test_calcular_valores(){
		vendaService.chamarProduto(produtoService.save(produto).getId());
		Assert.assertEquals(new BigDecimal(10.00), vendaService.calcularValorVenda(new BigDecimal("5.00")));
	}
	
	@Test
	public void test_fechar_venda(){
		Long i = produtoService.save(produto).getId();
		Produto produto1 = vendaService.chamarProduto(i);
		vendaService.calcularValorVenda(new BigDecimal("10"));
		vendaService.FecharVenda();
		List<Produto> produtos = new ArrayList<>();
		produtos.add(produto1);
		ProdutoVenda produtosVenda = produtoVendaService.findById(1L);
		
		System.out.println(produtosVenda.getCriado());
	}
	
	
}
