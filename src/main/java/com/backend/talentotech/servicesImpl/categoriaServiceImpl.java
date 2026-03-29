package com.backend.talentotech.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.talentotech.entities.categoria;
import com.backend.talentotech.repositories.IcategoriaRepository;
import com.backend.talentotech.servicesInterfaces.IcategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class categoriaServiceImpl implements IcategoriaService {
	
	private final IcategoriaRepository categoriaRepository;
	
	public categoria crearCategoria(String nombre) {
		
		categoria c = new categoria();
		c.setNombre(nombre);
		return categoriaRepository.save(c);
	}
	
	public categoria editarCategoria(int id, String nombre) {
		categoria existente = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
		existente.setNombre(nombre);
		return categoriaRepository.save(existente);
	}
	
	public void eliminarCategoria(int id) {
		categoriaRepository.deleteById(id);
	}
	
	public List<categoria> listarCategorias(){
		return categoriaRepository.findAll();
	}

}
