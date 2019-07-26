package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConffyaPartidaGenericaDAO;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Organizacion;

@Service
public class ConffyaPartidaGenericaService {
	@Autowired
	private ConffyaPartidaGenericaDAO conffyaPartidaGenericaDAO;

	public void save(ConffyaPartidaGenerica conffyaPartidaGenerica) {
		conffyaPartidaGenericaDAO.save(conffyaPartidaGenerica);
	}

	public void delete(ConffyaPartidaGenerica conffyaPartidaGenerica) {
		conffyaPartidaGenericaDAO.delete(conffyaPartidaGenerica);
	}

	public ConffyaPartidaGenerica getById(Long idConffyaPartidaGenerica) {
		return conffyaPartidaGenericaDAO.getById(idConffyaPartidaGenerica);
	}

	public List<ConffyaPartidaGenerica> getAll() {
		return conffyaPartidaGenericaDAO.getAll();
	}	

	public ConffyaPartidaGenerica getByNombre(String nombre) {
		return conffyaPartidaGenericaDAO.getByNombre(nombre);
	}
	public List<ConffyaPartidaGenerica> getByOrganizacion(Organizacion organizacion) {
		return conffyaPartidaGenericaDAO.getByOrganizacion(organizacion);
	}
	
	public List<ConffyaPartidaGenerica> getAbsolutyAllSqlNative() {
		return conffyaPartidaGenericaDAO.getAbsolutyAllSqlNative();
	}
	
	public List<ConffyaPartidaGenerica> getByClaveIn(List<String> listIn) {
		return conffyaPartidaGenericaDAO.getByClaveIn(listIn);
	}
	
	public int getRowCount() {
		return conffyaPartidaGenericaDAO.getRowCount();
	}
//	public void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion){
//		conffyaPartidaGenericaDAO.updatePartidaGenericaFromConffya(xml, usuario, organizacion);
//	}
}
