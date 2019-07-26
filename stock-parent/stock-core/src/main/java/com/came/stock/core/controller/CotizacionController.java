package com.came.stock.core.controller;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Produces;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Proveedor;

@RestController
public class CotizacionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_COTIZACION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Cotizacion itemObject) {
		// itemReturn = createResponseToCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Cotizacion itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("idFinder", idFinder);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_FECHA_ENVIO_COTIZACION })
	@Produces("application/json")
	public String getByFechaEnvioCotizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByFechaEnvioCotizacion(Calendar fechaEnvioSolucion,
		// Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("fechaEnvioSolucion", fechaEnvioSolucion);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_FECHA_RESOLICION })
	@Produces("application/json")
	public String getByFechaResolicion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByFechaResolicion(Calendar fechaResolucion) {

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_STATUS })
	@Produces("application/json")
	public String getByStatus(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByStatus(Integer status) {

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_PROVEEDOR })
	@Produces("application/json")
	public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProveedor(Proveedor proveedor, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("proveedor", proveedor);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_REQUISICION })
	@Produces("application/json")
	public String getByRequisicion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByRequisicion(Requisicion requisicion, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("requisicion", requisicion);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_TOP_COMPRAS })
	@Produces("application/json")
	public String getTopCompras(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getTopCompras(Organizacion organizacion) {

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_COUNT_ROWS })
	@Produces("application/json")
	public String countRows(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService countRows(Organizacion organizacion) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_FOLIO })
	@Produces("application/json")
	public String getByFolio(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByFolio(String folioCotizacion, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("folioCotizacion", folioCotizacion);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_ESTATUS_REQUISICION_AND_FOLIO_OR_PROVEEDOR_BY_FOLIO })
	@Produces("application/json")
	public String getByEstatusRequisicionAndFolioOrProveedorByFolio(@RequestBody String jsonItem)
			throws ParseException {
		// public CtrlRestService getByEstatusRequisicionAndFolioOrProveedorByFolio(
		// String folioCotizacion, List<Proveedor> profolioCotizacionveedores,
		// List<EstatusRequisicion> estatus, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("folioCotizacion", folioCotizacion);
		// tranferParam.put("profolioCotizacionveedores", profolioCotizacionveedores);
		// tranferParam.put("estatus", estatus);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_REQUISICION_PROVEEDOR_AND_PRODUCTO })
	@Produces("application/json")
	public String getByRequisicionProveedorAndProducto(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByRequisicionProveedorAndProducto( Requisicion
		// requisicion, Proveedor proveedor, Producto producto, Organizacion
		// organizacion) {

		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("requisicion", requisicion);
		// tranferParam.put("proveedor", proveedor);
		// tranferParam.put("producto", producto);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToCotizacion(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_COTIZACION_BY_PROVEEDOR_FOLIO_COTIZACION_NUEVA })
	@Produces("application/json")
	public String getByProveedorFolioCotizacionNueva(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByProveedorFolioCotizacionNueva(Proveedor
		// proveedor, String folio, EstatusRequisicion estatusRequisicion, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("proveedor", proveedor);
		// tranferParam.put("folio", folio);
		// tranferParam.put("estatusRequisicion", estatusRequisicion);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListCotizacion(response);
		return "";
	}
	
}
