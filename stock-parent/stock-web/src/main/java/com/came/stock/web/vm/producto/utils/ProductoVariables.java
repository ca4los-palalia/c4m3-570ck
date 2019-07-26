package com.came.stock.web.vm.producto.utils;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.NotifyChange;

import com.came.stock.beans.ClasificacionPrecios;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.web.vm.BasicStructure;
import com.came.stock.web.vm.producto.TabInfo;

public abstract class ProductoVariables extends BasicStructure {
	private static final long serialVersionUID = -6626407102060983517L;
	protected String claveProducto;
	protected String nombreProducto;
	protected boolean hiddeFamilia;
	protected boolean hiddeProveedor;
	protected List<Producto> productoDB;
	protected List<Cotizacion> cotizacionDB;
	protected Producto productoSeleccionado;
	protected List<ClasificacionPrecios> precios;
	protected List<FuncionesModificacion> funcionesModificaciones;
	protected FuncionesModificacion funcionesModificacione;
	protected ClasificacionPrecios precioSelected;
	private Date costoMaximoFechaDate;
	private Date costoReposicionFechaDate;
	private Date costoCapaFechaDate;
	private Date costoUltimoFechaDate;
	protected ModoDeBusqueda modoDeBusqueda;
	protected Integer progressValue;
	protected String progressValueLabel;
	protected TabInfo tabSelected;
	protected Validator productoValidator;
	protected Organizacion organizacion;
	protected boolean selectedTab0;
	protected boolean selectedTab1;
	protected boolean selectedTab2;
	protected boolean selectedTab3;
	protected boolean selectedTab4;

	public String getClaveProducto() {
		return this.claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Producto> getProductoDB() {
		return this.productoDB;
	}

	public void setProductoDB(List<Producto> productoDB) {
		this.productoDB = productoDB;
	}

	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Validator getProductoValidator() {
		return this.productoValidator;
	}

	public List<ProductoTipo> getProductoTipoDB() {
		return this.productoTipoDB;
	}

	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}

	public void setTabSelected(TabInfo tabSelected) {
		this.tabSelected = tabSelected;
	}

	@NotifyChange({ "*" })
	public TabInfo getTabSelected() {
		return this.tabSelected;
	}

	public List<Cotizacion> getCotizacionDB() {
		return this.cotizacionDB;
	}

	public void setCotizacionDB(List<Cotizacion> cotizacionDB) {
		this.cotizacionDB = cotizacionDB;
	}

	public List<ClasificacionPrecios> getPrecios() {
		return this.precios;
	}

	public void setPrecios(List<ClasificacionPrecios> precios) {
		this.precios = precios;
	}

	public ClasificacionPrecios getPrecioSelected() {
		return this.precioSelected;
	}

	public void setPrecioSelected(ClasificacionPrecios precioSelected) {
		this.precioSelected = precioSelected;
	}

	public Date getCostoMaximoFechaDate() {
		return this.costoMaximoFechaDate;
	}

	public void setCostoMaximoFechaDate(Date costoMaximoFechaDate) {
		this.costoMaximoFechaDate = costoMaximoFechaDate;
	}

	public Date getCostoReposicionFechaDate() {
		return this.costoReposicionFechaDate;
	}

	public void setCostoReposicionFechaDate(Date costoReposicionFechaDate) {
		this.costoReposicionFechaDate = costoReposicionFechaDate;
	}

	public Date getCostoCapaFechaDate() {
		return this.costoCapaFechaDate;
	}

	public void setCostoCapaFechaDate(Date costoCapaFechaDate) {
		this.costoCapaFechaDate = costoCapaFechaDate;
	}

	public Date getCostoUltimoFechaDate() {
		return this.costoUltimoFechaDate;
	}

	public void setCostoUltimoFechaDate(Date costoUltimoFechaDate) {
		this.costoUltimoFechaDate = costoUltimoFechaDate;
	}
	
	public boolean isHiddeFamilia() {
		return this.hiddeFamilia;
	}

	public void setHiddeFamilia(boolean hiddeFamilia) {
		this.hiddeFamilia = hiddeFamilia;
	}

	public boolean isHiddeProveedor() {
		return this.hiddeProveedor;
	}

	public void setHiddeProveedor(boolean hiddeProveedor) {
		this.hiddeProveedor = hiddeProveedor;
	}

	public List<FuncionesModificacion> getFuncionesModificaciones() {
		return this.funcionesModificaciones;
	}

	public void setFuncionesModificaciones(List<FuncionesModificacion> funcionesModificaciones) {
		this.funcionesModificaciones = funcionesModificaciones;
	}

	public FuncionesModificacion getFuncionesModificacione() {
		return this.funcionesModificacione;
	}

	public void setFuncionesModificacione(FuncionesModificacion funcionesModificacione) {
		this.funcionesModificacione = funcionesModificacione;
	}

	public ModoDeBusqueda getModoDeBusqueda() {
		return this.modoDeBusqueda;
	}

	public void setModoDeBusqueda(ModoDeBusqueda modoDeBusqueda) {
		this.modoDeBusqueda = modoDeBusqueda;
	}

	public Integer getProgressValue() {
		return this.progressValue;
	}

	public void setProgressValue(Integer progressValue) {
		this.progressValue = progressValue;
	}

	public String getProgressValueLabel() {
		return this.progressValueLabel;
	}

	public void setProgressValueLabel(String progressValueLabel) {
		this.progressValueLabel = progressValueLabel;
	}
}
