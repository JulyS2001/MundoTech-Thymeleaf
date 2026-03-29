package com.backend.talentotech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.talentotech.entities.categoria;

public interface IcategoriaRepository extends JpaRepository<categoria, Integer> {
	
	Optional<categoria> findByNombre(String nombre);

}
