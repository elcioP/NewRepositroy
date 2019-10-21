package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Convidado;

@Repository
public interface ConvidadoRepository extends JpaRepository<Convidado, Long>{

}
