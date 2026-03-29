package com.backend.talentotech.dto;

import com.backend.talentotech.entities.producto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class carritoItem {
	
	private producto producto; 
	private int cantidad;

}
