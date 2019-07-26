package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.LabelsModulos;

public abstract interface LabelsModulosDAO {

	public void save(LabelsModulos labelsModulos);

	void delete(LabelsModulos labelsModulos);

	public LabelsModulos getById(Long idLabelsModulos);

	public List<LabelsModulos> getByNombre(String nombre);

	public List<LabelsModulos> getByModulo(String modulo);

	public List<LabelsModulos> getAll();

}
