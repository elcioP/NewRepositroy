package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loja extends PessoaGen{
	
	private String nomeLoja;
	private String nrTelefone;
	private String endereco;
	private String cnpj;
	private String razaoSocial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Cidade cidade;
	
	
	private Loja(String nomeLoja , String nrTelefone, String endereco){
		this.nomeLoja = nomeLoja;
		this.nrTelefone = nrTelefone;
		this.endereco = endereco;
	}
	
	private Loja(){
		
	}
	
	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}
	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		endereco = endereco;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
