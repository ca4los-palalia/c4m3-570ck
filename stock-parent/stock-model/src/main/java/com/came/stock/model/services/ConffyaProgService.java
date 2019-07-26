package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConffyaProgDAO;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.Organizacion;

@Service
public class ConffyaProgService {
	@Autowired
	private ConffyaProgDAO conffyaProgDAO;

	public void save(ConffyaProg conffyaProg) {
		conffyaProgDAO.save(conffyaProg);
	}

	public void delete(ConffyaProg conffyaProg) {
		conffyaProgDAO.delete(conffyaProg);
	}

	public ConffyaProg getById(Long idConffyaProg) {
		return conffyaProgDAO.getById(idConffyaProg);
	}

	public List<ConffyaProg> getAll() {
		return conffyaProgDAO.getAll();
	}

	public List<ConffyaProg> getByOrganizacion(Organizacion organizacion) {
		return conffyaProgDAO.getByOrganizacion(organizacion);
	}

	public List<ConffyaProg> getByClaveIn(List<String> listIn) {
		return conffyaProgDAO.getByClaveIn(listIn);
	}

//	public void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion) {
//		conffyaProgDAO.updatePartidaGenericaFromConffya(xml, usuario, organizacion);
//	}
}
