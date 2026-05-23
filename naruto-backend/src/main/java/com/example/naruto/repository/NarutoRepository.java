package com.example.naruto.repository;

import com.example.naruto.entity.Naruto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NarutoRepository
extends JpaRepository<Naruto, Long> {

    Optional<Naruto> findByNombre(String nombre);

}