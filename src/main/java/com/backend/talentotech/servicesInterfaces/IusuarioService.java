package com.backend.talentotech.servicesInterfaces;

import java.util.List;
import java.util.Optional;

import com.backend.talentotech.entities.usuario;

public interface IusuarioService {
	
	usuario crearUsuario(String nombre, String email, String contrasenia);
	
	List<usuario> traerListaUsuarios();

}
