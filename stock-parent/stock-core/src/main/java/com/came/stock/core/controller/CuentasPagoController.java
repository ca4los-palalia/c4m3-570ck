package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class CuentasPagoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CUENTA_PAGO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(CuentaPago itemObject) {
		// itemReturn = createResponseToCuentaPago(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CUENTA_PAGO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(CuentaPago itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CUENTA_PAGO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToCuentaPago(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CUENTA_PAGO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll() {

		// itemReturn = createResponseToListCuentaPago(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CUENTA_PAGO_BY_PROVEEDOR })
	@Produces("application/json")
	public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProveedor(Proveedor proveedor) {

		// itemReturn = createResponseToListCuentaPago(response);
		return "";
	}
}
