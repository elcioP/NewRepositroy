package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.demo.util.TipoPessoa;
@Entity
public class Promotor extends PessoaGen{
	
	@ApiModelProperty(notes = "nome da pessoa")
	private String nome;
	
	@ApiModelProperty(notes = "tipo de pessoa")
	private TipoPessoa tipo;
	
	@ApiModelProperty(notes = "número de documento")
	private String documento;
	
	
	@ApiModelProperty(notes = "Lista de convidados")
	@OneToMany(mappedBy= "promotor")
	private List<Convidado> convidados;
	
	@ApiModelProperty(notes = "Evento que será realizado")
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id" )
	private Evento evento;
	
	
	public Promotor(){
		
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


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}


	public List<Convidado> getConvidados() {
		return convidados;
	}


	public void setConvidados(List<Convidado> convidados) {
		this.convidados = convidados;
	}


	public Evento getEvento() {
		return evento;
	}


	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	

}
