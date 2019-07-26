package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class RequisicionProductoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(RequisicionProducto itemObject) {
		// itemReturn = createResponseToRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(RequisicionProducto itemObject) {

//		itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idRequisicionProducto, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idRequisionProducto", idRequisicionProducto);
//		tranferParam.put("organizacion", organizacion);
		// itemReturn = createResponseToRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProducto(Producto itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("producto", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_REQUISICION })
	@Produces("application/json")
	public String getByRequisicion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByRequisicion(Requisicion itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("requisicion", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_PROVEEDOR })
	@Produces("application/json")
	public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProveedor(Proveedor itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("proveedor", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_LUGAR })
	@Produces("application/json")
	public String getByLugar(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByLugar(Lugar itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("lugar", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_ALL_REQUISICIONES })
	@Produces("application/json")
	public String getAllRequisiciones(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAllRequisiciones(Organizacion organizacion) {
		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_REQUISICIONES_CON_ESTADO_ESPECIFICO })
	@Produces("application/json")
	public String getRequisicionesConEstadoEspecifico(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getRequisicionesConEstadoEspecifico(EstatusRequisicion itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("estatusRequisicion", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_ALL_DISTINCT_BY_PROVEEDOR })
	@Produces("application/json")
	public String getAllDistinctByProveedor(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAllDistinctByProveedor(Organizacion organizacion) {
		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_CONFIA_PARTIDA_GENERICA })
	@Produces("application/json")
	public String getByConfiaPartidaGenerica(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByConfiaPartidaGenerica(ConffyaPartidaGenerica itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("cofiaPartidaGenerica", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_REQUISICION_PRODUCTO_BY_COTIZACION })
	@Produces("application/json")
	public String getByCotizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByCotizacion(Cotizacion itemObject, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("cotizacion", itemObject);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListRequisicionProducto(response);
		return "";
	}

}
