package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	
	public Categoria save(Categoria categoria){
		return repository.save(categoria);
	}
	
	public boolean delet(Categoria categoria){
		if(categoria != null){
			
			repository.delete(categoria);
			return true;
		}
		return false;
	}

	public boolean delet(Long id) {
		if (id != null) {

			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria findById(Long id){
		return repository.findById(id).orElse(null);
	}
}
