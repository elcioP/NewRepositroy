package com.example.demo.core;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@ControllerAdvice
public class ResponseErrorHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(ResponseErrorHandler.class);
	
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public ResponseEntity<Object> handleDataAccessException(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	@ExceptionHandler({Exception.class, NullPointerException.class})
	public ResponseEntity<Erro> handle(Exception ex,
			HttpServletRequest request, HttpServletResponse response) {

		String title = ex.getMessage();
		String type = null;
		String instance = request.getRequestURL().toString();
		String detail = ex.getStackTrace()[0].toString();
		Integer status = 500;

		LOG.error(type);

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new Erro(type, title, detail, status, instance));

	}

	@JsonInclude(Include.NON_NULL)
	public static class Erro {

		private String type;
		private String title;
		private String detail;
		private Integer status;
		private String instance;

		public Erro(String type, String title, String detail, Integer status,
				String instance) {

			this.type = type;
			this.title = title;
			this.detail = detail;
			this.status = status;
			this.instance = instance;

		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getInstance() {
			return instance;
		}

		public void setInstance(String instance) {
			this.instance = instance;
		}

	}

}

/**
 * 
 * 
 * RFC 7807
 * 
 * References
 * https://tools.ietf.org/html/rfc7807
 * https://blog.restcase.com/rest-api-error-handling-problem-details-response/
 * https://medium.com/tableless/padronização-de-respostas-de-erro-em-apis-com-problem-details-23e58edf25ab
 * 
 * 
 * Title: um breve resumo do tipo de problema. Não deve mudar para ocorrências
 * do mesmo tipo, exceto para fins de localização;
 *
 * Detail: descrição detalhada do problema;
 *
 * Type: uma URL para um documento que descreva o tipo do problema;
 *
 * Status: o status HTTP gerado pelo servidor de origem. Normalmente deve ser o
 * mesmo status HTTP da resposta, e pode servir de referência para casos onde um
 * servidor proxy altera o status da resposta;
 *
 * Instance: propriedade opcional, com um URI exclusivo para o erro específico,
 * que geralmente aponta para um log de erros para essa resposta.
 * 
 * 
 *    
 *    
   {
   "type": "https://example.net/validation-error",
   "title": "Your request parameters didn't validate.",
   "invalid-params": [ {
                         "name": "age",
                         "reason": "must be a positive integer"
                       },
                       {
                         "name": "color",
                         "reason": "must be 'green', 'red' or 'blue'"}
                     ]
   }
 * 
 * 
 */
