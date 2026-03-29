package com.backend.talentotech.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.backend.talentotech.dto.carritoItem;
import com.backend.talentotech.entities.producto;
import com.backend.talentotech.servicesInterfaces.IcarritoService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@SessionScope
@RequiredArgsConstructor
public class carritoServiceImpl implements IcarritoService {
	
	private final List<carritoItem> items = new ArrayList<>();
	
	@Override
	public void agregarProducto(producto producto, int cantidad) {
		for(carritoItem item : items) {
			if(item.getProducto().getIdProducto() == producto.getIdProducto()) {
				item.setCantidad(item.getCantidad() + cantidad);
				return;
			}
		}
		items.add(new carritoItem(producto, cantidad));
	}

	
	@Override
    public void eliminarProducto(int idProducto) {
        items.removeIf(item -> item.getProducto().getIdProducto() == idProducto);
    }

    @Override
    public void vaciarCarrito() {
        items.clear();
    }

    @Override
    public List<carritoItem> getItems() {
        return items;
    }

    @Override
    public float getTotal() {
        float total = 0;
        for (carritoItem item : items) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }
}
