package com.came.stock.model.dao;

import java.util.Calendar;
import java.util.List;

import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.OrdenCompra;

public abstract interface OrdenCompraDAO {
	public abstract void save(OrdenCompra paramOrdenCompra);

	public abstract void delete(OrdenCompra paramOrdenCompra);

	public abstract OrdenCompra getById(Long paramLong);

	public abstract List<OrdenCompra> getAll();

	public abstract List<OrdenCompra> getByCotizacion(Cotizacion paramCotizacion);

	public abstract OrdenCompra getByCodigo(String paramString);

	public abstract List<OrdenCompra> getByFechaOrden(Calendar paramCalendar);

	public abstract String getCodigoDeOrden();

	public abstract List<OrdenCompra> getOrdenesByEstatusAndFolioOr(String paramString,
			List<EstatusRequisicion> paramList);
}
