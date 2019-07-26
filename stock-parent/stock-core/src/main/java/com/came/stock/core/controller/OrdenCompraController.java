package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class OrdenCompraController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(OrdenCompra itemObject) {

		// itemReturn = createResponseToOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(OrdenCompra itemObject) {

//itemReturn.setSingle(response);		
		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idFinder", idFinder);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organizacion) {

		// itemReturn = createResponseToListOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_BY_COTIZACION })
	@Produces("application/json")
	public String getByCotizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByCotizacion(Cotizacion cotizacion, Organizacion
		// organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("cotizacion", cotizacion);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_BY_CODIGO })
	@Produces("application/json")
	public String getByCodigo(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByCodigo(String codigo, Organizacion organizacion)
		// {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("codigo", codigo);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_BY_FECHA_ORDEN })
	@Produces("application/json")
	public String getByFechaOrden(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByFechaOrden(Calendar fechaOrden, Organizacion
		// organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("fechaOrden", fechaOrden);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_CODIGO_DE_ORDEN })
	@Produces("application/json")
	public String getCodigoDeOrden(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getCodigoDeOrden() {

		// itemReturn = createResponseToOrdenCompra(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_ORDEN_COMPRA_BYE_STATUS_AND_FOLIO_OR })
	@Produces("application/json")
	public String getByEstatusAndFolioOr(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByEstatusAndFolioOr(String folioOrdenCompra,
		// List<EstatusRequisicion> estatus, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("folioOrdenCompra", folioOrdenCompra);
//		tranferParam.put("estatus", estatus);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListOrdenCompra(response);

		return "";
	}
}
