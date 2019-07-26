package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ConfiguracionCorreoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CONFIGURACION_CORREO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ConfiguracionCorreo itemObject) {
		// itemReturn = createResponseToConfiguracionCorreo(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFIGURACION_CORREO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {

		// itemReturn = createResponseToConfiguracionCorreo(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFIGURACION_CORREO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListConfiguracionCorreo(response);
		return "";
	}
}
