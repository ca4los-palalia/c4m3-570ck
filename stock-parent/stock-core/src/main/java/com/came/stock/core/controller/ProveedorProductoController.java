package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProveedorProductoController extends ServicesCore {
	
	@PostMapping({ ConstPath.MAP_UNIDAD_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(ProveedorProducto itemObject) {
		//itemReturn = createResponseToProveedorProducto(response);
		return "";
	}
	
	@PostMapping({ ConstPath.MAP_UNIDAD_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(ProveedorProducto itemObject) 
		//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder) {
		//itemReturn = createResponseToListProveedorProducto(response);
				return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {
		//itemReturn = createResponseToProveedorProducto(response);
				return "";
	}

@PostMapping({ ConstPath.MAP_PROVEEDOR_PRODUCTO_BY_PROVEEDOR })
@Produces("application/json")
public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProveedor(Proveedor proveedor) {
	//itemReturn = createResponseToListProveedorProducto(response);
			return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_PRODUCTO_BY_PRODUCTO })
	@Produces("application/json")
	public String getByProducto(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProducto(Producto producto) {
		//itemReturn = createResponseToListProveedorProducto(response);
				return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_PRODUCTO_BY_PRODUCTO_PROVEEDOR })
	@Produces("application/json")
	public String getByProductoProveedor(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByProductoProveedor(Producto producto, Proveedor proveedor) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("producto", producto);
//		tranferParam.put("proveedor", proveedor);

		//itemReturn = createResponseToListProveedorProducto(response);
		return "";
	}
}
