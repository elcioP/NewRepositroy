package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Promotor;
import com.example.demo.repository.ConvidadoRepository;
import com.example.demo.repository.PromotorRepository;


@Service
public class PromotorService {
	
	@Autowired
	private PromotorRepository repository;
	
	@Autowired
	ConvidadoRepository convidadoRepository;
	
	
	public Promotor save(Promotor entity ){
		return repository.save(entity);
		
	}
	
	public List<Promotor> findAll(){
		return repository.findAll();
	}
	
	public Promotor findById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public boolean delete(Promotor entity){
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id){
		Promotor entity = findById(id);
		if(entity != null){
			repository.delete(entity);
			return true;
		}
		return false;
	}
	
	public int contarConvidados(Long id){
		
		Promotor promotor = this.findById(id);
			if(promotor.getEvento() != null){
				promotor.setConvidados(convidadoRepository.findByEventoIdAndPromotorId(promotor.getEvento().getId(), promotor.getId()));
			}
		promotor.getConvidados().forEach(c -> System.out.println(c.getNome()));
		return  1;
	}

}
