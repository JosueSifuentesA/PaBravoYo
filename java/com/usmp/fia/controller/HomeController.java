package com.usmp.fia.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.usmp.fia.entity.DetallePedido;
import com.usmp.fia.entity.Pedido;
import com.usmp.fia.entity.Plato;
import com.usmp.fia.entity.TipoEntrega;
import com.usmp.fia.entity.TipoLocal;
import com.usmp.fia.entity.TipoPago;
import com.usmp.fia.entity.Usuario;
import com.usmp.fia.service.IDetallePedidoService;
import com.usmp.fia.service.IEntregaService;
import com.usmp.fia.service.ILocalService;
import com.usmp.fia.service.IPagoService;
import com.usmp.fia.service.IPedidoService;
import com.usmp.fia.service.IPlatoService;
import com.usmp.fia.service.IUsuarioService;

@Controller
@RequestMapping("/")
public class HomeController {

	
	@Autowired
	private IPlatoService platoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IDetallePedidoService detallePedidoService;
	
	@Autowired
	private IPagoService pagoService;
	
	@Autowired
	private IEntregaService entregaService ;
	
	@Autowired
	private ILocalService localService ;
	
	List<DetallePedido> detallesP = new ArrayList<DetallePedido>();
	
	
	Pedido pedido=new Pedido();
	Plato plato = new Plato();
	DetallePedido detallePedido = new DetallePedido();
	//PAGINA PRINCIPAL
		@GetMapping({"/PaBravoYo","paginaPrincipal","/"})
		private String home(Model model, HttpSession session) {
			model.addAttribute("sesion", session.getAttribute("idusuario"));
			return "usuario/paginaPrincipall";
		}
		
		//PAGINA PRINCIPAL CLIENTE
		@GetMapping({"/paginaCliente"})
		private String homeC(Model model, HttpSession session) {
			model.addAttribute("sesion", session.getAttribute("idusuario"));
			return "usuario/paginaPrincipalCliente";
		}
		
		//PAGINA PRINCIPAL ADMIN
			@GetMapping({"/paginaPrincipalAdmin"})
			private String homeA(Model model, HttpSession session) {
				model.addAttribute("sesion", session.getAttribute("idusuario"));
				return "administrador/paginaPrincipalAdmin";
			}
			
			
		//PLATOS REGISTRADOS
		@GetMapping("/plato")
		private String plato(Model model, HttpSession session) {
			model.addAttribute("sesion", session.getAttribute("idusuario"));
			model.addAttribute("platos", platoService.findAll());
			
			return "usuario/plato";
		}
		
		//REGISTRAR PEDIDO
				
		// **** EDITAR DIRECCION Y REFERENCIA ******//

		@RequestMapping(value = "/formPedido")
		public String crear(Map<String, Object> model, HttpSession session) {
			List<TipoEntrega> listEntregas=entregaService.findAll();
			List<TipoPago> listPagos=pagoService.findAll();
			List<TipoLocal> listLocales=localService.findAll();
			model.put("sesion", session.getAttribute("idusuario"));
			model.put("pedido", pedido);
			model.put("entregas", listEntregas);
			model.put("pagos", listPagos);
			model.put("locales", listLocales);
			return "usuario/registroPedido";
		}

		@GetMapping("/recibirData")
		public String capturarDatos(@RequestParam String direccion, @RequestParam String referencias,
				@RequestParam TipoEntrega tipoEntrega,@RequestParam TipoPago tipoPago, @RequestParam TipoLocal tipoLocal,
				HttpSession session,
				Map<String, Object> model) {

			model.put("sesion", session.getAttribute("idusuario"));

			pedido.setDireccion(direccion);
			pedido.setReferencias(referencias);
			pedido.setTipoEntrega(tipoEntrega);
			pedido.setTipoPago(tipoPago);
			pedido.setTipoLocal(tipoLocal);

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

		
		//MUESTRA EL PLATO SELECCIONADO Y ELEGIR CANTIDAD
		@GetMapping("/platoHome/{id}")
		public String platoHome(@PathVariable Integer id, Model model, HttpSession session) {

			model.addAttribute("sesion", session.getAttribute("idusuario"));
			Plato plato = new Plato();
			
			Optional<Plato> platoOptional = platoService.get(id);
			plato = platoOptional.get();

			model.addAttribute("plato", plato);
			model.addAttribute("pedido", pedido);
			model.addAttribute("detallePedido", detallePedido);
			return "usuario/platoHome";
		}
		
		/*==========================================================*/	

			@PostMapping("/carrito")
			public String addCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model,
					HttpSession session) {
				model.addAttribute("sesion", session.getAttribute("idusuario"));
				DetallePedido detallePedido = new DetallePedido();
				Plato plato = new Plato();
				double sumaTotal = 0;
				
				Optional<Plato> platoOptional = platoService.get(id);
				plato = platoOptional.get(); 
				
				detallePedido.setCantidad(cantidad);
				detallePedido.setNombre(plato.getNombre()); 
				detallePedido.setSubtotal(plato.getPrecio()*cantidad); 
				detallePedido.setPlato(plato);

				//VALIDAR EL PLATO SELECCIONADO
				Integer idPlato = plato.getId();
				boolean ingresado = detallesP.stream().anyMatch(p -> p.getPlato().getId() == idPlato);

				if (!ingresado) {
					
					detallesP.add(detallePedido);

				}

				// CALCULAR EL TOTAL
				sumaTotal = detallesP.stream().mapToDouble(dt -> dt.getSubtotal()).sum();

				pedido.setTotal(sumaTotal); 
				model.addAttribute("car", detallesP);
				model.addAttribute("pedido", pedido);
				model.addAttribute("plato", plato);
				return "usuario/carrito";
			}

			// QUITAR EL PLATO SELECCIONADO
			@GetMapping("/delete/carrito/{id}")
			public String deletePedidoCarrito(@PathVariable Integer id, Model model,HttpSession session) {
				
				model.addAttribute("sesion", session.getAttribute("idusuario"));
				List<DetallePedido> pedidoNuevo = new ArrayList<DetallePedido>();

				// Si el id es diferente lo agrega
				for (DetallePedido detallePedido : detallesP) {
					if (detallePedido.getPlato().getId() != id) {
						pedidoNuevo.add(detallePedido);
					}
				}

				// Nueva lista
				detallesP = pedidoNuevo;

				double sumaTotal = 0;
				sumaTotal = detallesP.stream().mapToDouble(dt -> dt.getSubtotal()).sum();

				pedido.setTotal(sumaTotal);
				model.addAttribute("car", detallesP);
				model.addAttribute("pedido", pedido);
				model.addAttribute("plato", plato);
				return "redirect:/getCarrito";
			}

			// Metodo para ir a carrito
			@GetMapping("/getCarrito")
			public String getCarrito(Model model, HttpSession session) {
				
				model.addAttribute("car", detallesP);
				model.addAttribute("pedido", pedido);
				model.addAttribute("plato", plato);
				// Sesion
				model.addAttribute("sesion", session.getAttribute("idusuario"));

				return "usuario/carrito";
			}

			// Metodo para ver la orden

			@GetMapping("/DetallePedido")
			public String DetallePedido(Model model, HttpSession session) {
				
				model.addAttribute("sesion", session.getAttribute("idusuario"));
				Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

				model.addAttribute("car", detallesP);
				model.addAttribute("pedido", pedido);
				model.addAttribute("usuario", usuario);
				return "usuario/detallePedido";
			}

			@GetMapping("/savePedido")
			public String savePedido(HttpSession session) {
				
				Date fechaCreacion = new Date();
				pedido.setFecha(fechaCreacion);
				// Usuario
				Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
				pedido.setUsuario(usuario);
				pedidoService.save(pedido);
				
				for (DetallePedido dt : detallesP) {
					dt.setPedido(pedido);
					detallePedidoService.save(dt);
				}

				// Limpiar la lista y la orden
				pedido = new Pedido();
				detallesP.clear();
				
				return "redirect:/paginaCliente";
			}

}
