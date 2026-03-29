package com.backend.talentotech.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategoria; 
	private String nombre; 
	
	@OneToMany(mappedBy = "categoria")
	private List<producto> productos; 
	

}
