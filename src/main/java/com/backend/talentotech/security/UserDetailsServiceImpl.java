package com.backend.talentotech.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.talentotech.entities.usuario;
import com.backend.talentotech.repositories.IusuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IusuarioRepository iusuarioRepository;

	    public UserDetailsServiceImpl(IusuarioRepository usuarioRepository, IusuarioRepository iusuarioRepository) {
	        this.iusuarioRepository = usuarioRepository;
	
	    }

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Optional<usuario> usuarioOpt = iusuarioRepository.findByEmail(email);
	        if (usuarioOpt.isEmpty()) {
	            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
	        }
	        return new UserDetailsImpl(usuarioOpt.get());
	    }
	}