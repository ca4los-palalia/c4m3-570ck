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
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

@RestController
public class AlmacenController extends ServicesCore {

	@PostMapping({ConstPath.MAP_ALMACEN_SAVE})
    @Produces({"application/json"})
	public String save(@RequestBody String jsonItem) throws ParseException {
		Almacen itemProcess = gson.fromJson(jsonItem, Almacen.class);
		almacenService.save(itemProcess);
		return gson.toJson(itemProcess);
	}
	
	@PostMapping({ConstPath.MAP_ALMACEN_DELETE})
    @Produces({"application/json"})
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Almacen itemProcess = gson.fromJson(jsonItem, Almacen.class);
		almacenService.delete(itemProcess);
		return "OK";
	}
	
	@PostMapping({ ConstPath.MAP_ALMACEN_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		Almacen itemProcess = almacenService.getById(new Long(String.valueOf(map.get("idFinder"))),
				((Organizacion) map.get("organizacion")));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Almacen());
	}
	
	@PostMapping({ConstPath.MAP_ALMACEN_ALL})
    @Produces({"application/json"})
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		List<Almacen> list = almacenService.getAll(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Almacen>());
	}
	
	@PostMapping({ConstPath.MAP_ALMACEN_BY_AREA})
    @Produces({"application/json"})
	public String getByArea(@RequestBody String jsonItem) throws ParseException {
		Area itemProcess = gson.fromJson(jsonItem, Area.class);
		List<Almacen> list = almacenService.getByArea(itemProcess);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Almacen>());
	}
}
