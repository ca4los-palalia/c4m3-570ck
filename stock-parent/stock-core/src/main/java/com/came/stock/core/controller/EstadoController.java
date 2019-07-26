package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class EstadoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_ESTADO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Estado itemObject) {

		// itemReturn = createResponseToContacto(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTADO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Estado itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTADO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTADO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListContacto(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTADO_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String name) {

		// itemReturn = createResponseToContacto(response);

		return "";
	}
}
