package com.example.demo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.model.Evento;

public class EventoEspecification {
	
	  @SuppressWarnings("serial")
	public static Specification<Evento> byNomeEvento(String name) {
		    return new Specification<Evento>() {
		      @Override
		      public Predicate toPredicate(Root<Evento> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		    	 
		    	  List<Predicate> predicates = new ArrayList<>();
		        
		    	  predicates.add(builder.like(root.<String>get("nomeEvento"), 
		            String.format("%s%", name.trim())));
		        return builder.and(predicates.toArray(new Predicate[]{}));
		      }
		    };
		  }
	  
	  

	  @SuppressWarnings("serial")
	public static Specification<Evento> teste(String name) {
		    return new Specification<Evento>() {
		      @Override
		      public Predicate toPredicate(Root<Evento> root,
		          CriteriaQuery<?> query, CriteriaBuilder builder) {
		        return builder.like(root.<String>get("nomeEvento"), 
		            String.format("s", name.trim()));
		      }
		    };
		  }
	  

}
