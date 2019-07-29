package com.came.stock.web.vm.producto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.ClaveArmonizada;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.model.domain.ProductoFactores;
import com.came.stock.model.domain.ProductoMargen;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.utils.ReportesBuild;

@VariableResolver({ DelegatingVariableResolver.class })
public class ProductosVM extends ProductoMetaClass {
	private static final long serialVersionUID = 313977001812349337L;
	private Button clasificacionButton;
	public Button saveButton;

	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		// organizacion.setNavigating("Panel de control | Productos:");

		productoDB = (List<Producto>) productoRest.getAllNativeSQL(organizacion).getSingle();
		catalogoPartidaGenericas = (List<ConffyaPartidaGenerica>) conffyaPartidaGenericaRest.getAll(organizacion).getSingle();
		presentaciones = (List<Presentacion>) presentacionRest.getAll(organizacion).getSingle();
		getStylesGlobal();
	}

	// ****** TAB INFORMACION DEL PRODUCTO COMANDOS *********
	@Command
	@NotifyChange({ "producto" })
	public void onImageUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		if (producto != null && producto.getIdProducto() != null) {
			UploadEvent upEvent = null;
			Object objUploadEvent = ctx.getTriggerEvent();
			if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
				upEvent = (UploadEvent) objUploadEvent;
			}
			if (upEvent != null) {
				Media media = upEvent.getMedia();
				if (media instanceof org.zkoss.image.Image) {

					AImage imagenProducto = (AImage) media;
					producto.setImagenAImage(imagenProducto);
					producto.setImagen(imagenProducto.getByteData());
					StockUtils.showSuccessmessage("la imagen se ha cargado con exito", Clients.NOTIFICATION_TYPE_INFO,
							0, null);

				} else {
					Messagebox.show("EL archivo seleccionado no es una imagen.");
				}
			}
		} else {
			Messagebox.show("primero debe seleccionar un producto de la lista");
		}

	}

	@Command
	@NotifyChange({ "productoDB" })
	public void onUploadExcel(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		claveArmonizadaList = (List<ClaveArmonizada>) claveArmonizadaRest.getAll().getSingle();
		unidadesDB = (List<Unidad>) unidadRest.getAll(organizacion).getSingle();
		productosNaturalezas = (List<ProductoNaturaleza>) productoNaturalezaRest.getAll().getSingle();
		monedasDB = (List<Moneda>) monedaRest.getAll(organizacion).getSingle();
		productoTipo = (List<ProductoTipo>) productoTipoRest.getAll(organizacion).getSingle();

		productoDB = leerDatosDesdeExcel(getStreamMediaExcel(ctx));
		if (productoDB.size() > 0) {
			for (Producto item : productoDB) {
				item = (Producto) productoRest.save(item).getSingle();

				saveCostosProductos(item);
				savePreciosProductos(item);
				saveCodigobarrasProductos(item);
				saveFamiliasProductosDeProductos(item);
				saveToKardexProductos(item);

			}
			// salvarPreciosYCostosDeProductoImportadoDeExcel(productosDB);

			String mensaje = "";
			if (productoDB != null && productoDB.size() > 0)
				mensaje = productoDB.size() + " Productos Importados";
			else
				mensaje = "Productos Importados";
			Messagebox.show(mensaje);
		} else
			Messagebox.show("No se importaron Productos. El documento esta vacio");
	}

	private void saveToKardexProductos(Producto prod) {
		Kardex temObject = new Kardex();
		temObject.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
		temObject.setProducto(prod);
		temObject.setUsuario(usuario);
		temObject.setOrganizacion(organizacion);
		temObject.setEntradaCantidad(prod.getEnExistencia());
		Float debe = 0F;
		Float precioProducto = 0F;
		Integer cantidadProducto = 0;

		if (prod.getProductoPrecios() != null)
			precioProducto = prod.getProductoPrecios().get(0).getPrecioValue();
		if (prod.getEnExistencia() != null)
			cantidadProducto = prod.getEnExistencia();

		if (precioProducto != null && cantidadProducto != null)
			debe = Integer.parseInt(String.valueOf(Math.round(cantidadProducto))) * precioProducto;
		temObject.setDebe(debe);
		temObject.setIcon(stockUtils.Encriptar("/images/toolbar/infoxOrange16.png"));
		temObject.setPrecioUnitario(precioProducto);
		temObject.setTypeFormat("E");
		temObject = (Kardex) kardexRest.save(temObject).getSingle();
	}

	private void savePreciosProductos(Producto prod) {
		List<ProductoPrecios> listPrecios = prod.getProductoPrecios();
		if (listPrecios != null) {
			for (ProductoPrecios itemP : listPrecios) {
				itemP.setOrganizacion(organizacion);
				itemP.setProducto(prod);
				itemP.setUsuario(usuario);
				itemP.setActualizacion(Calendar.getInstance());
				itemP = (ProductoPrecios) productoPreciosRest.save(itemP).getSingle();
			}
		}
	}

	private void saveCostosProductos(Producto prod) {
		List<ProductoCostos> listCostos = prod.getProductoCostos();
		if (listCostos != null) {
			for (ProductoCostos itemC : listCostos) {
				itemC.setOrganizacion(organizacion);
				itemC.setProducto(prod);
				itemC.setUsuario(usuario);
				itemC.setActualizacion(Calendar.getInstance());
				itemC = (ProductoCostos) productoCostosRest.save(itemC).getSingle();
			}
		}
	}

	private void saveCodigobarrasProductos(Producto prod) {
		List<CodigoBarrasProducto> listCodigos = prod.getCodigosDeBarras();
		if (listCodigos != null) {
			for (CodigoBarrasProducto itemCode : listCodigos) {
				itemCode.setProducto(prod);
				itemCode = (CodigoBarrasProducto) codigoBarrasProductoRest.save(itemCode).getSingle();
			}
		}
	}

	private void saveFamiliasProductosDeProductos(Producto prod) {
		List<FamiliasProducto> listFamilias = prod.getFamiliasProducto();
		if (listFamilias != null) {
			for (FamiliasProducto itemFamilia : listFamilias) {
				itemFamilia.setProducto(prod);
				itemFamilia = (FamiliasProducto) familiasProductoRest.save(itemFamilia).getSingle();
			}
		}
	}

	/*
	 * private void
	 * salvarPreciosYCostosDeProductoImportadoDeExcel(List<Producto>
	 * listaProductos){ for (Producto item : listaProductos) {
	 * List<ProductoPrecios> listPrecios = item.getProductoPrecios();
	 * if(listPrecios != null){ for (ProductoPrecios itemP : listPrecios) {
	 * itemP.setOrganizacion(organizacion); itemP.setProducto(item);
	 * itemP.setUsuario(usuario);
	 * itemP.setActualizacion(Calendar.getInstance());
	 * productoPreciosService.save(itemP); } }
	 * 
	 * List<ProductoCostos> listCostos = item.getProductoCostos(); if(listCostos
	 * != null){ for (ProductoCostos itemC : listCostos) {
	 * itemC.setOrganizacion(organizacion); itemC.setProducto(item);
	 * itemC.setUsuario(usuario);
	 * itemC.setActualizacion(Calendar.getInstance());
	 * productoCostosService.save(itemC); } }
	 * 
	 * } }
	 */
	@SuppressWarnings("rawtypes")
	private List<Producto> leerDatosDesdeExcel(InputStream inPutStream) {
		List<Producto> productoNuevosExcel = new ArrayList<Producto>();
		Usuarios usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");

		catalogoPartidaGenericas = (List<ConffyaPartidaGenerica>) conffyaPartidaGenericaRest.getAll(organizacion).getSingle();
		
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;

			//excelToXml(workBook);
			
			while (rowIterator.hasNext()) {
				Producto producto = new Producto();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 29) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							producto = crearProducto(producto, hssfCell, j);
						} else
							break;
						j++;
					}
					producto.setUsuario(usuario);
					producto.setOrganizacion(organizacion);
					productoNuevosExcel.add(producto);
				}
				i++;
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoNuevosExcel;

	}

	private void excelToXml(XSSFWorkbook workBook) {
		FileWriter fostream;
		PrintWriter out = null;

		try {
			fostream = new FileWriter("D:\\excelToXml.xml");
			out = new PrintWriter(new BufferedWriter(fostream));
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<Bin-code>");

			XSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			List<String> columnName = new ArrayList<>();
			
			String xmlOutput = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			xmlOutput += "<Bin-code>";
			
			
			XSSFDrawing drawing = ((XSSFSheet)hssfSheet).createDrawingPatriarch();
	        System.out.println(drawing.getCTDrawing().toString());
	        
	        
			while (rowIterator.hasNext()) {				
				if (i > 0) {
					XSSFRow hssfRow = (XSSFRow) rowIterator.next();
					Iterator iterator = hssfRow.cellIterator();
					
					out.println("\t<PRODUCTO" + i + ">");
					xmlOutput += "\t<PRODUCTO" + i + ">";
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 30) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							out.println(formatElement("\t\t", "ID", String.valueOf(hssfCell)));
							xmlOutput += "\t\t<" + columnName.get(j) + ">" + String.valueOf(hssfCell) + "</" + columnName.get(j) + ">";
						} else
							break;
						j++;
					}
					out.println("\t</PRODUCTO" + i + ">");
					xmlOutput += "\t</PRODUCTO" + i + ">";
				}else{
					XSSFRow hssfRowColumn = (XSSFRow) rowIterator.next();
					Iterator iteratorColumn = hssfRowColumn.cellIterator();
					int k = 0;
					while (iteratorColumn.hasNext()) {
						if (k < 30) {
							XSSFCell hssfCellColumn = (XSSFCell) iteratorColumn.next();
							String name = String.valueOf(hssfCellColumn).replace(" ", "_").toUpperCase();
							columnName.add(name);
						} else
							break;
						k++;
					}
				}
					
				i++;
			}
			out.write("</Bin-code>");
			xmlOutput += "</Bin-code>";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private static String formatElement(String prefix, String tag, String value) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append("<");
		sb.append(tag);
		if (value != null && value.length() > 0) {
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(tag);
			sb.append(">");
		} else {
			sb.append("/>");
		}
		return sb.toString();
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "producto", "cofiaPartidaGenerica", "productosDB", "enableComboBoxUnidades", "producto" })
	public void nuevaCaptura() {
		producto = new Producto();
		cofiaPartidaGenerica = new ConffyaPartidaGenerica();
		productosDB = new ArrayList();
		producto.setConffyaPartidaGenerica(cofiaPartidaGenerica);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "producto", "familiasProductos" })
	public void newRecord() {
		if ((producto != null) && (producto.getIdProducto() == null)) {
			String validar = validarNuevoProducto();
			if (validar.isEmpty()) {
				producto.setOrganizacion(organizacion);
				producto = (Producto) productoRest.save(producto).getSingle();
				if (familiasProductos != null) {
					for (FamiliasProducto fp : familiasProductos) {
						fp.setProducto(producto);
						if (fp.isEliminar())
							familiasProductoRest.delete(fp);
						else
							familiasProductoRest.save(fp).getSingle();
					}
				}
				StockUtils.showSuccessmessage(
						"Un nuevo producto con nombre " + producto.getNombre() + " ha sido creado.", "info",
						Integer.valueOf(0), saveButton);

				producto = new Producto();
				familiasProductos = new ArrayList();
			} else {
				Clients.showNotification(validar, "warning", saveButton, null, 0);
			}
		} else {
			producto = (Producto) productoRest.save(producto).getSingle();
			if (familiasProductos != null) {
				for (FamiliasProducto fp : familiasProductos) {
					fp.setProducto(producto);
					if (fp.isEliminar())
						familiasProductoRest.delete(fp);
					else
						fp = (FamiliasProducto) familiasProductoRest.save(fp).getSingle();
				}
			}
			StockUtils.showSuccessmessage(producto.getNombre() + " ha sido actualizado.", "info", Integer.valueOf(0),
					this.saveButton);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "productoDB", "familiasProductos" })
	public void deleteRecord() {
		if (this.producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado Asegurese de haber seleccionado un producto");

			return;
		}
		String validarProducto = detectarEliminacionDeProducto(this.producto);
		if (validarProducto.equals("")) {
			Messagebox.show("�Est� seguro de remover este producto?, esta acci�n es irreversible", "Question", 3,
					"z-msgbox z-msgbox-question", new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == 1) {
								productoRest.delete(producto);
								productoDB.remove(producto);

								familiasProductos = (List<FamiliasProducto>) familiasProductoRest.getByProducto(producto).getSingle();
								if (familiasProductos != null) {
									for (FamiliasProducto fp : familiasProductos) {
										familiasProductoRest.delete(fp);
									}
								}
								familiasProductos = new ArrayList();

								StockUtils.showSuccessmessage(
										"el producto -" + producto.getNombre() + "- ha sido eliminado", "info",
										Integer.valueOf(0), null);

								producto = null;
							} else {
								StockUtils.showSuccessmessage(
										"La eliminacion del producto -" + producto.getNombre() + "- ha sido cancelada",
										"info", Integer.valueOf(0), null);

								producto = (Producto) productoRest.getById(producto.getIdProducto(), organizacion).getSingle();
							}
						}
					});
		} else {
			StockUtils.showSuccessmessage(validarProducto, "info", Integer.valueOf(0), null);
		}
		producto = (Producto) productoRest.getById(producto.getIdProducto(), organizacion).getSingle();
	}

	@NotifyChange({ "producto" })
	@Command
	public void copiarClaveArmonizadaToClaveProducto() {
		/*
		 * if ((producto != null) && (producto.getClaveArmonizada() != null)) {
		 * String clave =
		 * String.valueOf(producto.getClaveArmonizada().getGrupo()) +
		 * String.valueOf(producto.getClaveArmonizada().getSubGrupo()) +
		 * String.valueOf(producto.getClaveArmonizada().getClase()); if
		 * ((producto.getClaveArmonizada().getSubclase() != null) &&
		 * (producto.getClaveArmonizada().getTipoDeBien() != null)) { clave =
		 * clave + producto.getClaveArmonizada().getSubclase() +
		 * producto.getClaveArmonizada().getTipoDeBien(); }
		 * producto.setClave(clave); }
		 */
	}

	@Command
	@NotifyChange({ "productoDB" })
	public void findProductos() {
		if ((buscarProducto.getNombre() != null) && (!buscarProducto.getNombre().isEmpty())) {
			if (!buscarProducto.getNombre().equals("*")) {
				productoDB = (List<Producto>) productoRest.getByClaveNombre(buscarProducto.getNombre(), organizacion).getSingle();
			} else if (buscarProducto.getNombre().equals("*")) {
				productoDB = (List<Producto>) productoRest.getAllNativeSQL(organizacion).getSingle();
			}
			if (productoDB != null) {
				String mensaje = "";
				if (productoDB.size() == 1) {
					mensaje = "producto";
				} else if (productoDB.size() > 1) {
					mensaje = "productos";
				}

				if (buscarProducto.getNombre().equals("*")) {
					StockUtils.showSuccessmessage(
							"Tu búsqueda -" + buscarProducto.getNombre() + "- no obtuvo ning�n resultado", "warning",
							Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage("Tu b�squeda -" + buscarProducto.getNombre() + "- obtuvo "
							+ productoDB.size() + " " + mensaje, "info", Integer.valueOf(0), null);
				}
				buscarProducto.setDescripcion(String.valueOf(productoDB.size()));

				producto = new Producto();
			} else {
				productoDB = new ArrayList<>();
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + buscarProducto.getNombre() + "- no obtuvo ning�n resultado", "warning",
						Integer.valueOf(0), null);
			}
		} else {
			productoDB = new ArrayList<>();
		}
	}

	@NotifyChange({ "productoDB" })
	@Command
	public void buscarProductosPorFamiliaChangeCombo() {
		String mensaje = "Ningun producto encontrado de tipo: " + productoTipoSelected.getNombre();

		familiasProductos = (List<FamiliasProducto>) familiasProductoRest.getByFamilia(productoTipoSelected).getSingle();
		if (familiasProductos != null) {
			if (familiasProductos.size() == 1) {
				mensaje = "Se encontro " + familiasProductos.size() + " producto de tipo "
						+ productoTipoSelected.getNombre();
			} else
				mensaje = "Se encontraron " + familiasProductos.size() + " productos de la familia "
						+ productoTipoSelected.getNombre();

			productoDB = new ArrayList<>();
			for (FamiliasProducto item : familiasProductos) {
				productoDB.add(item.getProducto());
			}

			StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), clasificacionButton);
		} else {
			StockUtils.showSuccessmessage(mensaje, "warning", Integer.valueOf(0), clasificacionButton);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange({ "productoDB" })
	public void reporteProductos() {
		if (productoDB != null) {
			ReportesBuild reporteador = new ReportesBuild();

			Map<String, Object> inputParams = new HashMap();
			inputParams.put("executeNameMethod", "closeVisorPdf");
			inputParams.put("titulo", "Reporte de productos");
			inputParams.put("fuente", reporteador.generarReporteProductosPdf(productoDB, organizacion));

			Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul",
					inputParams);
			windowModalView.doModal();
		} else {
			StockUtils.showSuccessmessage("NO existe alg�n resultado de busqueda para generar el reporte (PDF)",
					"error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "claveArmonizadaList", "productoTipo", "productoTipoDB", "producto", "codigosBarrasProductos",
			"productoPrecios", "productoFactores", "productoMargenes", "productoCostos", "costosTiposLista" })
	public void obtenerListaFamiliasProducto() {
		producto = (Producto) productoRest.getById(producto.getIdProducto(), organizacion).getSingle();

		if (producto.getUnidad() != null)
			producto.setUnidad(iteratorList.getUnidadFromList(unidadesDB, producto.getUnidad().getIdUnidad()));
		if (producto.getProductoNaturaleza() != null)
			producto.setProductoNaturaleza(
					iteratorList.getProductoNaturalezaFromList(productosNaturalezas, producto.getProductoNaturaleza().getIdProductoNaturaleza()));
		if (producto.getPresentacion() != null)
			producto.setPresentacion(iteratorList.getPresentacionFromList(presentaciones, producto.getPresentacion().getIdPresentacion()));

		generarListaFamiliasProductos();
		if (costosTiposLista == null)
			costosTiposLista = (List<CostosTipos>) costosTiposRest.getAll(true, organizacion).getSingle();
		productoPrecios = (List<ProductoPrecios>) productoPreciosRest.getByProductoOrderMostRecentDate(producto, organizacion).getSingle();
		productoFactores = (List<ProductoFactores>) productoFactoresRest.getByProductoOrderMostRecentDate(producto).getSingle();
		productoMargenes = (List<ProductoMargen>) productoMargenRest.getByProductoOrderMostRecentDate(producto).getSingle();
		productoCostos = (List<ProductoCostos>) productoCostosRest.getByProductoOrderMostRecentDate(producto).getSingle();

		List<CodigoBarrasProducto> codigos = (List<CodigoBarrasProducto>) codigoBarrasProductoRest.getByProducto(producto).getSingle();
		if (codigos != null) {
			producto.setCodigosDeBarras(codigos);
			producto.setCodigosDeBarrasSelected(codigos.get(0));
		}

		if (producto.getConffyaPartidaGenerica() != null)
			producto.setConffyaPartidaGenerica(
					iteratorList.getPartidaGenericaFromListById(producto.getConffyaPartidaGenerica(), catalogoPartidaGenericas));

		if (productoCostos != null) {
			for (ProductoCostos item : productoCostos) {
				CostosTipos returnItem = iteratorList.getCostosTiposByIdFromList(item.getCostosTipos().getIdCostosTipos(),
						costosTiposLista);
				if (returnItem != null)
					item.setCostosTipos(returnItem);
			}
		}

	}

	private void generarListaFamiliasProductos() {
		productoTipo = new ArrayList<>();
		familiasProductos = new ArrayList<>();

		if (producto != null) {
			// producto = productoService.getById(producto.getIdProducto());

			if (selectedTab0 || selectedTab2) {
				familiasProductos = (List<FamiliasProducto>) familiasProductoRest.getByProducto(producto).getSingle();
			}

			productoTipoDB = (List<ProductoTipo>) productoTipoRest.getAll(organizacion).getSingle();
			/*
			 * if (producto.getConffyaPartidaGenerica() != null){
			 * ConffyaPartidaGenerica itemTemp =
			 * producto.getConffyaPartidaGenerica(); itemTemp =
			 * getPartidaGenericaFromListByClave(itemTemp,
			 * catalogoPartidaGenericas, organizacion);
			 * producto.setConffyaPartidaGenerica(itemTemp); }
			 */

			if (producto.getProductoNaturaleza() != null)
				producto.setProductoNaturaleza(
						iteratorList.getProductoNaturalezaFromList(productosNaturalezas, producto.getProductoNaturaleza().getIdProductoNaturaleza()));
			if (producto.getMoneda() != null)
				producto.setMoneda(iteratorList.getMonedaFromList(monedasDB, producto.getMoneda().getIdMoneda()));
			if (producto.getUnidad() != null)
				producto.setUnidad(iteratorList.getUnidadFromList(unidadesDB, producto.getUnidad().getIdUnidad()));

			if (selectedTab3)
				codigosBarrasProductos = (List<CodigoBarrasProducto>) codigoBarrasProductoRest.getByProducto(producto).getSingle();

			if (familiasProductos != null) {
				for (FamiliasProducto item : familiasProductos) {
					ProductoTipo temp = iteratorList.getProductoTipoFromList(productoTipoDB,
							item.getProductoTipo().getIdProductoTipo());
					if (temp != null)
						productoTipo.add(temp);

				}

				for (ProductoTipo item : productoTipo) {
					for (ProductoTipo item2 : productoTipoDB) {
						if (item.getIdProductoTipo().equals(item2.getIdProductoTipo())) {
							productoTipoDB.remove(item2);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * Metodo para realizar Drag and Drop dual list para asignar familias a un
	 * producto
	 * 
	 * @param Componente
	 *            del zul <b>item listBox</b> seleccionado
	 * 
	 **/
	@Command
	@NotifyChange({ "productoTipoDB", "productoTipo" })
	public void move(@ContextParam(ContextType.COMPONENT) Component self,
			@ContextParam(ContextType.TRIGGER_EVENT) DropEvent dropEvent) {
		Component dragged = dropEvent.getDragged();
		if (producto != null && producto.getIdProducto() != null) {

			if (self instanceof Listitem)
				self.getParent().insertBefore(dragged, self);
			else
				self.appendChild(dragged);

			ProductoTipo movingItem = ((Listitem) dragged).getValue();

			if (self.getId().equals("right")) {// se arrastra a: derecha |
												// izquierda

				if (existeProductoTipo(movingItem.getIdProductoTipo())) {
					productoTipo.add(movingItem);

					FamiliasProducto productosfamiliaTemp = (FamiliasProducto) familiasProductoRest.getByProductoProductoTipo(producto,
							movingItem).getSingle();
					if (productosfamiliaTemp != null) {
						familiasProductos.add(productosfamiliaTemp);
					} else {
						if (familiasProductos == null)
							familiasProductos = new ArrayList<>();
						FamiliasProducto familiasProducto = new FamiliasProducto();
						familiasProducto.setEliminar(false);
						familiasProducto.setProductoTipo(movingItem);
						familiasProducto.setProducto(producto);
						familiasProductos.add(familiasProducto);
					}
					StockUtils.showSuccessmessage(movingItem.getNombre() + " ha sido agregado --->",
							Clients.NOTIFICATION_TYPE_INFO, 0, dragged);
				}
				productoTipoDB.remove(movingItem);
			} else if (self.getId().equals("left")) {
				productoTipoDB.add(movingItem);
				productoTipo.remove(movingItem);

				FamiliasProducto productosfamiliaTemp = (FamiliasProducto) familiasProductoRest.getByProductoProductoTipo(producto,
						movingItem).getSingle();
				for (FamiliasProducto item : familiasProductos) {
					if (productosfamiliaTemp != null) {
						if (item.getIdFamiliasProducto().equals(productosfamiliaTemp.getIdFamiliasProducto())) {
							item.setEliminar(true);
							break;
						}
					} else if (item.getProducto().getIdProducto().equals(producto.getIdProducto())
							&& item.getProductoTipo().getIdProductoTipo().equals(movingItem.getIdProductoTipo())) {
						familiasProductos.remove(item);
						break;
					}
				}
				StockUtils.showSuccessmessage(movingItem.getNombre() + " ha sido removido --->",
						Clients.NOTIFICATION_TYPE_INFO, 0, dragged);
			}
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto primero", Clients.NOTIFICATION_TYPE_WARNING, 0,
					dragged);
	}
	// ****** FIN TAB INFORMACION DEL PRODUCTO COMANDOS *********

	// ****** TAB REPORTES DE ARTICULOS COMANDOS *********
	// ****** FIN TAB REPORTES DE ARTICULOS COMANDOS *********

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// ****** TAB CLASIFICACIONES COMANDOS *********
	@Command
	@NotifyChange({ "*" })
	public void reporteProductosClasificacionSubmenu() {
		if ((familiasProductos != null) && (familiasProductos.size() > 0)) {
			if (productoDB == null) {
				productoDB = new ArrayList();
			}
			for (FamiliasProducto fp : familiasProductos) {
				productoDB.add(fp.getProducto());
			}
			if (productoDB != null) {
				ReportesBuild reporteador = new ReportesBuild();

				Map<String, Object> inputParams = new HashMap();
				inputParams.put("executeNameMethod", "closeVisorPdf");
				inputParams.put("titulo",
						"Reporte de productos '" + productoTipoSelected.getNombre().toUpperCase() + "'");
				inputParams.put("fuente", reporteador.generarReporteProductosClasificacionPdf(productoTipoSelected,
						productoDB, organizacion));

				Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul",
						inputParams);
				windowModalView.doModal();
			} else {
				StockUtils.showSuccessmessage("NO existe alg�n resultado de busqueda para generar el reporte (PDF)",
						"error", Integer.valueOf(0), null);
			}
		}
	}

	@NotifyChange({ "familiasProductos" })
	@Command
	public void obtenerProductosPorFamiliaSelectItemList() {
		String mensaje = "Ningun producto encontrado de tipo: " + productoTipoSelected.getNombre();

		familiasProductos = (List<FamiliasProducto>) familiasProductoRest.getByFamilia(productoTipoSelected).getSingle();
		if (familiasProductos != null) {
			if (familiasProductos.size() == 1) {
				mensaje = "Se encontro " + familiasProductos.size() + " producto de tipo "
						+ productoTipoSelected.getNombre();
			} else
				mensaje = "Se encontraron " + familiasProductos.size() + " productos de la familia "
						+ productoTipoSelected.getNombre();

			StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), clasificacionButton);
		} else {
			StockUtils.showSuccessmessage(mensaje, "warning", Integer.valueOf(0), clasificacionButton);
		}
	}
	// ****** FIN TAB CLASIFICACIONES COMANDOS *********

	// ****** MULTIPLES CODIGOS COMANDOS ***************

	@Command
	public void codigoSeleccionado() {
		codigoBarrasProducto.setSelected(true);
		codigoBarrasProducto.setProducto(producto);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "codigosBarrasProductos" })
	public void addNewItemCodigo() {
		if ((producto != null) && (producto.getIdProducto() != null)) {
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "continuarProcesoCodeBarNuevo");
			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/ingresarCodeBar.zul", inputParams);
			productoModalView.doModal();

		} else {
			StockUtils.showSuccessmessage("para poder agregar un codigo, asegurese de haber cargado un producto",
					"error", Integer.valueOf(0), null);
		}
	}

	@GlobalCommand
	@NotifyChange({ "codigosBarrasProductos" })
	public void continuarProcesoCodeBarNuevo(@BindingParam("barCode") CodigoBarrasProducto nuevoCodigo) {
		nuevoCodigo.setProducto(producto);
		codigosBarrasProductos.add(nuevoCodigo);
	}

	@Command
	@NotifyChange({ "codigosBarrasProductos" })
	public void delItemCodigo() {
		if ((codigoBarrasProducto != null) && (codigoBarrasProducto.isSelected())
				&& (codigosBarrasProductos.size() > 0)) {
			codigosBarrasProductos.remove(codigoBarrasProducto);
			if (codigoBarrasProducto.getIdCodigoBarrasProducto() != null) {
				codigoBarrasProductoRest.delete(codigoBarrasProducto);
			}
			if (codigoBarrasProducto.getCodigo() != null) {
				StockUtils.showSuccessmessage(codigoBarrasProducto.getCodigo() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			}
			codigoBarrasProducto = new CodigoBarrasProducto();
		}
	}

	@Command
	@NotifyChange({ "codigosBarrasProductos" })
	public void guardarCodigosBarras() {
		if ((codigosBarrasProductos != null) && (codigosBarrasProductos.size() > 0)) {
			for (CodigoBarrasProducto item : codigosBarrasProductos) {
				item.setProducto(producto);
				if ((item.getCodigo() != null) && (!item.getCodigo().isEmpty())) {
					item.setCodigo(item.getCodigo().toUpperCase());
					item = (CodigoBarrasProducto) codigoBarrasProductoRest.save(item).getSingle();
				}
			}
			StockUtils.showSuccessmessage("Nuevos codigos han sido guardados para el producto " + producto.getNombre(),
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("No se llevo a cabo ningun cambio", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	public void descargarCodigoBarras() {
		Filedownload.save(codigoBarrasProducto.getBarCode(), "image/png",
				codigoBarrasProducto.getProducto().getNombre() + " " + codigoBarrasProducto.getCodigo() + ".png");
	}
	// ****** FIN MULTIPLES CODIGOS COMANDOS ***************

	@Command
	@NotifyChange({ "productoDB", "producto", "productoPrecios", "productoCostos", "productoFactores",
			"productoMargenes", "productoCosto", "productoFactor", "productoMargen", "productoPrecio" })
	public void tabSelectedInfoProducto() {
		selectedTab0 = true;
		selectedTab1 = false;
		selectedTab2 = false;
		selectedTab3 = false;
		selectedTab4 = false;

		producto = new Producto();
		if (productoDB == null || productoDB.size() == 0) {
			// productoDB = productoService.getAllNativeSQL();
			productoDB = (List<Producto>) productoRest.getAll(organizacion).getSingle();
		}

		productoPrecios = new ArrayList<>();
		productoCostos = new ArrayList<>();
		productoFactores = new ArrayList<>();
		productoMargenes = new ArrayList<>();
		productoCosto = new ProductoCostos();
		;
		productoFactor = new ProductoFactores();
		productoMargen = new ProductoMargen();
		productoPrecio = new ProductoPrecios();

	}

	@Command
	@NotifyChange({ "" })
	public void tabSelectedReportesArticulos() {
		selectedTab0 = false;
		selectedTab1 = true;
		selectedTab2 = false;
		selectedTab3 = false;
		selectedTab4 = false;
	}

	@Command
	@NotifyChange({ "productoTipoClasificaciones" })
	public void tabSelectedClasificaciones() {
		selectedTab0 = false;
		selectedTab1 = false;
		selectedTab2 = true;
		selectedTab3 = false;
		selectedTab4 = false;
		productoTipoClasificaciones = (List<ProductoTipo>) productoTipoRest.getAll(organizacion).getSingle();
	}

	@Command
	@NotifyChange({ "producto", "codigosBarrasProductos" })
	public void tabSelectedMultiplesCodigos() {
		selectedTab0 = false;
		selectedTab1 = false;
		selectedTab2 = false;
		selectedTab3 = true;
		selectedTab4 = false;
		producto = new Producto();
		codigosBarrasProductos = new ArrayList<>();
	}

	@Command
	@NotifyChange({ "producto" })
	public void tabSelectedActualizarCodigos() {
		producto = new Producto();
		selectedTab0 = false;
		selectedTab1 = false;
		selectedTab2 = false;
		selectedTab3 = false;
		selectedTab4 = true;
	}

	@Command
	@NotifyChange({ "productoPrecios" })
	public void agregarNuevoPrecio(@BindingParam("compAddPrecioBtn") Component comp) {
		if (productoPrecios == null)
			productoPrecios = new ArrayList<>();
		if (producto != null && producto.getIdProducto() != null) {
			ProductoPrecios item = new ProductoPrecios();
			item.setIndex(productoPrecios.size() + 1);
			item.setPrecioDescripcion(String.valueOf(productoPrecios.size() + 1));
			productoPrecios.add(item);
		}

		else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoPrecios" })
	public void savePrecio(@BindingParam("compSavePrecioBtn") Component comp) {
		if (producto != null && producto.getIdProducto() != null) {
			if (productoPrecios != null && productoPrecios.size() > 0) {
				for (ProductoPrecios item : productoPrecios) {
					if (item.getActualizacion() == null)
						item.setActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);
					item.setProducto(producto);
					item.setUsuario(usuario);
					item = (ProductoPrecios) productoPreciosRest.save(item).getSingle();
					StockUtils.showSuccessmessage("Un nuevo precio ha sido agregado", Clients.NOTIFICATION_TYPE_INFO, 0,
							comp);
				}
			} else
				StockUtils.showSuccessmessage("Nada que salvar, Lista vacia", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoPrecios" })
	public void eliminarProductoPrecios(@BindingParam("index") Integer index) {
		productoPrecio = productoPrecios.get(index);
		productoPrecios.remove(productoPrecio);
		if (productoPrecio.getIdProductoPrecios() != null)
			productoPreciosRest.delete(productoPrecio);

		int i = 0;
		for (ProductoPrecios item : productoPrecios) {
			item.setIndex(i + 1);
			i++;
		}
	}

	@Command
	@NotifyChange({ "productoFactores" })
	public void agregarNuevoFactor(@BindingParam("compAddFactBtn") Component comp) {
		if (productoFactores == null)
			productoFactores = new ArrayList<>();
		if (producto != null && producto.getIdProducto() != null) {
			ProductoFactores item = new ProductoFactores();
			item.setIndex(productoFactores.size() + 1);
			item.setFactorDescripcion(String.valueOf(productoFactores.size() + 1));
			productoFactores.add(item);
		}

		else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoFactores" })
	public void saveFactor(@BindingParam("compSaveFactBtn") Component comp) {
		if (producto != null && producto.getIdProducto() != null) {
			if (productoFactores != null && productoFactores.size() > 0) {
				for (ProductoFactores item : productoFactores) {
					if (item.getActualizacion() == null)
						item.setActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);
					item.setProducto(producto);
					item.setUsuario(usuario);
					item = (ProductoFactores) productoFactoresRest.save(item).getSingle();
					StockUtils.showSuccessmessage("Un nuevo factor ha sido agregado", Clients.NOTIFICATION_TYPE_INFO, 0,
							comp);
				}
			} else
				StockUtils.showSuccessmessage("Nada que salvar, Lista vacia", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoFactores" })
	public void eliminarProductoFactores(@BindingParam("index") Integer index) {
		productoFactor = productoFactores.get(index);
		productoFactores.remove(productoFactor);
		if (productoFactor.getIdProductoFactores() != null)
			productoFactoresRest.delete(productoFactor);

		int i = 0;
		for (ProductoFactores item : productoFactores) {
			item.setIndex(i + 1);
			i++;
		}
	}

	@Command
	@NotifyChange({ "productoMargenes" })
	public void agregarNuevoMargen(@BindingParam("compAddMargBtn") Component comp) {
		if (productoMargenes == null)
			productoMargenes = new ArrayList<>();
		if (producto != null && producto.getIdProducto() != null) {
			ProductoMargen item = new ProductoMargen();
			item.setIndex(productoMargenes.size() + 1);
			item.setMargenDescripcion(String.valueOf(productoMargenes.size() + 1));
			productoMargenes.add(item);
		}

		else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoMargenes" })
	public void saveMargen(@BindingParam("compSaveMargBtn") Component comp) {
		if (producto != null && producto.getIdProducto() != null) {
			if (productoMargenes != null && productoMargenes.size() > 0) {
				for (ProductoMargen item : productoMargenes) {
					if (item.getActualizacion() == null)
						item.setActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);
					item.setProducto(producto);
					item.setUsuario(usuario);
					item = (ProductoMargen) productoMargenRest.save(item).getSingle();
					StockUtils.showSuccessmessage("Un nuevo margen ha sido agregado", Clients.NOTIFICATION_TYPE_INFO, 0,
							comp);
				}
			} else
				StockUtils.showSuccessmessage("Nada que salvar, Lista vacia", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoMargenes" })
	public void eliminarProductoMargenes(@BindingParam("index") Integer index) {
		productoMargen = productoMargenes.get(index);
		productoMargenes.remove(productoMargen);
		if (productoMargen.getIdProductoMargen() != null)
			productoMargenRest.delete(productoMargen);

		int i = 0;
		for (ProductoMargen item : productoMargenes) {
			item.setIndex(i + 1);
			i++;
		}
	}

	@Command
	@NotifyChange({ "productoCostos" })
	public void agregarNuevoCosto(@BindingParam("compAddCostBtn") Component comp) {
		if (productoCostos == null)
			productoCostos = new ArrayList<>();
		if (producto != null && producto.getIdProducto() != null) {
			ProductoCostos item = new ProductoCostos();
			item.setIndex(productoCostos.size() + 1);
			item.setCostosTipos(new CostosTipos());
			productoCostos.add(item);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoCostos" })
	public void saveCosto(@BindingParam("compSaveCostBtn") Component comp) {
		if (producto != null && producto.getIdProducto() != null) {
			if (productoCostos != null && productoCostos.size() > 0) {
				for (ProductoCostos item : productoCostos) {
					if (item.getActualizacion() == null)
						item.setActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);
					item.setProducto(producto);
					item.setUsuario(usuario);
					item = (ProductoCostos) productoCostosRest.save(item).getSingle();
					StockUtils.showSuccessmessage("Un nuevo costo ha sido agregado", Clients.NOTIFICATION_TYPE_INFO, 0,
							comp);
				}
			} else
				StockUtils.showSuccessmessage("Nada que salvar, Lista vacia", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange({ "productoCostos" })
	public void eliminarProductoCostos(@BindingParam("index") Integer index) {
		productoCosto = productoCostos.get(index);
		productoCostos.remove(productoCosto);
		if (productoCosto.getIdProductoCostos() != null)
			productoCostosRest.delete(productoCosto);

		int i = 0;
		for (ProductoCostos item : productoCostos) {
			item.setIndex(i + 1);
			i++;
		}
	}

}
