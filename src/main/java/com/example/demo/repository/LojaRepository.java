package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>{

}
