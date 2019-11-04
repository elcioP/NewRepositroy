package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Leilao;
import com.example.demo.repository.LeilaoRepository;

@Service
public class LeilaoService {

	@Autowired
	private LeilaoRepository repository;
	
	
	public Leilao save(Leilao entity){
		return repository.save(entity);
	}
	
	public Leilao findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public List<Leilao> findAll(){
		return repository.findAll();
	}
	
	public boolean delete(Long id){
		Leilao leilao = repository.findById(id).orElse(null);
		if(leilao != null){
			repository.delete(leilao);
			return true;
		}
		return false;
	}
	public boolean delete(Leilao leilao){
		if(leilao != null){
			repository.delete(leilao);
			return true;
		}
		return false;
	}
}
