package com.example.demo.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Convidado;
import com.example.demo.model.Evento;
import com.example.demo.model.Promotor;
import com.example.demo.repository.ConvidadoRepository;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.FecharEventoRepository;
import com.example.demo.repository.PromotorRepository;
import com.example.demo.util.HibernateUtil;
import com.google.common.collect.Lists;

@Repository
public class FecharEventoDAO {
	
	@Autowired
	FecharEventoRepository fecharEventorepository; 
	
	@Autowired
	ConvidadoRepository convidadoRepository;
	
	@Autowired
	PromotorRepository promotorRepository;
	
	@Autowired
	EventoRepository eventoRepository;
	
	
	
	public List<Evento> buscar(Evento evento){
		Session session = HibernateUtil.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Evento> cr = builder.createQuery(Evento.class);
		Root<Evento> from = cr.from(Evento.class);
		
		Query<Evento> query = 	session.createQuery(cr);
		List<Evento> results  = query.getResultList();
		
		return null;
		

	}
	
	public List<Evento> agoraVai(String nomeEvento){
		Specification<Evento> especification =	EventoEspecification.teste(nomeEvento);
			return Lists.newArrayList(fecharEventorepository.findAll(especification));
	}
	
	
	public Evento retornarEventoCompleto(Long id){
		Evento evento = eventoRepository.findById(id).orElse(null);
		evento.setPromotores(promotorRepository.findByEventoId(id));
		evento.setConvidados(convidadoRepository.findByEventoId(id));
		
		return evento;
	}
	
	

}
