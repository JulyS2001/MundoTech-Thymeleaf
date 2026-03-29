package com.backend.talentotech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.backend.talentotech.servicesInterfaces.IusuarioService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class usuarioController {
	
	private final IusuarioService usuarioService; 
	
	@GetMapping("/registro")
    public ModelAndView vistaRegistro(@RequestParam(required = false) String mensaje) {
        ModelAndView mav = new ModelAndView("registro/registroUsuario");
        mav.addObject("error", mensaje);
        return mav;
    }
	
	@PostMapping("/registro")
    public ModelAndView registrarUsuario(@RequestParam String nombre,
                                         @RequestParam String email,
                                         @RequestParam String contrasenia) {

        usuarioService.crearUsuario(nombre, email, contrasenia);

        return new ModelAndView("redirect:/login");
    }
	
	@GetMapping("/login")
	public ModelAndView mostrarLogin(@RequestParam(required = false) String error,
			@RequestParam(required = false) String logout) {
		ModelAndView mav = new ModelAndView("registro/login");

		if (error != null) {
			mav.addObject("mensaje", "Credenciales incorrectas");
		}
		if (logout != null) {
			mav.addObject("mensaje", "Sesión cerrada con éxito");
		}

		return mav;
	}
}
