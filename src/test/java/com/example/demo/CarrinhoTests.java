package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.carrinho.Carrinho;
import com.example.demo.carrinho.CarrinhoService;
import com.example.demo.model.Produto;

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
		produto.setNome("veja");
		produto.setPreco(new BigDecimal(14.99));
		
		produto2.setDescricao("produto de lazer");
		produto2.setNome("ursinho de pelúcia");
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
	public void test_delete_by_body() throws Exception {
		LOG.info("CHAMANDO O DELETE POR CORPO");

		String content = this.mapper.writeValueAsString(this.entity);
		Carrinho carrinho = service.post(entity);
	

		ResultActions resp = this.mock
				.perform(delete("/carrinho").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void test_validador_preco() throws Exception{
		Assert.assertEquals(true, service.validarPrecoProduto(entity));
	}
	
	@Test
	public void test_valor_total_compra() throws Exception{
		BigDecimal valor = BigDecimal.ZERO;
		valor = valor.add(new BigDecimal(14.99));
		valor = valor.add(new BigDecimal(49.99));
		
		Assert.assertEquals(valor, service.setValorTotal(entity).getValorTotal());
	}
	
	@Test
	public void teste_qtd_produto() throws Exception{
		service.setQuantidadeProdutos(entity);
		Assert.assertEquals(2, entity.getQtdProduto());
		
	}
	
	@Test
	public void test_update_at() throws Exception{
		
		Produto produto  = new Produto();
		produto.setDescricao("produto de limpeza");
		produto.setNome("alcool em gel");
		produto.setPreco(new BigDecimal(5.00));
		
		Produto prod2 = new Produto();
		prod2.setDescricao("produto de higiene");
		prod2.setNome("escova de dente");
		prod2.setPreco(new BigDecimal(10.00));
		
		Produto prod3 = new Produto();
		prod3.setNome("Carro");
		prod3.setDescricao("produto automobilistico");
		prod3.setPreco(new BigDecimal(30.00));
		
		
		Carrinho carrinho = new Carrinho();
		carrinho.setNomeCliente("joão");
		carrinho.setProduto(Arrays.asList(produto, prod2, prod3));
		BigDecimal valorTotal = BigDecimal.ZERO;
		
//		carrinho.getProduto().forEach(p -> valorTotal = valorTotal.add(p.getPreco()));
		for(Produto p : carrinho.getProduto()){
			valorTotal =	valorTotal.add(p.getPreco());
		}
		carrinho.setValorTotal(valorTotal);
		
		System.out.println(carrinho.getValorTotal());
		
		

		
		
		
	}
	
	
	
	@Test
	public void test_caellum(){

		List<String> palavras = Arrays.asList(" LETS GO da silva junior zibranium duas vezes zibranium","zibranium","rodrigo", "paulo", "caelum", "pi"); 
//		
//		for(String p : palavras){
//			System.out.println(p);
//		}
//		
		Comparator<String> comparador = (s1, s2) -> Integer.compare(s1.length(), s2.length());
	
		palavras.sort(comparador);
		
		System.out.println("<------------------------------------------------------>");
		
		palavras.forEach(p -> System.out.println(p));
		
		
	}
	

}
