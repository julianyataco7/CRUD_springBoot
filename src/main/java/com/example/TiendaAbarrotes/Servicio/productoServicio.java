package com.example.TiendaAbarrotes.Servicio;
import java.util.List;

import com.example.TiendaAbarrotes.Modelo.productos;
import com.example.TiendaAbarrotes.Repositorio.productoRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class productoServicio {
	
	@Autowired
	private productoRepositorio repositorio;
	
	
	
	public void agregarProducto (String nombre,double precio,int stock) {
		productos producto = new productos();
		
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setStock(stock);
		
		repositorio.agregarProducto(producto);
	}
	
	public List<productos> listarProductos (){
		return repositorio.obtenerLista();
	}
	
	public void editarProducto (Long id,String nombre,double precio,int stock) {
		productos producto = new productos();
		
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setStock(stock);
		repositorio.editarProducto(id, producto);
	}
	
	public void eliminarProducto(Long id) {
		repositorio.eliminarProducto(id);
	}
	
	public Optional<productos> obtenerProductoId(Long id) {
		return repositorio.obtenerProductoId(id);
	}
	
}
