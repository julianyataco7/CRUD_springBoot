package com.example.TiendaAbarrotes.Repositorio;
import com.example.TiendaAbarrotes.Modelo.productos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class productoRepositorio {

	private List<productos> listaProductos = new ArrayList<>();
	
	private Long nextId =1L;
	
	public void agregarProducto (productos producto) {
		producto.setIdProducto(nextId++);
		listaProductos.add(producto);
	}
	
	public List<productos> obtenerLista (){
		
		return listaProductos;
	}
	
	public String editarProducto (Long id,productos producto) {
		
		Optional<productos> productoEditar = obtenerProductoId(id);
		 if (productoEditar.isPresent()) {
	            productos productoedit = productoEditar.get();
	            productoedit.setNombre(producto.getNombre());
	            productoedit.setPrecio(producto.getPrecio());
	            productoedit.setStock(producto.getStock());
	            return "edicion exitosa";
	        } else {
	            return "edicion falleda"; 
	        }
	}
	
	public void eliminarProducto (Long id) {
		Optional<productos> productoEliminar = obtenerProductoId(id);
		productos productoeliminar = productoEliminar.get();
		listaProductos.remove(productoeliminar);
		
	}
	
	public Optional<productos> obtenerProductoId(Long id){
		return listaProductos.stream().filter(products->products.getIdProducto().equals(id)).findFirst();
	}
}
