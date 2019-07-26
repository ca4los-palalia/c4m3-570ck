package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class CostosProductoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_COSTOS_PRODUCTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(CostosProducto itemObject) {

		// itemReturn = createResponseToCostosProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COSTOS_PRODUCTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {

		// public CtrlRestService delete(CostosProducto itemObject) {

		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COSTOS_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToCostosProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COSTOS_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListCostosProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COSTOS_PRODUCTO_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProducto(Producto producto) {
		// itemReturn = createResponseToCostosProducto(response);
		return "";
	}
}
