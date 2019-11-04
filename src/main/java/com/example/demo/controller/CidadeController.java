package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cidade;
import com.example.demo.service.CidadeService;

@RestController 
@RequestMapping("/cidade")
@SpringBootApplication
public class CidadeController {
	
	@Autowired
	private CidadeService service;
	
	@PostMapping
	public ResponseEntity<?> post(@Valid @RequestBody List<Cidade> entity){
		if(entity != null){
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@PutMapping
	public ResponseEntity<?> put(@Valid @RequestBody Cidade entity){
		entity = service.save(entity);
		if(entity != null){
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(entity);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
	
	@GetMapping
	public ResponseEntity<?> get(){
		List<Cidade> cidades = service.findAll();
		if(cidades != null){
			return ResponseEntity.status(HttpStatus.OK).body(cidades);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Cidade entity){
		if(service.delete(entity)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete( @PathVariable(value = "id") Long id){
		
		if(service.delete(id)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
