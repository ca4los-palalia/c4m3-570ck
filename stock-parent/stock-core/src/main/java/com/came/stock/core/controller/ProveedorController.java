package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProveedorController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PROVEEDOR_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(Proveedor itemObject) {
		// itemReturn = createResponseToProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(Proveedor itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idFinder", idFinder);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organinzacion) {
		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_CONTACTO })
	@Produces("application/json")
	public String getByContacto(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByContacto(Contacto contacto, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("contacto", contacto);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_CONTRATO })
	@Produces("application/json")
	public String getByContrato(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByContrato(Contrato contrato, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("contrato", contrato);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_DIRECCION_DEVOLUCION })
	@Produces("application/json")
	public String getByDireccionDevolucion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByDireccionDevolucion(Direccion direccion, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("direccionDevolucion", direccion);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_DIRECCION_FISCAL })
	@Produces("application/json")
	public String getByDireccionFiscal(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByDireccionFiscal(Direccion direccion, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("direccionFiscal", direccion);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_GERENTE_FINANZAS })
	@Produces("application/json")
	public String getByGerenteFinanzas(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByGerenteFinanzas(Persona persona, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("gerenteFinanzas", persona);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_GERENTE_VENTAS })
	@Produces("application/json")
	public String getByGerenteVentas(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByGerenteVentas(Persona persona, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("gerenteVentas", persona);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_REPRESENTANTE_LEGAL })
	@Produces("application/json")
	public String getByRepresentanteLegal(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByRepresentanteLegal(Persona persona, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("representanteLegal", persona);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_REPRESENTANTE_CLIENTES })
	@Produces("application/json")
	public String getByRepresentanteClientes(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByRepresentanteClientes(Persona persona, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("representanteAteCliente", persona);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_CLAVE_NOMBRE_RFC })
	@Produces("application/json")
	public String getByClaveNombreRfc(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByClaveNombreRfc(String buscarTexto, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("buscarTexto", buscarTexto);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByNombre(String nombre, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("nombre", nombre);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PROVEEDOR_BY_ARRAY_IDS })
	@Produces("application/json")
	public String getByArrayIds(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByArrayIds(List<Long> idsProveedores, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idProveedor", idsProveedores);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListProveedor(response);
		return "";
	}
}
