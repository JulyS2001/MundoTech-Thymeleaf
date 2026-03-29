package com.backend.talentotech.servicesInterfaces;

import java.util.List;

import com.backend.talentotech.entities.producto;

public interface IproductoService {

	producto crearProducto(String nombre, String descripcion, float precio, String imagen, int stock, int idCategoria);

	producto actualizarProducto(int id, String nombre, String descripcion, float precio, String imagen, int stock,
			int idCategoria);

	void eliminarProducto(int id);

	List<producto> listarProductos();

	producto obtenerProductoPorId(int id);
	
	List<producto> listarProductosPorCategoria(int id_categoria);
	
	List<producto> buscarPorNombre(String nombre);
	
	List<producto> buscarSimilares(String nombre);
	
	List<producto> obtenerProductosAleatorios(int cantidad);
	

}
