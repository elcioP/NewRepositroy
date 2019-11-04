package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;



@Entity
public class Categoria extends PessoaGen{
	private String nome;
	
	@OneToMany(orphanRemoval = true , mappedBy= "categoria")
	private List<Produto> produtos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}


}
