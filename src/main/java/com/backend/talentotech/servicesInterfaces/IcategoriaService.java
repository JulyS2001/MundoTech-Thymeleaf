package com.backend.talentotech.servicesInterfaces;

import java.util.List;

import com.backend.talentotech.entities.categoria;

public interface IcategoriaService {
	
	categoria crearCategoria(String nombre);
	categoria editarCategoria(int id, String nombre);
	void eliminarCategoria(int id);
	List<categoria> listarCategorias();

}
