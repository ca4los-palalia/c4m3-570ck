package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ContactoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CONTACTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Contacto itemObject) {
		// itemReturn = createResponseToContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Contacto itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idContacto) {
		// itemReturn = createResponseToContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_BY_TELEFONO })
	@Produces("application/json")
	public String getByTelefono(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByTelefono(Telefono telefono) {
		// itemReturn = createResponseToContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_BY_ULTIMO_REGISTRO_CONTACTO })
	@Produces("application/json")
	public String getUltimoRegistroContacto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getUltimoRegistroContacto() {
		// itemReturn = createResponseToContacto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONTACTO_BY_EMAIL })
	@Produces("application/json")
	public String getByEmail(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEmail(Email email) {
		// itemReturn = createResponseToContacto(response);
		return "";
	}
}
