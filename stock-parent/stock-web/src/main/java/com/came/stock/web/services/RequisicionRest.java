package com.came.stock.web.services;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Requisicion;

@Repository
public class RequisicionRest extends RestMetaclas {

	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(Requisicion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(Requisicion itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getById(Long idRequisicion, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("idRequisicion", idRequisicion);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_BY_ID), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getByPersona(Persona persona, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("persona", persona);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_BY_PERSONA), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getUltimoFolio(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_ULTIMO_FOLIO), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getAll(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_ALL), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getByEstatusRequisicion(EstatusRequisicion estatusRequisicion, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("estatusRequisicion", estatusRequisicion);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_BY_ESTATUS_REQUISICION), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getByFolio(String folio, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("folio", folio);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_BY_FOLIO), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	public CtrlRestService getByUnidadResponsable(Area area, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("area", area);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_BY_UNIDAD_RESPONSABLE), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> estatusRequisiciones,
			String folio, Area area, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("estatusRequisiciones", estatusRequisiciones);
		tranferParam.put("folio", folio);
		tranferParam.put("area", area);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_REQUISICIONES_CON_LISTA_DE_ESTATUS_FOLIO_AREA), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicion(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
