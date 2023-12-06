package com.usmp.fia.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	private String folder = "images//";

	// guardar imagen
	public String saveImage(MultipartFile file) throws Exception {
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		return "default.jpg";
	}

	// Eliminar imagen
	public void deleteImagen(String nombre) {
		String ruta = "images//";
		File file = new File(ruta + nombre);
		file.delete();
	}
}
