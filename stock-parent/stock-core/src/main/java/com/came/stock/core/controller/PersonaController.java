package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class PersonaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PERSONA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Persona itemObject) {

		// itemReturn = createResponseToPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Persona itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {

		// itemReturn = createResponseToPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_BY_SEXO })
	@Produces("application/json")
	public String getBySexo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getBySexo(Long sexo) {

		// itemReturn = createResponseToListPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_BY_DIRECCION })
	@Produces("application/json")
	public String getByDireccion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByDireccion(Direccion direccion) {

		// itemReturn = createResponseToListPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_BY_CONTACTO })
	@Produces("application/json")
	public String getByContacto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByContacto(Contacto contacto) {

		// itemReturn = createResponseToListPersona(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PERSONA_BY_ULTIMO_REGISTRO })
	@Produces("application/json")
	public String getUltimoRegistro(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getUltimoRegistro() {

		// itemReturn = createResponseToPersona(response);
		return "";
	}
}
