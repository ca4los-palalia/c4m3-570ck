package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.RequisicionProveedorDAO;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProveedor;

@Repository
public class RequisicionProveedorDAOImpl extends DatabaseMetaclass implements RequisicionProveedorDAO {
	public void save(RequisicionProveedor requisicionProveedor) {
	}

	public void update(RequisicionProveedor requisicionProveedor) {
	}

	public void delete(RequisicionProveedor requisicionProveedor) {
	}

	public RequisicionProveedor getById(Long idRequisicionProveedor) {
		return null;
	}

	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion) {
		return null;
	}

	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor) {
		return null;
	}

	public List<RequisicionProveedor> getAll() {
		return null;
	}
}
