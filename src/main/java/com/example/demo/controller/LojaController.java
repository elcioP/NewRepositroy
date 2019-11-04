package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Loja;
import com.example.demo.service.LojaService;

@RestController 
@RequestMapping("/loja")
@SpringBootApplication
public class LojaController {
	
	@Autowired
	private LojaService service;
	
	@PostMapping
	public ResponseEntity<?> post(@RequestBody Loja entity) {
		Loja loja = service.save(entity);
		if (loja != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(loja);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping
	public ResponseEntity<?> get(){
		List loja = service.findAll();
		if(loja.size() > 0){
			return ResponseEntity.status(HttpStatus.CREATED).body(loja); 
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") Long id){
		Loja loja = service.findById(id);
		if(loja != null){
			return ResponseEntity.status(HttpStatus.CREATED).body(loja); 
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Loja entity){
		if(service.delete(entity)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
		Loja loja = service.findById(id);
		
		if(service.delete(loja)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Loja entity){
		Loja ent =service.save(entity);
		if(ent != null){
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(ent);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
