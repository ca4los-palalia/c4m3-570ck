package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class FamiliasProductoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(FamiliasProducto itemObject) {

		// itemReturn = createResponseToFamiliasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(FamiliasProducto itemObject) {

		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idContacto) {

		// itemReturn = createResponseToFamiliasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListFamiliasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProducto(Producto producto) {

		// itemReturn = createResponseToListFamiliasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_BY_FAMILIA })
	@Produces("application/json")
	public String getByFamilia(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByFamilia(ProductoTipo productoTipo) {
		// itemReturn = createResponseToListFamiliasProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_FAMILIAS_PRODUCTO_BY_PRODUCTO_PRODUCTO_TIPO })
	@Produces("application/json")
	public String getByProductoProductoTipo(@RequestBody String jsonItem) throws ParseException {

		// public CtrlRestService getByProductoProductoTipo(Producto producto,
		// ProductoTipo productoTipo) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("productoTipo", productoTipo);
		// tranferParam.put("producto", producto);

		// itemReturn = createResponseToFamiliasProducto(response);
		return "";
	}
}
