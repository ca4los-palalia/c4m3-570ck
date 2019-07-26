package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class EstatusRequisicionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(EstatusRequisicion itemObject) {

		// itemReturn = createResponseToEstatusRequisicion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(EstatusRequisicion itemObject) {

		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {

		// itemReturn = createResponseToEstatusRequisicion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToListEstatusRequisicion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String nombre) {
		// itemReturn = createResponseToEstatusRequisicion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_BY_CLAVE })
	@Produces("application/json")
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByClave(String clave) {

		// itemReturn = createResponseToEstatusRequisicion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_ESTATUS_REQUISICION_BY_ESTADO })
	@Produces("application/json")
	public String getByEstado(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstado(boolean estado) {
		// itemReturn = createResponseToEstatusRequisicion(response);
		return "";
	}
}
