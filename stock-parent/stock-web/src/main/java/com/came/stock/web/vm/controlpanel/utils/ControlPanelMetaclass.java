package com.came.stock.web.vm.controlpanel.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.XML;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.util.Clients;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ControlPanelMetaclass extends ControlPanelVariables {
	public static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
	}

	public void initProperties() {
		
	}

	// ********************************************************************************************


	public void guardarBanco() {
		if(bancoSeleccionado != null){
			if(bancoSeleccionado.getNombre() != null && !bancoSeleccionado.getNombre().isEmpty()){
				String mensaje = "";
				if(bancoSeleccionado.getIdBanco() == null){
					bancoSeleccionado.setToolTipIndice("Seleccionar un banco");
					bancoSeleccionado.setOrganizacion(organizacion);
					bancoSeleccionado.setUsuario(usuario);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				bancoSeleccionado.setFechaActualizacion(date);
				bancoSeleccionado.setNuevoRegistro(false);
				
				bancoSeleccionado = (Banco) bancoRest.save(bancoSeleccionado).getSingle();
				
				if(!bancosDB.contains(bancoSeleccionado)){
					bancosDB.add(bancoSeleccionado);
					mensaje = "Se ha creado un nuevo Banco";
				}else
					mensaje = "El banco " + bancoSeleccionado.getNombre() + " ha sido nodificado";
				
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0,
						compListBancosCatalogo);
			}else
				StockUtils.showSuccessmessage("Nombre del banco requerido",
						Clients.NOTIFICATION_TYPE_WARNING, 0,
						compTxtBancoNombre);
		}else
			StockUtils.showSuccessmessage("Seleccione un banco para realizar modificacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0,
					compListBancosCatalogo);
	}

	public void guardarConffya() {
	}

	public void guardarContratos() {
		if(contrato != null){
			if(contrato.getNombre() != null && !contrato.getNombre().isEmpty()){
				if(contrato.getFechaVigenciaInicioDate() != null){
					if(contrato.getFechaVigenciaFinDate() != null){
						String mensaje ="";
						if(contrato.getIdContrato() == null){
							contrato.setOrganizacion(organizacion);
							contrato.setUsuario(usuario);
							mensaje = contrato.getNombre() + " ha sido creado y agregado a la lista";
						}else
							mensaje = contrato.getNombre() + " ha sido modificado";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
						String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
						contrato.setFechaActualizacion(date);
						
						contrato = (Contrato) contratoRest.save(contrato).getSingle();
						if(!contratos.contains(contrato))
							contratos.add(contrato);
						StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO,
								0, compListContratosCatalogo);
					}else
						StockUtils.showSuccessmessage("Fecha de fin de vigencia es requerido", Clients.NOTIFICATION_TYPE_WARNING,
								0, compDateBoxVigenciaFin);
				}else
					StockUtils.showSuccessmessage("Fecha de inicio de vigencia es requerido", Clients.NOTIFICATION_TYPE_WARNING,
							0, compDateBoxVigenciaInicio);
			}else
				StockUtils.showSuccessmessage("Nombre de contrato requerido", Clients.NOTIFICATION_TYPE_WARNING,
						0, compTxtNombreContrato);
		}else
			StockUtils.showSuccessmessage("Seleccione un Contrato para poder realizar cambios", Clients.NOTIFICATION_TYPE_WARNING,
					0, compListContratosCatalogo);
	}

	public void guardarMoneda() {
		if(monedaSeleccionada != null){
			if(monedaSeleccionada.getNombre() != null && !monedaSeleccionada.getNombre().isEmpty()){
				String mensaje = "";
				monedaSeleccionada.setNuevoRegistro(false);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				if(monedaSeleccionada.getIdMoneda() == null){
					monedaSeleccionada.setOrganizacion(organizacion);
					monedaSeleccionada.setUsuario(usuario);
				}
				monedaSeleccionada.setFechaActualizacion(date);
				monedaSeleccionada = (Moneda) monedaRest.save(monedaSeleccionada).getSingle();
				if(!monedasDB.contains(monedaSeleccionada)){
					monedasDB.add(monedaSeleccionada);
					mensaje = monedaSeleccionada.getNombre() + " ha sido creada";
				}else
					mensaje = monedaSeleccionada.getNombre() + " ha sido Actualizada";
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, compListDivisasCatalogo);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre de la divisa requerido!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombreDivisa);
		}else{
			StockUtils.showSuccessmessage("Seleccione una divisa...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListDivisasCatalogo);
			StockUtils.showSuccessmessage("... O cree una nueva divisa para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevaDivisa);
		}
		/*
		for (Moneda monedaRecord : monedasDB) {
			try {
				monedaRecord.setToolTipIndice("Seleccionar una moneda");

				monedaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((monedaRecord.getNombre() != null) && (!monedaRecord.getNombre().equals(""))) {
					monedaRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					monedaRecord.setFechaActualizacion(date);
					monedaRecord.setOrganizacion(organizacion);

					monedaRecord.setUsuario(usuario);

					monedaService.save(monedaRecord);
				}
			} catch (Exception e) {
			}
		}
		monedasDB.clear();
		monedasDB = monedaService.getAll();
		StockUtils.showSuccessmessage("El catalogo de divisas ha sido actualizado", "info", Integer.valueOf(0), null);
		*/
	}

	public void guardarProductos() {
	}

	public void guardarProveedores() {
	}

	public void guardarPuesto() {
		if(posicion != null){
			if(posicion.getNombre() != null && !posicion.getNombre().isEmpty()){
				String mensaje = "";
				posicion.setNuevoRegistro(false);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				posicion.setFechaActualizacion(date);
				posicion = (Posicion) posicionRest.save(posicion).getSingle();
				
				if(!posiciones.contains(posicion)){
					posiciones.add(posicion);
					mensaje = posicion.getNombre() + " ha sido creada";
				}else
					mensaje = posicion.getNombre() + " ha sido Actualizada";
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, compListPuestosCatalogo);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre del puesto requerido!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombrePosicion);
		}else{
			StockUtils.showSuccessmessage("Seleccione un puesto...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListPuestosCatalogo);
			StockUtils.showSuccessmessage("... O cree un nuevo puesto para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevoPuesto);
		}
		/*
		for (Posicion posicionRecord : posiciones) {
			try {
				posicionRecord.setToolTipIndice("Seleccionar puesto");
				posicionRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				posicionRecord.setFechaActualizacion(date);
				posicionRecord.setOrganizacion(organizacion);

				posicionRecord.setUsuario(usuario);
				if (posicionRecord.isNuevoRegistro()) {
					posicionRecord.setNuevoRegistro(false);
					posicionRecord.setIdPosicion(null);
					posicionService.save(posicionRecord);
				} else if (!posicionRecord.getNombre().equals("")) {
					posicionService.update(posicionRecord);
				}
			} catch (Exception e) {
			}
		}
		posiciones.clear();
		posiciones = posicionService.getAll();

		StockUtils.showSuccessmessage("Catalogo de puestos actualizados", "info", Integer.valueOf(0), null);
		*/
	}

	public void guardarProductoTipo() {
		if(productoTipoSelected != null){
			if(productoTipoSelected.getNombre() != null && !productoTipoSelected.getNombre().isEmpty()){
				String mensaje = "";
				productoTipoSelected.setNuevoRegistro(false);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				productoTipoSelected.setFechaActualizacion(date);
				productoTipoSelected = (ProductoTipo) productoTipoRest.save(productoTipoSelected).getSingle();
				if(!productoTipoDB.contains(productoTipoSelected)){
					productoTipoDB.add(productoTipoSelected);
					mensaje = productoTipoSelected.getNombre() + " ha sido creada";
				}else
					mensaje = productoTipoSelected.getNombre() + " ha sido Actualizada";
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, compListTipoProductosCatalogo);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre del tipo de producto requerido!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombreTipoProductos);
		}else{
			StockUtils.showSuccessmessage("Seleccione un tipo de producto...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListTipoProductosCatalogo);
			StockUtils.showSuccessmessage("... O cree un nuevo tipo de producto para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevoTipoProductos);
		}
		/*
		for (ProductoTipo productoTipoRecord : productoTipoDB) {
			try {
				productoTipoRecord.setToolTipIndice("Seleccionar un tipo de producto");

				productoTipoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				productoTipoRecord.setFechaActualizacion(date);
				productoTipoRecord.setOrganizacion(organizacion);

				productoTipoRecord.setUsuario(usuario);
				if (productoTipoRecord.isNuevoRegistro()) {
					productoTipoRecord.setNuevoRegistro(false);
					productoTipoRecord.setIdProductoTipo(null);
					productoTipoService.save(productoTipoRecord);
				} else if (!productoTipoRecord.getNombre().equals("")) {
					productoTipoService.update(productoTipoRecord);
				}
			} catch (Exception e) {
			}
		}
		productoTipoDB.clear();
		productoTipoDB = productoTipoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de tipo de productos actualizado", "info", Integer.valueOf(0), null);
		*/
	}

	public void guardarUnidades() {
		if(unidad != null){
			if(unidad.getNombre() != null && !unidad.getNombre().isEmpty()){
				String mensaje = "";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				if(unidad.getIdUnidad() == null){
					unidad.setOrganizacion(organizacion);
					unidad.setUsuario(usuario);
				}
				unidad.setFechaActualizacion(Calendar.getInstance());
				unidad = (Unidad) unidadRest.save(unidad).getSingle();
				
				
				if(!unidadesDB.contains(unidad)){
					unidadesDB.add(unidad);
					mensaje = unidad.getNombre() + " ha sido creada";
				}else{
					mensaje = unidad.getNombre() + " ha sido Actualizada";
				}
				
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, compListUnidadesCatalogo);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre de la unidad requerida!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombreUnidades);
		}else{
			StockUtils.showSuccessmessage("Seleccione una unidad...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListUnidadesCatalogo);
			StockUtils.showSuccessmessage("... O cree una nueva unidad para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevoUnidades);
		}
		/*
		if ((unidadesDB != null) && (unidadesDB.size() > 0)) {
			for (Unidad item : unidadesDB) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setFechaActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);

					item.setUsuario(usuario);

					unidadService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Unidades de medida-", "info",
					Integer.valueOf(0), null);

			mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage(
					"No se puede llevar a cabo una actualizacion en el catalogo -Unidades de medida-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
		*/
	}

	public void guardarGiros() {
		if(giro != null){
			if(giro.getNombre() != null && !giro.getNombre().isEmpty()){
				String mensaje = "";
				if(giro.getIdGiro() == null)
					giro.setOrganizacion(organizacion);
				giro = (Giro) giroRest.save(giro).getSingle();
				if(!giros.contains(giro)){
					giros.add(giro);
					mensaje = giro.getNombre() + " ha sido creada";
				}else
					mensaje = giro.getNombre() + " ha sido Actualizada";
				StockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, compListGirosCatalogo);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre del giro requerido!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombreGiros);
		}else{
			StockUtils.showSuccessmessage("Seleccione un giro...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListGirosCatalogo);
			StockUtils.showSuccessmessage("... o cree un giro para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevoGiros);
		}
		/*
		if ((giros != null) && (giros.size() > 0)) {
			for (Giro item : giros) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setOrganizacion(organizacion);
					giroService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Giros-", "info",
					Integer.valueOf(0), null);

			mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage("No se puede llevar la actualizacion en el catalogo -Giros-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
		*/
	}

	public void guardarProductoNaturaleza() {
		if(productoNaturaleza != null){
			if(productoNaturaleza.getNombre() != null && !productoNaturaleza.getNombre().isEmpty()){
				if(productoNaturaleza.getSimbolo() != null && !productoNaturaleza.getSimbolo().isEmpty()){
					String mensaje = "";
					
					productoNaturaleza = (ProductoNaturaleza) productoNaturalezaRest.save(productoNaturaleza).getSingle();
					if(!productosNaturalezas.contains(productoNaturaleza)){
						productosNaturalezas.add(productoNaturaleza);
						mensaje = productoNaturaleza.getNombre() + " ha sido creada";
					}else
						mensaje = productoNaturaleza.getNombre() + " ha sido Actualizada";
					StockUtils.showSuccessmessage(mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, compListProductoNaturalezaCatalogo);
				}else
					StockUtils.showSuccessmessage("La clave maestra es requerida",
							Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtClaveMaestraProductoNaturaleza);
					
			}else
				StockUtils.showSuccessmessage("¡Nombre del producto naturaleza requerido!",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtNombreProductoNaturaleza);
		}else{
			StockUtils.showSuccessmessage("Seleccione un producto naturaleza...",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compListProductoNaturalezaCatalogo);
			StockUtils.showSuccessmessage("... o cree un nuevo producto naturaleza para actualizar informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnNuevoProductoNaturaleza);
		}
		
		/*
		if ((productosNaturalezas != null) && (productosNaturalezas.size() > 0)) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					productoNaturalezaService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Naturaleza de productos-",
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"No se puede llevar la actualizacion en el catalogo -Naturaleza de productos-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
		*/
	}

	// *********************************************************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Area crearColumnaVaciaArea() {
		Area areaVacia = new Area();
		if (areas != null) {
			areaVacia.setIdArea(Long.valueOf(String.valueOf(areas.size() + 1)));
		} else {
			areas = new ArrayList();
			areaVacia.setIdArea(Long.valueOf(1L));
		}
		areaVacia.setNuevoRegistro(true);
		areaVacia.setNombre("");
		areaVacia.setToolTipIndice("Seleccionar �rea");
		areaVacia.setToolTipNombre("Clic sobre esta columna para editar nombre");

		return areaVacia;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Posicion crearColumnaVaciaP() {
		Posicion objeto = new Posicion();
		if (posiciones != null) {
			objeto.setIdPosicion(Long.valueOf(String.valueOf(posiciones.size() + 1)));
		} else {
			posiciones = new ArrayList();
			objeto.setIdPosicion(Long.valueOf(1L));
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar una posicion");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	public Area crearColumnaVaciaAreaEstandar() {
		Area objeto = new Area();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un �rea");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	public Almacen crearColumnaVaciaAlmacenEstandar() {
		Almacen objeto = new Almacen();
		objeto.setArea(area);
		objeto.setDescripcion("");
		objeto.setNombre("");
		objeto.setOrganizacion(organizacion);
		objeto.setDireccion(new Direccion());
		return objeto;
	}

	public Banco crearColumnaVaciaBanco() {
		Banco objeto = new Banco();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un banco");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	public Contrato crearColumnaVaciaContrato() {
		Contrato objeto = new Contrato();
		return objeto;
	}

	public Moneda crearColumnaVaciaMonedas() {
		Moneda objeto = new Moneda();
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProductoTipo crearColumnaVaciaTipoProducto() {
		ProductoTipo objeto = new ProductoTipo();
		if (productoTipoDB != null) {
			objeto.setIdProductoTipo(Long.valueOf(String.valueOf(productoTipoDB.size() + 1)));
		} else {
			productoTipoDB = new ArrayList();
			objeto.setIdProductoTipo(Long.valueOf(1L));
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	// **********************************************************************************************
	public void recargarAreas() {
		if (areas == null) {
			areas.add(crearColumnaVaciaArea());
		} else {
			try {
				if (!((Area) areas.get(areas.size() - 1)).getNombre().equals("")) {
					areas.add(crearColumnaVaciaArea());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarPosiciones() {
		if ((posiciones == null) || (posiciones.size() == 0)) {
			try {
				posiciones = new ArrayList();
				posiciones.add(crearColumnaVaciaP());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Posicion) posiciones.get(posiciones.size() - 1)).getNombre().equals("")) {
					posiciones.add(crearColumnaVaciaP());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarBanco() {
		if ((bancosDB == null) || (bancosDB.size() == 0)) {
			try {
				bancosDB = new ArrayList();
				bancosDB.add(crearColumnaVaciaBanco());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Banco) bancosDB.get(bancosDB.size() - 1)).getNombre().equals("")) {
					bancosDB.add(crearColumnaVaciaBanco());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarMonedas() {
		if ((monedasDB == null) || (monedasDB.size() == 0)) {
			try {
				monedasDB = new ArrayList();
				monedasDB.add(crearColumnaVaciaMonedas());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Moneda) monedasDB.get(monedasDB.size() - 1)).getNombre().equals("")) {
					monedasDB.add(crearColumnaVaciaMonedas());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarProductoTipo() {
		if ((productoTipoDB == null) || (productoTipoDB.size() == 0)) {
			try {
				productoTipoDB = new ArrayList();
				productoTipoDB.add(crearColumnaVaciaTipoProducto());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((ProductoTipo) productoTipoDB.get(productoTipoDB.size() - 1)).getNombre().equals("")) {
					productoTipoDB.add(crearColumnaVaciaTipoProducto());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	// **********************************************************************************************

	/*
	 * public void activarBotonesAreas() { selectTab.setTabAreas(true);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar �rea"); selectTab.setToolTipDelete("Eliminar �rea");
	 * }
	 * 
	 * public void activarBotonesBancos() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(true); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar banco"); selectTab.setToolTipDelete("Eliminar banco"
	 * ); }
	 * 
	 * public void activarBotonesConffya() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(true);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(true);
	 * selectTab.setActivarButtonSave(true);
	 * selectTab.setVisibleButtonSave(false); selectTab.setToolTipSave(
	 * "Actualizar/Guardar banco"); selectTab.setToolTipDelete("Eliminar banco"
	 * ); }
	 * 
	 * public void activarBotonesContrato() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(true); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar contrato"); selectTab.setToolTipDelete(
	 * "Eliminar contrato"); }
	 * 
	 * public void activarBotonesMonedas() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(true);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar divisa"); selectTab.setToolTipDelete(
	 * "Eliminar divisa"); }
	 * 
	 * public void activarBotonesProductos() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(true); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(false); }
	 * 
	 * public void activarBotonesProveedores() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(true);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(false); }
	 * 
	 * public void activarBotonesPuestos() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(true); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar puesto"); selectTab.setToolTipDelete(
	 * "Eliminar puesto"); }
	 * 
	 * public void activarBotonesTiposProductos() {
	 * selectTab.setTabAreas(false); selectTab.setTabBancos(false);
	 * selectTab.setTabConffya(false); selectTab.setTabContratos(false);
	 * selectTab.setTabDivisas(false); selectTab.setTabProductos(false);
	 * selectTab.setTabProveedores(false); selectTab.setTabPuestos(false);
	 * selectTab.setTabTipoProductos(true); selectTab.setTabUnidades(false);
	 * selectTab.setActivarButtonDelete(false);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar tipo de productos"); selectTab.setToolTipDelete(
	 * "Eliminar productos"); }
	 * 
	 * public void activarBotonesUnidades() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(true); selectTab.setActivarButtonDelete(true);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar unidad de medida"); selectTab.setToolTipDelete(
	 * "Eliminar productos"); }
	 * 
	 * public void activarBotonesGiros() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setTabGiros(true);
	 * selectTab.setActivarButtonDelete(true);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar giro"); selectTab.setToolTipDelete("Eliminar giro");
	 * }
	 * 
	 * public void activarBotonesNaturaleza() { selectTab.setTabAreas(false);
	 * selectTab.setTabBancos(false); selectTab.setTabConffya(false);
	 * selectTab.setTabContratos(false); selectTab.setTabDivisas(false);
	 * selectTab.setTabProductos(false); selectTab.setTabProveedores(false);
	 * selectTab.setTabPuestos(false); selectTab.setTabTipoProductos(false);
	 * selectTab.setTabUnidades(false); selectTab.setTabGiros(false);
	 * selectTab.setTabNaturaleza(true); selectTab.setActivarButtonDelete(true);
	 * selectTab.setActivarButtonSave(false);
	 * selectTab.setVisibleButtonSave(true); selectTab.setToolTipSave(
	 * "Actualizar/Guardar giro"); selectTab.setToolTipDelete("Eliminar giro");
	 * }
	 * 
	 */

	// ***************************************************************************
	/*
	 * public void eliminarArea() { if (area != null) {
	 * areaService.delete(area);
	 * 
	 * areas.clear(); areas = areaService.getAll(); if ((areas != null) &&
	 * (!((Area) areas.get(areas.size() - 1)).getNombre().equals(""))) {
	 * areas.add(crearColumnaVaciaArea()); } else { Area nuevo =
	 * crearColumnaVaciaArea(); areas.add(nuevo); }
	 * StockUtils.showSuccessmessage(area.getNombre() + " ha sido eliminado",
	 * "info", Integer.valueOf(0), null); } else {
	 * StockUtils.showSuccessmessage(
	 * "Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
	 * Integer.valueOf(0), null); } }
	 */
	public boolean validarCamposALmacenes() {
			if (almacenSelected.getNombre().equals("")) {
				StockUtils.showSuccessmessage("Nombre del almacen requerido",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtAlmacenNombre);
				return false;
			}else if (almacenSelected.getDireccion().getCalle().equals("")) {
				StockUtils.showSuccessmessage("Nombre de calle requerido (Dirección)",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtAlmacenCalle);
				return false;
			}else if (almacenSelected.getDireccion().getNumExt().equals("")) {
				StockUtils.showSuccessmessage("Numero exterior requerido (Dirección)",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtAlmacenNumero);
				return false;
			}else if (almacenSelected.getDireccion().getCp() == null || almacenSelected.getDireccion().getCp().equals("")) {
				StockUtils.showSuccessmessage("Codigo postal requerido (Direccion)",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtAlmacenCp);
				return false;
			}else if(almacenSelected.getDireccion().getEstado() == null){
				StockUtils.showSuccessmessage("Clic en buscar codigo postal",
						Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnBuscaCp);
				return false;
			}else
				return true;
	}

	// --------- UNIDAD RESPONSABLE ---------------------------
	public int exportarXmlToDBUnidadResponsable(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);
		area = new Area();
		areasUr = new ArrayList<>();
		contadorCamposJson = 0;

		dumpUnidadResponsableJSONElement(json);
		if (areasUr != null) {
			
			Organizacion org = iteratorList.getOrganizacionByNumeroAndEjercicio(organizaciones, orgIn);
			areas = (List<Area>) areaRest.getByOrganizacion(org).getSingle();
			for (Area item : areasUr) {
				if (iteratorList.getAreasFromListByNombreAndOrganizacion(areas, item.getNombre(), org.getIdOrganizacion()) == null) {
					item.setOrganizacion(org);
					item = (Area) areaRest.save(item).getSingle();
					count++;
				}
			}
		}

		return count;

	}

	public void dumpUnidadResponsableJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					area = construirUnidadResponsable(entrada.getKey(), entrada.getValue(), area);
				if (contadorCamposJson == 2) {
					area.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					area.setNuevoRegistro(true);
					// area.setOrganizacion(geto);
					area.setUsuario(usuario);

					areasUr.add(area);
					area = new Area();
					contadorCamposJson = 0;
				}

				dumpUnidadResponsableJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpUnidadResponsableJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public Area construirUnidadResponsable(String key, JsonElement value, Area areaBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						areaBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						areaBuiding.setNombre(stringOut);
						areaBuiding.setDescripcion(stringOut);
						contadorCamposJson++;
					}
				}
			}
		}
		return areaBuiding;
	}
	// --------- FIN UNIDAD RESPONSABLE CATALOGO ---------------------------

	// --------- FUENTE FINANCIAMIENTO ---------------------------
	public int exportarXmlToDBFuenteFinanciamiento(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);

		cofiaFuenteFinanciamiento = new ConffyaFuenteFinanciamiento();
		cofiaFuenteFinanciamientos = new ArrayList<ConffyaFuenteFinanciamiento>();
		contadorCamposJson = 0;

		dumpFuenteFinanciamientoJSONElement(json);
		if (cofiaFuenteFinanciamientos != null) {
			List<ConffyaFuenteFinanciamiento> list = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest.getAll(orgIn).getSingle();
			for (ConffyaFuenteFinanciamiento item : cofiaFuenteFinanciamientos) {
				
				if (iteratorList.getFuenteFinanciamientoFromListByClave(item, list, orgIn) == null) {
					item.setOrganizacion(orgIn);
					item = (ConffyaFuenteFinanciamiento) conffyaFuenteFinanciamientoRest.save(item).getSingle();
					count++;
				}
			}
		}
		return count;
	}

	public void dumpFuenteFinanciamientoJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					cofiaFuenteFinanciamiento = construirFuenteFinanciamiento(entrada.getKey(), entrada.getValue(),
							cofiaFuenteFinanciamiento);
				if (contadorCamposJson == 2) {
					cofiaFuenteFinanciamiento
							.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					cofiaFuenteFinanciamiento.setUltimaActualizacion(Calendar.getInstance());
					// cofiaFuenteFinanciamiento.setOrganizacion(organizacion);
					cofiaFuenteFinanciamiento.setUsuario(usuario);
					cofiaFuenteFinanciamientos.add(cofiaFuenteFinanciamiento);
					cofiaFuenteFinanciamiento = new ConffyaFuenteFinanciamiento();
					contadorCamposJson = 0;
				}

				dumpFuenteFinanciamientoJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpFuenteFinanciamientoJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public ConffyaFuenteFinanciamiento construirFuenteFinanciamiento(String key, JsonElement value,
			ConffyaFuenteFinanciamiento fuenteFinanciamientoBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						fuenteFinanciamientoBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						fuenteFinanciamientoBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}
				}
			}
		}
		return fuenteFinanciamientoBuiding;
	}

	// --------- FIN FUENTE FINANCIAMIENTO ---------------------------

	// --------- PARTIDA GENERICA -------------------------------
	public int exportarXmlToDBPartidaGenerica(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);

		cofiaPartidaGenerica = new ConffyaPartidaGenerica();
		cofiaPartidaGenericas = new ArrayList<ConffyaPartidaGenerica>();
		contadorCamposJson = 0;

		dumpPartidaGenericaJSONElement(json);
		if (cofiaPartidaGenericas != null) {
			List<ConffyaPartidaGenerica> list = (List<ConffyaPartidaGenerica>) conffyaPartidaGenericaRest.getAll(orgIn).getSingle();
			for (ConffyaPartidaGenerica item : cofiaPartidaGenericas) {
				
				if (iteratorList.getPartidaGenericaFromListByClave(item, list, orgIn) == null) {
					item.setOrganizacion(orgIn);

					String temp = "";
					if (item.getGrupo() != null)
						temp += String.valueOf(item.getGrupo());
					if (item.getSubgrupo() != null)
						temp += String.valueOf(item.getSubgrupo());
					if (item.getClase() != null)
						temp += String.valueOf(item.getClase());
					item.setClave(String.valueOf(temp));
					item = (ConffyaPartidaGenerica) conffyaPartidaGenericaRest.save(item).getSingle();
					count++;
				}
			}
		}
		return count;
	}

	public void dumpPartidaGenericaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					cofiaPartidaGenerica = construirPartidaGenerica(entrada.getKey(), entrada.getValue(),
							cofiaPartidaGenerica);
				if (contadorCamposJson == 2) {
					cofiaPartidaGenerica
							.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					cofiaPartidaGenerica.setUltimaActualizacion(Calendar.getInstance());
					// cofiaPartidaGenerica.setOrganizacion(organizacion);
					cofiaPartidaGenerica.setUsuario(usuario);

					String clave = cofiaPartidaGenerica.getClave();
					cofiaPartidaGenerica.setGrupo(Integer.parseInt(clave.substring(0, 1)));
					cofiaPartidaGenerica.setSubgrupo(Integer.parseInt(clave.substring(1, 2)));
					cofiaPartidaGenerica.setClase(Integer.parseInt(clave.substring(2, 3)));

					cofiaPartidaGenerica.setClasificacionId("1");
					cofiaPartidaGenerica.setClasificacionNombre("Muebles");
					cofiaPartidaGenerica.setDescripcion(cofiaPartidaGenerica.getNombre());

					cofiaPartidaGenericas.add(cofiaPartidaGenerica);
					cofiaPartidaGenerica = new ConffyaPartidaGenerica();
					contadorCamposJson = 0;
				}

				dumpPartidaGenericaJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpPartidaGenericaJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public ConffyaPartidaGenerica construirPartidaGenerica(String key, JsonElement value,
			ConffyaPartidaGenerica partidaGenericaBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						partidaGenericaBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						partidaGenericaBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}
				}
			}
		}
		return partidaGenericaBuiding;
	}
	// --------- FIN PARTIDA GENERICA ---------------------------

	// --------- PROGRAMAS -------------------------------
	public int exportarXmlToDBCofiaPrograma(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);

		cofiaProg = new ConffyaProg();
		cofiaProgs = new ArrayList<ConffyaProg>();
		contadorCamposJson = 0;

		dumpProgramaJSONElement(json);
		if (cofiaProgs != null) {
			List<ConffyaProg> list = (List<ConffyaProg>) conffyaProgRest.getAll(orgIn).getSingle();
			for (ConffyaProg item : cofiaProgs) {
				if (iteratorList.getCofiaProgFromListByClave(item, list, orgIn) == null) {
					item.setOrganizacion(orgIn);
					item = (ConffyaProg) conffyaProgRest.save(item).getSingle();
					count++;
				}
			}
		}
		return count;
	}

	public void dumpProgramaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					cofiaProg = construirPrograma(entrada.getKey(), entrada.getValue(), cofiaProg);
				if (contadorCamposJson == 2) {
					cofiaProg.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					cofiaProg.setUltimaActualizacion(Calendar.getInstance());
					// cofiaProg.setOrganizacion(organizacion);
					cofiaProg.setUsuario(usuario);

					cofiaProgs.add(cofiaProg);
					cofiaProg = new ConffyaProg();
					contadorCamposJson = 0;
				}

				dumpProgramaJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpProgramaJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public ConffyaProg construirPrograma(String key, JsonElement value, ConffyaProg programasBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						programasBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						programasBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}
				}
			}
		}
		return programasBuiding;
	}
	// --------- FIN PROGRAMAS -------------------------------

	// --------- PROYECTOS -------------------------------
	public int exportarXmlToDBCofiaPy(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);

		cofiaPy = new ConffyaPy();
		cofiaPys = new ArrayList<ConffyaPy>();
		contadorCamposJson = 0;

		dumpCofiaPyJSONElement(json);
		if (cofiaPys != null) {
			List<ConffyaPy> list = (List<ConffyaPy>) conffyaPyRest.getAll(orgIn).getSingle();
			for (ConffyaPy item : cofiaPys) {
				if (iteratorList.getCofiaPyFromListByClave(item, list, orgIn) == null) {
					item.setOrganizacion(orgIn);
					item = (ConffyaPy) conffyaPyRest.save(item).getSingle();
					count++;
				}
			}
		}
		return count;
	}

	public void dumpCofiaPyJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					cofiaPy = construirCofiaPy(entrada.getKey(), entrada.getValue(), cofiaPy);
				if (contadorCamposJson == 2) {
					cofiaPy.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					cofiaPy.setUltimaActualizacion(Calendar.getInstance());
					// cofiaPy.setOrganizacion(organizacion);
					cofiaPy.setUsuario(usuario);

					cofiaPys.add(cofiaPy);
					cofiaPy = new ConffyaPy();
					contadorCamposJson = 0;
				}

				dumpCofiaPyJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpCofiaPyJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public ConffyaPy construirCofiaPy(String key, JsonElement value, ConffyaPy cofiaPyBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						cofiaPyBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						cofiaPyBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}
				}
			}
		}
		return cofiaPyBuiding;
	}

	// --------- FIN PROYECTOS -------------------------------

	// --------- FIN PROGRAMAS -------------------------------

	// --------- EMPRESAS CATALOLGO ---------------------------
	public List<Organizacion> exportarXmlToObjectListEmpresas(String xmlString) {
		List<Organizacion> orgsTemp = new ArrayList<>();
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);
		orgTemp = new Organizacion();
		organizacionestemp = new ArrayList<>();
		contadorCamposJson = 0;
		organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();

		dumpEmpresasJSONElement(json);
		orgsTemp = organizacionestemp;
		organizacionestemp = null;

		return orgsTemp;

	}

	public int recordOrganizaciones(List<Organizacion> list) {
		int count = 0;
		if (list != null) {
			for (Organizacion item : list) {
				if (iteratorList.getOrganizacionesListByNombreAndEjercicioAndNumero(list, item) == null) {

					Persona personaTemp = new Persona();
					personaTemp.setApellidoPaterno("Administrador");
					personaTemp.setNombre(item.getNumero().toString() + " " + item.getNombre());
					
					personaTemp = (Persona) personaRest.save(personaTemp).getSingle();

					AImage aimagen = getAImageOrganizacionInicial("layout/companyProfile.png");
					item.setLogotipo(aimagen.getByteData());
					item.setNombre(String.valueOf(item.getNombre() + " " + item.getEjercicio()));
					item.setActivar(false);
					item.setDisableActiv(false);
					item.setProveedor(false);
					item = (Organizacion) organizacionRest.save(item).getSingle();

					Usuarios usuarioTemp = new Usuarios();
					
					usuarioTemp.setBenutzer(new StockUtils().Encriptar(item.getNumero().toString() + " " + item.getEjercicio().toString()));
					usuarioTemp.setKennwort(new StockUtils().Encriptar(item.getNumero().toString()));
					usuarioTemp.setPersona(personaTemp);
					usuarioTemp.setOrganizacion(item);
					usuarioTemp.setOwner(false);
					usuarioTemp.setClient(true);
					usuarioTemp = (Usuarios) usuarioRest.save(usuarioTemp).getSingle();
					count++;
				}
			}
		}
		return count;
	}

	// boolean romperExtraccion = false;

	public void dumpEmpresasJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				/*
				 * if (keyJSon.contains("descripcion")) {
				 * if(String.valueOf(valueJSon).contains(
				 * "No se encontro informac")) romperExtraccion = true; }
				 */

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					orgTemp = construirEmpresa(entrada.getKey(), entrada.getValue(), orgTemp);
				if (contadorCamposJson == 3) {
					orgTemp.setSucursalId(0);
					organizacionestemp.add(orgTemp);
					orgTemp = new Organizacion();
					contadorCamposJson = 0;
				}
				// if(!romperExtraccion)
				dumpEmpresasJSONElement(entrada.getValue());
			}
		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpEmpresasJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public Organizacion construirEmpresa(String key, JsonElement value, Organizacion organizacionBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("numero")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						organizacionBuiding.setNumero(Long.parseLong(stringOut));
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("nombre")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						organizacionBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("ejercicio")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						organizacionBuiding.setEjercicio(Long.parseLong(stringOut));
						contadorCamposJson++;
					}
				}
			}
		}
		return organizacionBuiding;
	}

	public List<Organizacion> eliminarEmpresasRepetidas(List<Organizacion> list) {
		List<Organizacion> orgtemp = new ArrayList<>();
		for (Organizacion item : list) {
			boolean salvar = false;
			for (Organizacion dubItem : orgtemp) {
				if (item.getNumero().equals(dubItem.getNumero())) {
					salvar = true;
					break;
				}
			}
			if (!salvar)
				orgtemp.add(item);
			salvar = false;
		}
		return orgtemp;
	}
	// --------- FIN EMPRESAS CATALOGO ----------------------

	// ------------- PRESUPUESTO EGRESO -------------------
	public int exportarXmlToDBConffyaPresupuestoEgresosDeslgosado(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);

		conffyaPresupuestoDesglosado = new ConffyaPresupuestoDesglosado();
		conffyaPresupuestoDesglosadoList = new ArrayList<ConffyaPresupuestoDesglosado>();
		contadorCamposJson = 0;

		dumpConffyaPresupuestoEgresosDeslgosadoJSONElement(json);
		if (conffyaPresupuestoDesglosadoList != null) {
			for (ConffyaPresupuestoDesglosado item : conffyaPresupuestoDesglosadoList) {
				item.setOrganizacion(orgIn);
				item = (ConffyaPresupuestoDesglosado) conffyaPresupuestoDesglosadoRest.save(item).getSingle();
				count++;
			}
		}
		return count;
	}

	public void dumpConffyaPresupuestoEgresosDeslgosadoJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			Set<Entry<String, JsonElement>> entradas = obj.entrySet();
			Iterator<Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					conffyaPresupuestoDesglosado = construirConffyaPresupuestoEgresosDeslgosado(entrada.getKey(),
							entrada.getValue(), conffyaPresupuestoDesglosado);
				if (contadorCamposJson == 42) {
					conffyaPresupuestoDesglosado
							.setFechaActualizacion(stockUtils.convertirCalendarToStringFormato1(Calendar.getInstance()));
					conffyaPresupuestoDesglosado.setUltimaActualizacion(Calendar.getInstance());
					conffyaPresupuestoDesglosado.setUsuario(usuario);

					conffyaPresupuestoDesglosadoList.add(conffyaPresupuestoDesglosado);
					conffyaPresupuestoDesglosado = new ConffyaPresupuestoDesglosado();
					contadorCamposJson = 0;
				}

				dumpConffyaPresupuestoEgresosDeslgosadoJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpConffyaPresupuestoEgresosDeslgosadoJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public ConffyaPresupuestoDesglosado construirConffyaPresupuestoEgresosDeslgosado(String key, JsonElement value,
			ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosadoBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setDescripcion(stringOut);
						contadorCamposJson++;
					}
				}
			}

			if (key.equals("autorizado")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setAutorizado(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("comprometido")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setComprometido(Float.parseFloat(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setComprometido(Float.parseFloat(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("porEjercer")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPorEjercer(Float.parseFloat(stringOut));
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("R1")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setR1(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setR1(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("R1_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setR1_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("UR")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setUR(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("UR_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setUR_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("GF")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						
						try {
							conffyaPresupuestoDesglosadoBuiding.setGF(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setGF(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("GF_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setGF_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FN")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {						
						try {
							conffyaPresupuestoDesglosadoBuiding.setFN(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setFN(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FN_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setFN_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("SF")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setSF(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setSF(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("SF_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setSF_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("AC")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setAC(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("AC_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setAC_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PP")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPP(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PP_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPP_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}

			if (key.equals("PYC")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPYC(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PYC_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPYC_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("CA")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						
						try {
							conffyaPresupuestoDesglosadoBuiding.setCA(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setCA(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("CA_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setCA_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("CO")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setCO(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setCO(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("CO_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setCO_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PA")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setPA(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setPA(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PA_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPA_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PE")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setPE(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setPE(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("PE_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setPE_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG1")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setTG1(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setTG1(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG1_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setTG1_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG2")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setTG2(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setTG2(Long.parseLong(concatString[0]));
						}
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG2_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setTG2_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG3")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setTG3(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setTG3(Long.parseLong(concatString[0]));
						}
						
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG3_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setTG3_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG4")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setTG4(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setTG4(Long.parseLong(concatString[0]));
						}
						
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG4_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setTG4_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG5")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setTG5(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setTG5(Long.parseLong(concatString[0]));
						}
						
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("TG5_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setTG5_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FF")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						try {
							conffyaPresupuestoDesglosadoBuiding.setFF(Long.parseLong(stringOut));
						} catch (Exception e) {
							String[] concatString = stringOut.split(",");
							conffyaPresupuestoDesglosadoBuiding.setFF(Long.parseLong(concatString[0]));
						}
						
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FF_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setFF_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FF1")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setFF1(stringOut);
						contadorCamposJson++;
					}
				}
			}
			if (key.equals("FF1_DESCRIPCION")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						conffyaPresupuestoDesglosadoBuiding.setFF1_DESCRIPCION(stringOut);
						contadorCamposJson++;
					}
				}
			}

		}
		return conffyaPresupuestoDesglosadoBuiding;
	}
	// ------------- FIN PRESUPUESTO EGRESO -------------------

}
