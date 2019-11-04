package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Convidado;
import com.example.demo.model.Evento;
import com.example.demo.model.Promotor;
import com.example.demo.service.EventoService;
import com.example.demo.service.FecharEventoService;

public class FecharEventoTests extends ExemploApplicationTests  {
	
	private static final Logger LOG = LoggerFactory.getLogger(FecharEventoTests.class);
	
	Evento evento;
	
	@Autowired
	FecharEventoService service;
	
	@Autowired
	EventoService eventoService;
	
	@Before
	public void criarEntidade(){
		evento = new Evento();
		evento.setDescricaoEvento("inauguração do mercado");
		evento.setNomeEvento("evento");
		evento.setDataEvento(new Date());
		
		Promotor promotor = new Promotor();
		promotor.setNome("promotor");
		promotor.setDocumento("21312412");
		
		Convidado convidado = new Convidado();
		convidado.setNome("convidado");
		convidado.setPromotor(promotor);
		
		promotor.setConvidados(Arrays.asList(convidado));
		
		evento.setPromotores(Arrays.asList(promotor));
		evento.setConvidados(Arrays.asList(convidado));
		
	}

	
	@Test
	public void test_criteria(){
		
	List<Evento> eventos =	service.buscarRelacionamentos(eventoService.save(evento).getNomeEvento());
		
		eventos.forEach(e -> System.out.println( "<--------------EVENTO--------------->" + e.getNomeEvento()));
		
		List<Promotor> promotores = evento.getPromotores();
		
		promotores.forEach(p -> System.out.println("<--------------PROMOTOR--------------->" + p.getNome()));
		
		List<Convidado> convidados = evento.getConvidados();
		
		convidados.forEach(c -> System.out.println("<--------------CONVIDADOS--------------->" + c.getNome()));
		
	}
	
	@Test
	public void test_repository(){
		Evento evs = service.buscarEventoCompleto(eventoService.save(evento).getId());
		
		System.out.println("evento: " + evento.getNomeEvento());
		System.out.println("promotor: " + evento.getPromotores().get(0).getNome());
		System.out.println("convidado: " + evento.getConvidados().get(0).getNome());
		
		
	}
	

}
