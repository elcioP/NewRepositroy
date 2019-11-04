package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.service.CategoriaService;

public class CategoriaTests extends ExemploApplicationTests {
	
	private static final Logger LOG = LoggerFactory.getLogger(CategoriaTests.class);
	
	@Autowired
	private CategoriaService service;
	
	private Categoria categoria;
	
	@Before
	public void criarEntidade(){
		categoria = new Categoria();
		categoria.setNome("primeira categoria");
		
		Produto produto = new Produto();
		produto.setDescricao("produto de limpeza");
		produto.setNome("limpa vidros");
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(produto);
		categoria.setProdutos(produtos);
	}
	
	
	@Test
	public void post_test() throws Exception{
		LOG.info("<---------------------TESTE POST CATEGORIA--------------------------->");
		
		String content = this.mapper.writeValueAsString(categoria);
		
		ResultActions rep = this.mock.perform(post("/categoria").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	public void delete_test() throws Exception {
		LOG.info("<-------------TESTE DELETE CATEGORIA------------------>");

		String content = this.mapper
				.writeValueAsString(service.save(categoria));

		ResultActions resp = this.mock.perform(
				delete("/categoria").content(content).contentType(
						MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

	}
	
	@Test
	public void test_get_all() throws Exception {
		LOG.info("<-------------TESTE GET TODAS AS  CATEGORIAS------------------>");
		String content = this.mapper.writeValueAsString(service.save(categoria));
		
		ResultActions resp = this.mock.perform(get("/categoria"))
				.andExpect(status().isOk());
			
				
	}
	
	@Test
	public void teste_get_by_id() throws Exception{
	String content = this.mapper.writeValueAsString(service.save(categoria));
		
		ResultActions resp = this.mock.perform(get("/categoria/" + categoria.getId()))
				.andExpect(status().isOk());
	}

}
