package com.usmp.fia.controller;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.usmp.fia.entity.CategoriaPlato;
import com.usmp.fia.entity.Plato;
import com.usmp.fia.entity.Usuario;
import com.usmp.fia.service.ICategoriaService;
import com.usmp.fia.service.IPlatoService;
import com.usmp.fia.service.IUsuarioService;
import com.usmp.fia.service.UploadFileService;

@Controller
@RequestMapping("/platos")
public class PlatoController {

	@Autowired
	private IPlatoService platoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ICategoriaService categoriasService;
	
	@Autowired
	private UploadFileService upload;

	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("platos", platoService.findAll());
		return "/plato/listar";
	}

	@GetMapping("/crearPlato")
	public String create(Model model) {

		Plato plato = new Plato();
		List<CategoriaPlato> categorias = categoriasService.findAll();
		
		model.addAttribute("categorias", categorias);
		model.addAttribute("plato", plato);
		return "/plato/crearPlato";
	}

	@PostMapping("/save")
	public String save(Plato plato, @RequestParam("img") MultipartFile file, HttpSession session, Model model)
			throws Exception {

		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		plato.setUsuario(u);
		
		if (plato.getId() == null) {
			String nombreImagen = upload.saveImage(file);
			plato.setImagen(nombreImagen);
		}

		platoService.save(plato);
		return "redirect:/platos";
	}

	@GetMapping("/editar/{id}")
	public String edit(@PathVariable Integer id, Model model, HttpSession session) {

		Plato plato = new Plato();
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		plato.setUsuario(u);
		
		Optional<Plato> optionalPlato = platoService.get(id);
		plato = optionalPlato.get();

		model.addAttribute("plato", plato);

		return "plato/editar";
	}

	@PostMapping("/update")
	public String update(Plato plato, @RequestParam("img") MultipartFile file, 
			HttpSession session) throws Exception{

		Plato p = new Plato();
		Usuario u=usuarioService.findById
				(Integer.parseInt(session.getAttribute
						("idusuario").toString())).get();
		plato.setUsuario(u);
		p = platoService.get(plato.getId()).get();

		if (file.isEmpty()) {

			plato.setImagen(p.getImagen());
		} else {

			if (!p.getImagen().equals("default.jpg")) {
				upload.deleteImagen(p.getImagen());
			}

			String nombreImagen = upload.saveImage(file);
			plato.setImagen(nombreImagen);
		}

		platoService.update(plato);
		return "redirect:/platos";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {

		Plato p = new Plato();
		p = platoService.get(id).get();

		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImagen(p.getImagen());
		}

		platoService.delete(id);
		return "redirect:/platos";
	}
}
