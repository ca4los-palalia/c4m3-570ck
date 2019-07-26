package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class RequisicionProveedorController extends ServicesCore {
	
	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(RequisicionProveedor itemObject) {
		//itemReturn = createResponseToRequisicionProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(RequisicionProveedor itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_BYID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idRequisicionProveedor) {
		//itemReturn = createResponseToRequisicionProveedor(response);
				return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_BY_REQUISICION })
	@Produces("application/json")
	public String getByRequisicion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByRequisicion(Requisicion itemObject) {
		//itemReturn = createResponseToListRequisicionProveedor(response);
				return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_BY_PROVEEDOR })
	@Produces("application/json")
	public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProveedor(Proveedor itemObject) {
		//itemReturn = createResponseToListRequisicionProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PROVEEDORES_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {
		//itemReturn = createResponseToListRequisicionProveedor(response);
		return "";
	}
	
	
}
