package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.example.demo.carrinho.Venda;
@Entity
public class ProdutoVenda extends PessoaGen{
	
	@OneToMany(orphanRemoval = true , mappedBy= "produtoVenda")
	private List<Produto> produtos;

	@OneToMany(orphanRemoval = true , mappedBy= "produtoVenda")
	private List<Venda> vendas;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	

}
