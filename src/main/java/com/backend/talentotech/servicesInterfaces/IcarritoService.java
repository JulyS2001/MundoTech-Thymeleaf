package com.backend.talentotech.servicesInterfaces;

import java.util.List;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.entities.producto;

public interface IcarritoService {
	
	void agregarProducto(producto producto, int cantidad);
    void eliminarProducto(int idProducto);
    void vaciarCarrito();
    List<carritoItem> getItems();
    float getTotal();

}
