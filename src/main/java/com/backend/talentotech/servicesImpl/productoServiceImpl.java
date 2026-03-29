package com.backend.talentotech.servicesImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.talentotech.entities.categoria;
import com.backend.talentotech.entities.producto;
import com.backend.talentotech.repositories.IcategoriaRepository;
import com.backend.talentotech.repositories.IproductoRepository;
import com.backend.talentotech.servicesInterfaces.IproductoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class productoServiceImpl implements IproductoService{
	
	private final IproductoRepository productoRepository; 
	private final IcategoriaRepository categoriaRepository;
	
	
	
	public producto crearProducto(String nombre, String descripcion, float precio, String imagen, int stock,
			int idCategoria) {
		categoria categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

		producto producto = new producto();
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecio(precio);
		producto.setImagen(imagen);
		producto.setStock(stock);
		producto.setCategoria(categoria);

		return productoRepository.save(producto);
	}

	@Override
	public producto actualizarProducto(int id, String nombre, String descripcion, float precio, String imagen,
			int stock, int idCategoria) {
		producto existente = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		categoria categoria = categoriaRepository.findById(idCategoria)
				.orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

		existente.setNombre(nombre);
		existente.setDescripcion(descripcion);
		existente.setPrecio(precio);
		existente.setImagen(imagen);
		existente.setStock(stock);
		existente.setCategoria(categoria);

		return productoRepository.save(existente);
	}

	@Override
	public void eliminarProducto(int id) {
		productoRepository.deleteById(id);
	}

	@Override
	public List<producto> listarProductos() {
		return productoRepository.findAll();
	}
	
	public List<producto> listarProductosPorCategoria(int idCategoria) {
	    return productoRepository.findByCategoriaIdCategoria(idCategoria);
	}
	
	@Override
	public List<producto> buscarPorNombre(String nombre) {
	    return productoRepository.findByNombreContainingIgnoreCase(nombre);
	}
	
	@Override
    public List<producto> buscarSimilares(String nombre) {
        return productoRepository.buscarSimilares(nombre);
    }
    
    public List<producto> obtenerProductosAleatorios(int cantidad) {
        List<producto> todos = productoRepository.findAll();
        Collections.shuffle(todos);
        return todos.stream().limit(cantidad).toList();
    }
	
	@Override
	public producto obtenerProductoPorId(int id) {
		return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	}
}
