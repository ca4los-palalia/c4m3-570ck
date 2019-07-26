package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConffyaFuenteFinanciamientoDAO;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.Organizacion;

@Service
public class ConffyaFuenteFinanciamientoService {
	@Autowired
	private ConffyaFuenteFinanciamientoDAO conffyaFinanciamientoDAO;

	public void save(ConffyaFuenteFinanciamiento conffyaFinanciamiento) {
		conffyaFinanciamientoDAO.save(conffyaFinanciamiento);
	}

	public void delete(ConffyaFuenteFinanciamiento conffyaFinanciamiento) {
		conffyaFinanciamientoDAO.delete(conffyaFinanciamiento);
	}

	public ConffyaFuenteFinanciamiento getById(Long idConffyaFuenteFinanciamiento) {
		return conffyaFinanciamientoDAO.getById(idConffyaFuenteFinanciamiento);
	}

	public List<ConffyaFuenteFinanciamiento> getAll() {
		return conffyaFinanciamientoDAO.getAll();
	}
	public List<ConffyaFuenteFinanciamiento> getByOrganizacion(Organizacion organizacion) {
		return conffyaFinanciamientoDAO.getByOrganizacion(organizacion);
	}
	
	public List<ConffyaFuenteFinanciamiento> getByClaveIn(List<String> listIn) {
		return conffyaFinanciamientoDAO.getByClaveIn(listIn);
	}
//	public void updateFuenteFinanciamientoFromConffya(String xml, Long usuario, Long organizacion){
//		conffyaFinanciamientoDAO.updateFuenteFinanciamientoFromConffya(xml, usuario, organizacion);
//	}
	
}
