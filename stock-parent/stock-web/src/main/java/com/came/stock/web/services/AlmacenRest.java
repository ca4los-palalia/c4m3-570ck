package com.came.stock.web.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

@Repository
public class AlmacenRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public CtrlRestService save(Almacen itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_ALMACEN_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToAlmacen(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(Almacen itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_ALMACEN_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("idFinder", idFinder);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_ALMACEN_BY_ID), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToAlmacen(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getAll(Organizacion organinzacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_ALMACEN_ALL), gson.toJson(organinzacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListAlmacen(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getByArea(Area area) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_ALMACEN_BY_AREA), gson.toJson(area));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListAlmacen(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
