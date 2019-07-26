package com.came.stock.web.vm.requisicion;

import java.util.Calendar;

import org.zkoss.bind.annotation.Init;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;

public class ControlMetaclass extends ControlVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		
	}

	public void initProperties() {
		
	}


	
	public AlmacenEntrada crearAlmacenEntradaVacia(Cotizacion cotiz, OrdenCompra ordCompra) {
		AlmacenEntrada objeto = new AlmacenEntrada();
		objeto.setFechaEntrada(Calendar.getInstance());
		objeto.setArea(new Area());
		objeto.setAlmacen(new Almacen());
		objeto.setCotizacion(cotiz);
		objeto.setActivarCantidad(true);
		objeto.setOrdenCompra(ordCompra);
		if(cotiz != null && cotiz.getProducto() != null)
			objeto.setProducto(cotiz.getProducto());
		objeto.setAreas(areas);
		return objeto;
	}	

}
