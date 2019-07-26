package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class EmailController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_EMAIL_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Email itemObject) {
		// itemReturn = createResponseToEmail(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_EMAIL_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Email itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_EMAIL_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idContacto) {
		// itemReturn = createResponseToEmail(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_EMAIL_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListEmail(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_EMAIL_BY_ULTIMO_REGISTRO })
	@Produces("application/json")
	public String getUltimoRegistro(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getUltimoRegistro() {
		// itemReturn = createResponseToEmail(response);
		return "";
	}
}
