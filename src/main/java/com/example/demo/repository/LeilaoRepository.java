package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Leilao;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long>{

}
