package com.came.stock.web.vm.controlpanel;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.utilidades.UserPrivileges;
import com.came.stock.web.vm.controlpanel.utils.UsuariosClientesVariables;

@VariableResolver({ DelegatingVariableResolver.class })
public class UsuariosClientesVM extends UsuariosClientesVariables {
	private static final long serialVersionUID = -6187792714156559485L;

	
	@Init
	public void init() {
		super.init();
		typePassword = "password";
		getStylesGlobal();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({ "*" })
	@Command
	public void nevoUsuarioCliente() {
		super.init();
	}

	@NotifyChange({ "usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado",
			"privilegioCotizacionAutorizacion", "privilegioOrdenCompra" })
	@Command
	public void prepareUsuarioForEdition(@BindingParam("index") Integer index) {
		if (index != null) {
			usuarioSeleccionado = ((Usuarios) usuarios.get(index.intValue()));
			privilegioRequision = false;
			privilegioConcentrado = false;
			privilegioCotizacionAutorizacion = false;
			privilegioOrdenCompra = false;
			
			if(usuarioSeleccionado.getPrivilegios() != null){
				for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
					switch (privilegio.getUserPrivileges()) {
					case REQUISION:
						privilegioRequision = true;
						break;
					case CONCENTRAR:
						privilegioConcentrado = true;
						break;
					case COTIZAR_AUTORIZAR:
						privilegioCotizacionAutorizacion = true;
						break;
					case ORDEN_COMPRA:
						privilegioOrdenCompra = true;
					}
				}
			}
		}
	}

	@NotifyChange({ "usuarios", "usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado",
			"privilegioCotizacionAutorizacion", "privilegioOrdenCompra", "areas" })
	@Command
	public void saveChanges() {

		privilegioRequision = false;
		privilegioConcentrado = false;
		privilegioCotizacionAutorizacion = false;
		privilegioOrdenCompra = false;
		if (!usuarios.contains(usuarioSeleccionado)) {
			usuarios.add(usuarioSeleccionado);
		}
		for (Usuarios usuario : usuarios) {
			usuario.getPersona().getContacto().setEmail((Email) emailRest.save(usuario.getPersona().getContacto().getEmail()).getSingle());
			usuario.getPersona().setContacto((Contacto)contactoRest.save(usuario.getPersona().getContacto()).getSingle());
			usuario.setPersona( (Persona) personaRest.save(usuario.getPersona()).getSingle());
			usuario = (Usuarios) usuarioRest.save(usuario).getSingle();
			if(usuario.getPrivilegios() != null){
				for (Privilegios privilegios : usuario.getPrivilegios()) {
					privilegios = (Privilegios) privilegioRest.save(privilegios).getSingle();
				}
			}
		}
		super.init();
		StockUtils.showSuccessmessage("Usuario actualizado", Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void delete() {
		if ((usuarioSeleccionado != null) && (usuarioSeleccionado.getIdUsuario() != null)) {
			Messagebox.show(
					"�Est� seguro de remover a " + usuarioSeleccionado.getPersona().getNombreCompleto()
							+ "?. Esta acci�n es irreversible ",
					"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == 1) {
								
								if(usuarioSeleccionado.getPrivilegios() != null){
									for (Privilegios privilegios : usuarioSeleccionado
											.getPrivilegios()) {
										privilegioRest.delete(privilegios);
									}
								}
								usuarioRest
										.delete(usuarioSeleccionado);
								personaRest
										.delete(usuarioSeleccionado.getPersona());
								contactoRest
										.delete(usuarioSeleccionado.getPersona().getContacto());
								emailRest.delete(usuarioSeleccionado
										.getPersona().getContacto().getEmail());

								usuarios.remove(usuarioSeleccionado);
								privilegioRequision = false;
								privilegioConcentrado = false;
								privilegioCotizacionAutorizacion = false;
								privilegioOrdenCompra = false;
								init();
								BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
							}
						}
					});
		}
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addRequisicionPrivilege() {
		if (this.privilegioRequision.booleanValue()) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.REQUISION)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(this.usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.REQUISION);
				privilegios.setIcono("/images/toolbar/linedpaperpencil32.png");
				privilegios.setPathLocationModule("/modulos/requisicion/requisicion.zul");
				this.usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.REQUISION)) {
					this.toRemove = privilegio;
					break;
				}
			}
			if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
				if (this.toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"�Est� seguro de remover este privilegio para "
									+ this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?",
							"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
								public void onEvent(Event event) throws Exception {
									if (((Integer) event.getData()).intValue() == 1) {
										UsuariosClientesVM.this.privilegioRest
												.delete(UsuariosClientesVM.this.toRemove);
										UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios()
												.remove(UsuariosClientesVM.this.toRemove);
									} else {
										UsuariosClientesVM.this.privilegioRequision = Boolean.valueOf(true);
									}
									BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
								}
							});
				} else {
					this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarios", "usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado",
			"privilegioCotizacionAutorizacion", "privilegioOrdenCompra" })
	@GlobalCommand
	public void refreshPrivilegios() {
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addConcentradosPrivilege() {
		if (this.privilegioConcentrado.booleanValue()) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.CONCENTRAR)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(this.usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.CONCENTRAR);
				privilegios.setIcono("/images/toolbar/linedpaperplus32.png");
				privilegios.setPathLocationModule("/modulos/requisicion/concentrado.zul");
				this.usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.CONCENTRAR)) {
					this.toRemove = privilegio;
					break;
				}
			}
			if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
				if (this.toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"�Est� seguro de remover este privilegio para "
									+ this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?",
							"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
								public void onEvent(Event event) throws Exception {
									if (((Integer) event.getData()).intValue() == 1) {
										UsuariosClientesVM.this.privilegioRest
												.delete(UsuariosClientesVM.this.toRemove);
										UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios()
												.remove(UsuariosClientesVM.this.toRemove);
									} else {
										UsuariosClientesVM.this.privilegioConcentrado = Boolean.valueOf(true);
									}
									BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
								}
							});
				} else {
					this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addCotizaAutorizaPrivilege() {
		if (this.privilegioCotizacionAutorizacion.booleanValue()) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.COTIZAR_AUTORIZAR)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(this.usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.COTIZAR_AUTORIZAR);
				privilegios.setIcono("/images/toolbar/notecheck32.png");
				privilegios.setPathLocationModule("/modulos/requisicion/cotizacion.zul");

				this.usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.COTIZAR_AUTORIZAR)) {
					this.toRemove = privilegio;
					break;
				}
			}
			if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
				if (this.toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"�Est� seguro de remover este privilegio para "
									+ this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?",
							"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
								public void onEvent(Event event) throws Exception {
									if (((Integer) event.getData()).intValue() == 1) {
										UsuariosClientesVM.this.privilegioRest
												.delete(UsuariosClientesVM.this.toRemove);
										UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios()
												.remove(UsuariosClientesVM.this.toRemove);
									} else {
										UsuariosClientesVM.this.privilegioCotizacionAutorizacion = Boolean
												.valueOf(true);
									}
									BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
								}
							});
				} else {
					this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addOrdenCompraPrivilege() {
		if (this.privilegioOrdenCompra.booleanValue()) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.ORDEN_COMPRA)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(this.usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.ORDEN_COMPRA);
				privilegios.setIcono("/images/toolbar/shoppingcart32.png");
				privilegios.setPathLocationModule("/modulos/ordenCompra/ordenCompra.zul");
				this.usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(UserPrivileges.ORDEN_COMPRA)) {
					this.toRemove = privilegio;
					break;
				}
			}
			if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
				if (this.toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"�Est� seguro de remover este privilegio para "
									+ this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?",
							"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
								public void onEvent(Event event) throws Exception {
									if (((Integer) event.getData()).intValue() == 1) {
										UsuariosClientesVM.this.privilegioRest
												.delete(UsuariosClientesVM.this.toRemove);
										UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios()
												.remove(UsuariosClientesVM.this.toRemove);
									} else {
										UsuariosClientesVM.this.privilegioOrdenCompra = Boolean.valueOf(true);
									}
									BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
								}
							});
				} else {
					this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarioSeleccionado", "areas" })
	@Command
	public void selectUrUsuario() {
		if (usuarioSeleccionado.getArea() != null) {
			usuarioSeleccionado.setArea(iteratorList.getAreasFromList(areas, usuarioSeleccionado.getArea().getIdArea()));
		}
		usuarioSeleccionado.setRetypeKennwort(usuarioSeleccionado.getKenDec());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openURSelectWindow() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateAreaSelected");
		inputParams.put("catalogoAreas", areas);
		Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/productos/utils/selectUr.zul",
				inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "usuarioSeleccionado" })
	public void updateAreaSelected(@BindingParam("areaSeleccionada") Area itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (usuarioSeleccionado != null) {
				usuarioSeleccionado.setArea(itemSeleccionado);
			}

		}
	}
	
	@NotifyChange({ "typePassword"})
	@Command
	public void changeTypePassword(){
		if (verPassword) {
			typePassword = "text";
		} else
			typePassword = "password";
	}
	
}
