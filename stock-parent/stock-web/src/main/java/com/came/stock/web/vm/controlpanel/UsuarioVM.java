package com.came.stock.web.vm.controlpanel;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.controlpanel.utils.UsuarioVariables;
import com.cplsystems.stock.app.utils.ConexionManual;

@VariableResolver({ DelegatingVariableResolver.class })
public class UsuarioVM extends UsuarioVariables {
	private static final long serialVersionUID = -6605100514948939575L;

	@Init
	public void init() {
		super.init();
		ConexionManual cn = new ConexionManual();
		usuarioRest.get
		usuarioList = cn.getUsuariosActivos();
		cn.cerrar();
		getStylesGlobal();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		System.err.println(view);
	}

	@NotifyChange({ "usuario", "typePassword", "checkPass" })
	@Command
	public void nuevaOrganizacion() {
		super.init();
	}

	@NotifyChange({ "organizacion", "usuario" })
	@Command
	public void saveChanges() {
		if (usuario.getOrganizacion().getIdOrganizacion() == null) {
			usuario.getOrganizacion().setActivar(false);
			usuario.getOrganizacion().setDisableActiv(false);
			usuario.getOrganizacion().setEjercicio(new Long(String.valueOf(Calendar.getInstance().getTime().getYear())));
			usuario.getOrganizacion().setNumero(new Long(String.valueOf(organizacionService.getCountRows() + 1)));
			usuario.getOrganizacion().setProveedor(false);
			usuario.getOrganizacion().setSucursalId(0);
			
			usuario.getPersona().setNombre(usuario.getOrganizacion().getNombre() + " Responsable");
			usuario.setOwner(false);
			usuario.setClient(true);
		}
		
		usuario.getOrganizacion().setDireccion((Direccion)direccionRest.save(usuario.getOrganizacion().getDireccion()).getSingle());
		usuario.setOrganizacion((Organizacion) organizacionRest.save(usuario.getOrganizacion()).getSingle());
		usuario.setPersona( (Persona) personaRest.save(usuario.getPersona()).getSingle());
		usuario = (Usuarios) usuarioRest.save(usuario).getSingle();
		
		
		String mensaje = "";
		if(iteratorList.getUsuarioFromList(usuario.getIdUsuario(), usuarioList) == null){
			usuarioList.add(usuario);
			indiceSelected = compListResultadosClientes.getItemCount();
			mensaje = "Un nuevo usuario ha sido creado.";
		}else
			mensaje = "Usuario actualizado.";	
		
		super.init();
		StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0,
				compListResultadosClientes.getItemAtIndex(indiceSelected));
		
	}

	@Command
	@NotifyChange({"usuarios"})
	public void delete() {
		if(usuario != null){
			if ((usuario.getOrganizacion() != null) && (usuario.getOrganizacion().getIdOrganizacion() != null)) {
				Messagebox.show(
						"¿Esta seguro de remover El cliente " + usuario.getOrganizacion().getNombre()
								+ "?. Esta acción es irreversible y removerá toda la información relacionada con "
								+ "usuarios, requisiciones, cotizaciones, etc. ",
						"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
							public void onEvent(Event event) throws Exception {
								if (((Integer) event.getData()).intValue() == 1) {
									Usuarios client = (Usuarios) usuarioRest
											.getClienteByOrganizacion(usuario.getOrganizacion()).getSingle();
									if (client != null) {
										List<Usuarios> usuarios = (List<Usuarios>) usuarioRest
												.getUsuariosByOrganizacion(usuario.getOrganizacion()).getSingle();
										if (usuarios != null) {
											for (Usuarios toRemove : usuarios) {
												for (Privilegios privilegios : toRemove.getPrivilegios()) {
													privilegioRest.delete(privilegios);
												}
												usuarioRest.delete(toRemove);
												personaRest.delete(toRemove.getPersona());
												contactoRest.delete(toRemove.getPersona().getContacto());
												emailRest
													.delete(toRemove.getPersona().getContacto().getEmail());

												usuarios.remove(toRemove);
												organizacionRest.delete(usuario.getOrganizacion());
												StockUtils.showSuccessmessage("Cliente eliminado", Clients.NOTIFICATION_TYPE_INFO, 0,
														compListResultadosClientes);
											}
										} else {
											usuarioRest.delete(client);
											organizacionRest.delete(usuario.getOrganizacion());
											init();
										}
										BindUtils.postGlobalCommand(null, null, "refreshOrganizaciones", null);
									} else
										StockUtils.showSuccessmessage("El cliente no puede ser eliminado. Posee todos los privilegios del sistema", Clients.NOTIFICATION_TYPE_ERROR, 0,
												compListResultadosClientes.getItemAtIndex(indiceSelected));
								}
							}
						});
			} else
				StockUtils.showSuccessmessage("El cliente no puede ser Eliminado ya que aun no ha sido registrado", Clients.NOTIFICATION_TYPE_ERROR, 0,
						compListResultadosClientes.getItemAtIndex(indiceSelected));
		}else
			StockUtils.showSuccessmessage("Seleccione un cliente para llevar acabo la eliminacion", Clients.NOTIFICATION_TYPE_ERROR, 0,
					null);
			
		
		
	}

		
	@Command
	public void buscarOrganizacion() {
		Window companiaDialog = this.stockUtils
				.createModelDialog("/modulos/controlPanel/utils/buscarOrganizaciones.zul");

		companiaDialog.doModal();
	}

	

	@NotifyChange({ "usuario" })
	@Command
	public void showInfoUsuarioSelected(){
		updateIndexSelected();
		usuario = (Usuarios) usuarioRest.getUsuarioById(usuario.getIdUsuario()).getSingle();
		if(usuario.getOrganizacion().getDireccion() == null)
			usuario.getOrganizacion().setDireccion(new Direccion());
		usuario.setRetypeKennwort(usuario.getKenDec());
	}
	
	@Command
	@NotifyChange({ "usuarioList", "busquedaFilter"})
	public void realizarBusqueda(){
		if(busquedaFilter != null && !busquedaFilter.isEmpty()){
			if(busquedaFilter.equals("*")){
				busquedaFilter = "";
				ConexionManual cn = new ConexionManual();
				usuarioList = cn.getUsuariosActivos();
				cn.cerrar();
				if(usuarioList == null)
					StockUtils.showSuccessmessage("No existen clientes activos", Clients.NOTIFICATION_TYPE_INFO, 0,
							compTxtBuscarClientes);
				else
					StockUtils.showSuccessmessage("Se encontraron " + usuarioList.size() + " Clientes activos", Clients.NOTIFICATION_TYPE_INFO, 0,
							compTxtBuscarClientes);
			}else{
				String mensajeBuscador = "";
				usuarioList = (List<Usuarios>) usuarioRest.getUsuariosLikeNombreOrganizacion(busquedaFilter).getSingle();
				
				if(usuarioList == null){
					usuarioList = (List<Usuarios>) usuarioRest.getUsuariosLikeRfcOrganizacion(busquedaFilter).getSingle();
					if(usuarioList == null){
						Long numero = 0L;
						try {
							numero = Long.parseLong(busquedaFilter);
							usuarioList = (List<Usuarios>) usuarioRest.getUsuariosLikeNumeroOrganizacion(numero).getSingle();
							if(usuarioList == null){
								StockUtils.showSuccessmessage("No pudimos obtener algun resultado, intenta con otro parametro", Clients.NOTIFICATION_TYPE_WARNING, 0,
										compTxtBuscarClientes);
							}else
								StockUtils.showSuccessmessage("tu busqueda por NUMERO de organizacion: " + busquedaFilter + " obtuvo resultados", Clients.NOTIFICATION_TYPE_INFO, 0,
										compListResultadosClientes);
						} catch (Exception e) {
							StockUtils.showSuccessmessage("No pudimos obtener algun resultado, intenta con otro parametro", Clients.NOTIFICATION_TYPE_WARNING, 0,
									compTxtBuscarClientes);
						}
					}else{
						StockUtils.showSuccessmessage("tu busqueda por RFC de organizacion: " + busquedaFilter + " obtuvo resultados", Clients.NOTIFICATION_TYPE_INFO, 0,
								compListResultadosClientes);
						
					}
				}else
					StockUtils.showSuccessmessage("tu busqueda por NOMBRE de organizacion: " + busquedaFilter + " obtuvo resultados", Clients.NOTIFICATION_TYPE_INFO, 0,
							compListResultadosClientes);
			}
		}else
			StockUtils.showSuccessmessage("Ningun criterio de busqueda, intente nuevamente", Clients.NOTIFICATION_TYPE_WARNING, 0,
					compTxtBuscarClientes);
	}
	
	@Command
	@NotifyChange({ "typePassword"})
	public void verContrasena(){
		if(checkPass){
			typePassword = "text";
			StockUtils.showSuccessmessage("Contraseña visible", Clients.NOTIFICATION_TYPE_INFO, 0,
					compTxtContrasena1);
		}else{
			typePassword = "password";
			StockUtils.showSuccessmessage("Contraseña oculta", Clients.NOTIFICATION_TYPE_INFO, 0,
					compTxtContrasena1);
		}
			
	}

	private void updateIndexSelected(){
		Set<Listitem> set = compListResultadosClientes.getSelectedItems();
		for (Listitem listitem : set)
			indiceSelected = listitem.getIndex();
	}
}
