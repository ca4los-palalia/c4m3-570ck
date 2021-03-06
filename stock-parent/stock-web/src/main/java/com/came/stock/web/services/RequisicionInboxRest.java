package com.came.stock.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.RequisicionInbox;

@Repository
public class RequisicionInboxRest extends RestMetaclas {

	@PostConstruct
	public void init() {
		super.init();
	}

	public CtrlRestService save(RequisicionInbox itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_INBOX_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService delete(RequisicionInbox itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_INBOX_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAllNews(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_INBOX_ALL_NEWS), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	public CtrlRestService getAll(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_INBOX_ALL), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListUnidad(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
}
