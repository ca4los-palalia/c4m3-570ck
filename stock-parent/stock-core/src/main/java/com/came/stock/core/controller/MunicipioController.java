package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class MunicipioController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_MUNICIPIO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Municipio itemObject) {

		// itemReturn = createResponseToMunicipio(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_MUNICIPIO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Municipio itemObject) {
//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MUNICIPIO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {

		// itemReturn = createResponseToMunicipio(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MUNICIPIO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListMunicipio(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MUNICIPIO_BY_ESTADO })
	@Produces("application/json")
	public String getByEstado(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstado(Estado estado) {

		// itemReturn = createResponseToListMunicipio(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_MUNICIPIO_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String name) {

		// itemReturn = createResponseToMunicipio(response);
		return "";
	}

}
