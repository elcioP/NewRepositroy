package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Loja;
import com.example.demo.repository.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repository;

	public Loja save(Loja entity){
		return repository.save(entity);
	}

	public boolean delete(Loja entity){
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public boolean delete(long id){
		Loja loja = repository.findById(id).orElse(null);
		if(loja != null){
			repository.delete(loja);
			return true;
		}
		return false;
	}
	
	public List<Loja> findAll(){
		return repository.findAll();
	}
	
	public Loja findById(Long id){
		return repository.findById(id).orElse(null);
	}
}
