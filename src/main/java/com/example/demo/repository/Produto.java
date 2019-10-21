package com.example.demo.repository;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.carrinho.Carrinho;

@Entity
@ApiModel(description = "Todos os detalhes sobre os Produtos ")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "ID do Produto")
	public Long id;
	
	@ApiModelProperty(notes = "Nome do Produto")
	public String nome;
	
	@ApiModelProperty(notes = "Descrição do Produto")
	public String descricao;
	
	@ApiModelProperty(notes = "Preço do Produto")
	public BigDecimal preco;
	
	@ApiModelProperty(notes = "Data de Criação")
	@CreationTimestamp
	public Calendar createdAt;
	
	@ApiModelProperty(notes = "Data de Atualização")
	@UpdateTimestamp
	public Calendar updatedAt;
	
	@ApiModelProperty(notes = "Carrinho de compra que esse produto pode estar")
	
	@ManyToOne 
	@JoinColumn(name = "produto")
	public Carrinho carrinho;
	
	public void Produto(){
		
	}
	public void Produto(String descricao, String nome){
		this.descricao = descricao;
		this.nome = nome;
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}