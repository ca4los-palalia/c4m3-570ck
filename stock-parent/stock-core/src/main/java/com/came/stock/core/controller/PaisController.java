package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class PaisController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PAIS_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Pais itemObject) {

		// itemReturn = createResponseToPais(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PAIS_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//public CtrlRestService delete(Pais itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PAIS_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {

		// itemReturn = createResponseToPais(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PAIS_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListPais(response);
		return "";
	}

}
