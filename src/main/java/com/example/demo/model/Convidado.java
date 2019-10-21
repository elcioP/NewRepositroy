package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.util.TipoPessoa;
@Entity
public class Convidado extends PessoaGen {
	
	@ApiModelProperty(notes = "nome da pessoa")
	private String nome;
	
	@ApiModelProperty(notes = "tipo de pessoa")
	private TipoPessoa tipo;
	
	@ApiModelProperty(notes = "número de documento")
	private String documento;
	
	@ApiModelProperty(notes = "Promotor que fez o convite")
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name = "promotor_id" )
	private Promotor promotor;
	
	@ApiModelProperty(notes = "Evento que será realizado")
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id" )
	private Evento evento;

	public Convidado(){
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
