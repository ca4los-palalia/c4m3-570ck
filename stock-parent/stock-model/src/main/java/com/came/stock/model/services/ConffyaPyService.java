package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConffyaPyDAO;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Organizacion;

@Service
public class ConffyaPyService {
	@Autowired
	private ConffyaPyDAO conffyaPyDAO;

	public void save(ConffyaPy conffyaPy) {
		conffyaPyDAO.save(conffyaPy);
	}

	public void delete(ConffyaPy conffyaPy) {
		conffyaPyDAO.delete(conffyaPy);
	}

	public ConffyaPy getById(Long idConffyaPy) {
		return conffyaPyDAO.getById(idConffyaPy);
	}

	public List<ConffyaPy> getAll() {
		return conffyaPyDAO.getAll();
	}
	
	public List<ConffyaPy> getByOrganizacion(Organizacion organizacion) {
		return conffyaPyDAO.getByOrganizacion(organizacion);
	}
	
	public List<ConffyaPy> getByClaveIn(List<String> listIn) {
		return conffyaPyDAO.getByClaveIn(listIn);
	}
	
//	public void updateProyectoFromConffya(String xml, Long usuario, Long organizacion){
//		conffyaPyDAO.updateProyectoFromConffya(xml, usuario, organizacion);
//	}
}
