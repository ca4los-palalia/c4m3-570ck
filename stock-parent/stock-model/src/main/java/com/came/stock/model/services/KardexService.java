package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.KardexDAO;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;

@Service
public class KardexService {
	@Autowired
	private KardexDAO kardexDAO;

	public void save(Kardex kardex) throws DataAccessException {
		kardexDAO.save(kardex);
	}

	public void delete(Kardex kardex) throws DataAccessException {
		kardexDAO.delete(kardex);
	}

	public Kardex getById(Long idKardex, Organizacion organizacion) throws DataAccessException {
		return kardexDAO.getById(idKardex, organizacion);
	}

	public List<Kardex> getAll(Organizacion organizacion) throws DataAccessException {
		return kardexDAO.getAll(organizacion);
	}
	public List<Kardex> getByEstatus(EstatusRequisicion estatus, Organizacion organizacion) throws DataAccessException {
		return kardexDAO.getByEstatus(estatus, organizacion);
	}
	
	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus, Organizacion organizacion) throws DataAccessException {
		return kardexDAO.getByKardexProveedorEstatus(kardexProveedor, estatus, organizacion);
	}
	public List<Kardex> getByProducto(Producto producto, Organizacion organizacion) throws DataAccessException {
		return kardexDAO.getByProducto(producto, organizacion);
	}
	
	public List<Producto> getAllProductosNoRepetidos(Organizacion organizacion){
		return kardexDAO.getAllProductosNoRepetidos(organizacion);
	}
	
	public List<Kardex> getKardexOrderByDateMasReciente(List<Long> listaDesordenada, Organizacion organizacion){
		return kardexDAO.getKardexOrderByDateMasReciente(listaDesordenada, organizacion);
	}
}
