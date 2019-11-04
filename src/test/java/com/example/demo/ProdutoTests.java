package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoService;

public class ProdutoTests extends ExemploApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoTests.class);

	private Produto entity;

	@Autowired
	private ProdutoService service;

	@Before
	public void createEntity() {
		entity = new Produto();

		entity.setId(new Long(1));
		entity.setNome("Camisa Social Jedi");
		entity.setDescricao("Camisa preferida do Mestre Yoda");
		entity.setPreco(new BigDecimal("99.90"));

	}

	@Test
	public void test_save() throws Exception {
		LOG.info("Produto Save Called!");

		String content = this.mapper.writeValueAsString(this.entity);

		ResultActions resp = this.mock
				.perform(post("/produto").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void test_findAll() throws Exception {
		LOG.info("Produto findAll Called!");

		service.save(this.entity);

		ResultActions resp = this.mock
				.perform(get("/produto"))
				.andExpect(status().isOk());
	}

	@Test
	public void test_put() throws Exception {
		LOG.info("Produto Put Called!");

		String content = this.mapper.writeValueAsString(this.entity);
		service.save(this.entity);

		ResultActions resp = this.mock
				.perform(put("/produto").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}

	@Test
	public void test_delete() throws Exception {
		LOG.info("Produto Delete Called!");
		
		Produto produto = service.findById(service.save(this.entity).getId());

		ResultActions resp = this.mock.perform(delete("/produto/"+produto.getId()))
				.andExpect(status().isNoContent());
	}
}
