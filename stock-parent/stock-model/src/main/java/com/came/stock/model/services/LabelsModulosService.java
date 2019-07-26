package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.LabelsModulosDAO;
import com.came.stock.model.domain.LabelsModulos;

@Service
public class LabelsModulosService {
	@Autowired
	private LabelsModulosDAO labelsModulosDAO;

	public void save(LabelsModulos labelsModulos) throws DataAccessException {
		labelsModulosDAO.save(labelsModulos);
	}

	public void delete(LabelsModulos labelsModulos) throws DataAccessException {
		labelsModulosDAO.delete(labelsModulos);
	}

	public LabelsModulos getById(Long idLabelsModulos) throws DataAccessException {
		return labelsModulosDAO.getById(idLabelsModulos);
	}

	public List<LabelsModulos> getByNombre(String nombre){
		return labelsModulosDAO.getByNombre(nombre);
	}
	public List<LabelsModulos> getAll(){
		return labelsModulosDAO.getAll();
	}
	
	public List<LabelsModulos> getByModulo(String modulo){
		return labelsModulosDAO.getByModulo(modulo);
	}
}
