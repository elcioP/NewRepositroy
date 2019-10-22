package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Evento;
import com.example.demo.repository.EventoRepository;
import com.example.demo.repository.EventoRepository;

@Service
public class EventoService {

	@Autowired
	private EventoRepository repository;
	
	
	public Evento save(Evento entity ){
		return repository.save(entity);
		
	}
	
	public List<Evento> findAll(){
		return repository.findAll();
	}
	
	public Evento findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public boolean delete(Evento entity){
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id){
		Evento entity = findById(id);
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
}
