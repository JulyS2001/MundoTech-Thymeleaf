package com.backend.talentotech.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.backend.talentotech.entities.producto;
import com.backend.talentotech.repositories.IcategoriaRepository;
import com.backend.talentotech.repositories.IproductoRepository;
import com.backend.talentotech.servicesImpl.categoriaServiceImpl;
import com.backend.talentotech.servicesImpl.productoServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class productoController {

	private final productoServiceImpl productoService;
	private final categoriaServiceImpl categoriaService;
	private final IproductoRepository productoRepository;
	private final IcategoriaRepository categoriaRepository;

	@GetMapping("/listaProductos")
	public ModelAndView listarProductos() {
		ModelAndView mav = new ModelAndView("producto/listaProductos");
		mav.addObject("productos", productoService.listarProductos());
		return mav;
	}
	
	@GetMapping("/verProductos")
	public ModelAndView verProductos() {
		ModelAndView mav = new ModelAndView("producto/verProductos2");

	    var categorias = categoriaService.listarCategorias();
	    var productosAleatorios = productoService.obtenerProductosAleatorios(8);
	    mav.addObject("categorias", categorias);
	    mav.addObject("productos", productosAleatorios);
	    mav.addObject("categoriaSeleccionada", null); // sin categoría activa
	    return mav;
	}

	@GetMapping("/crearProducto")
	public ModelAndView vistaCrearProducto() {
		ModelAndView mav = new ModelAndView("producto/crearProducto");
		mav.addObject("categorias", categoriaService.listarCategorias());
		return mav;
	}

	@PostMapping("/crearProducto")
	public ModelAndView crearProducto(@RequestParam String nombre, @RequestParam String descripcion,
			@RequestParam float precio, @RequestParam MultipartFile imagen, @RequestParam int stock,
			@RequestParam int idCategoria) {

		ModelAndView mav = new ModelAndView("producto/listaProductos");
		
		String nombreArchivo = guardarImagen(imagen);

		productoService.crearProducto(nombre, descripcion, precio, nombreArchivo, stock, idCategoria);

		mav.addObject("productos", productoService.listarProductos());
		
		return mav;
	}
	
	   @GetMapping("/editarProducto")
	    public ModelAndView vistaEditarProducto(@RequestParam int id) {
	        ModelAndView mav = new ModelAndView("producto/editarProducto");
	        producto producto = productoService.obtenerProductoPorId(id);
	        mav.addObject("producto", producto);
	        mav.addObject("categorias", categoriaService.listarCategorias());
	        return mav;
	    }
	   
	   @PostMapping("/editarProducto")
	    public ModelAndView editarProducto(@RequestParam int id,
	                                       @RequestParam String nombre,
	                                       @RequestParam String descripcion,
	                                       @RequestParam float precio,
	                                       @RequestParam MultipartFile imagen,
	                                       @RequestParam int stock,
	                                       @RequestParam int idCategoria) {
		   
		   String nombreArchivo = guardarImagen(imagen);

	        productoService.actualizarProducto(id, nombre, descripcion, precio, nombreArchivo, stock, idCategoria);
	        
	        ModelAndView mav = new ModelAndView("producto/listaProductos");
	        mav.addObject("productos", productoService.listarProductos());
	        
	        return mav;
	    }
	
	 @GetMapping("/eliminarProducto")
	    public ModelAndView eliminarProducto(@RequestParam int id) {
	        productoService.eliminarProducto(id);
	        ModelAndView mav = new ModelAndView("producto/listaProductos");
	        mav.addObject("productos", productoService.listarProductos());
	        return mav; 
	    }
	 
	 private String guardarImagen(MultipartFile archivo) {
	        if (archivo.isEmpty()) return "default.jpg"; // Imagen por defecto

	        try {
	            String nombre = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
	            Path ruta = Paths.get("src/main/resources/static/images/" + nombre);
	            Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
	            return nombre;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "default.jpg";
	        }
	    }
	 
	 @GetMapping("/verDetalleProducto/{id}")
	 public ModelAndView verDetalleProducto(@PathVariable("id") int id) {
		 ModelAndView mav = new ModelAndView("producto/verDetalleProducto");
		 producto producto = productoService.obtenerProductoPorId(id);
		 mav.addObject("producto",producto);
		 return mav;
	 }
	 
	 @GetMapping("/buscar")
	 public ModelAndView buscarProductos(@RequestParam("nombre") String nombre) {
		 ModelAndView mav = new ModelAndView("producto/busquedaProducto");
		 List<producto> productos = productoService.buscarPorNombre(nombre);
		 mav.addObject("productos", productos);
		 
		 List<producto> similares = new ArrayList<>();
		    if (productos.isEmpty()) {
		        similares = productoService.buscarSimilares(nombre); // Por ejemplo, por categoría o descripción
		        mav.addObject("similares", similares);
		    }
		 
		 return mav; 
		 
	 }
	 
	 @GetMapping("/productos/categoria")
	 public ModelAndView productosPorCategoria(@RequestParam int idCategoria) {
	     ModelAndView mav = new ModelAndView("producto/verProductos2");

	     var categorias = categoriaService.listarCategorias(); 
	     var categoria = categoriaRepository.findById(idCategoria); 
	     var productos = productoService.listarProductosPorCategoria(idCategoria); 

	     mav.addObject("categorias", categorias);
	     mav.addObject("productos", productos);
	     if (categoria.isPresent()) {
	         mav.addObject("categoriaSeleccionada", categoria.get().getNombre());
	     } else {
	         mav.addObject("categoriaSeleccionada", "Categoría no encontrada");
	     }
	     return mav;
	 }
	 
	}

