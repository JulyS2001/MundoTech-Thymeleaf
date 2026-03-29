package com.backend.talentotech.servicesImpl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.talentotech.entities.usuario;
import com.backend.talentotech.repositories.IusuarioRepository;
import com.backend.talentotech.servicesInterfaces.IusuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class usuarioServiceImpl implements IusuarioService {
	
	private final IusuarioRepository usuarioRepository; 
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public usuario crearUsuario(String nombre, String email, String contrasenia) {
		usuario u = new usuario();
		u.setNombre(nombre);
		u.setEmail(email);
		u.setContrasenia(passwordEncoder.encode(contrasenia));
		u.setRol("ROLE_CLIENTE");
		return usuarioRepository.save(u);
	}
	
	public List<usuario> traerListaUsuarios(){
		return usuarioRepository.findAll();
	}

}
