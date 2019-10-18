package com.example.demo.carrinho;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.util.FormaPagamento;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository repository;
	
	public Carrinho post(Carrinho entidade){
		return repository.save(entidade);
	}
	
	public List<Carrinho> getAll(){
		return repository.findAll();
	}
	
	public Carrinho getById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public boolean delete(Long id){
		if(id != null){
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean delete(Carrinho entidade){
		if(entidade != null){
			repository.delete(entidade);
			return true;
		}
		return false;
	}
	
	
	
	
	

}
