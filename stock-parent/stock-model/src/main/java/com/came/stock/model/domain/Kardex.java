package com.came.stock.model.domain;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.came.stock.utilidades.StockUtils;

@Entity
@Table
public class Kardex {
	private Long idKardex;
	private Date fechaEntrada;
	
	
	private String factura;
	private Integer entradaCantidad;
	private Integer entradaLote;
	private Integer salidaCantidad;
	private Integer salidaLote;
	private Integer existenciaCantidad;
	private Integer existenciaLote;
	
	private Float costoPromedio;
	private Float debe;
	private Float haber;
	private Float saldo;
	private Float precioUnitario;
	private String precioUnitarioString;
	private String debeString;
	private String haberString;
	private String saldoString;
	
	private boolean botonBuscarProducto;
	private String icon;
	
	private Usuarios usuario;
	private Organizacion organizacion;
	private EstatusRequisicion estatusRequisicion;
	private Producto producto;
	private KardexProveedor kardexProveedor;
	private boolean conf;
	
	private boolean entradaReadOnly;
	private boolean entradaEditVisible;
	private boolean entradaLoteReadOnly;
	private boolean entradaLoteEditVisible;
	private boolean salidaReadOnly;
	private boolean salidaCantidadEditVisible;
	private boolean salidaLoteReadOnly;
	private boolean salidaLoteEditVisible;
	
	private boolean enableSaveBotton;
	private boolean devolucionVenta;
	private boolean devolucionEntrada;
	private String salidaId;
	private String costoPromedioString;
	private String typeFormat;
	private NumberFormat nf;
	private Integer uepsRestan;
	private Long referenciaKardex;
	private Long idKardexClonacion;
	
	
	private List<AlmacenEntrada> almacenEntradaList;
	
	
	public Kardex() {
		inicializarRegion();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdKardex() {
		return this.idKardex;
	}
	public void setIdKardex(Long idKardex) {
		this.idKardex = idKardex;
	}
	
	@Column
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	
					
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	
	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Column
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	
	@Column
	public Integer getEntradaCantidad() {
		if(entradaCantidad != null)
			entradaReadOnly = true;
		else
			entradaEditVisible = true;
		return entradaCantidad;
	}
	public void setEntradaCantidad(Integer entradaCantidad) {
		this.entradaCantidad = entradaCantidad;
	}
	
	@Column
	public Integer getEntradaLote() {
		if(entradaLote != null)
			entradaLoteReadOnly = true;
		else
			entradaLoteEditVisible = true;
		return entradaLote;
	}
	public void setEntradaLote(Integer entradaLote) {
		this.entradaLote = entradaLote;
	}
	
	@Column
	public Integer getSalidaCantidad() {
		if(salidaCantidad != null)
			salidaReadOnly = true;
		else
			salidaCantidadEditVisible = true;
		return salidaCantidad;
	}
	public void setSalidaCantidad(Integer salidaCantidad) {
		this.salidaCantidad = salidaCantidad;
	}
	
	@Column
	public Integer getSalidaLote() {
		if(salidaLote != null)
			salidaLoteReadOnly = true;
		else
			salidaLoteEditVisible = true;
		return salidaLote;
	}
	public void setSalidaLote(Integer salidaLote) {
		this.salidaLote = salidaLote;
	}
	
	@Column
	public Integer getExistenciaCantidad() {
		return existenciaCantidad;
	}
	public void setExistenciaCantidad(Integer existenciaCantidad) {
		this.existenciaCantidad = existenciaCantidad;
	}
	
	@Column
	public Integer getExistenciaLote() {
		return existenciaLote;
	}
	public void setExistenciaLote(Integer existenciaLote) {
		this.existenciaLote = existenciaLote;
	}
	
	@Column
	public Float getCostoPromedio() {
		if(costoPromedio != null)
			costoPromedioString = nf.format(costoPromedio);
		return costoPromedio;
	}
	public void setCostoPromedio(Float costoPromedio) {
		if(costoPromedio != null)
			costoPromedioString = nf.format(costoPromedio);
		this.costoPromedio = costoPromedio;
	}
	
	@Column
	public Float getDebe() {
		if(debe != null)
			debeString = nf.format(debe);
		return debe;
	}
	public void setDebe(Float debe) {
		if(debe != null)
			debeString = nf.format(debe);
		this.debe = debe;
	}
	
	@Column
	public Float getHaber() {
		if(haber != null)
			haberString = nf.format(haber);
		return haber;
	}
	public void setHaber(Float haber) {
		if(haber != null)
			haberString = nf.format(haber);
		this.haber = haber;
	}
	
	@Column
	public Float getSaldo() {
		if(saldo != null)
			saldoString = nf.format(saldo);
		return saldo;
	}
	public void setSaldo(Float saldo) {
		if(saldo != null)
			saldoString = nf.format(saldo);	
		this.saldo = saldo;
	}
	
	@Transient
	public boolean isBotonBuscarProducto() {
		return botonBuscarProducto;
	}
	public void setBotonBuscarProducto(boolean botonBuscarProducto) {
		this.botonBuscarProducto = botonBuscarProducto;
	}
	
	@Column
	public String getIcon() {
		if(icon != null)
			try {
				String des = new StockUtils().Desencriptar(icon);
				if(!des.equals(""))
					icon = des;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@OneToOne
	@JoinColumn(name = "estatusRequisicion")
	public EstatusRequisicion getEstatusRequisicion() {
		return this.estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}
	
	@OneToOne
	@JoinColumn(name = "kardexProveedor")
	public KardexProveedor getKardexProveedor() {
		return kardexProveedor;
	}
	public void setKardexProveedor(KardexProveedor kardexProveedor) {
		this.kardexProveedor = kardexProveedor;
	}
	@Transient
	public List<AlmacenEntrada> getAlmacenEntradaList() {
		return almacenEntradaList;
	}

	public void setAlmacenEntradaList(List<AlmacenEntrada> almacenEntradaList) {
		this.almacenEntradaList = almacenEntradaList;
	}
	
	@Column
	public boolean isConf() {
		return conf;
	}
	public void setConf(boolean conf) {
		this.conf = conf;
	}
	@Transient
	public String getDebeString() {
		return debeString;
	}
	public void setDebeString(String debeString) {
		this.debeString = debeString;
	}
	@Transient
	public String getHaberString() {
		return haberString;
	}
	public void setHaberString(String haberString) {
		this.haberString = haberString;
	}
	@Transient
	public String getSaldoString() {
		return saldoString;
	}
	public void setSaldoString(String saldoString) {
		this.saldoString = saldoString;
	}
	
	
	@Column
	public Float getPrecioUnitario() {
		if(precioUnitario != null)
			precioUnitarioString = nf.format(precioUnitario);
		return precioUnitario;
	}

	public void setPrecioUnitario(Float precioUnitario) {
		if(precioUnitario != null)
			precioUnitarioString = nf.format(precioUnitario);
		this.precioUnitario = precioUnitario;
	}

	@Transient
	public String getPrecioUnitarioString() {
		if(precioUnitario != null)
			precioUnitarioString = nf.format(precioUnitario);
		return precioUnitarioString;
	}

	public void setPrecioUnitarioString(String precioUnitarioString) {
		this.precioUnitarioString = precioUnitarioString;
	}

	@Transient
	public String getCostoPromedioString() {
		if(costoPromedio != null)
			costoPromedioString = nf.format(costoPromedio);
		return costoPromedioString;
	}

	public void setCostoPromedioString(String costoPromedioString) {
		if(costoPromedio != null)
			costoPromedioString = nf.format(costoPromedio);
		this.costoPromedioString = costoPromedioString;
	}
	
	@Column
	public String getTypeFormat() {
		return typeFormat;
	}

	public void setTypeFormat(String typeFormat) {
		this.typeFormat = typeFormat;
	}

	@Transient
	public boolean isEntradaReadOnly() {
		return entradaReadOnly;
	}

	public void setEntradaReadOnly(boolean entradaReadOnly) {
		this.entradaReadOnly = entradaReadOnly;
	}

	@Transient
	public boolean isSalidaReadOnly() {
		return salidaReadOnly;
	}

	public void setSalidaReadOnly(boolean salidaReadOnly) {
		this.salidaReadOnly = salidaReadOnly;
	}
	
	@Transient
	public boolean isEntradaLoteReadOnly() {
		return entradaLoteReadOnly;
	}

	public void setEntradaLoteReadOnly(boolean entradaLoteReadOnly) {
		this.entradaLoteReadOnly = entradaLoteReadOnly;
	}

	@Transient
	public boolean isSalidaLoteReadOnly() {
		return salidaLoteReadOnly;
	}
	
	

	public void setSalidaLoteReadOnly(boolean salidaLoteReadOnly) {
		this.salidaLoteReadOnly = salidaLoteReadOnly;
	}
	
	@Transient
	public boolean isEnableSaveBotton() {
		return enableSaveBotton;
	}

	public void setEnableSaveBotton(boolean enableSaveBotton) {
		this.enableSaveBotton = enableSaveBotton;
	}

	@Transient
	public String getSalidaId() {
		return salidaId;
	}

	public void setSalidaId(String salidaId) {
		this.salidaId = salidaId;
	}
	
	@Transient
	public Integer getUepsRestan() {
		return uepsRestan;
	}

	public void setUepsRestan(Integer uepsRestan) {
		this.uepsRestan = uepsRestan;
	}
	
	@Column
	public boolean isDevolucionVenta() {
		return devolucionVenta;
	}

	public void setDevolucionVenta(boolean devolucionVenta) {
		this.devolucionVenta = devolucionVenta;
	}

	@Column
	public boolean isDevolucionEntrada() {
		return devolucionEntrada;
	}

	public void setDevolucionEntrada(boolean devolucionEntrada) {
		this.devolucionEntrada = devolucionEntrada;
	}
	
	@Column
	public Long getReferenciaKardex() {
		return referenciaKardex;
	}

	public void setReferenciaKardex(Long referenciaKardex) {
		this.referenciaKardex = referenciaKardex;
	}
	
	@Transient
	public boolean isEntradaEditVisible() {
		return entradaEditVisible;
	}

	public void setEntradaEditVisible(boolean entradaEditVisible) {
		this.entradaEditVisible = entradaEditVisible;
	}
	
	private void inicializarRegion(){
		String country = System.getProperty("user.country");
		String language = System.getProperty("user.language");
		//nf = NumberFormat.getCurrencyInstance(new Locale("es","MX"));
		nf = NumberFormat.getCurrencyInstance(new Locale(language,country));
	}

	@Transient
	public boolean isEntradaLoteEditVisible() {
		return entradaLoteEditVisible;
	}

	public void setEntradaLoteEditVisible(boolean entradaLoteEditVisible) {
		this.entradaLoteEditVisible = entradaLoteEditVisible;
	}

	@Transient
	public boolean isSalidaCantidadEditVisible() {
		return salidaCantidadEditVisible;
	}

	public void setSalidaCantidadEditVisible(boolean salidaCantidadEditVisible) {
		this.salidaCantidadEditVisible = salidaCantidadEditVisible;
	}

	@Transient
	public boolean isSalidaLoteEditVisible() {
		return salidaLoteEditVisible;
	}

	public void setSalidaLoteEditVisible(boolean salidaLoteEditVisible) {
		this.salidaLoteEditVisible = salidaLoteEditVisible;
	}

	@Transient
	public Long getIdKardexClonacion() {
		return idKardexClonacion;
	}

	public void setIdKardexClonacion(Long idKardexClonacion) {
		this.idKardexClonacion = idKardexClonacion;
	}

	
	

	

	
	
	
	
}
