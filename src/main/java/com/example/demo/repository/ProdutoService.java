package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Produto;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repo;

	public List<Produto> findAll() {
		return repo.findAll();
	}

	public Produto findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public Produto save(Produto entity) {
		return repo.save(entity);
	}

	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean delete(Produto param) {
		try {
			repo.delete(param);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Produto saveAndFlush(Produto param) {
		return repo.saveAndFlush(param);
	}

}
