package com.usmp.fia.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Plato;
import com.usmp.fia.entity.TipoEntrega;
import com.usmp.fia.entity.TipoPago;
import com.usmp.fia.entity.Usuario;
import com.usmp.fia.service.IEntregaService;
import com.usmp.fia.service.IPagoService;
import com.usmp.fia.service.IPedidoService;
import com.usmp.fia.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IPedidoService pedidoService;

	Pedido pedido = new Pedido();

	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private IEntregaService entregaService ;
	
	@GetMapping("/registro")
	public String create() {
		return "usuario/registro";
	}

	@PostMapping("/save")
	public String save(Usuario usuario, HttpSession session, Model model, RedirectAttributes flash) {

		model.addAttribute("sesion", session.getAttribute("idusuario"));

		String mensajeFlash = "Registro completado";

		usuario.setTipo("USER");
		usuarioService.save(usuario);
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/";
	}

	@GetMapping("/iniciarSesion")
	public String login() {
		return "usuario/iniciarSesion";
	}

	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {

		Optional<Usuario> user = usuarioService.findByCodigo(usuario.getCodigo());

		if (user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());

			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			} else {
				return "redirect:/paginaCliente";
			}
		}

		return "redirect:/";
	}

	//---------------------------
	/*@PostMapping("/update")
	public String update(Pedido pedido, HttpSession session, Model model) throws Exception {
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Usuario u = new Usuario();
		u = usuarioService.findById(usuario.getId()).get();

		usuario.setNombre(u.getNombre());
		usuario.setApellido(u.getApellido());
		usuario.setEmail(u.getEmail());
		usuario.setPassword(u.getPassword());
		usuario.setTelefono(u.getTelefono());
		usuario.setTipo(u.getTipo());
		usuario.setUsername(u.getUsername());

		usuarioService.save(usuario);
		return "redirect:/pedido";
	}

	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Usuario usuario = new Usuario();
		Pedido pedido=new Pedido();
		Optional<Usuario> optionalUsuario = usuarioService.findById(id);
		Optional<Pedido> optionalPedido=pedidoService.findById(id);
		usuario = optionalUsuario.get();
		pedido=optionalPedido.get();
		model.addAttribute("pedido", pedido);
		model.addAttribute("usuario", usuario);

		return "usuario/editDatoPedido";
	}
	
	public String crear(Map<String, Object> model) {
		
		Pedido pedido=new Pedido();
		model.put("pedido", pedido);
		return "usuario/registroPedido";
	}
	
	@RequestMapping(value="/formPedido")
	public String crearP(Map<String, Object> model) {
		
		Pedido pedido=new Pedido();
		model.put("pedido", pedido);
		return "usuario/registroPedido";
	}
	@RequestMapping(value="/formPedido",method = RequestMethod.POST)
	public String guardar(@Valid Pedido pedido, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
		return "usuario/registroPedido";
		}
		pedidoService.save(pedido);
		status.setComplete();
		return "redirect:/DetallePedido";
	}*/
	
	@RequestMapping(value = "/formPedido")
	public String crear(Map<String, Object> model, HttpSession session) {
		List<TipoEntrega> listEntregas=entregaService.findAll();
		List<TipoPago> listPagos=pagoService.findAll();
		model.put("sesion", session.getAttribute("idusuario"));
		model.put("pedido", pedido);
		model.put("entregas", listEntregas);
		model.put("pagos", listPagos);
		return "usuario/registroPedido";
	}

	@GetMapping("/recibirData")
	public String capturarDatos(@RequestParam String direccion, @RequestParam String referencias, HttpSession session,
			Map<String, Object> model) {

		model.put("sesion", session.getAttribute("idusuario"));

		pedido.setDireccion(direccion);
		pedido.setReferencias(referencias);

		model.put("pedido", pedido);

		return "redirect:/plato";
	}
	
	
	// **********//
	
	@GetMapping("/pedidos")
	public String obtenerPedidos(Model model, HttpSession session) {
		model.addAttribute("sesion", session.getAttribute("idusuario"));

		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		List<Pedido> pedidos = pedidoService.findByUsuario(usuario);

		model.addAttribute("pedidos", pedidos);
		model.addAttribute("usuarios", usuario);
		return "usuario/listarPedido";
	}
	
	@GetMapping("/detalle/{id}")
	public String detallePedido(@PathVariable Integer id, HttpSession session, Model model) {

		Optional<Pedido> pedido = pedidoService.findById(id);
		Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		model.addAttribute("detalles", pedido.get().getDetallePedido());
		model.addAttribute("pedidos", pedido);
		model.addAttribute("usuarios", usuario);
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		return "usuario/detallePedido2";
	}
	
	@GetMapping("/cerrar")
	public String cerrarSesion(HttpSession session) {
		session.removeAttribute("idusuario");
		return "redirect:/";
	}

	
}