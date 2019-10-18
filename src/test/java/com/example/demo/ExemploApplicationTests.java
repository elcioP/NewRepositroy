package com.example.demo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
public abstract class ExemploApplicationTests {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExemploApplicationTests.class);
	
	@Autowired
	protected WebApplicationContext wac;
	
	@Autowired
	protected MockMvc mock;
	
	@Autowired
	protected ObjectMapper mapper;
	
	@Before
	public void configureTest() {	
		LOG.info("-----------INICIANDO TESTES GLOBAIS-----------");
		this.mock = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
}
