package com.came.stock.web.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;

@Repository
public class UnidadRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public CtrlRestService save(Unidad itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(Unidad itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getById(Long idUnidad, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("idUnidad", idUnidad);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_BY_ID_AND_ORG), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_ALL_BY_ORG), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByNombre(String nombre, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("nombreUnidad", nombre);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_BY_NOMBRE_AND_ORG), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByOrganizacion(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_BY_ORGANIZACION), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getByUsuario(Usuarios usuario) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_UNIDAD_BY_USUARIO), gson.toJson(usuario));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

}
