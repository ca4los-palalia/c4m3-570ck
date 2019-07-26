package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;

public abstract interface RequisicionProductoDAO {
	public abstract void save(RequisicionProducto paramRequisicionProducto);

	public abstract void delete(RequisicionProducto paramRequisicionProducto);

	public abstract RequisicionProducto getById(Long paramLong);

	public abstract List<RequisicionProducto> getByProducto(Producto paramProducto);

	public abstract List<RequisicionProducto> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionProducto> getRequisicionesConEstadoEspecifico(
			EstatusRequisicion paramEstatusRequisicion);

	public abstract List<RequisicionProducto> getByProveedor(Proveedor paramProveedor);

	public abstract List<RequisicionProducto> getByLugar(Lugar paramLugar);

	public abstract List<RequisicionProducto> getAll();

	public abstract List<RequisicionProducto> getAllRequisiciones();

	public abstract List<Proveedor> getAllDistinctByProveedor();

	public abstract List<RequisicionProducto> getByConfiaPartidaGenerica(
			ConffyaPartidaGenerica paramCofiaPartidaGenerica);

	public abstract List<RequisicionProducto> getByCotizacion(Cotizacion paramCotizacion);
}
