package com.backend.talentotech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.talentotech.entities.producto;

public interface IproductoRepository extends JpaRepository<producto, Integer> {
	
	List<producto> findByCategoriaIdCategoria(int idCategoria);
	
	List<producto> findByNombreContainingIgnoreCase(String nombre);
	
	@Query("SELECT p FROM producto p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<producto> buscarSimilares(@Param("nombre") String nombre);

}
