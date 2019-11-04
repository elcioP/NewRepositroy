package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BalancoPeriodoRepository;

@Service
public class BalancoPeriodoService {
	
	@Autowired
	private BalancoPeriodoRepository balancoRepository;
	
	@Autowired
	private VendaService vendaService;
	
	private void calcularTudo(Date de, Date ate){
		vendaService.buscarVendasBetween(de, ate).forEach(v -> System.out.println(v));
	}
	
	

}
