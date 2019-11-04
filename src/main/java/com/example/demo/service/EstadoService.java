package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Estado;
import com.example.demo.repository.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repository;
	
	public Estado save(Estado entity){
		return repository.save(entity);
	}
	
	public boolean delete(Estado entity){
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id){
		Estado entity = repository.findById(id).orElse(null);
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public List<Estado> findAll(){
		return repository.findAll();
	}
	
	public Estado findById(Long id){
		return repository.findById(id).orElse(null);
	}
}
