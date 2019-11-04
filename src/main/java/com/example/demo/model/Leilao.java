package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Leilao extends PessoaGen{
	private Date dataLeilao;
	private String nomeLeilao;
	List<Produto> produto;
	
	@ManyToOne
	@JoinColumn(name = "produtoLeilao")
	private ProdutoLeilao produtoLeilao;
	
	public Date getDataLeilao() {
		return dataLeilao;
	}
	public void setDataLeilao(Date dataLeilao) {
		this.dataLeilao = dataLeilao;
	}
	public String getNomeLeilao() {
		return nomeLeilao;
	}
	public void setNomeLeilao(String nomeLeilao) {
		this.nomeLeilao = nomeLeilao;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	public ProdutoLeilao getProdutoLeilao() {
		return produtoLeilao;
	}
	public void setProdutoLeilao(ProdutoLeilao produtoLeilao) {
		this.produtoLeilao = produtoLeilao;
	}
}
