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

import com.example.demo.repository.Produto;
import com.example.demo.repository.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // Garante que a API permaneça sem estado
@RequestMapping("/produto")
@SpringBootApplication
@Api(value = "Sistema de Controle de Produto", description = "Operações para controle dos Produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	@ApiOperation(value = "Retorna a lista de todos os Produtos", response = Produto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 204, message = "Não possui conteúdo"),
			@ApiResponse(code = 401, message = "Não Autorizado"), @ApiResponse(code = 403, message = "Acesso Restrito"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<?> get() {
		try {
			List<Produto> entity = produtoService.findAll();

			if (entity.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna o Produto com o ID passado", response = Produto.class)
	public ResponseEntity<?> get(
			@ApiParam(value = "ID do Produto que será exibido", required = true) @PathVariable(value = "id") Long id) {
		try {
			Produto entity = produtoService.findById(id);

			if (entity == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			return ResponseEntity.status(HttpStatus.OK).body(entity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@PostMapping()
	@ApiOperation(value = "Cria um Produto")
	public ResponseEntity<?> save(
			@ApiParam(value = "Produto que sera inserido na base de dados", required = true) @Valid @RequestBody Produto entity) throws Exception {

			entity = produtoService.save(entity);
			if (entity == null) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(entity);
	}

	@PutMapping()
	@ApiOperation(value = "Edita um produto")
	public ResponseEntity<?> put(@ApiParam(value = "Edita um produto", required = true) @Valid @RequestBody Produto param) {

		Produto entity = produtoService.save(param);
		if (entity == null) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(entity);
	}

	@DeleteMapping
	@ApiOperation(value = "Exclui um Produto pelo corpo")
	public ResponseEntity<?> delete(@ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Produto param) {

		if (produtoService.delete(param) != true) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um produto pelo ID")
	public ResponseEntity<?> delete(@ApiParam(value = "ID do Produto que será removido da base de dados", required = true) @PathVariable(value = "id") Long id) {
		if (produtoService.delete(id) != true) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	

}