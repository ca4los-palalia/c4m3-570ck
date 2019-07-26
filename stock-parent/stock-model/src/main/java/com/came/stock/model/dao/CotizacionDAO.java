package com.came.stock.model.dao;

import java.util.Calendar;
import java.util.List;

import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;

public abstract interface CotizacionDAO {
	public abstract void save(Cotizacion paramCotizacion);

	public abstract void delete(Cotizacion paramCotizacion);

	public abstract Cotizacion getById(Long paramLong);

	public abstract List<Cotizacion> getAll();

	public abstract List<Cotizacion> getByFechaEnvioCotizacion(Calendar paramCalendar);

	public abstract List<Cotizacion> getByFechaResolicion(Calendar paramCalendar);

	public abstract List<Cotizacion> getByStatus(Integer paramInteger);

	public abstract List<Cotizacion> getByProveedor(Proveedor paramProveedor);

	public abstract List<Cotizacion> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<Cotizacion> getTopCompras();

	public abstract Long getCountRowsCotizacion();

	public abstract Cotizacion getCotizacionByFolio(String paramString);

	public abstract List<Cotizacion> getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(String paramString,
			List<Proveedor> paramList, List<EstatusRequisicion> paramList1);

	public abstract Cotizacion getCotizacionByRequisicionProveedorAndProducto(Requisicion paramRequisicion,
			Proveedor paramProveedor, Producto paramProducto);

	public abstract List<Cotizacion> getByProveedorFolioCotizacionNueva(Proveedor paramProveedor, String paramString,
			EstatusRequisicion paramEstatusRequisicion);
}
