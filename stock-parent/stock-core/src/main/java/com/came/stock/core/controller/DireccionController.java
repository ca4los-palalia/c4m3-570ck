package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class DireccionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_DIRECCION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Direccion itemObject) {
		// itemReturn = createResponseToDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_BY_CODIGO_POSTAL_ID })
	@Produces("application/json")
	public String getByCodigoPostalId(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByCodigoPostalId(String codigoPostal) {

		// itemReturn = createResponseToListDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_BY_ESTADO })
	@Produces("application/json")
	public String getByEstado(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstado(Estado estado) {

		// itemReturn = createResponseToListDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_BY_MUNICIPIO })
	@Produces("application/json")
	public String getByMunicipio(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByMunicipio(Municipio municipio) {
		// itemReturn = createResponseToListDireccion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_DIRECCION_BY_ULTIMO_REGISTRO })
	@Produces("application/json")
	public String getUltimoRegistro(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getUltimoRegistro() {
		// itemReturn = createResponseToDireccion(response);
		return "";
	}
}
