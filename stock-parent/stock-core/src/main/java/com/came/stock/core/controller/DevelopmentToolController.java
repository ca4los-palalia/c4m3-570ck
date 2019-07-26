package com.came.stock.core.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;
import com.came.stock.model.domain.DevelopmentTool;

@RestController
public class DevelopmentToolController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = gson.fromJson(jsonItem, DevelopmentTool.class);
		developmentToolService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		//DevelopmentTool itemProcess = gson.fromJson(jsonItem, DevelopmentTool.class);
		return "Funcion no presente";
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = developmentToolService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DevelopmentTool());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<DevelopmentTool> list = developmentToolService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<DevelopmentTool>());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_BY_VALUE })
	@Produces("application/json")
	public String getByValue(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = developmentToolService.getByValue(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DevelopmentTool());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_BY_DESCRIPCION })
	@Produces("application/json")
	public String getByDescripcion(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = developmentToolService.getByDescripcion(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DevelopmentTool());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_COUNT_LAYOUTS })
	@Produces("application/json")
	public String getCountLayouts(@RequestBody String jsonItem) throws ParseException {
		List<DevelopmentTool> list = developmentToolService.getCountLayouts();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<DevelopmentTool>());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = developmentToolService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DevelopmentTool());
	}

	@PostMapping({ ConstPath.MAP_DEVELOPMENT_TOOL_BY_BUSSY })
	@Produces("application/json")
	public String getBussy(@RequestBody String jsonItem) throws ParseException {
		DevelopmentTool itemProcess = developmentToolService.getBussy(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new DevelopmentTool());
	}
}
