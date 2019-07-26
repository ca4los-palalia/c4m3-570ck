package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProductoNaturalezaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(ProductoNaturaleza itemObject) {
		// itemReturn = createResponseToProductoNaturaleza(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(ProductoNaturaleza itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToProductoNaturaleza(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {

		// itemReturn = createResponseToListProductoNaturaleza(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByNombre(String nombre) {
		// itemReturn = createResponseToProductoNaturaleza(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_NATURALEZA_BY_SIMBOLO })
	@Produces("application/json")
	public String getBySimbolo(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getBySimbolo(String simbolo) {
		// itemReturn = createResponseToProductoNaturaleza(response);
		return "";
	}
}
