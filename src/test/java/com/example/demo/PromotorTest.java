package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Promotor;
import com.example.demo.service.PromotorService;
import com.example.demo.util.TipoPessoa;

public class PromotorTest extends ExemploApplicationTests  {
	private static final Logger LOG = LoggerFactory.getLogger(PromotorTest.class);
	
	@Autowired
	private PromotorService promotorService;
	
	private Promotor promotor;
	
	@Before
	public void criarEntidade(){
		promotor = new Promotor();
		promotor.setNome("promotor");
		promotor.setDocumento("213213");
		promotor.setTipo(TipoPessoa.PROMOTOR);
	}
	
	@Test
	public void test_Save() throws Exception{
		String content = this.mapper.writeValueAsString(this.promotor);
		
			ResultActions resp = this.mock
				.perform(post("/promotor").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void test_Put() throws Exception{
		String content = this.mapper.writeValueAsString(promotor);
		promotorService.save(promotor);
		
		ResultActions resp = this.mock.perform(put("/promotor").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void test_Delete() throws Exception{
		LOG.info("<---- deletando promotor");
		promotorService.save(promotor);
		
		String content = this.mapper.writeValueAsString(promotor);
		ResultActions respo = this.mock.perform(delete("/promotor").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void test_Post_All() throws Exception{
		LOG.info("<----buscar todos os promotores");
		promotorService.save(promotor);
		
		String content = this.mapper.writeValueAsString(promotor);
		
		ResultActions resp = this.mock.perform(get("/promotor"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void test_post_by_id()throws Exception{
		promotorService.save(promotor);
		
		String content = this.mapper.writeValueAsString(promotor);
		
		ResultActions resp = this.mock.perform(get("/promotor/" + promotor.getId()))
				.andExpect(status().isOk());
		
	}
	
}
