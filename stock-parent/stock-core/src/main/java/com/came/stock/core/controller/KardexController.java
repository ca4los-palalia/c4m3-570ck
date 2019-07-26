package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class KardexController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_KARDEX_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Kardex itemObject) {
		// itemReturn = createResponseToKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Kardex itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("idFinder", idFinder);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organizacion) {

		// itemReturn = createResponseToListKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_BY_ESTATUS })
	@Produces("application/json")
	public String getByEstatus(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstatus(EstatusRequisicion estatus, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("estatusRequisicion", estatus);
		// tranferParam.put("organizacion", organizacion);

		//itemReturn = createResponseToListKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_BY_KARDEX_PROVEEDOR_ESTATUS })
	@Produces("application/json")
	public String getByKardexProveedorEstatus(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByKardexProveedorEstatus(KardexProveedor
		// kardexProveedor, EstatusRequisicion estatus, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("kardexProveedor", kardexProveedor);
		// tranferParam.put("estatusRequisicion", estatus);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProducto(Producto producto, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("producto", producto);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_ALL_PRODUCTOS_NO_REPETIDOS })
	@Produces("application/json")
	public String getAllProductosNoRepetidos(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAllProductosNoRepetidos(Organizacion organizacion)
		// {
		// itemReturn = createResponseToListKardex(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_KARDEX_ORDER_BY_DATE_MAS_RECIENTE })
	@Produces("application/json")
	public String getKardexOrderByDateMasReciente(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getKardexOrderByDateMasReciente(List<Long>
		// listaDesordenada, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("idKardex", listaDesordenada);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListKardex(response);
		return "";
	}
}
