package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class CodigoBarrasProductoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(CodigoBarrasProducto itemObject) {
		// itemReturn = createResponseToCodigoBarrasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(CodigoBarrasProducto itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToCodigoBarrasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListCodigoBarrasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_BY_CODIGO })
	@Produces("application/json")
	public String getByCodigo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByCodigo(String codigo) {
		// itemReturn = createResponseToListCodigoBarrasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CODIGO_BARRAS_PRODUCTO_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProducto(Producto producto) {
		// itemReturn = createResponseToListCodigoBarrasProducto(response);
		return "";
	}
}
