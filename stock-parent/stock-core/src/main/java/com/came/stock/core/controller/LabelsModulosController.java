package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class LabelsModulosController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(LabelsModulos itemObject) {
		// itemReturn = createResponseToLabelsModulos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(LabelsModulos itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToLabelsModulos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {
		// itemReturn = createResponseToLabelsModulos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String nombre) {
		// itemReturn = createResponseToLabelsModulos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_LABELS_MODULOS_BY_MODULO })
	@Produces("application/json")
	public String getByModulo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByModulo(String modulo) {
		// itemReturn = createResponseToLabelsModulos(response);
		return "";
	}

}
