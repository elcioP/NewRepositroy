package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cidade;
import com.example.demo.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public Cidade save(Cidade entity){
		return repository.save(entity);
	}
	
	public List<Cidade> save(List<Cidade> entity){
		return repository.saveAll(entity);
	}
	
	public List<Cidade> findAll(){
		return repository.findAll();
	}
	
	public Cidade findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public boolean delete(Long id){
		Cidade cidade = repository.findById(id).orElse(null);
		if(cidade != null){
			repository.delete(cidade);
			return true;
		}
		return false;
	}
	public boolean delete(Cidade cidade){
		if(cidade != null){
			repository.delete(cidade);
			return true;
		}
		return false;
	}
}
