package com.example.demo.util;

public enum FormaPagamento {
	CARTAO_DEBITO("CARTAO_DEBITO"), CARTAO_CREDITO("CARTAO_CREDITO"),
	BOLETO("BOLETO"), DINHEIRO("DINHEIRO"), BARRA_DE_OURO("BARRA_DE_OUTO"), ABRACO("ABRACO");
	

	
	private String descricao;
	
	FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }

}
