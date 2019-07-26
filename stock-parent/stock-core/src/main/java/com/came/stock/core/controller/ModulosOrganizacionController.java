package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ModulosOrganizacionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_MODULOS_ORGANIZACION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ModulosOrganizacion itemObject) {

		// itemReturn = createResponseToModulosOrganizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MODULOS_ORGANIZACION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(ModulosOrganizacion itemObject) {
//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MODULOS_ORGANIZACION_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByOrganizacion(Organizacion organizacion) {

		// itemReturn = createResponseToListModulosOrganizacion(response);
		return "";
	}
}
