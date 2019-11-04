package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Convidado;
import com.example.demo.model.Promotor;

@Repository
public interface PromotorRepository extends JpaRepository<Promotor, Long>{
	
	List<Promotor> findByEventoId(Long id);
	

}
