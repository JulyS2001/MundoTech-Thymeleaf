package com.backend.talentotech.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.talentotech.entities.usuario;

public interface IusuarioRepository extends JpaRepository<usuario, Integer> {
	
	Optional<usuario> findByEmail(String email);
	Optional<usuario> findById(int id);

}
