package com.example.demo.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.carrinho.Venda;
@Repository
public interface VendaRepository  extends JpaRepository<Venda, Long>{
	
	   @Query(value = "select v.* from venda v where v.DataVenda between ?1 and ?2", nativeQuery = true)
	    List<Venda> buscarPessoasComDataDeNascimentoNoIntervalo(Date de, Date ate);

}
