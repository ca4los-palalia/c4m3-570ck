package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ClaveArmonizadaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ClaveArmonizada itemObject) {

		// itemReturn = createResponseToClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(ClaveArmonizada itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("idFinder", idFinder);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_GRUPO })
	@Produces("application/json")
	public String getByGrupo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByGrupo(Integer grupo) {
		// itemReturn = createResponseToListClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_SUB_GRUPO })
	@Produces("application/json")
	public String getBySubGrupo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getBySubGrupo(Integer subGrupo) {
		// itemReturn = createResponseToListClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_CLASE })
	@Produces("application/json")
	public String getByClase(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByClase(Integer clase) {
		// itemReturn = createResponseToListClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_CLAVE })
	@Produces("application/json")
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByClave(String clave) {
		// itemReturn = createResponseToClaveArmonizada(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CLAVE_ARMONIZADA_BY_DESCRIPCION })
	@Produces("application/json")
	public String getByDescripcion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByDescripcion(String descripcion) {
		// itemReturn = createResponseToClaveArmonizada(response);
		return "";
	}
}
