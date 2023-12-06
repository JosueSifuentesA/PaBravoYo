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

import com.usmp.fia.entity.TipoPago;
import com.usmp.fia.service.IPagoService;

@Controller
@RequestMapping("/pago")
public class PagoController {
	@Autowired
	private IPagoService pagoService;
	
	@GetMapping("")
	public String listarTipoPagos(Model model) {
		List<TipoPago> pagos= pagoService.findAll();
		
		model.addAttribute("pagos", pagos);
		
		return "/pago/listarPago";
	}
	
	@GetMapping("/crearPago")
	public String crearTipoPago(Model model) {
		
		TipoPago pago = new TipoPago();
		model.addAttribute("pago", pago);
		return "/pago/crearPago";
	}
	
	@PostMapping("/save")
	public String guardarPago(TipoPago pago , Model model) {
		
		pagoService.save(pago);
		
		return "redirect:/pago";
		
	}
	
	@PostMapping("/update")
	public String update(TipoPago pago , Model model) {
		
		pagoService.save(pago);
		return "redirect:/pago";
	}
	
	@GetMapping("/editar/{id}")
	public String editarTipoPago(@PathVariable Integer id, Model model){
		
		TipoPago pago=new TipoPago();
		Optional<TipoPago> optionalTipoPago=pagoService.findById(id);
		
		pago=optionalTipoPago.get();
		
		
		model.addAttribute("pago", pago);
		
		
		return "/pago/editarPago";
	}
	@GetMapping("/borrar/{id}")
	public String eliminarTipoPago(@PathVariable Integer id) {
		TipoPago pago=new TipoPago();
		pago=pagoService.findById(id).get();
		
		pagoService.delete(id);
		return "redirect:/pago";
		
	}
}
