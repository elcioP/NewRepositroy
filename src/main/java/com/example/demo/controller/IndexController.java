package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.ProdutoService;


@RestController // Garante que a API permaneça sem estado
@RequestMapping("/api/v1")
@SpringBootApplication
public class IndexController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/{ident}")	
	public String getById(@PathVariable Long ident){
		return "{\"message\": \"Select By ID Called!\"}";
	}
	
	@PostMapping()
	public String post(Object obj){
		return "{\"message\": \"Create Called!\"}";
	}
	
	@PutMapping("/{ident}")
	public String put(@PathVariable Long ident){
		return "{\"message\": \"Update Called!\", \"id\":"+ ident +"";
	}
	
	@DeleteMapping("/{ident}")
	public String delete(@PathVariable Long ident){
		return "{\"message\": \"Delete Called!\", \"id\":"+ ident +"}";
	}
}
