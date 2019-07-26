package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class JustificacionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_JUSTIFICACION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Justificacion itemObject) {
		// itemReturn = createResponseToJustificacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_JUSTIFICACION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Justificacion itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_JUSTIFICACION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idContacto) {
		// itemReturn = createResponseToJustificacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_JUSTIFICACION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListJustificacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_JUSTIFICACION_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String nombre) {
		// itemReturn = createResponseToJustificacion(response);
		return "";
	}
}
