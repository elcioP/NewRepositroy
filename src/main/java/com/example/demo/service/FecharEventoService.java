package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.FecharEventoDAO;
import com.example.demo.model.Evento;
import com.example.demo.model.Promotor;

@Service
public class FecharEventoService {

	@Autowired
	EventoService eventoService;

	@Autowired
	private FecharEventoDAO dao;

	public List<Evento> buscarRelacionamentos(String nomeEvento) {
		return dao.agoraVai(nomeEvento);
	}
	
	public Evento buscarEventoCompleto(Long id){
		return dao.retornarEventoCompleto(id);
	}
}
