package com.came.stock.web.services;

import java.util.HashMap;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;

@Repository
public class RequisicionProductoRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
	
	
	public CtrlRestService save(RequisicionProducto itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_SAVE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService delete(RequisicionProducto itemObject) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_DELETE), gson.toJson(itemObject));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION))
			itemReturn.setSingle(response);
		else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getById(Long idRequisicionProducto, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("idRequisionProducto", idRequisicionProducto);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_ID), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByProducto(Producto itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("producto", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_PRODUCTO), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByRequisicion(Requisicion itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("requisicion", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_REQUISICION), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByProveedor(Proveedor itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("proveedor", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_PROVEEDOR), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByLugar(Lugar itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("lugar", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_LUGAR), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getAll(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_ALL), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getAllRequisiciones(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_ALL_REQUISICIONES), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getRequisicionesConEstadoEspecifico(EstatusRequisicion itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("estatusRequisicion", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_REQUISICIONES_CON_ESTADO_ESPECIFICO), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getAllDistinctByProveedor(Organizacion organizacion) {
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_ALL_DISTINCT_BY_PROVEEDOR), gson.toJson(organizacion));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListProveedor(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByConfiaPartidaGenerica(ConffyaPartidaGenerica itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("cofiaPartidaGenerica", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_CONFIA_PARTIDA_GENERICA), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}

	
	public CtrlRestService getByCotizacion(Cotizacion itemObject, Organizacion organizacion) {
		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
		tranferParam.put("cotizacion", itemObject);
		tranferParam.put("organizacion", organizacion);
		
		CtrlRestService itemReturn = new CtrlRestService();
		String response = procesarRestFULL((cfg.getValor() + ConstPath.MAP_REQUISICION_PRODUCTO_BY_COTIZACION), gson.toJson(tranferParam));
		if(!response.contains(ConstAtributos.ERROR_EXCEPTION)){
			itemReturn = createResponseToListRequisicionProducto(response);
		} else
			itemReturn.setResponse(response);
		return itemReturn;
	}
	
	
}
