package com.example.demo.model;

import java.util.List;

import javax.persistence.OneToMany;

public class ProdutoLeilao extends PessoaGen{

	@OneToMany(orphanRemoval = true, mappedBy = "produtoLeilao")
	private List<Produto> produtos;

	@OneToMany(orphanRemoval = true, mappedBy = "produtoLeilao")
	private List<Leilao> leilao;

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Leilao> getLeilao() {
		return leilao;
	}

	public void setLeilao(List<Leilao> leilao) {
		this.leilao = leilao;
	}
}
