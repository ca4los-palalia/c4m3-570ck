package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ValidarUsuarioAccionVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#validarUsuarioAccionVMModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;
	List<String> credenciales;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String nombre;
	private String contrasena;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("organizacion") Organizacion organizacion,
			@ExecutionArgParam("usuario") Usuarios usuario) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.organizacion = organizacion;
		this.usuario = usuario;
		credenciales = new ArrayList();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (validarEntrada()) {
			credenciales.add(nombre);
			credenciales.add(contrasena);
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("credenciales", credenciales);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
	}

	@Command
	public void closeDialog() {
		if (windowModalDialog != null) {
			windowModalDialog.detach();
		}
	}
	
	private boolean validarEntrada(){
		boolean correcto = false;
		if(!nombre.equals("") && !contrasena.equals("")){
			correcto = true;
		}
		return correcto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	

}
