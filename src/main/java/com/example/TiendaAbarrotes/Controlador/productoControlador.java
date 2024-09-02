package com.example.TiendaAbarrotes.Controlador;

import com.example.TiendaAbarrotes.Servicio.productoServicio;
import com.example.TiendaAbarrotes.Modelo.productos;

import org.springframework.ui.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/productos")
public class productoControlador {

	@Autowired
	private productoServicio servicio;
	
	@GetMapping("/listar") //// muestra la vista con una lista de los productos
	public String mostrarListaProductos(Model modelo) {
		modelo.addAttribute ("listaProductos",servicio.listarProductos());
		return "Lista-Productos";
	}
	
	@GetMapping("/agregarProducto")
	public String mostrarAgregarProducto(Model modelo) {
		
		modelo.addAttribute("producto",new productos());
		return "Agregar-Producto";
	}
	
	@PostMapping("/agregar")
	public String agregarProducto(@ModelAttribute productos producto) {
		
		servicio.agregarProducto(producto.getNombre(), producto.getPrecio(), producto.getStock());
		return "redirect:/productos/listar";
	}
	
	@PostMapping("/actualizar")
    public String actualizarProducto(
        @RequestParam("idProducto") Long idProducto,
        @RequestParam("nombre") String nombre,
        @RequestParam("precio") Double precio,
        @RequestParam("stock") Integer stock) {
        
        servicio.editarProducto(idProducto, nombre, precio, stock);

        return "redirect:/productos/listar";
    }
	
	@PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam("idProducto") Long idProducto) {
        
        servicio.eliminarProducto(idProducto);

        return "redirect:/productos/listar";
    }
	
	@GetMapping("/{id}")
	public String mostrarDetalle (@PathVariable Long id,Model modelo) {
		
		 Optional <productos> producto = servicio.obtenerProductoId(id);
		 
		 if(producto.isPresent()){
			 modelo.addAttribute("producto", producto.get());
			 return "Producto-Detalle";
		 }
		 else {
			 return "redirect:/productos";
		 }
		 
	}
	
	
	
}
