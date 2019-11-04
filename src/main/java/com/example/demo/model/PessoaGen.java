package com.example.demo.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class PessoaGen implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "ID da pessoa")
	protected Long id;
	
	@ApiModelProperty(notes = "data de criação")
	@CreationTimestamp
	protected Date criado;
	
	@ApiModelProperty(notes = "data que foi alterado")
	@UpdateTimestamp
	protected Date alterado;

	public PessoaGen(){
		
	}
	public Date getCriado() {
		return criado;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setCriado(Date criado) {
		this.criado = criado;
	}
	public Date getAlterado() {
		return alterado;
	}
	public void setAlterado(Date alterado) {
		this.alterado = alterado;
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
		PessoaGen other = (PessoaGen) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
