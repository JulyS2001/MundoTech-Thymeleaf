package com.backend.talentotech.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.backend.talentotech.entities.producto;
import com.backend.talentotech.servicesImpl.productoServiceImpl;
import com.backend.talentotech.servicesInterfaces.IproductoService;

import lombok.RequiredArgsConstructor;

	@Controller
	@RequiredArgsConstructor
	public class homeController {
		
		private final productoServiceImpl productoService;

		@GetMapping("/")
		public ModelAndView inicio() {
		    ModelAndView mav = new ModelAndView("home/index");
		    mav.addObject("productos", productoService.listarProductos());
		    return mav;
		}
	}
