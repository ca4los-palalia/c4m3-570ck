package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ModulosController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_MODULOS_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Modulos itemObject) {

		// itemReturn = createResponseToModulos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MODULOS_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Modulos itemObject) {
//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MODULOS_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListModulos(response);

		return "";
	}
}
