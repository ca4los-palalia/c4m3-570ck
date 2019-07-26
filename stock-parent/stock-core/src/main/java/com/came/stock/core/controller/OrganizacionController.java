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
import com.came.stock.model.domain.Organizacion;

@RestController
public class OrganizacionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_ORGANIZACION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		organizacionService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		organizacionService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = organizacionService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Organizacion());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_GET_ORGANIZACIONES })
	@Produces("application/json")
	public String getOrganizaciones(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getOrganizaciones();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_GET_COMPANIAS_BY_NOMBRE_RFC })
	@Produces("application/json")
	public String getCompaniasByNombreRFC(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		
		List<Organizacion> list = organizacionService.getCompaniasByNombreRFC(
				String.valueOf(map.get("compania")), 
				String.valueOf(map.get("rfc")));
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_GET_COMPANIAS_BY_NOMBRE })
	@Produces("application/json")
	public String getCompaniasByNombre(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getCompaniasByNombre(jsonItem);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_GET_COMPANIAS_BY_RFC })
	@Produces("application/json")
	public String getCompaniasByRFC(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getCompaniasByRFC(jsonItem);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_ALL_PROVEEDORES })
	@Produces("application/json")
	public String getAllProveedores(@RequestBody String jsonItem) throws ParseException {
		List<Organizacion> list = organizacionService.getAllProveedores();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<Organizacion>());
	}

	@PostMapping({ ConstPath.MAP_ORGANIZACION_COUNT_ROWS })
	@Produces("application/json")
	public String getCountRows(@RequestBody String jsonItem) throws ParseException {
		return organizacionService.getCountRows().toString();
	}
}
