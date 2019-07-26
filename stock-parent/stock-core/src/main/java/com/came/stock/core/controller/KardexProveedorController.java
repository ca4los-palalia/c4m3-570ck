package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class KardexProveedorController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(KardexProveedor itemObject) {
		// itemReturn = createResponseToKardexProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(KardexProveedor itemObject) {
//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("idFinder", idFinder);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToKardexProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organizacion) {

		// itemReturn = createResponseToListKardexProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_BY_ESTATUS })
	@Produces("application/json")
	public String getByEstatus(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstatus(EstatusRequisicion estatus, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("estatusRequisicion", estatus);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListKardexProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_KARDEX_PROVEEDOR_BY_PROVEEDOR_ESTATUS })
	@Produces("application/json")
	public String getByProveedorEstatus(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProveedorEstatus(Proveedor proveedor,
		// EstatusRequisicion estatus, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		// tranferParam.put("proveedor", proveedor);
		// tranferParam.put("estatusRequisicion", estatus);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListKardexProveedor(response);
		return "";
	}

}
