package com.backend.talentotech.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.backend.talentotech.entities.pedido;
import com.backend.talentotech.servicesImpl.pedidoServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class pedidoController {
	
	private final pedidoServiceImpl pedidoService;

    @GetMapping("/misCompras")
    public ModelAndView verPedidos(@RequestParam int idUsuario) {
        List<pedido> pedidos = pedidoService.obtenerPedidosPorUsuario(idUsuario);
        ModelAndView mav = new ModelAndView("pedidos/misCompras");
        mav.addObject("pedidos", pedidos);
        return mav;
    }

}
