package com.example.demo.carrinho;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.Produto;

@Service
public class CarrinhoService {
	
	@Autowired
	private CarrinhoRepository repository;
	
	public Carrinho post(Carrinho entidade){
		if(validarPrecoProduto(entidade)){
			setValorTotal(entidade);
		}
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
	
	public Carrinho setValorTotal(Carrinho entidade){
		List<Produto> produtos = entidade.getProduto();
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		for(Produto produto : produtos ){
			
			valorTotal = valorTotal.add(produto.getPreco());
		}
		entidade.setValorTotal(valorTotal);
		return entidade;
	}
	
	public  boolean validarPrecoProduto(Carrinho entidade){
		if(entidade.getProduto() != null){
			List<Produto> prods  =  entidade.getProduto();
			for(Produto prod : prods){
				if(prod.getPreco() != null){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	

}
