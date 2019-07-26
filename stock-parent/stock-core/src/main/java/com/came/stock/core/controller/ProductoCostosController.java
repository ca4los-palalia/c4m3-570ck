package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProductoCostosController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(ProductoCostos itemObject) {
		// itemReturn = createResponseToProductoCostos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(ProductoCostos itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idFinder", idFinder);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToProductoCostos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListProductoCostos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_BY_DESCRIPCION_AND_PRODUCTO })
	@Produces("application/json")
	public String getByDescripcionAndProducto(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByDescripcionAndProducto(String descripcion, Producto producto) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("costoDescripcion", descripcion);
//		tranferParam.put("producto", producto);

		// itemReturn = createResponseToListProductoCostos(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_COSTOS_BY_PRODUCTO_ORDER_MOST_RECENT_DATE })
	@Produces("application/json")
	public String getByProductoOrderMostRecentDate(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProductoOrderMostRecentDate(Producto producto) {
		// itemReturn = createResponseToListProductoCostos(response);
		return "";
	}
}
