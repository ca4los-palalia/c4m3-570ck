package com.came.stock.web.vm.producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.zkoss.bind.annotation.Init;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;

import com.came.stock.beans.ClasificacionPrecios;
import com.came.stock.model.domain.ClaveArmonizada;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Unidad;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.producto.utils.FuncionesModificacion;
import com.came.stock.web.vm.producto.utils.ModoDeBusqueda;
import com.came.stock.web.vm.producto.utils.ProductoVariables;

public abstract class ProductoMetaClass extends ProductoVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initObjects() {
		producto = new Producto();
		buscarProducto = new Producto();
		productoTipoSelected = new ProductoTipo();
		cotizacionDB = new ArrayList();
		precios = new ArrayList();
		precioSelected = new ClasificacionPrecios();
		familiasProductos = new ArrayList();
		codigosBarrasProductos = new ArrayList();
		codigoBarrasProducto = new CodigoBarrasProducto();
		costosProductoNuevo = new CostosProducto();
		costosProductos = new ArrayList();
		modoDeBusqueda = new ModoDeBusqueda();
		//claveArmonizada = new ClaveArmonizada();
		cofiaPartidaGenerica = new ConffyaPartidaGenerica();
	}

	public void initProperties() {
		claveArmonizadaList = (List<ClaveArmonizada>) claveArmonizadaRest.getAll().getSingle();
		monedasDB = (List<Moneda>) monedaRest.getAll(organizacion).getSingle();
		productoTipoDB = (List<ProductoTipo>) productoTipoRest.getAll(organizacion).getSingle();
		unidadesDB = (List<Unidad>) unidadRest.getAll(organizacion).getSingle();
		productosNaturalezas = (List<ProductoNaturaleza>) productoNaturalezaRest.getAll().getSingle();

		//producto.setClaveArmonizada(claveArmonizada);
		producto.setConffyaPartidaGenerica(cofiaPartidaGenerica);
		cargarListaPrecios();
		crearFuncionesModificaciones();
		modoDeBusqueda.setTipoFamilia(true);
		modoDeBusqueda.setTipoPersonalizado(true);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void crearFuncionesModificaciones() {
		funcionesModificaciones = new ArrayList();
		FuncionesModificacion item1 = new FuncionesModificacion();
		item1.setIdentificador(Integer.valueOf(1));
		item1.setNombre("Margen o factor");

		FuncionesModificacion item2 = new FuncionesModificacion();
		item2.setIdentificador(Integer.valueOf(2));
		item2.setNombre("Precio");

		FuncionesModificacion item3 = new FuncionesModificacion();
		item3.setIdentificador(Integer.valueOf(3));
		item3.setNombre("M�ximo costo");

		FuncionesModificacion item4 = new FuncionesModificacion();
		item4.setIdentificador(Integer.valueOf(4));
		item4.setNombre("Incremento precio por porcentaje");

		FuncionesModificacion item5 = new FuncionesModificacion();
		item5.setIdentificador(Integer.valueOf(5));
		item5.setNombre("Incrementar precios en valor");

		FuncionesModificacion item6 = new FuncionesModificacion();
		item6.setIdentificador(Integer.valueOf(6));
		item6.setNombre("Incrementar m�ximo costo por porcentaje");

		FuncionesModificacion item7 = new FuncionesModificacion();
		item7.setIdentificador(Integer.valueOf(7));
		item7.setNombre("Incrementar m�ximo costo en valor");

		funcionesModificaciones.add(item1);
		funcionesModificaciones.add(item2);
		funcionesModificaciones.add(item3);
		funcionesModificaciones.add(item4);
		funcionesModificaciones.add(item5);
		funcionesModificaciones.add(item6);
		funcionesModificaciones.add(item7);
	}

	private void cargarListaPrecios() {
		ClasificacionPrecios precioMinimo = new ClasificacionPrecios();
		precioMinimo.setNombre("Precio Minimo");
		precioMinimo.setTipo("MIN");
		ClasificacionPrecios precioMaximo = new ClasificacionPrecios();
		precioMaximo.setNombre("Precio Maximo");
		precioMaximo.setTipo("MAX");
		ClasificacionPrecios precioPromedio = new ClasificacionPrecios();
		precioPromedio.setNombre("Precio Promedio");
		precioPromedio.setTipo("PRO");
		ClasificacionPrecios precioPersonalizado = new ClasificacionPrecios();
		precioPersonalizado.setNombre("Precio Personalizado");
		precioPersonalizado.setTipo("PER");

		precios.add(precioMinimo);
		precios.add(precioMaximo);
		precios.add(precioPromedio);
		precios.add(precioPersonalizado);
	}

	public String validarNuevoProducto() {
		String validacion = "";
		if ((producto.getClave() != null) && (!producto.getClave().isEmpty())) {
			if ((producto.getNombre() != null) && (!producto.getNombre().isEmpty())) {
				if ((producto.getMarca() != null) && (!producto.getMarca().isEmpty())) {
					if ((producto.getModelo() != null) && (!producto.getModelo().isEmpty())) {
						if (producto.getUnidad() != null) {
							if (producto.getActivo()) {
								if (producto.getProductoNaturaleza() != null) {
									if (producto.getProductoNaturaleza().getSimbolo().equals("PRO")) {
										if ((producto.getMaximo() != null)
												&& (producto.getMaximo().longValue() > 0L)
												&& (producto.getMinimo() != null)
												&& (producto.getMinimo().longValue() > 0L)) {
											if (producto.getMaximo().longValue() < producto.getMinimo()
													.longValue()) {
												validacion = "Existencia maxima no puede ser menor al minimo";
											} else if (producto.getMinimo().longValue() > producto.getMaximo()
													.longValue()) {
												validacion = "Existencia minima no puede ser mayor al maximo";
											}
										} else {
											validacion = "Es requerido el minimo y maximo de existencia para un producto";
										}
									}
									if (validacion.isEmpty()) {
										if ((familiasProductos != null) && (familiasProductos.size() > 0)) {
											/*
											if (((producto.getPrecio() == null)
													|| (producto.getPrecio().floatValue() <= 1.0F))
													&& ((producto.getPrecio2() == null)
															|| (producto.getPrecio2().floatValue() <= 1.0F))
													&& ((producto.getPrecio3() == null)
															|| (producto.getPrecio3().floatValue() <= 1.0F))
													&& ((producto.getPrecio4() == null)
															|| (producto.getPrecio4().floatValue() <= 1.0F))
													&& ((producto.getPrecio5() == null)
															|| (producto.getPrecio5().floatValue() <= 1.0F))) {
												validacion = "Es necesario asignar al menos un precio para el articulo/servicio";
											}
											*/
										} else {
											validacion = "El nuevo registro debe ser agregado al menos a una familia";
										}
									}
								} else {
									validacion = "Es necesario marcar la naturaleza del nuevo registro (producto/servicio)";
								}
							} else {
								validacion = "Es necesario marcar com producto activo";
							}
						} else {
							validacion = "La unidad de medida del producto es requerido";
						}
					} else {
						validacion = "El modelo del producto es requerido";
					}
				} else {
					validacion = "La marca del producto es requerido";
				}
			} else {
				validacion = "El nombre del producto es requerido";
			}
		} else {
			validacion = "La clave del producto es requerido";
		}
		return validacion;
	}

	public String getReadJasperReconstruccion(String urlInicial, String file) {
		String nuevaUrl = "";

		String[] numerosComoArray = urlInicial.split("/");
		for (int i = 0; i < numerosComoArray.length; i++) {
			if (i == numerosComoArray.length - 1) {
				nuevaUrl = nuevaUrl + file;
			} else {
				nuevaUrl = nuevaUrl + numerosComoArray[i];
			}
		}
		return nuevaUrl;
	}

	public void copiarArchivo(Media media, String destino) {
		try {
			File dst = new File(destino);
			Files.copy(dst, media.getStreamData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String detectarEliminacionDeProducto(Producto producto) {
		String mensaje = "";
		if ((List<RequisicionProducto>) requisicionProductoRest.getByProducto(producto, organizacion).getSingle() != null) {
			mensaje = producto.getNombre() + " se encuentra en el catalogo de requisicion producto";
		}
		if ((mensaje.equals("")) && ( (List<ProveedorProducto>) proveedorProductoRest.getByProducto(producto).getSingle() != null)) {
			mensaje = producto.getNombre() + " se encuentra en el catalogo de proveedor producto";
		}
		if ((mensaje.equals("")) && ( (List<FamiliasProducto>) familiasProductoRest.getByProducto(producto).getSingle() != null)) {
			mensaje = producto.getNombre() + " esta asignado al catalogo familias";
		}
		if ((mensaje.equals("")) && ( (List<CodigoBarrasProducto>) codigoBarrasProductoRest.getByProducto(producto).getSingle() != null)) {
			mensaje = producto.getNombre() + " tiene asignado codigos de barras";
		}
		if ((mensaje.equals("")) && ( (CostosProducto) costosProductoRest.getByProducto(producto).getSingle() != null)) {
			mensaje = producto.getNombre() + " tiene asignado costos";
		}
		return mensaje;
	}
	
	/**
	 * Verifica si ya existe un elemento familia en
	 * la lista de familias de un producto
	 * 
	 * @param
	 * Long idProductoTipo, identificador del objeto <b>ProductoTipo</b>
	 * 
	 * @return
	 * valor booleano, true = no existe (se puede agregar a la lista)
	 * false = si existe (no se agregara a la lista)
	 * **/
	public boolean existeProductoTipo(Long idProductoTipo){
		boolean limpio = true;
		for (ProductoTipo itemDub : productoTipo) {
			if(itemDub.getIdProductoTipo().equals(idProductoTipo)){
				limpio = false;
			}
		}
		return limpio;
	}
	
	/**
	 * Metodo que construye un producto con informacion extraida de un archivo de excel
	 * 
	 * @param producto objeto de tipo Producto que se construye para la extraccion de informacion
	 * @param valorDePropiedad objeto de tipo XSSFCell contiene el valor para un atributo que será asignado al objeto producto
	 * @param indice de tipo int que indica el indice de la columna de excel correspondiente al un atributo del objeto producto
	 * @return regresa el objeto de tipo Producto con los valores asignados correspondientes
	**/
	@SuppressWarnings("null")
	public Producto crearProducto(Producto producto, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				producto.setClave(valor);
			}
			break;
		case 1:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty())
				producto.setNombre(valor);
			break;
		case 2:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty())
				producto.setMarca(valor);
			break;
		case 3:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()){
				int intValue = (int) valorDePropiedad.getNumericCellValue();
				producto.setModelo(String.valueOf(intValue));
			}
				
			break;
		case 4:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()){
				int intValue = (int) valorDePropiedad.getNumericCellValue();
				if(producto.getCodigosDeBarras() == null)
					producto.setCodigosDeBarras(new ArrayList<CodigoBarrasProducto>());
				
				CodigoBarrasProducto codeBar = new CodigoBarrasProducto();
				codeBar.setActivo(true);
				codeBar.setCodigo(String.valueOf(intValue));
				producto.getCodigosDeBarras().add(codeBar);
			}
				
			break;
		case 5:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				producto.setUnidad(iteratorList.getUnidadFromList(unidadesDB, Long.parseLong(valor)));
			}

			break;
		case 6:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty())
				producto.setDescripcion(valor);
			break;
		case 7:
			if (valor != null || !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				if (valor.equals("1"))
					producto.setActivo(true);
				else
					producto.setActivo(false);
			}

			break;
		case 8:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				valor = String.valueOf(valorDePropiedad);
				if (valor.contains(".0")) {
					String[] splitter = valor.split(".0");
					valor = splitter[0];
				}
				producto.setProductoNaturaleza(iteratorList.getProductoNaturalezaFromList(productosNaturalezas, Long.parseLong(valor)));
			}

			break;
		case 9:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				producto.setEnExistencia(Integer.parseInt(valor));
			}

			break;

		case 10:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				producto.setMinimo(Long.valueOf(valor));
			}
			break;
		case 11:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				producto.setMaximo(Long.valueOf(valor));
			}
			break;
		case 12:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				FamiliasProducto fp = new FamiliasProducto();
				if(producto.getFamiliasProducto() == null)
					producto.setFamiliasProducto(new ArrayList<FamiliasProducto>());
				fp.setProductoTipo(iteratorList.getProductoTipoFromList(productoTipo, new Long(valor)));
				
				producto.getFamiliasProducto().add(fp);
				// FAMILIAS
			}
			break;
		case 13:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				valor = String.valueOf(valorDePropiedad);
				if (valor.contains(".0")) {
					String[] splitter = valor.split(".0");
					valor = splitter[0];
				}
				producto.setMoneda(iteratorList.getMonedaFromList(monedasDB, Long.parseLong(valor)));
			}
			break;
		case 14:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty())
				producto.setExcento(new Double(valor));
			break;
		case 15:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				producto.setImp1(Double.parseDouble(valor)/100);
				// % Impuesto 1
			}

			break;
		case 16:
			if (valor != null && !valor.equals("NULL") && !valor.isEmpty()) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				producto.setImp2(Double.parseDouble(valor)/100);
				// % Impuesto 2
			}
			break;
		case 17:
			valor = String.valueOf(valorDePropiedad);
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioDescripcion("1");
				item.setPrecioValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				listaPrecios.add(item);
				producto.setProductoPrecios(listaPrecios);
			}
			break;
		case 18:
			valor = String.valueOf(valorDePropiedad);
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				if(producto.getProductoPrecios() != null)
					listaPrecios = producto.getProductoPrecios();
				
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioDescripcion("2");
				item.setPrecioValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				listaPrecios.add(item);
				producto.setProductoPrecios(listaPrecios);
			}
			break;
		case 19:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				if(producto.getProductoPrecios() != null)
					listaPrecios = producto.getProductoPrecios();
				
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioDescripcion("3");
				item.setPrecioValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				listaPrecios.add(item);
				producto.setProductoPrecios(listaPrecios);
			}
			break;
		case 20:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				if(producto.getProductoPrecios() != null)
					listaPrecios = producto.getProductoPrecios();
				
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioDescripcion("4");
				item.setPrecioValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				listaPrecios.add(item);
				producto.setProductoPrecios(listaPrecios);
			}
			break;
		case 21:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				if(producto.getProductoPrecios() != null)
					listaPrecios = producto.getProductoPrecios();
				
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioDescripcion("5");
				item.setPrecioValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				listaPrecios.add(item);
				producto.setProductoPrecios(listaPrecios);
			}
			break;
		case 22:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (costosTiposLista == null)
					costosTiposLista = (List<CostosTipos>) costosTiposRest.getAll(true, organizacion).getSingle();
				
				CostosTipos returnItem = iteratorList.getCostosTiposByNombreFromList("max",
						costosTiposLista);
				
				List<ProductoCostos> listaCostos = new ArrayList<>();
				if(producto.getProductoCostos() != null)
					listaCostos = producto.getProductoCostos();
				
				ProductoCostos item = new ProductoCostos();
				item.setCostoDescripcion("Maximo");
				item.setCostoValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				item.setCostosTipos(returnItem);
				listaCostos.add(item);
				producto.setProductoCostos(listaCostos);
				
			}
			break;
		case 23:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				CostosTipos returnItem = iteratorList.getCostosTiposByNombreFromList("repo",
						costosTiposLista);
				
				List<ProductoCostos> listaCostos = new ArrayList<>();
				if(producto.getProductoCostos() != null)
					listaCostos = producto.getProductoCostos();
				
				ProductoCostos item = new ProductoCostos();
				item.setCostoDescripcion("Reposicion");
				item.setCostoValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				item.setCostosTipos(returnItem);
				listaCostos.add(item);
				producto.setProductoCostos(listaCostos);
			}
			break;
		case 24:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				CostosTipos returnItem = iteratorList.getCostosTiposByNombreFromList("prom",
						costosTiposLista);
				List<ProductoCostos> listaCostos = new ArrayList<>();
				if(producto.getProductoCostos() != null)
					listaCostos = producto.getProductoCostos();
				
				ProductoCostos item = new ProductoCostos();
				item.setCostoDescripcion("Promedio");
				item.setCostoValue(Float.parseFloat(String.valueOf(valorDePropiedad)));
				item.setCostosTipos(returnItem);
				listaCostos.add(item);
				producto.setProductoCostos(listaCostos);
			}
			break;
		case 25:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCeroDeCadena(valor);
				
				ConffyaPartidaGenerica itemTemp = new ConffyaPartidaGenerica();
				itemTemp.setClave(valor);
				
				itemTemp = iteratorList.getPartidaGenericaFromListByClave(itemTemp, catalogoPartidaGenericas, organizacion);
				if(itemTemp != null)
					producto.setConffyaPartidaGenerica(itemTemp);
			}
			//Clave generica
			break;
		case 26:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				int intValue = (int) valorDePropiedad.getNumericCellValue();
				producto.setNumeroSerie(String.valueOf(intValue));
			}
			//Numero de serie
			break;
		case 27:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				producto.setLote(valor);
			}
			//Lote
			break;
		case 28:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCeroDeCadena(valor);
				String[] split = valor.split("-");
				producto.setFechaCaducidadCalendar(new StockUtils().convertirStringToCalendar(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0])));
			}
			//fecha de caducidad
			break;
		}
	
		return producto;
	}
}
