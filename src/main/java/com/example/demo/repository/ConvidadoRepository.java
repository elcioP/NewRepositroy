package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Convidado;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{
	
	Convidado findByNome(String nome);
	
	List<Convidado> findByEventoId(Long id);
	List<Convidado> findByEventoIdAndPromotorId(Long idEvento, Long idPromotor);
}
