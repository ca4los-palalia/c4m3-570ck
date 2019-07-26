package com.came.stock.web.services;


import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.Producto;

@Repository
public class CostosProductoRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public CtrlRestService save(CostosProducto itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_PRODUCTO_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCostosProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(CostosProducto itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_PRODUCTO_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_PRODUCTO_BY_ID), idFinder.toString());
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCostosProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_PRODUCTO_ALL), "*");
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListCostosProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByProducto(Producto producto) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_COSTOS_PRODUCTO_BY_PRODUCTO), gson.toJson(producto));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToCostosProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
