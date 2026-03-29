package com.backend.talentotech.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.talentotech.entities.pedido;

public interface IpedidoRepository extends JpaRepository<pedido, Integer> {
	
	List<pedido> findByUsuarioIdUsuario(int idUsuario);
}
