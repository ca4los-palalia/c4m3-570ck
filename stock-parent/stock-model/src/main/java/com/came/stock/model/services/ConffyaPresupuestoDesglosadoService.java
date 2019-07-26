package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConffyaPresupuestoDesglosadoDAO;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.Organizacion;

@Service
public class ConffyaPresupuestoDesglosadoService {
	@Autowired
	private ConffyaPresupuestoDesglosadoDAO conffyaPresupuestoDesglosadoDAO;

	public void save(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado) {
		conffyaPresupuestoDesglosadoDAO.save(conffyaPresupuestoDesglosado);
	}

	public void delete(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado) {
		conffyaPresupuestoDesglosadoDAO.delete(conffyaPresupuestoDesglosado);
	}

	public ConffyaPresupuestoDesglosado getById(Long idConffyaPresupuestoDesglosado) {
		return conffyaPresupuestoDesglosadoDAO.getById(idConffyaPresupuestoDesglosado);
	}

	public List<ConffyaPresupuestoDesglosado> getAll() {
		return conffyaPresupuestoDesglosadoDAO.getAll();
	}
	
	public List<ConffyaPresupuestoDesglosado> getByOrganizacion(Organizacion organizacion) {
		return conffyaPresupuestoDesglosadoDAO.getByOrganizacion(organizacion);
	}
	
	public List<ConffyaPresupuestoDesglosado> getByUr(String ur) {
		return conffyaPresupuestoDesglosadoDAO.getByUr(ur);
	}
	
	public Integer getCountRows(){
		return conffyaPresupuestoDesglosadoDAO.getCountRows();
	}
	
	public List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltrado(String ur, String programa, String proyecto, String fuenteFinanciamiento) {
		return conffyaPresupuestoDesglosadoDAO.getPartidaGenericaFiltrado(ur, programa, proyecto, fuenteFinanciamiento);
	}
	public List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltradoConPartidaGenerica(String ur, String programa, String proyecto, String fuenteFinanciamiento, String partidaGenerica) {
		return conffyaPresupuestoDesglosadoDAO.getPartidaGenericaFiltradoConPartidaGenerica(ur, programa, proyecto, fuenteFinanciamiento, partidaGenerica);
	}
//	public void updatePresupuestoFromConffya(String xml, Long usuario, Long organizacion){
//		conffyaPresupuestoDesglosadoDAO.updatePresupuestoFromConffya(xml, usuario, organizacion);
//	}
}
