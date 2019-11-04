package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Leilao;
import com.example.demo.service.LeilaoService;

@RestController 
@RequestMapping("/leilao")
@SpringBootApplication
public class LeilaoController {
	
	@Autowired
	private LeilaoService service;
	
	@PostMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable(value = "id") Long id){
		Leilao entity = service.findById(id);
		
		if(entity != null){
			
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
}
