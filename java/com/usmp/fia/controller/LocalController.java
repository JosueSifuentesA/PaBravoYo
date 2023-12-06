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

import com.usmp.fia.entity.TipoLocal;
import com.usmp.fia.service.ILocalService;

@Controller
@RequestMapping("/local")
public class LocalController {
	@Autowired
	private ILocalService localService;
	
	@GetMapping("")
	public String listarTipoLocales(Model model) {
		List<TipoLocal> locales= localService.findAll();
		
		model.addAttribute("locales", locales);
		
		return "/local/listarLocal";
	}
	
	@GetMapping("/crearLocal")
	public String crearTipoLocal(Model model) {
		
		TipoLocal local = new TipoLocal();
		model.addAttribute("local", local);
		return "/local/crearLocal";
	}
	
	@PostMapping("/save")
	public String guardarLocal(TipoLocal local , Model model) {
		
		localService.save(local);
		
		return "redirect:/local";
		
	}
	
	@PostMapping("/update")
	public String update(TipoLocal local , Model model) {
		
		localService.save(local);
		return "redirect:/local";
	}
	
	@GetMapping("/editar/{id}")
	public String editarTipoLocal(@PathVariable Integer id, Model model){
		
		TipoLocal local=new TipoLocal();
		Optional<TipoLocal> optionalTipoLocal=localService.findById(id);
		
		local=optionalTipoLocal.get();
		
		
		model.addAttribute("local", local);
		
		
		return "/local/editarLocal";
	}
	@GetMapping("/borrar/{id}")
	public String eliminarTipoLocal(@PathVariable Integer id) {
		TipoLocal local=new TipoLocal();
		local=localService.findById(id).get();
		
		localService.delete(id);
		return "redirect:/local";
		
	}
}
