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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.carrinho.Carrinho;
import com.example.demo.carrinho.CarrinhoService;
import com.example.demo.model.Produto;


@RestController 
@RequestMapping("/carrinho")
@SpringBootApplication
@Api(value = "Recurso para controle do carrinho de compras", description = "Endpoints para gerenciamento do carrinho de compras")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService service;
	
	@GetMapping()
	@ApiOperation(value = "Retorna a lista de todos os Carrinhos de compra", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso. Seu carrinho de compras foi buscado e retornado na sua response"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Nenhum carrinho de compras foi usado ainda"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})
	public ResponseEntity<?> get() {
		try {
			List<Carrinho> entity = service.getAll();
			if (entity.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Busca o Carrinho de compras pelo ID que foi passado", response = Carrinho.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso. Seu carrinho de compras será retornado"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Não foi encontrado nenhum carrinho com esse ID"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})

	public ResponseEntity<?> get(
			@ApiParam(value = "ID do Produto desejado para busca no banco de dados", required = true) @PathVariable(value = "id") Long id) {
		try {
			Carrinho entity = service.getById(id);

			if (entity == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PostMapping()
	@ApiOperation(value = "Cria um Carrinho de compras")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Criado. Seu carrinho de compras foi criado e retornado em sua requisição"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Não foi possuí criar seu carrinho de compras, verifique os  atributos do json"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})
	public ResponseEntity<?> post(
			@ApiParam(value = "Atributos do Carrinho que será criado ", required = true) @Valid @RequestBody Carrinho entity) throws Exception {

			entity = service.post(entity);
			if (entity == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}
	
	
	@PutMapping()
	@ApiOperation(value = "Atualiza um carrinho de compras ")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Atualizado. seu Json foi recebido e salvo no banco de dados"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Não foi possuí buscar seu carrinho de compras, verifique os  atributos do json"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 406, message = "Não aceito. Não encontra nenhum conteúdo seguindo os critérios fornecidos"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})

	public ResponseEntity<?> put(@ApiParam(value = "Corpo do carrinho que será editado", required = true) @Valid @RequestBody Carrinho entidade) {

		Carrinho entity = service.post(entidade);
		if (entity == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(entity);
	}
	
	@DeleteMapping
	@ApiOperation(value = "Exclui um carrinho de compras pelo corpo passado como parâmetro")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Atualizado. seu Json foi recebido e salvo no banco de dados"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Seu carronho foi deletado"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 406, message = "Não aceito. Não encontra nenhum conteúdo seguindo os critérios fornecidos"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})
	public ResponseEntity<?> delete(@ApiParam(value = "Objeto já com os valores alterados", required = true) @Valid @RequestBody Carrinho entity) {

		if (service.delete(entity) != true) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um carrinho de compras  pelo ID fornecido")
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Atualizado. seu Json foi recebido e salvo no banco de dados"),
			@ApiResponse(code = 204, message = "Não possui conteúdo. Seu carrinho foi deletado"),
			@ApiResponse(code = 401, message = "Não Autorizado. Favor fazer sua autenticação para obter a resposta desejada"), 
			@ApiResponse(code = 403, message = "Acesso Restrito. Você não possuí permissão para fazer essa interação"),
			@ApiResponse(code = 406, message = "Não aceito. Não encontramos nenhum conteúdo seguindo os critérios fornecidos"),
			@ApiResponse(code = 404, message = "Não Encontrado. Não conseguimos achar o serviço que você precisa"),
			@ApiResponse(code = 500, message = "Erro Interno. Desculpe, não conseguimos fazer o que você precisa, verifique suas configurações ou tente novamente mais tarde"),})
	public ResponseEntity<?> delete(@ApiParam(value = "ID do carrinho que será removido do banco de dados", required = true) @PathVariable(value = "id") Long id) {
		if (service.delete(id) != true) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	

}
