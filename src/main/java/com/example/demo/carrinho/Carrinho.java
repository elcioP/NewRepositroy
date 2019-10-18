package com.example.demo.carrinho;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.demo.repository.Produto;
import com.example.demo.util.FormaPagamento;

@Entity
public class Carrinho {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "ID do carrinho de compas")
	private Long id;
	
	@ApiModelProperty(notes = "data de pagamento da compra")
	private Date dataPagamento;
	
	@ApiModelProperty(notes = "lista de produtos que estão no  carrinho")
	@OneToMany(orphanRemoval = true , mappedBy= "carrinho")
	private List<Produto> produto;
	
	@ApiModelProperty(notes = "quantidade de produtos que esse carrinho possui")
	private int qtdProduto;
	
	@ApiModelProperty(notes = "data de criação do carrinho")
	@CreationTimestamp
	private Date createAt;
	
	@ApiModelProperty(notes = "data que foi alterado o carrino")
	@UpdateTimestamp
	private Date updateAt;
	
	@ApiModelProperty(notes = "tipo de pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPagamento formaDePagamento;
	
	@ApiModelProperty(notes = "data que foi alterado o carrino")
	private BigDecimal valorTotal;
	
	@ApiModelProperty(notes = "nome do cliente")
	private String nomeCliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public int getQtdProduto() {
		return qtdProduto;
	}

	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public FormaPagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaPagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
		Carrinho other = (Carrinho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
