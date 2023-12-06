package com.usmp.fia.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usmp.fia.entity.CategoriaPlato;
import com.usmp.fia.service.ICategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	
	@Autowired
	ICategoriaService categoriaService;
	
	
	
	@GetMapping("")
	public String mostrarLista(Model model) {
		
		
		List<CategoriaPlato> categorias = categoriaService.findAll(); 
		
		model.addAttribute("categorias", categorias);
		
		return "/administrador/listarCategoria";
		
	}
	
	
	@GetMapping("/crearCategoria")
	public String crearCategoria(Model model) {
		
		CategoriaPlato categoria = new CategoriaPlato();
		model.addAttribute("categoria", categoria);
		return "/administrador/crearCategoria";
		
	}
	
	
	@PostMapping("/save")
	public String guardarCategoria(CategoriaPlato categoria , Model model) {
		
		categoriaService.save(categoria);
		
		return "redirect:/categoria";
		
	}
	
	@PostMapping("/update")
	public String update(CategoriaPlato categoria,Model model) {
		
		categoriaService.save(categoria);
		return "redirect:/categoria";
	}
	
	
	
	
	@GetMapping("/editar/{id}")
	public String editarCategoria(@PathVariable Integer id, Model model) {
		
		CategoriaPlato categoria = new CategoriaPlato();
		Optional<CategoriaPlato> optionalCategoria = categoriaService.findById(id);
		categoria = optionalCategoria.get();
		
		model.addAttribute("categoria",categoria);
		return "/administrador/editarCategoria";
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarCategoria(@PathVariable Integer id){
		CategoriaPlato categoria = new CategoriaPlato();
		categoria=categoriaService.findById(id).get();
		
		
		categoriaService.delete(id);
		return "redirect:/categoria";
	}
	
	
	
	
}