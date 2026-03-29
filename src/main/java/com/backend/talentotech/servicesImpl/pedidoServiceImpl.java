package com.backend.talentotech.servicesImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.entities.pedido;
import com.backend.talentotech.entities.pedidoproducto;
import com.backend.talentotech.entities.producto;
import com.backend.talentotech.entities.usuario;
import com.backend.talentotech.repositories.IpedidoRepository;
import com.backend.talentotech.repositories.IpedidoproductoRepository;
import com.backend.talentotech.repositories.IproductoRepository;
import com.backend.talentotech.repositories.IusuarioRepository;
import com.backend.talentotech.servicesInterfaces.IpedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class pedidoServiceImpl implements IpedidoService {
	
	private final IpedidoRepository pedidoRepository;
    private final IpedidoproductoRepository pedidoProductoRepository;
    private final IusuarioRepository usuarioRepository;
    private final IproductoRepository productoRepository;

    @Override
    public void generarPedidoDesdeCarrito(int idUsuario, List<carritoItem> items) {
        usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        pedido pedido = new pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(calcularTotal(items));
        pedido.setDetalle("Pedido generado desde carrito");

        pedidoRepository.save(pedido);

        for (carritoItem item : items) {
        	
        	producto p = item.getProducto();
        	
        	if(p.getStock() < item.getCantidad()) {
        		throw new RuntimeException("Stock insuficiente");
        	}
        	
        	p.setStock(p.getStock() - item.getCantidad());
        	productoRepository.save(p);
        	
            pedidoproducto pp = new pedidoproducto();
            pp.setPedido(pedido);
            pp.setProducto(item.getProducto());
            pp.setCantidad(item.getCantidad());

            pedidoProductoRepository.save(pp);
        }
    }

    @Override
    public List<pedido> obtenerPedidosPorUsuario(int idUsuario) {
        return pedidoRepository.findByUsuarioIdUsuario(idUsuario);
    }

    private float calcularTotal(List<carritoItem> items) {
        float total = 0;
        for (carritoItem item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

}
