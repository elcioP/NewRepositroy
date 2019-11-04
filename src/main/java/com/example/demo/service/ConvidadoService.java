package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Convidado;
import com.example.demo.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository repository;
	
	
	public Convidado save(Convidado entity ){
		return repository.save(entity);
		
	}
	
	public List<Convidado> findAll(){
		return repository.findAll();
	}
	
	public Convidado findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public boolean delete(Convidado entity){
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id){
		Convidado entity = findById(id);
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public Convidado findByNome(String nome){
		return repository.findByNome(nome);
	}
	
}
