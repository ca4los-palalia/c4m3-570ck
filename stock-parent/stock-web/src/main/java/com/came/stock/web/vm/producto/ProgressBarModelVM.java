package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.ProcesoTime;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ProgressBarModelVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#progressBarVMModalDialog")
	private Window windowModalDialog;
	@Wire("#progressBarVMModalDialog, timer")
	private Timer timer;
	@Wire
	private Label selectedText;
	@Wire
	private Progressmeter progress;

	private int all, current = 0;
	BindContext ctx;
	private List<ProcesoTime> procesos;
	
	
	
	private String globalCommandName;

	

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("sizeProgress") Integer sizeProgress,
			@ExecutionArgParam("timer") Integer timerProgress,
			@ExecutionArgParam("mensajes") List<String> mensajes,
			@ExecutionArgParam("contexto") BindContext ctx) {
		Selectors.wireComponents(view, this, false);
		this.ctx = ctx;
		globalCommandName = updateCommandFromItemFinder;
		procesos = new ArrayList<>();
		
		ProcesoTime p1 = new ProcesoTime();
		p1.setNombre("Estados");
		p1.setTime(117f);
		
		ProcesoTime p2 = new ProcesoTime();
		p2.setNombre("Municipios");
		p2.setTime(113f);
		
		ProcesoTime p3 = new ProcesoTime();
		p3.setNombre("Paises");
		p3.setTime(73f);
		
		ProcesoTime p4 = new ProcesoTime();
		p4.setNombre("Productos");
		p4.setTime(143f);
		
		ProcesoTime p5 = new ProcesoTime();
		p5.setNombre("Leer excel");
		p5.setTime(439f);
		
		ProcesoTime p6 = new ProcesoTime();
		p6.setNombre("Direccion proveedores");
		p6.setTime(463f);
		
		ProcesoTime p7 = new ProcesoTime();
		p7.setNombre("Extraer proveedorees");
		p7.setTime(361f);
		
		ProcesoTime p8 = new ProcesoTime();
		p8.setNombre("Creando producto proveedor");
		p8.setTime(747f);
		
		ProcesoTime p9 = new ProcesoTime();
		p9.setNombre("Insertando proveedores a DB");
		p9.setTime(14241f);
		
		procesos.add(p1);
		procesos.add(p2);
		procesos.add(p3);
		procesos.add(p4);
		procesos.add(p5);
		procesos.add(p6);
		procesos.add(p7);
		procesos.add(p8);
		procesos.add(p9);
		//leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		
		selectedText = (Label) windowModalDialog.getFellow("borderLay").getFellow("winProgress").getFellow("selectedText");
		progress = (Progressmeter) windowModalDialog.getFellow("borderLay").getFellow("winProgress").getFellow("progress");
	}

	
	boolean start;
	@Command
	public void startTimer() {
		if(!start){
			System.err.println("Start");
			start = true;
			inicializarProgressBar();
			realizarProcesos();
		}
		
	}
	
	private void inicializarProgressBar(){
		//all = fetchingItems.size();
		all = 10;
		current = 0;
		progress.setValue(0);
		timer.start();
	}
	
	XSSFWorkbook workBook;
	int percent = 0;
	
	
	
	private Integer getPorcentaje(Float ml){
		Integer cien = 16717;
		Float operacion = (ml * 100f) / new Float(cien.toString());
		return Math.round(operacion);
	}
	
	@Command
	public void fetchingSimulatorTimer() {
		try {
			//int percent = ++current * 100 / all;
			progress.setValue(percent);
			
		} catch (Exception e) {
			progress.setValue(100);
			//selectedText.setValue("100%");
			timer.stop();
			System.err.println("FINISH PROGRESS " + e.getMessage());
			
		}
		
	}
	
	private void realizarProcesos(){
		
		float time_start, time_end;
		time_start = System.currentTimeMillis();
		percent = getPorcentaje(procesos.get(0).getTime());
		estados = estadoService.getAll();
		time_end = System.currentTimeMillis();
		System.err.println("Estados: " + (time_end - time_start) + " " + percent + "%");
		
		
		
		
		time_start = System.currentTimeMillis();
		percent = getPorcentaje(procesos.get(1).getTime());
		municipios = municipioService.getAll();
		time_end = System.currentTimeMillis();
		System.err.println("Municipios: " + (time_end - time_start) + " " + percent + "%");
		
		time_start = System.currentTimeMillis();
		percent = getPorcentaje(procesos.get(2).getTime());
		paises = paisService.getAll();
		time_end = System.currentTimeMillis();
		System.err.println("Paises: " + (time_end - time_start) + " " + percent + "%");
		
		time_start = System.currentTimeMillis();
		percent = getPorcentaje(procesos.get(3).getTime());
		productosDB = productoService.getAllNativeSQL();
		time_end = System.currentTimeMillis();
		System.err.println("Productos: " + (time_end - time_start) + " " + percent + "%");
		
		
			try {
				time_start = System.currentTimeMillis();
				percent = getPorcentaje(procesos.get(4).getTime());
				workBook = new XSSFWorkbook(getStreamMediaExcel(ctx));
				time_end = System.currentTimeMillis();
				System.err.println("Excel read: " + (time_end - time_start) + " " + percent + "%");
				
				
				time_start = System.currentTimeMillis();
				percent = getPorcentaje(procesos.get(5).getTime());
				leerDatosDesdeExcel(0);//Extraer direccion de proveedores
				time_end = System.currentTimeMillis();
				System.err.println("Direccion proveedores: " + (time_end - time_start) + " " + percent + "%");
				
				
				time_start = System.currentTimeMillis();
				percent = getPorcentaje(procesos.get(6).getTime());
				leerDatosDesdeExcel(1);//Extraer proveedores
				time_end = System.currentTimeMillis();
				System.err.println("Extraer proveedore4s: " + (time_end - time_start) + " " + percent + "%");
				
				
				leerDatosDesdeExcel(2);//Extraer productos de proveedores
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.err.println();
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		
		windowModalDialog.detach();
		Map<String, Object> args = new HashMap();
		//args.put("justificacionSeleccionado", justificacion);
		if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		} else {
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
		}
	
	}


	

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_JUSTIFICACION_TITLE = propiedades.getProperty("window.select.justificacion.title");//Seleccionar justificacion de la requisición
		WINDOW_SELECT_JUSTIFICACION_EMPTY = propiedades.getProperty("window.select.justificacion.empty");//Catalogo de justificaciones vacio
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
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
				direccionService.save(item);
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
				proveedorService.save(item);
			}
			proveedoresLista = proveedorTemp;
		}
		return proveedorTemp;
	}
	
	@SuppressWarnings("rawtypes")
	private List<ProveedorProducto> extraerProductosProveedoresDeExcel(Iterator rowIterator){
		float time_start, time_end;
		
		List<ProveedorProducto> proveedorProductoTemp = new ArrayList<>();
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		Usuarios usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		
		
		time_start = System.currentTimeMillis();
		percent = getPorcentaje(procesos.get(7).getTime());
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
		time_end = System.currentTimeMillis();
		System.err.println("Creando producto proveedor: " + (time_end - time_start) + " " + percent + "%");
		
		if(proveedorProductoTemp.size() > 0){
			time_start = System.currentTimeMillis();
			percent = getPorcentaje(procesos.get(8).getTime());
			for (ProveedorProducto item : proveedorProductoTemp) {
				proveedorProductoService.save(item);
			}
			proveedorProductos = proveedorProductoTemp;
			time_end = System.currentTimeMillis();
			System.err.println("Salvando proveedores a DB: " + (time_end - time_start) + " " + percent + "%");
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
					valor = removerPuntoCero(valor);
				}
				direccion.setEstado(getEstadoFromList(Long.parseLong(valor)));
			}
				
			break;
		case 7:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				direccion.setMunicipio(getMunicipioFromList(Long.parseLong(valor)));
			}
			break;
		case 8:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				direccion.setPais(getPaisFromList(Long.parseLong(valor)));
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
					valor = removerPuntoCero(valor);
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
					valor = removerPuntoCero(valor);
					proveedor.setDireccionFiscal(getDireccionFromList(Long.parseLong(valor)));
				}
			}
			break;
		case 5:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
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
						valor = removerPuntoCero(valor);				
					proveedorProducto.setProducto(getProductoFromList(Long.parseLong(valor), productosDB));
				}
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
					if (valor.contains(".0"))
						valor = removerPuntoCero(valor);				
					proveedorProducto.setProveedor(getProveedorFromList(Long.parseLong(valor), proveedoresLista));
				}
				break;
			case 2:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
					if (valor.contains(".0"))
						valor = removerPuntoCero(valor);
					proveedorProducto.setCantidad(valor);
				}
				break;
			
			}
		} catch (Exception e) {
			
		}
		return proveedorProducto;
	}
	
	
}
