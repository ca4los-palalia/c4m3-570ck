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
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Usuarios;

@RestController
public class ProveedorUsuarioController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		ProveedorUsuario itemProcess = gson.fromJson(jsonItem, ProveedorUsuario.class);
		proveedorUsuarioService.save(itemProcess);
		return gson.toJson(itemProcess);
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		ProveedorUsuario itemProcess = gson.fromJson(jsonItem, ProveedorUsuario.class);
		proveedorUsuarioService.delete(itemProcess);
		return "OK";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		ProveedorUsuario itemProcess = proveedorUsuarioService.getById(new Long(jsonItem));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new ProveedorUsuario());
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		List<ProveedorUsuario> list = proveedorUsuarioService.getAll();
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ProveedorUsuario>());
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_BY_PROVEEDOR })
	@Produces("application/json")
	public String getByProveedor(@RequestBody String jsonItem) throws ParseException {
		Proveedor itemProcess = gson.fromJson(jsonItem, Proveedor.class);
		ProveedorUsuario proveedorUsuario = proveedorUsuarioService.getByProveedor(itemProcess);
		return proveedorUsuario != null ? gson.toJson(proveedorUsuario) : gson.toJson(new ProveedorUsuario());
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_BY_USUARIO })
	@Produces("application/json")
	public String getByUsuario(@RequestBody String jsonItem) throws ParseException {
		Usuarios itemProcess = gson.fromJson(jsonItem, Usuarios.class);
		ProveedorUsuario proveedorUsuario = proveedorUsuarioService.getByUsuario(itemProcess);
		return proveedorUsuario != null ? gson.toJson(proveedorUsuario) : gson.toJson(new ProveedorUsuario());
	}
	
	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_LIKE_NOMBRE })
	@Produces("application/json")
	public String getLikeNombre(@RequestBody String jsonItem) throws ParseException {
		List<ProveedorUsuario> list = proveedorUsuarioService.getLikeNombre(jsonItem);
		return list != null ? gson.toJson(list) : gson.toJson(new ArrayList<ProveedorUsuario>());
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		ProveedorUsuario itemProcess = proveedorUsuarioService.getByNombre(jsonItem);
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new ProveedorUsuario());
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_USUARIO_ACCESO })
	@Produces("application/json")
	public String getAcceso(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		ProveedorUsuario itemProcess = proveedorUsuarioService.getAcceso(
				String.valueOf(map.get("usuario")), 
				String.valueOf(map.get("contrasena")));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new ProveedorUsuario());
	}
}
