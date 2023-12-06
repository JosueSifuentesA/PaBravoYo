package com.usmp.fia.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.usmp.fia.entity.CategoriaPlato;
import com.usmp.fia.entity.TipoEntrega;
import com.usmp.fia.service.IEntregaService;

@Controller
@RequestMapping("/entrega")
public class EntregaController {
	@Autowired
	private IEntregaService entregaService;
	
	@GetMapping("")
	public String listarTipoEntregas(Model model) {
		List<TipoEntrega> entregas= entregaService.findAll();
		
		model.addAttribute("entregas", entregas);
		
		return "/entrega/listarEntrega";
	}
	
	@GetMapping("/crearEntrega")
	public String crearTipoEntrega(Model model) {
		
		TipoEntrega entrega = new TipoEntrega();
		model.addAttribute("entrega", entrega);
		return "/entrega/TipoEntrega";
	}
	
	@PostMapping("/save")
	public String guardarEntrega(TipoEntrega entrega , Model model) {
		
		entregaService.save(entrega);
		
		return "redirect:/entrega";
		
	}
	
	@PostMapping("/update")
	public String update(TipoEntrega entrega , Model model) {
		
		entregaService.save(entrega);
		return "redirect:/entrega";
	}
	
	
	@GetMapping("/editar/{id}")
	public String editarTipoEntrega(@PathVariable Integer id, Model model){
		
		TipoEntrega entrega=new TipoEntrega();
		Optional<TipoEntrega> optionalTipoEntrega=entregaService.findById(id);
		
		entrega=optionalTipoEntrega.get();
		
		
		model.addAttribute("entrega", entrega);
		
		
		return "/entrega/editarEntrega";
	}
	@GetMapping("/borrar/{id}")
	public String eliminarTipoEntrega(@PathVariable Integer id) {
		TipoEntrega entrega=new TipoEntrega();
		entrega=entregaService.findById(id).get();
		
		entregaService.delete(id);
		return "redirect:/entrega";
		
	}
}
