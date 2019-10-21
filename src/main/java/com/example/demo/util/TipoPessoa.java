package com.example.demo.util;

public enum TipoPessoa {
	CONVIDADO("CONVIDADO"), PROMOTOR("PROMOTOR");
	
	
	private String descricao;
	
	TipoPessoa(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
}
