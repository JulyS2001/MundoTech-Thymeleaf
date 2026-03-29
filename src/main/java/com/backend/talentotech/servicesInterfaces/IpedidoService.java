package com.backend.talentotech.servicesInterfaces;

import java.util.List;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.entities.pedido;
import com.backend.talentotech.entities.pedidoproducto;
import com.backend.talentotech.entities.usuario;

public interface IpedidoService {
	
	void generarPedidoDesdeCarrito(int idUsuario, List<carritoItem> items);

    List<pedido> obtenerPedidosPorUsuario(int idUsuario);

}
