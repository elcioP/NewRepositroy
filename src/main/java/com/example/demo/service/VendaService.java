package com.example.demo.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.carrinho.Venda;
import com.example.demo.model.Produto;
import com.example.demo.model.ProdutoVenda;
import com.example.demo.repository.ProdutoService;
import com.example.demo.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoVendaService produtoVendaService;

	@Autowired
	private VendaService vendaService;

	@Autowired
	private VendaRepository vendaRepository;

	private Produto produto;
	private BigDecimal valortotalVenda = BigDecimal.ZERO;
	private List<Produto> produtos = new ArrayList<>();
	private ProdutoVenda produtoVenda = new ProdutoVenda();
	private Venda venda = new Venda();
	private HashMap<Long, BigDecimal> quantidadeProduto = new HashMap<>();

	public void FecharVenda() {
		montarVenda();
		montarProdutoVenda();
		venda.setProdutoVenda(produtoVenda);
		produto.setProdutoVenda(produtoVenda);
		
		produtoVendaService.salvarProdutoVenda(produtoVenda);
		this.salvarVenda(venda);
		
		limparElementos();
	}

	public BigDecimal calcularValorVenda(BigDecimal quantidade) {
		MathContext mathContext = new MathContext(2, RoundingMode.UP );
		quantidadeProduto.put(produto.getId(), quantidade);
		BigDecimal valorTotal = BigDecimal.ZERO;
		valorTotal = produto.getPreco();
		valorTotal =  valorTotal.multiply(quantidade, mathContext);

		valortotalVenda = valortotalVenda.add(valorTotal, mathContext);
		return valortotalVenda;
	}

	public Produto chamarProduto(Long id) {
		if (produto == null) {
			produto = produtoService.findById(id);
			produtos.add(produto);
			return produto;
		}
		return produto;
	}

	public Venda salvarVenda(Venda venda) {
		return vendaRepository.save(venda);
	}

	public void montarVenda() {
		venda.setValorVenda(valortotalVenda);
		venda.setDataVenda(new Date());

	}

	public void montarProdutoVenda() {
		List<Venda> vendas = new ArrayList<>();
		produtoVenda.setProdutos(produtos);
		vendas.add(venda);
		produtoVenda.setVendas(vendas);

	}
	
	private void limparElementos(){
		produto = null;
		valortotalVenda = null;
		produtos = null;
		produtoVenda = null;
		venda = null;
		quantidadeProduto = null;
	}

	
	public List<Venda> buscarVendasBetween(Date de , Date ate){
		return vendaRepository.buscarPessoasComDataDeNascimentoNoIntervalo(de, ate);
		
	}
	
	public Venda findById(Long id){
		return vendaRepository.findById(id).orElse(null);
	}
}
