package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Evento;

@Repository
public interface FecharEventoRepository extends JpaRepository<Evento, Long> , JpaSpecificationExecutor<Evento>{

}
