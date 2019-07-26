package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class TelefonoController extends ServicesCore {
	
	@PostMapping({ConstPath.MAP_TELEFONO_SAVE})
    @Produces({"application/json"})
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(Telefono itemObject) {
		return "";
	}

	@PostMapping({ConstPath.MAP_TELEFONO_DELETE})
    @Produces({"application/json"})
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(Telefono itemObject) {
		return "";
	}

	
	@PostMapping({ConstPath.MAP_TELEFONO_BY_ID})
    @Produces({"application/json"})
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idTelefono) {
		return "";
	}

	@PostMapping({ConstPath.MAP_TELEFONO_ALL})
    @Produces({"application/json"})
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {
		return "";
	}

	@PostMapping({ConstPath.MAP_TELEFONO_ULTIMO_REGISTRO_EMAIL})
    @Produces({"application/json"})
	public String getUltimoregistroEmail(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUltimoregistroEmail() {
		return "";
	}
	
	
	
	
	
	
	
}
