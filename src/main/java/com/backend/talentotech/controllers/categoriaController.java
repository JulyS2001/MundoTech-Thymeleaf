package com.backend.talentotech.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.talentotech.entities.categoria;
import com.backend.talentotech.repositories.IcategoriaRepository;
import com.backend.talentotech.servicesImpl.categoriaServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class categoriaController {
	
	private final categoriaServiceImpl categoriaService;
	private final IcategoriaRepository categoriaRepository;
	
	@GetMapping("/listaCategorias")
	public ModelAndView listarCategorias(@RequestParam(required = false) String mensaje) {
        ModelAndView mav = new ModelAndView("categoria/listaCategorias");
        mav.addObject("categorias", categoriaService.listarCategorias());
        mav.addObject("error", mensaje);
        return mav;
    }
	
	 @GetMapping("/crearCategoria")
	    public ModelAndView vistaCrearCategoria(@RequestParam(required = false) String mensaje) {
		   ModelAndView mav = new ModelAndView("categoria/crearCategoria");
		    mav.addObject("error", mensaje);
	        return mav;
	    }
	 
	 @PostMapping("/crearCategoria")
	    public ModelAndView crearCategoria(@RequestParam(required = false) String mensaje,@RequestParam String nombre) {
		    ModelAndView mav = new ModelAndView("categoria/listaCategorias");
	        categoriaService.crearCategoria(nombre);
	        mav.addObject("error", mensaje);
	        mav.addObject("categorias", categoriaService.listarCategorias());
	        return mav;
	    }
	 
	   @GetMapping("/editarCategoria")
	    public ModelAndView vistaEditarCategoria(@RequestParam int id) {
	        ModelAndView mav = new ModelAndView("categoria/editarCategoria");
	        Optional<categoria> categoria = categoriaRepository.findById(id);
	        mav.addObject("categoria", categoria);
	        return mav;
	    }

	    // Proceso de edición
	    @PostMapping("/editarCategoria")
	    public ModelAndView editarCategoria(@RequestParam int id, @RequestParam String nombre) {
	    	ModelAndView mav = new ModelAndView("categoria/listaCategorias");
	        categoriaService.editarCategoria(id, nombre);
	        mav.addObject("categorias", categoriaService.listarCategorias());
	        return mav;
	    }

	    // Proceso de eliminación
	    @GetMapping("/eliminarCategoria")
	    public ModelAndView eliminarCategoria(@RequestParam int id) {
	    	ModelAndView mav = new ModelAndView("categoria/listaCategorias");
	        categoriaService.eliminarCategoria(id);
	        mav.addObject("categorias", categoriaService.listarCategorias());
	        return mav;
	    }

}
