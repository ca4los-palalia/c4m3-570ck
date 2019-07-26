package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProductoTipoSubFamiliaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(ProductoTipoSubFamilia itemObject) {
		// itemReturn = createResponseToListProductoTipoSubFamilia(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(ProductoTipoSubFamilia itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToProductoTipoSubFamilia(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {
		// itemReturn = createResponseToListProductoTipoSubFamilia(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByNombre(String nombre) {
		// itemReturn = createResponseToProductoTipoSubFamilia(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_TIPO_SUB_FAMILIA_BY_PRODUCTO_TIPO })
	@Produces("application/json")
	public String getByProductoTipo(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProductoTipo(ProductoTipo productoTipo) {

		// itemReturn = createResponseToListProductoTipoSubFamilia(response);
		return "";
	}
}
