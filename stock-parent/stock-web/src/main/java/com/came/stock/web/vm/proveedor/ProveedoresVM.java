package com.came.stock.web.vm.proveedor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
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
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Justificacion;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.utils.ReportesBuild;

@VariableResolver({ DelegatingVariableResolver.class })
public class ProveedoresVM extends ProveedorMetaClass {
	private static final long serialVersionUID = -4963362932578502507L;
	private static List<Banco> completeBancos;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		completeBancos = bancosDB;
		paises = new ArrayList();
		paises.add((Pais) paisRest.getById(Long.valueOf(157L)).getSingle());
		getStylesGlobal();
	}

	@Command
	@NotifyChange({ "*" })
	public void newRecord() {
		if (this.nuevoProveedor.getNombre() != null) {
			List<Proveedor> validarExistencia = (List<Proveedor>) proveedorRest.getByNombre(nuevoProveedor.getNombre(), organizacion).getSingle();
			if (validarExistencia == null) {
				String mensajeValidacion = validarEntradaDatosProveedor();
				if (mensajeValidacion.equals("")) {
					nuevoProveedor.setProveedorActivo(true);
					nuevoProveedor.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));
					guardarProveedor();
					StockUtils.showSuccessmessage(this.nuevoProveedor.getNombre() + " ha sido guardado", "info",
							Integer.valueOf(0), null);

					initObjects();
				} else {
					StockUtils.showSuccessmessage("Los campos marcados con (*) son requeridos: \n" + mensajeValidacion,
							"warning", Integer.valueOf(0), null);
				}
			} else {
				StockUtils.showSuccessmessage(this.nuevoProveedor.getNombre() + " ya se encuentra registrado",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Nombre del proveedor requerido", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "proveedorSelected", "paisProveedor", "estadoProveedor", "municipioProveedor", "cuentaPago",
		"monedaSeleccionada", "bancoSeleccionado", "contratoVigenciaInicio", "proveedorProductos", "proveedoresLista" })
	public void saveChanges() {
		if ((proveedoresLista != null) && (proveedoresLista.size() > 0)) {
			
			if(cuentaPago != null){
				if(monedaSeleccionada != null)
					cuentaPago.setMoneda(monedaSeleccionada);
				if(bancoSeleccionado != null)
					cuentaPago.setBanco(bancoSeleccionado);
				cuentaPago.setProveedor(proveedorSelected);
				cuentaPago = (CuentaPago) cuentasPagoRest.save(cuentaPago).getSingle();
			}
			actualizarProveedorCambios();
			
			StockUtils.showSuccessmessage("La lista de proveedores ha sido actualizada", "info", Integer.valueOf(0),
					null);
		} else if ((nuevoProveedor != null) && (nuevoProveedor.getIdProveedor() == null)) {
			nuevoProveedor = proveedorSelected;
			newRecord();
		} else {
			StockUtils.showSuccessmessage("La lista no contiene proveedores", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void deleteRecord() {
		if (this.proveedorSelected != null) {
			Messagebox.show("�Est� seguro de remover este proveedor?, esta acci�n es irreversible", "Question", 3,
					"z-msgbox z-msgbox-question", new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == 1) {
								ProveedoresVM.this.proveedorSelected.setProveedorActivo(false);

								proveedorSelected = (Proveedor) proveedorRest.save(proveedorSelected).getSingle();
								proveedoresLista.remove(proveedorSelected);

								StockUtils.showSuccessmessage(
										proveedorSelected.getNombre() + " ha sido eliminado", "info",
										0, null);
								proveedorSelected = null;
								return;
							}
						}
					});
		} else {
			StockUtils.showSuccessmessage("Seleccione un proveedor para llevar acabo la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void performSearch() {
		if ((this.buscarProveedor.getNombre() != null) && (!this.buscarProveedor.getNombre().isEmpty())) {
			if (this.buscarProveedor.getNombre().equals("*")) {
				proveedoresLista = (List<Proveedor>) proveedorRest.getAll(organizacion).getSingle();
			} else
				proveedoresLista = (List<Proveedor>) proveedorRest.getByClaveNombreRfc(buscarProveedor.getNombre(), organizacion).getSingle();
			if (this.proveedoresLista != null) {
				String mensaje = "";
				if (this.proveedoresLista.size() == 1) {
					mensaje = "proveedor";
				} else if (this.proveedoresLista.size() > 1) {
					mensaje = "proveedores";
				}
				if (this.buscarProveedor.getNombre().equals("*")) {
					StockUtils.showSuccessmessage("Tu b�squeda obtuvo todos los proveedores", "info",
							Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage("Tu b�squeda -" + this.buscarProveedor.getNombre() + "- obtuvo "
							+ this.proveedoresLista.size() + " " + mensaje, "info", Integer.valueOf(0), null);
				}
				this.buscarProveedor.setComentario(this.proveedoresLista.size() + " " + mensaje);
			} else {
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + this.buscarProveedor.getNombre() + "- no obtuvo ning�n resultado", "warning",
						Integer.valueOf(0), null);

				this.proveedorSelected = new Proveedor();
			}
		} else {
			StockUtils.showSuccessmessage("Tu b�squeda no obtuvo ning�n resultado", "error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void performSearchProveedorAsociacion() {
		this.proveedoresAsociacionSelected = null;
		if ((this.buscarProveedorAsociar.getNombre() != null) && (!this.buscarProveedorAsociar.getNombre().isEmpty())) {
			if (this.buscarProveedorAsociar.getNombre().equals("*")) {
				proveedoresAsociacion = (List<Proveedor>) proveedorRest.getAll(organizacion).getSingle();
			} else {
				proveedoresAsociacion = (List<Proveedor>) proveedorRest
						.getByClaveNombreRfc(this.buscarProveedorAsociar.getNombre(), organizacion).getSingle();
			}
			if (this.proveedoresAsociacion != null) {
				String mensaje = "";
				if (this.proveedoresAsociacion.size() == 1) {
					mensaje = "proveedor";
				} else if (this.proveedoresAsociacion.size() > 1) {
					mensaje = "proveedores";
				}
				if (this.buscarProveedorAsociar.getNombre().equals("*")) {
					StockUtils.showSuccessmessage("Tu b�squeda obtuvo todos los proveedores", "info",
							Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage(
							"Tu b�squeda -" + this.buscarProveedorAsociar.getNombre() + "- obtuvo "
									+ this.proveedoresAsociacion.size() + " " + mensaje,
							"info", Integer.valueOf(0), null);
				}
				this.buscarProveedorAsociar.setComentario(String.valueOf(this.proveedoresAsociacion.size()));
			} else {
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + this.buscarProveedorAsociar.getNombre() + "- no obtuvo ning�n resultado",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Tu b�squeda no obtuvo ning�n resultado", "error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void performSearchProductoAsociacion() {
		if ((this.buscarProducto.getNombre() != null) && (!this.buscarProducto.getNombre().isEmpty())) {
			if (this.buscarProducto.getNombre().equals("*")) {
				productosDB = (List<Producto>) productoRest.getAll(organizacion).getSingle();
			} else {
				productosDB = (List<Producto>) productoRest.getByClaveNombre(buscarProducto.getNombre(), organizacion).getSingle();
			}
			if (this.productosDB != null) {
				String mensaje = "";
				if (this.productosDB.size() == 1) {
					mensaje = "producto";
				} else if (this.productosDB.size() > 1) {
					mensaje = "productos";
				}
				if (this.buscarProducto.getNombre().equals("*")) {
					StockUtils.showSuccessmessage("Tu b�squeda obtuvo todos los proveedores", "info",
							Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage("Tu b�squeda -" + this.buscarProducto.getNombre() + "- obtuvo "
							+ this.productosDB.size() + " " + mensaje, "info", Integer.valueOf(0), null);
				}
				this.buscarProducto.setDescripcion(String.valueOf(this.productosDB.size()));
			} else {
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + this.buscarProducto.getNombre() + "- no obtuvo ning�n resultado", "warning",
						Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Tu b�squeda no obtuvo ning�n resultado", "error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosDeProveedor() {
		proveedorProductos = (List<ProveedorProducto>) proveedorProductoRest.getByProveedor(proveedoresAsociacionSelected).getSingle();
	}

	public static List<Banco> getCompleteBancos() {
		return completeBancos;
	}

	@Command
	public void reporteProveedores() {
		if (this.proveedoresLista != null) {
			
			
			ReportesBuild reporteador = new ReportesBuild();
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("executeNameMethod", "closeVisorPdf");
			inputParams.put("titulo", "Reporte de Proveedores");
			inputParams.put("fuente", reporteador.generarReporteProveedoresPdf(proveedoresLista, organizacion));
			
			Window windowModalView = stockUtils
					.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul", inputParams);
			windowModalView.doModal();
		} else {
			StockUtils.showSuccessmessage("NO existen resultados de busqueda para generar el reporte (PDF)",
					"error", Integer.valueOf(0), null);
		}
	}

	
	XSSFWorkbook workBook;
	
	@Command
	@NotifyChange("proveedoresLista")
	public void onUploadExcel(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		/*
		List<String> messageProcessing = new ArrayList<>();
		
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "finishProgress");
		inputParams.put("sizeProgress", 100);
		inputParams.put("timer", 1000);
		inputParams.put("mensajes", messageProcessing);
		inputParams.put("contexto", ctx);
		
		
		Window windowModalView = stockUtils
				.createModelDialogWithParams("/modulos/productos/utils/progressBarModel.zul", inputParams);

		windowModalView.doModal();
		*/
		
		estados = (List<Estado>) estadoRest.getAll().getSingle();
		municipios = (List<Municipio>) municipioRest.getAll().getSingle();
		paises = (List<Pais>) paisRest.getAll().getSingle();
		
		productosDB = (List<Producto>) productoRest.getAllNativeSQL(organizacion).getSingle();
		
		try {
			workBook = new XSSFWorkbook(getStreamMediaExcel(ctx));
			leerDatosDesdeExcel(0);//Extraer direccion de proveedores
			leerDatosDesdeExcel(1);//Extraer proveedores
			leerDatosDesdeExcel(2);//Extraer productos de proveedores
			if(proveedoresLista.size() > 0){
				Messagebox.show(proveedoresLista.size() + " Proveedores Importados");
			}else
				Messagebox.show("No se importaron Proveedores. El documento esta vacio");
			
		} catch (Exception e) {
			System.err.println("Error en la precarga inicial");
			e.printStackTrace();
		}
		
	}
	
	@GlobalCommand
	@NotifyChange({ "requisicion"})
	public void updateJustificacionSelected(
			@BindingParam("justificacionSeleccionado") Justificacion itemSeleccionado) {
		if (itemSeleccionado != null)
			if(requisicion != null)
				requisicion.setJustificacion(itemSeleccionado.getNombre());
	}

	@SuppressWarnings("rawtypes")
	public void leerDatosDesdeExcel(int indiceSheet) {
		XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
		Iterator rowIterator = hssfSheet.rowIterator();
		
		switch (indiceSheet) {
		case 0:
			extraerDireccionesDeExcel(rowIterator);
			break;
		case 1:
			extraerProveedoresDeExcel(rowIterator);
			break;
		case 2:
			extraerProductosProveedoresDeExcel(rowIterator);
			break;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerDireccionesDeExcel(Iterator rowIterator){
		List<Direccion> direccionTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Direccion nuevoDireccion= new Direccion();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 9)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoDireccion = crearDireccion(nuevoDireccion, hssfCell, j);
					j++;
				}
				direccionTemp.add(nuevoDireccion);
			}
			i++;
		}
		if(direccionTemp.size() > 0){
			for (Direccion item : direccionTemp) {
				item = (Direccion) direccionRest.save(item).getSingle();
			}
			
			direccionesList = direccionTemp;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private List<Proveedor> extraerProveedoresDeExcel(Iterator rowIterator){
		List<Proveedor> proveedorTemp = new ArrayList<>();
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		Usuarios usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Proveedor nuevoProveedor= new Proveedor();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 6)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoProveedor = crearProveedor(nuevoProveedor, hssfCell, j);
					j++;
				}
				nuevoProveedor.setFechaActualizacion(Calendar.getInstance());
				nuevoProveedor.setOrganizacion(organizacion);
				nuevoProveedor.setUsuario(usuario);
				proveedorTemp.add(nuevoProveedor);
			}
			i++;
		}
		if(proveedorTemp.size() > 0){
			for (Proveedor item : proveedorTemp) {
				item = (Proveedor) proveedorRest.save(item).getSingle();
			}
			proveedoresLista = proveedorTemp;
		}
		return proveedorTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<ProveedorProducto> extraerProductosProveedoresDeExcel(Iterator rowIterator){
		List<ProveedorProducto> proveedorProductoTemp = new ArrayList<>();
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		Usuarios usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ProveedorProducto nuevoProveedorProducto= new ProveedorProducto();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 3)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoProveedorProducto = crearProveedorProducto(nuevoProveedorProducto, hssfCell, j);
					j++;
				}
				nuevoProveedorProducto.setFechaActualizacion(Calendar.getInstance());
				nuevoProveedorProducto.setOrganizacion(organizacion);
				nuevoProveedorProducto.setUsuario(usuario);
				proveedorProductoTemp.add(nuevoProveedorProducto);
			}
			i++;
		}
		if(proveedorProductoTemp.size() > 0){
			for (ProveedorProducto item : proveedorProductoTemp) {
				item = (ProveedorProducto) proveedorProductoRest.save(item).getSingle();
			}
			proveedorProductos = proveedorProductoTemp;
		}
		return proveedorProductoTemp;
	}
	
	
	private Direccion crearDireccion(Direccion direccion, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setCalle(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setColonia(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setCp(valor);
			break;
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setCuidad(valor);
			break;
		case 4:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setNumExt(valor);
			break;
		case 5:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				direccion.setNumInt(valor);
			break;
		case 6:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				direccion.setEstado(iteratorList.getEstadoFromList(estados, Long.parseLong(valor)));
			}
				
			break;
		case 7:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				direccion.setMunicipio(iteratorList.getMunicipioFromList(municipios, Long.parseLong(valor)));
			}
			break;
		case 8:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				direccion.setPais(iteratorList.getPaisFromList(paises, Long.parseLong(valor)));
			}
			break;
		
		}
		return direccion;
	}
	
	
	private Proveedor crearProveedor(Proveedor proveedor, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				proveedor.setClave(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				proveedor.setCuentaCargo(Long.parseLong(valor));
			}
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				proveedor.setNombre(valor);
			break;
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				proveedor.setRfc(valor);
			break;
		case 4:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
					proveedor.setDireccionFiscal(iteratorList.getDireccionFromList(direccionesList, Long.parseLong(valor)));
				}
			}
			break;
		case 5:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
					if(valor.equals("1"))
						proveedor.setProveedorActivo(true);
					else
						proveedor.setProveedorActivo(false);
				}
			}
				
			break;
		}
		return proveedor;
	}
	
	
	private ProveedorProducto crearProveedorProducto(ProveedorProducto proveedorProducto, XSSFCell valorDePropiedad, int indice) {
		
		try {
			String valor = String.valueOf(valorDePropiedad);
			switch (indice) {
			case 0:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
					if (valor.contains(".0"))
						valor = stockUtilString.removerPuntoCero(valor);				
					proveedorProducto.setProducto(iteratorList.getProductoFromList(Long.parseLong(valor), productosDB));
				}
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
					if (valor.contains(".0"))
						valor = stockUtilString.removerPuntoCero(valor);				
					proveedorProducto.setProveedor(iteratorList.getProveedorFromList(Long.parseLong(valor), proveedoresLista));
				}
				break;
			case 2:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
					if (valor.contains(".0"))
						valor = stockUtilString.removerPuntoCero(valor);
					proveedorProducto.setCantidad(valor);
				}
				break;
			
			}
		} catch (Exception e) {
			
		}
		
		
		return proveedorProducto;
	}
	
	@Command
	@NotifyChange({ "*" })
	public void seleccionarComboEstado() {
		if (this.estadoProveedor != null) {
			municipios = (List<Municipio>) municipioRest.getByEstado(estadoProveedor).getSingle();
			System.err.println("cargando municipios de " + this.estadoProveedor.getNombre());
		}
	}

	@Command
	@NotifyChange({ "monedasDB" })
	public void selectTabCuentaPago() {
		monedasDB = (List<Moneda>) monedaRest.getAll(organizacion).getSingle();
	}
	
	@Command
	@NotifyChange({ "productosDB" })
	public void selectTabProductos() {
		if(proveedorSelected != null && proveedorSelected.getIdProveedor() != null){
			if(productosDB == null){
				productosDB = (List<Producto>) productoRest.getAllNativeSQL(organizacion).getSingle();
			}
		}
	}

	@Command
	@NotifyChange({ "productosDB", "proveedorProductos" })
	public void move(@ContextParam(ContextType.COMPONENT) Component self,
			@ContextParam(ContextType.TRIGGER_EVENT) DropEvent dropEvent) {
		Component dragged = dropEvent.getDragged();
		if (proveedorSelected != null && proveedorSelected.getIdProveedor() != null) {
			
			if (self instanceof Listitem)
				self.getParent().insertBefore(dragged, self);
			else
				self.appendChild(dragged);
			
			Producto movingItem = null;
			ProveedorProducto movingItem2 = null;
			
			try {
				movingItem = ((Listitem) dragged).getValue();
			} catch (Exception e) {
				movingItem2 = ((Listitem) dragged).getValue();
			}
			

			if (self.getParent().getId().equals("right")) {// se arrastra a: derecha |
				productosDB.remove(movingItem);
				
				movingItem = (Producto) productoRest.getById(movingItem.getIdProducto(), organizacion).getSingle();
				if(!verificarProductoNoSeEncuentreEnLaLista(movingItem)){
					ProveedorProducto objetoDerecha = new ProveedorProducto();
					objetoDerecha.setFechaActualizacion(Calendar.getInstance());
					objetoDerecha.setOrganizacion(organizacion);
					objetoDerecha.setUsuario(usuario);
					objetoDerecha.setProducto(movingItem);
					objetoDerecha.setProveedor(proveedorSelected);
					
					objetoDerecha = (ProveedorProducto) proveedorProductoRest.save(objetoDerecha).getSingle();
					
					proveedorProductos.add(objetoDerecha);
				}else
					StockUtils.showSuccessmessage(movingItem.getNombre() + " Este producto ya se encuentra en la lista del proveedor",
							Clients.NOTIFICATION_TYPE_WARNING, 0, dragged);
				
			} else if (self.getParent().getId().equals("left")) {
				movingItem = (Producto) productoRest.getById(movingItem2.getProducto().getIdProducto(), organizacion).getSingle();
				productosDB.add(movingItem);
				proveedorProductos.remove(movingItem2);
				proveedorProductoRest.delete(movingItem2);

				StockUtils.showSuccessmessage(movingItem.getNombre() + " ha sido removido --->",
						Clients.NOTIFICATION_TYPE_INFO, 0, dragged);
			}
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un producto primero", Clients.NOTIFICATION_TYPE_WARNING, 0,
					dragged);
	}
	
	private boolean verificarProductoNoSeEncuentreEnLaLista(Producto suchenProduct){
		boolean encontrado = false;
		for (ProveedorProducto item : proveedorProductos) {
			if(suchenProduct.getIdProducto().equals(item.getProducto().getIdProducto())){
				encontrado = true;
				break;
			}
		}
		return encontrado;
	}
	
	@Command
	@NotifyChange("bandBoxEstadoOpen")
	public void abrirBandboxEstado() {
		bandBoxEstadoOpen = true;
	}
	
	@Command
	@NotifyChange("bandBoxEstadoOpen")
	public void cerrarBandboxEstado() {
		bandBoxEstadoOpen = false;
	}
	
	@Command
	@NotifyChange("bandBoxEstadoOpen")
	public void cancelarBandboxEstado() {
		bandBoxEstadoOpen = false;
	}
	
	/*
	@GlobalCommand
    @Command
    @NotifyChange("*")
    public void makeProgress(){
        int progress = 1;
        if(progress == 100){
            return;
        }
        progress++;
            Thread.sleep(100); // do some part of time consuming work

        setProgress(progress);

        BindUtils.postGlobalCommand(null, null, "makeProgress", null);
    }
	*/
}
