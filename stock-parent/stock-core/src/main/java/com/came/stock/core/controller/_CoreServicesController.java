package com.came.stock.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.model.domain.Usuarios;
import com.came.stock.model.services.UsuarioService;

@RestController
public class _CoreServicesController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping({"/tester"})
    @Produces({"application/json"})
	public String tester(@RequestBody String jsonItem) throws ParseException {
		List<String> usuarios = new ArrayList<String>();
		List<Usuarios> usrs = usuarioService.getAll();
		for (Usuarios item : usrs) {
			usuarios.add(item.getBenutzer());
		}
		
		return "Este es un mensaje de prueba: " + jsonItem + " DB: " + usuarios;
	}
}
