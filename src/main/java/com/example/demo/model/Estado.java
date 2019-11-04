package com.example.demo.model;

import javax.persistence.Entity;

@Entity
public class Estado extends PessoaGen{
	private String nomeEstado;
	private String uf;
	
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}
