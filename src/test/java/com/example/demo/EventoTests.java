package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Evento;
import com.example.demo.service.EventoService;
import com.example.demo.service.FecharEventoService;

public class EventoTests extends ExemploApplicationTests {
	private static final Logger LOG = LoggerFactory.getLogger(EventoTests.class);
	
	Evento evento;
	
	@Autowired
	EventoService service;
	
	
	
	@Before
	public void criarEntidade(){
		evento = new Evento();
		evento.setDescricaoEvento("inauguração do mercado");
		evento.setDataEvento(new Date());
	}
	
	
	@Test
	public void test_Save() throws Exception{
		String content = this.mapper.writeValueAsString(this.evento);
		
			ResultActions resp = this.mock
				.perform(post("/evento").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void test_Put() throws Exception{
		String content = this.mapper.writeValueAsString(evento);
		service.save(evento);
		
		ResultActions resp = this.mock.perform(put("/evento").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void test_Delete() throws Exception{
		service.save(evento);
		
		String content = this.mapper.writeValueAsString(evento);
		ResultActions respo = this.mock.perform(delete("/evento").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void test_get_All() throws Exception{
		service.save(evento);
		
		String content = this.mapper.writeValueAsString(evento);
		
		ResultActions resp = this.mock.perform(get("/evento"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void test_get_by_id()throws Exception{
		service.save(evento);
		
		String content = this.mapper.writeValueAsString(evento);
		
		ResultActions resp = this.mock.perform(get("/evento/" + evento.getId()))
				.andExpect(status().isOk());
		
	}
	

}
