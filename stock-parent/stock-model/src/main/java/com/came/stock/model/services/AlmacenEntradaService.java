package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.AlmacenEntradaDAO;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;

@Service
public class AlmacenEntradaService {
	@Autowired
	private AlmacenEntradaDAO almacenEntradaDAO;

	public void save(AlmacenEntrada almacenEntrada) throws DataAccessException {
		almacenEntradaDAO.save(almacenEntrada);
	}

	public void delete(AlmacenEntrada almacenEntrada) throws DataAccessException {
		almacenEntradaDAO.delete(almacenEntrada);
	}

	public AlmacenEntrada getById(Long idAlmacenEntrada, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getById(idAlmacenEntrada, organizacion);
	}

	public List<AlmacenEntrada> getAll(Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getAll(organizacion);
	}
	
	public List<AlmacenEntrada> getByArea(Area area, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getByArea(area, organizacion);
	}
	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getByCotizacion(cotizacion, organizacion);
	}
	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getByOrdenCompra(ordenCompra, organizacion);
	}
	public List<AlmacenEntrada> getByAlmacen(Almacen almacen, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getByAlmacen(almacen, organizacion);
	}
	
	public List<AlmacenEntrada> getByOrdenCompraProductoProveedor(OrdenCompra ordenCompra, Producto producto, Proveedor proveedor, Organizacion organizacion) throws DataAccessException {
		return almacenEntradaDAO.getByOrdenCompraProductoProveedor(ordenCompra, producto, proveedor,organizacion);
	}
}
