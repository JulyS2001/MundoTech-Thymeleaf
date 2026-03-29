package com.backend.talentotech.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.entities.pedido;
import com.backend.talentotech.entities.producto;
import com.backend.talentotech.repositories.IproductoRepository;
import com.backend.talentotech.servicesImpl.carritoServiceImpl;
import com.backend.talentotech.servicesImpl.pedidoServiceImpl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class carritoController {

	private final IproductoRepository productoRepository;

	private final carritoServiceImpl carritoService;
	
	private final pedidoServiceImpl pedidoService;

	@GetMapping("/verCarrito")
	public ModelAndView verCarrito(@RequestParam(required = false) String mensaje) {
		ModelAndView mav = new ModelAndView("carrito/verCarrito");
		List<carritoItem> items = carritoService.getItems();
		mav.addObject("items", items);
		mav.addObject("total", carritoService.getTotal());
		mav.addObject("error", mensaje);
		return mav;
	}

	// Agregar producto al carrito
	@PostMapping("/carritoAgregar")
	public ModelAndView agregarProducto(@RequestParam int idProducto, @RequestParam(defaultValue = "1") int cantidad) {
		Optional<producto> productoOpt = productoRepository.findById(idProducto);
		ModelAndView mav = new ModelAndView("redirect:/verCarrito");

		if (productoOpt.isPresent()) {
			carritoService.agregarProducto(productoOpt.get(), cantidad);
		} else {
			mav.addObject("mensaje", "Producto no encontrado");
		}

		return mav;
	}

	// Eliminar un producto del carrito
	@GetMapping("/carritoEliminar")
	public ModelAndView eliminarProducto(@RequestParam int idProducto) {
		carritoService.eliminarProducto(idProducto);
		return new ModelAndView("redirect:/verCarrito");
	}

	// Vaciar el carrito
	@GetMapping("/carritoVaciar")
	public ModelAndView vaciarCarrito() {
		carritoService.vaciarCarrito();
		return new ModelAndView("redirect:/carrito");
	}

	// Confirmar pedido (crear pedido en BD)
	@PostMapping("/carritoConfirmar")
	public ModelAndView confirmarPedido(@RequestParam int idUsuario) {
		ModelAndView mav = new ModelAndView("carrito/verCarrito");

		try {
			pedidoService.generarPedidoDesdeCarrito(idUsuario, carritoService.getItems());
			carritoService.vaciarCarrito();
			List<pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(idUsuario);
			mav.addObject("pedidos",pedidos);
			mav.setViewName("pedidos/misCompras");
		} catch (Exception e) {
			mav.addObject("mensaje", "Error al generar el pedido");
		}

		return mav;
	}

}
