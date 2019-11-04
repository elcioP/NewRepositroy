package com.example.demo.carrinho;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.PessoaGen;
import com.example.demo.model.ProdutoVenda;

@Entity
public class Venda extends PessoaGen{
	
	private Date DataVenda;
	private BigDecimal valorVenda;
	private Integer qtdProduto;
	
	@ApiModelProperty(notes = "Relacionamento da venda com o produto")
	@ManyToOne
	@JoinColumn(name = "produtoVenda")
	private ProdutoVenda produtoVenda;
	
	public Date getDataVenda() {
		return DataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		DataVenda = dataVenda;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public ProdutoVenda getProdutoVenda() {
		return produtoVenda;
	}
	public void setProdutoVenda(ProdutoVenda produtoVenda) {
		this.produtoVenda = produtoVenda;
	}
	public Integer getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(Integer qtdProduto) {
		this.qtdProduto = qtdProduto;
	}

}
