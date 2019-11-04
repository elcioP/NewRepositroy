package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Categoria;
import com.example.demo.model.Convidado;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	

}
