package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.carrinho.Carrinho;
import com.example.demo.carrinho.CarrinhoService;
import com.example.demo.repository.Produto;

public class CarrinhoTests extends ExemploApplicationTests  {
	
	private static final Logger LOG = LoggerFactory.getLogger(CarrinhoTests.class);
	
	private Carrinho entity;
	private List<Produto>  produtos;
	
	@Autowired
	private CarrinhoService service;
	
	
	@Before
	public void createEntity() {
		Produto produto = new Produto();
		Produto produto2 = new Produto();
	
		produto.setDescricao("produto de limpeza");
		produto.setNome("veja bem");
		produto.setPreco(new BigDecimal(14.99));
		
		produto2.setDescricao("ursinho de pelúcia");
		produto2.setNome("pimpão");
		produto2.setPreco(new BigDecimal(49.99));
		
		produtos = new ArrayList<>();
		produtos.add(produto);
		produtos.add(produto2);
		
		entity = new Carrinho();
		entity.setNomeCliente("joarez");
		entity.setQtdProduto(2);
		entity.setProduto(produtos);

	}
	
	
	@Test
	public void test_save() throws Exception {
		LOG.info("CHAMANDO SALVAR CARRINHO POR ENDPOINT");

		String content = this.mapper.writeValueAsString(this.entity);

		ResultActions resp = this.mock
				.perform(post("/carrinho").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	
	@Test
	public void test_findAll() throws Exception {
		LOG.info("CHAMANDO GET DE TODOS OS CARRINHOS");

		service.post(entity);

		ResultActions resp = this.mock
				.perform(get("/carrinho"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void test_put() throws Exception {
		LOG.info("CHAMANDO PUT DE UM CARRINHO");

		String content = this.mapper.writeValueAsString(this.entity);
		service.post(entity);

		ResultActions resp = this.mock
				.perform(put("/carrinho").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void test_delete_by_id() throws Exception {
		LOG.info("CHAMANDO DELETE DE UM CARRINHO");
		
		Carrinho carrinho = service.getById(service.post(this.entity).getId());

		ResultActions resp = this.mock.perform(delete("/carrinho/"+ carrinho.getId()))
				.andExpect(status().isNoContent());
	}
	
	
	@Test
	public void teste_dele_por_corpo() throws Exception {
		LOG.info("CHAMANDO O DELETE POR CORPO");

		String content = this.mapper.writeValueAsString(this.entity);
		Carrinho carrinho = service.post(entity);
	

		ResultActions resp = this.mock
				.perform(delete("/carrinho").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}
