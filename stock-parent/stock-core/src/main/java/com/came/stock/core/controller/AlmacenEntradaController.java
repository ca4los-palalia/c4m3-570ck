package com.came.stock.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;

@RestController
public class AlmacenEntradaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		AlmacenEntrada itemProcess = gson.fromJson(jsonItem, AlmacenEntrada.class);
		almacenEntradaService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		AlmacenEntrada itemProcess = gson.fromJson(jsonItem, AlmacenEntrada.class);
		almacenEntradaService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		AlmacenEntrada itemProcess = almacenEntradaService.getById(new Long(String.valueOf(map.get("idFinder"))),
				((Organizacion) map.get("organizacion")));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new AlmacenEntrada());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<AlmacenEntrada> list = almacenEntradaService.getAll(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_AREA })
	@Produces("application/json")
	public String getByArea(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		List<AlmacenEntrada> list = almacenEntradaService.getByArea(((Area) map.get("area")),
				((Organizacion) map.get("organizacion")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_COTIZACION })
	@Produces("application/json")
	public String getByCotizacion(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		List<AlmacenEntrada> list = almacenEntradaService.getByCotizacion(((Cotizacion) map.get("cotizacion")),
				((Organizacion) map.get("organizacion")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_ORDEN_COMPRA })
	@Produces("application/json")
	public String getByOrdenCompra(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		List<AlmacenEntrada> list = almacenEntradaService.getByOrdenCompra(((OrdenCompra) map.get("ordenCompra")),
				((Organizacion) map.get("organizacion")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_ALMACEN })
	@Produces("application/json")
	public String getByAlmacen(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		List<AlmacenEntrada> list = almacenEntradaService.getByAlmacen(((Almacen) map.get("almacen")),
				((Organizacion) map.get("organizacion")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}

	@PostMapping({ ConstPath.MAP_ALMACEN_ENTRADA_BY_ORDEN_COMPRA_PRODUCTO_PROVEEDOR })
	@Produces("application/json")
	public String getByOrdenCompraProductoProveedor(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		List<AlmacenEntrada> list = almacenEntradaService.getByOrdenCompraProductoProveedor(
				((OrdenCompra) map.get("ordenCompra")),
				((Producto) map.get("producto")),
				((Proveedor) map.get("proveedor")),
				((Organizacion) map.get("organizacion")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<AlmacenEntrada>());
	}
}
