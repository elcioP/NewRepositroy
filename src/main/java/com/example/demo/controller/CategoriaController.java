package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;

@RestController 
@RequestMapping("/categoria")
@SpringBootApplication
@Api(value = "Gerencia categorias", description = "Gerencia categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@PostMapping
	@ApiOperation(value = "cria uma nova categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Sucesso. Categoria criada"),
			@ApiResponse(code = 500, message = "erro interno")})
	public ResponseEntity<?> post( @ApiParam( value = "corpo da categoria que será criada" , required = true) @Valid @RequestBody Categoria entity){
		if(entity != null){
			return	ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping
	@ApiOperation(value = "atualiza uma categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK. Categoria editada"),
							@ApiResponse(code = 500, message = "erro interno")})
	public ResponseEntity<?> put(@ApiParam(value = "corpo da categoria que será editada", required = true)  @Valid @RequestBody Categoria entity){
		if(entity != null){
			ResponseEntity.status(HttpStatus.OK).body(service.save(entity));
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	@ApiResponses(value = { @ApiResponse(code = 204, message = "OK. Categoria excluica"),
							@ApiResponse(code = 500, message = "erro interno")})
	public ResponseEntity<?> delete( @ApiParam(value = "corpo da categoria que será deletada", required = true) @Valid @RequestBody Categoria entity){
	
		if(service.delet(entity) == true){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@DeleteMapping("/{id}")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "OK. Categoria excluica"),
							@ApiResponse(code = 500, message = "erro interno")})
	public ResponseEntity<?> delete( @ApiParam(value = "id da categoria deletada", required = true) Long id){
	
		if(service.delet(id) == true){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@GetMapping
	public ResponseEntity<?> get(Categoria categoria){
		List<Categoria> lista = service.findAll();
		if(lista != null){
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> get(Long id){
		Categoria categoria = service.findById(id);
		if(categoria != null){
			return ResponseEntity.status(HttpStatus.OK).body(categoria);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
