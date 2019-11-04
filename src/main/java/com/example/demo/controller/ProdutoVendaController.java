package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProdutoVenda;
import com.example.demo.service.ProdutoVendaService;

@RestController 
@RequestMapping("/produtoVenda-controller")
@SpringBootApplication
public class ProdutoVendaController {

	@Autowired
	private ProdutoVendaService service;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody ProdutoVenda entity) throws Exception {

			entity = service.salvarProdutoVenda(entity);
			if (entity == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
}
