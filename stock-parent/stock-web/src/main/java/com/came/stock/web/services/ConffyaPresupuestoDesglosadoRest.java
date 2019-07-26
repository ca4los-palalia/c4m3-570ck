package com.came.stock.web.services;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.Organizacion;

@Repository
public class ConffyaPresupuestoDesglosadoRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	public CtrlRestService save(ConffyaPresupuestoDesglosado itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService delete(ConffyaPresupuestoDesglosado itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getById(Long idFinder) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_ID), idFinder.toString());
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_ALL), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByOrganizacion(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_ORGANIZACION), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getByUr(String ur) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_UR), ur);
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	public CtrlRestService getCountRows() {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_COUNT_ROWS), "*");
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getPartidaGenericaFiltrado(String ur, String programa, String proyecto,
			String fuenteFinanciamiento, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("ur", ur);
		tranferParam.put("programa", programa);
		tranferParam.put("fuenteFinanciamiento", fuenteFinanciamiento);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_PARTIDA_GENERICA_FILTRADO), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getPartidaGenericaFiltradoConPartidaGenerica(String ur, String programa,
			String proyecto, String fuenteFinanciamiento, String partidaGenerica, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("ur", ur);
		tranferParam.put("programa", programa);
		tranferParam.put("proyecto", proyecto);
		tranferParam.put("fuenteFinanciamiento", fuenteFinanciamiento);
		tranferParam.put("partidaGenerica", partidaGenerica);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_PARTIDA_GENERICA_FILTRADO_CON_PARTIDA_GENERICA), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService updateFromConffya(String xml, Long usuario, Long organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("xml", xml);
		tranferParam.put("idUsuario", usuario);
		tranferParam.put("idOrganizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_UPDATE_FROM_CONFFYA), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}