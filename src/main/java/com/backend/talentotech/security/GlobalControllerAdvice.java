package com.backend.talentotech.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.servicesImpl.carritoServiceImpl;

import lombok.RequiredArgsConstructor;


@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
	
	private final carritoServiceImpl carritoService;

	 @ModelAttribute("usuario")
	    public String usuario(@AuthenticationPrincipal UserDetailsImpl userDetails) {
	        if (userDetails != null) {
	            return userDetails.getUsuario().getNombre();
	        }
	        return null;
	    }

	    @ModelAttribute("rol")
	    public String rol(@AuthenticationPrincipal UserDetailsImpl userDetails) {
	        if (userDetails != null) {
	            return userDetails.getAuthorities().stream()
	                    .findFirst()
	                    .map(GrantedAuthority::getAuthority)
	                    .orElse("ROLE_CLIENTE");
	        }
	        return null;
	    }
	    
	    @ModelAttribute("idUsuario")
	    public Integer idUsuario(@AuthenticationPrincipal UserDetailsImpl userDetails) {
	        if (userDetails != null) {
	            return userDetails.getUsuario().getIdUsuario();
	        }
	        return null;
	    }
	    
	    @ModelAttribute("carritoCantidad")
	    public int carritoCantidad() {
	        return carritoService.getItems().stream().mapToInt(carritoItem::getCantidad).sum();
	    }
	}