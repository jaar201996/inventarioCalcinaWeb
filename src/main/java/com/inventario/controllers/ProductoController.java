package com.inventario.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inventario.entity.Producto;
import com.inventario.service.IProductoService;



@Controller
@SessionAttributes("cliente")
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de clientes");
		model.addAttribute("producto", productoService.findAll());
		return "index";
   }
	
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id,Map<String,Object> model,RedirectAttributes flash) {
		
		Producto producto=productoService.findOne(id);
		if(producto==null) {
			flash.addFlashAttribute("error","El cliente no existe en la base de datos");
			return null;
		}
		
		model.put("producto", producto);
		model.put("titulo", "Detalle producto:" + producto.getV_nombreProducto());
		return "ver"; 
	}
	
	@RequestMapping(value="/form1")
	public String crear(Map<String,Object> model) {
		Producto producto=new Producto();
		model.put("producto",producto);
		model.put("titulo", "Formulario de Producto");
		return "form1";
	}
	
	@RequestMapping(value="/form")
	public String crear1(Map<String,Object> model) {
		Producto producto=new Producto();
		model.put("producto",producto);
		model.put("titulo", "Formulario de Producto");
		return "form";
	}
	
	
	@RequestMapping(value="/form1", method=RequestMethod.POST)
	public String guardar(@Valid Producto producto,BindingResult result,Model model,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de producto");
		   return "form1";
		}
		
		productoService.save(producto);
		status.setComplete();
		return "redirect:index";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar1(@Valid Producto producto,BindingResult result,Model model,SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de producto");
		   return "form";
		}
		
		productoService.save(producto);
		status.setComplete();
		return "redirect:index";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Producto producto =null;
		
		if(id>0) {
			producto=productoService.findOne(id);
		}else {
			return "redirect:index";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Editar Producto");
		return "form";
	}
	
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			productoService.delete(id);
		}
		return "redirect:/index";
	}
	
	
}
