package com.came.stock.model.domain;

import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Repository;
import org.zkoss.image.AImage;

import com.came.stock.utilidades.StockUtils;


@Repository
@Entity
@Table
public class Producto implements Serializable {
	private static final long serialVersionUID = 8065024909123508528L;
	private Long idProducto;
	private boolean activo;
	private String clave;
	//private String codigoBarras;
	private String descripcion;
	private String marca;
	private String modelo;
	private String nombre;
	private Float precio;
	private String precioString;
	private Float costoUltimo;
	private boolean seleccionar;
	private boolean cambioNaturaleza;
	private Integer enExistencia;
	private Unidad unidad;
	private ProductoNaturaleza productoNaturaleza;
	private Moneda moneda;
	private Long minimo;
	private Long maximo;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	private Date fechaCaducidadDate;
	private Calendar fechaCaducidadCalendar;
	private ConffyaPartidaGenerica conffyaPartidaGenerica;
	//private ClaveArmonizada claveArmonizada;
	private Integer restan;
	private byte[] imagen;
	private AImage imagenAImage;
	private String lote;
	private String numeroSerie;
	
	private Double excento;
	private Double imp1;
	private Double imp2;

	private Presentacion presentacion;
	
	private List<ProductoCostos> productoCostos;
	private List<ProductoPrecios> productoPrecios;
	private List<CodigoBarrasProducto> codigosDeBarras;
	private CodigoBarrasProducto codigosDeBarrasSelected;
	private List<FamiliasProducto> familiasProducto;
	
	private List<ProductoFactores> productoFactores;
	private List<ProductoMargen> productoMargenes;
	
	
	
	
	private NumberFormat nf;
	
	public Producto() {
		inicializarRegion();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false)
	public Long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "descripcion", columnDefinition = "TEXT")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/*
	@Column
	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	*/

	@Column
	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Column
	public Integer getEnExistencia() {
		return this.enExistencia;
	}

	public void setEnExistencia(Integer enExistencia) {
		this.enExistencia = enExistencia;
	}

	@Transient
	public boolean isSeleccionar() {
		return this.seleccionar;
	}

	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}

	@Transient
	public boolean isCambioNaturaleza() {
		return this.cambioNaturaleza;
	}

	public void setCambioNaturaleza(boolean cambioNaturaleza) {
		this.cambioNaturaleza = cambioNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "unidad")
	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@OneToOne
	@JoinColumn(name = "productoNaturaleza")
	public ProductoNaturaleza getProductoNaturaleza() {
		return this.productoNaturaleza;
	}

	public void setProductoNaturaleza(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturaleza = productoNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "moneda")
	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	@Column
	public Long getMinimo() {
		return this.minimo;
	}

	public void setMinimo(Long minimo) {
		this.minimo = minimo;
	}

	@Column
	public Long getMaximo() {
		return this.maximo;
	}

	public void setMaximo(Long maximo) {
		this.maximo = maximo;
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

	@Column
	public String getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToOne
	@JoinColumn(name = "conffyaPartidaGenerica")
	public ConffyaPartidaGenerica getConffyaPartidaGenerica() {
		return conffyaPartidaGenerica;
	}

	public void setConffyaPartidaGenerica(ConffyaPartidaGenerica conffyaPartidaGenerica) {
		this.conffyaPartidaGenerica = conffyaPartidaGenerica;
	}

	@Transient
	public Integer getRestan() {
		return restan;
	}

	public void setRestan(Integer restan) {
		this.restan = restan;
	}

	@Column
	@Lob
	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	@Transient
	public AImage getImagenAImage() {
		if (imagen != null) {
			try {
				imagenAImage = new AImage("imagenByte", imagen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagenAImage;
	}

	public void setImagenAImage(AImage imagenAImage) {
		this.imagenAImage = imagenAImage;
	}

	@Transient
	public List<ProductoCostos> getProductoCostos() {
		return productoCostos;
	}

	public void setProductoCostos(List<ProductoCostos> productoCostos) {
		this.productoCostos = productoCostos;
	}

	@Transient
	public List<ProductoFactores> getProductoFactores() {
		return productoFactores;
	}

	public void setProductoFactores(List<ProductoFactores> productoFactores) {
		this.productoFactores = productoFactores;
	}

	@Transient
	public List<ProductoMargen> getProductoMargenes() {
		return productoMargenes;
	}

	public void setProductoMargenes(List<ProductoMargen> productoMargenes) {
		this.productoMargenes = productoMargenes;
	}

	@Transient
	public List<ProductoPrecios> getProductoPrecios() {
		//productoPreciosService.getByProductoOrderMostRecentDate(producto);
		return productoPrecios;
	}

	public void setProductoPrecios(List<ProductoPrecios> productoPrecios) {
		this.productoPrecios = productoPrecios;
	}

	@Column
	public Float getPrecio() {
		if(precio != null)
			precioString = nf.format(precio);
		return precio;
	}

	public void setPrecio(Float precio) {
		if(precio != null)
			precioString = nf.format(precio);
		this.precio = precio;
	}

	@Column
	public Float getCostoUltimo() {
		return costoUltimo;
	}

	public void setCostoUltimo(Float costoUltimo) {
		this.costoUltimo = costoUltimo;
	}

	@Transient
	public String getPrecioString() {
		return precioString;
	}

	public void setPrecioString(String precioString) {
		this.precioString = precioString;
	}
	
	@Transient
	public Date getFechaCaducidadDate() {
		if(fechaCaducidadCalendar != null)
			fechaCaducidadDate = new StockUtils().convertirCalendarToDate(fechaCaducidadCalendar);
		return fechaCaducidadDate;
	}

	public void setFechaCaducidadDate(Date fechaCaducidadDate) {
		if(fechaCaducidadDate != null)
			fechaCaducidadCalendar = new StockUtils().convertirDateToCalendar(fechaCaducidadDate);
		this.fechaCaducidadDate = fechaCaducidadDate;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Calendar getFechaCaducidadCalendar() {
		return fechaCaducidadCalendar;
	}

	public void setFechaCaducidadCalendar(Calendar fechaCaducidadCalendar) {
		this.fechaCaducidadCalendar = fechaCaducidadCalendar;
	}

	@Column
	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	@Column
	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	@Transient
	public List<CodigoBarrasProducto> getCodigosDeBarras() {
		return codigosDeBarras;
	}

	public void setCodigosDeBarras(List<CodigoBarrasProducto> codigosDeBarras) {
		this.codigosDeBarras = codigosDeBarras;
	}
	
	@Transient
	public List<FamiliasProducto> getFamiliasProducto() {
		return familiasProducto;
	}

	public void setFamiliasProducto(List<FamiliasProducto> familiasProducto) {
		this.familiasProducto = familiasProducto;
	}
	
	@Column
	public Double getExcento() {
		return excento;
	}

	public void setExcento(Double excento) {
		this.excento = excento;
	}

	@Column
	public Double getImp1() {
		return imp1;
	}

	public void setImp1(Double imp1) {
		this.imp1 = imp1;
	}

	@Column
	public Double getImp2() {
		return imp2;
	}

	public void setImp2(Double imp2) {
		this.imp2 = imp2;
	}
	
	@Transient
	public CodigoBarrasProducto getCodigosDeBarrasSelected() {
		return codigosDeBarrasSelected;
	}

	public void setCodigosDeBarrasSelected(CodigoBarrasProducto codigosDeBarrasSelected) {
		this.codigosDeBarrasSelected = codigosDeBarrasSelected;
	}
	
	@OneToOne
	@JoinColumn(name = "presentacion")
	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}
	
	private void inicializarRegion(){
		String country = System.getProperty("user.country");
		String language = System.getProperty("user.language");
		//nf = NumberFormat.getCurrencyInstance(new Locale("es","MX"));
		nf = NumberFormat.getCurrencyInstance(new Locale(language,country));
	}
	
	
	
}
