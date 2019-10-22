package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.model.Convidado;
import com.example.demo.model.Evento;
import com.example.demo.model.Promotor;
import com.example.demo.service.ConvidadoService;
import com.example.demo.util.TipoPessoa;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConvidadoTests extends ExemploApplicationTests {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConvidadoTests.class);
	
	Convidado convidado = new Convidado();
	
	@Autowired
	private ConvidadoService service;
	
	@Before
	public void criarConvidado(){
		Evento evento = new Evento();
		evento.setNomeEvento("inauguração do mercado");
		evento.setDescricaoEvento("vai ter café da manhã para as pessoas conhecerem o espaço e os produtos");
		
		Promotor promotor = new Promotor();
		promotor.setNome("primeiro promotor");
		promotor.setDocumento("321923912");
		promotor.setTipo(TipoPessoa.PROMOTOR);
		
		
		convidado.setNome("primeiro convidado");
		convidado.setTipo(TipoPessoa.CONVIDADO);
	}
	
	
	
	
	@Test
	public void incluir_Convidado() throws Exception {
		LOG.info("<----INCLUINDO CONVIDADOS --->");
		
		String content = this.mapper.writeValueAsString(this.convidado);
		
		ResultActions resp = this.mock
				.perform(post("/convidado").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void alterar_Convidado() throws Exception{
		LOG.info("<----Alterandoconvidado");
		
		String content = this.mapper.writeValueAsString(this.convidado);
		service.save(convidado);
		
		ResultActions resp = this.mock.perform(put("/convidado").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
		
	}
	
	@Test
	public void deletar_Convidado() throws Exception{
		LOG.info("<---- deletando convidado");
		service.save(convidado);
		
		String content = this.mapper.writeValueAsString(this.convidado);
		ResultActions respo = this.mock.perform(delete("/convidado").content(content).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void buscar_Todos_Os_Convidados() throws Exception{
		LOG.info("<----buscar todos os convidados");
		service.save(convidado);
		
		String content = this.mapper.writeValueAsString(this.convidado);
		
		ResultActions resp = this.mock.perform(get("/convidado"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void buscarConvidadoPorId() throws Exception{
		LOG.info("<----buscar convidado por id");
		service.save(convidado);
		String content = this.mapper.writeValueAsString(convidado);
		
		ResultActions respo = this.mock.perform(get("/convidado/" + convidado.getId()))
				.andExpect(status().isOk());
		
	}

}
