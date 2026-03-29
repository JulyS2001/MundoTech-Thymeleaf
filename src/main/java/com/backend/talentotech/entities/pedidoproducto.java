package com.backend.talentotech.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class pedidoproducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_pedido_producto;

	@ManyToOne
	@JoinColumn(name = "pedido_id_pedido")
	private pedido pedido;

	@ManyToOne
	@JoinColumn(name = "producto_id_producto")
	private producto producto;

	private int cantidad;

}
