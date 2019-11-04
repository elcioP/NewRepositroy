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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Estado;
import com.example.demo.service.EstadoService;

@RestController 
@RequestMapping("/estado")
@SpringBootApplication
public class EstadoController {
	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<?> get(){
		List<Estado> estado = service.findAll();
		if(estado != null){
			
			return ResponseEntity.status(HttpStatus.OK).body(estado);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
		Estado entity = service.findById(id);
		if(entity != null){
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Estado entity){
		Estado salvo = service.save(entity);
		
		if(salvo != null){
			return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@Valid @RequestBody Estado entity) {
		Estado salvo = service.save(entity);
		if (salvo != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		if(service.delete(id)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@Valid @RequestBody Estado entity){
		if(service.delete(entity)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
 	

}
