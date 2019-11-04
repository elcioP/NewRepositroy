package com.example.demo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	Evento findByDataEvento(Date data);

}
