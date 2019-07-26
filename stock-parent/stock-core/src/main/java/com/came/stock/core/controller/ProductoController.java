package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ProductoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRODUCTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(Producto itemObject) {
		//itemReturn = createResponseToProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService c(Producto itemObject) {
		//itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idFinder", idFinder);
//		tranferParam.put("organizacion", organizacion);

		//itemReturn = createResponseToProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getAll(Organizacion organizacion) {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

@PostMapping({ ConstPath.MAP_PRODUCTO_ITEM_BY_KEY_OR_NAME })
@Produces("application/json")
public String getItemByKeyOrName(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getItemByKeyOrName(String claveProducto, String nombreProducto, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("claveProducto", claveProducto);
//		tranferParam.put("nombreProducto", nombreProducto);
//		tranferParam.put("organizacion", organizacion);

	//itemReturn = createResponseToListProducto(response);
	return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_ALL_KEYS })
	@Produces("application/json")
	public String getAllKeys(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getAllKeys() {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_CLAVE_NOMBRE })
	@Produces("application/json")
	public String getByClaveNombre(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getByClaveNombre(String buscarTexto, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("buscarTexto", buscarTexto);
//		tranferParam.put("organizacion", organizacion);


		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_PRECIOS_MAXIMOS })
	@Produces("application/json")
	public String getPreciosMaximos(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getPreciosMaximos() {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_PRECIOS_MINIMOS })
	@Produces("application/json")
	public String getPreciosMinimos(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getPreciosMinimos() {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_PRECIOS_PROMEDIO })
	@Produces("application/json")
	public String getPreciosPromedio(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getPreciosPromedio() {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_PRECIO })
	@Produces("application/json")
	public String getByPrecio(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getByPrecio(String precio) {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_CLAVE_NOMBRE_PRECIO_COSTO })
	@Produces("application/json")
	public String getByClaveNombrePrecioCosto(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getByClaveNombrePrecioCosto(String buscarTexto) {

		//itemReturn = createResponseToProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_CLAVE })
	@Produces("application/json")
	public String getByClave(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getByClave(String clave, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("clave", clave);
//		tranferParam.put("organizacion", organizacion);

		//itemReturn = createResponseToProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_ALL_LIMITED })
	@Produces("application/json")
	public String getAllLimited(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getAllLimited(Organizacion organizacion) {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_ALL_NATIVE_SQL })
	@Produces("application/json")
	public String getAllNativeSQL(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getAllNativeSQL(Organizacion organizacion) {

		//itemReturn = createResponseToListProducto(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRODUCTO_BY_CLAVE_PARTIDA_GENERICA })
	@Produces("application/json")
	public String getByClavePartidaGenerica(@RequestBody String jsonItem) throws ParseException {
	//public CtrlRestService getByClavePartidaGenerica(ConffyaPartidaGenerica conffyaPartidaGenerica, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("conffyaPartidaGenerica", conffyaPartidaGenerica);
//		tranferParam.put("organizacion", organizacion);

		//itemReturn = createResponseToListProducto(response);
		return "";
	}
}
