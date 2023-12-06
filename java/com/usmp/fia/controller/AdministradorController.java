package com.usmp.fia.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usmp.fia.entity.DetallePedido;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Plato;
import com.usmp.fia.entity.Usuario;
import com.usmp.fia.service.IDetallePedidoService;
import com.usmp.fia.service.IPedidoService;
import com.usmp.fia.service.IPlatoService;
import com.usmp.fia.service.IUsuarioService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private IPlatoService platoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetallePedidoService detallePedidoService;
	
	@GetMapping("")
	public String home(Model model) {
		List<Plato> platos = platoService.findAll();
		model.addAttribute("platos", platos);

		return "administrador/paginaPrincipalAdmin";
	}
	
	@GetMapping("/clientes")
	public String clientes(Model model) {

		model.addAttribute("usuarios", usuarioService.findAll());
		
		return "administrador/listarClientes";
	}

	@GetMapping("/pedidos")
	public String pedidos(Model model) {
		
		model.addAttribute("pedidos", pedidoService.findAll());
		model.addAttribute("usuarios", usuarioService.findAll());
		return "/administrador/listarPedidos";
	}
	
	
	// Obtener los pedidos
	@GetMapping("/editar/{id}")
	public String editarPedidos(@PathVariable Integer id ,Model model) {
		
		Pedido pedido=new Pedido();
		
		pedido=pedidoService.findById(id).get();
		
		model.addAttribute("pedido", pedido);
		return "/administrador/editarPedido";
	}
	
	@PostMapping("/save")
	public String save(Pedido pedido) {

	
		Pedido p = new Pedido();
		
		p = pedidoService.findById(pedido.getId()).get();
		
			pedido.setFecha(p.getFecha());
			pedido.setTotal(p.getTotal());
			pedido.setDireccion(p.getDireccion());
			pedido.setReferencias(p.getReferencias());
			pedido.setUsuario(p.getUsuario());

		pedidoService.save(pedido);
		return "redirect:/administrador/pedidos";

	}

	@GetMapping("/verDatos/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

		
		Pedido pedido = pedidoService.findById(id).get();
		
		model.addAttribute("usuarios", pedido.getUsuario());
		model.addAttribute("detalles", pedido.getDetallePedido());
		model.addAttribute("pedido", pedido);
		return "administrador/verDatos";
	}
}
