package com.came.stock.web.vm.perfil;

import org.zkoss.bind.annotation.Init;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Persona;

public abstract class PerfilMetaClass extends PerfilVariables {
	private static final long serialVersionUID = -2648381799748071319L;

	@Init
	public void init() {
		
	}

	
	
	
	
	
	public String validarFormularioUsuario(){
		String mensaje = "";
		
		//INFORMACION
		if(!personaUsuario.getNombre().equals("")){
				
		}else
			mensaje = "El nombre de usuario es requerido";
		
		if(mensaje.equals("") && !personaUsuario.getApellidoPaterno().equals("")){
				
		}else
			mensaje = "El apellido paterno es requerido";
		
		if(mensaje.equals("") && !personaUsuario.getApellidoMaterno().equals("")){
				
		}else
			mensaje = "El apellido materno es requerido";
		
		if(mensaje.equals("") && !usuario.getBenutzer().equals("")){
			
		}else
			mensaje = "El nombre de usuario requerido";
		if(mensaje.equals("") && !usuario.getKennwort().equals("")){
			
		}else
			mensaje = "El contraseña requerida";
		
		
		//DIRECCION		
		
		if(mensaje.equals("") &&  (direccionUsuario.getCalle() != null && !direccionUsuario.getCalle().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Calle de usuario requerida";
		
		if(mensaje.equals("") &&  (direccionUsuario.getCp() != null && !direccionUsuario.getCp().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Direccion postal del usuario requerida";
		
		if(mensaje.equals("") &&  (direccionUsuario.getNumExt() != null && !direccionUsuario.getNumExt().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Numero exterior del usuario requerido";
		
		if(mensaje.equals("")){
			if(paisUsuario != null && (paisUsuario.getIdPais() != null && paisUsuario.getIdPais() > 0)){
				if(estadoUsuario != null && (estadoUsuario.getIdEstado() != null && estadoUsuario.getIdEstado() > 0)){
					if(municipioUsuario != null && (municipioUsuario.getIdMunicipio() != null && municipioUsuario.getIdMunicipio() > 0)){
					}else
						mensaje = "Municipio del usuario requerido";
				}else
					mensaje = "Estado del usuario requerido";
			}else
				mensaje = "Pais del usuario requerido";
		}
		
		
		
		//CONTACTO
		/*
		if(mensaje.equals("") && ( emailUsuario.getEmail() != null && !emailUsuario.getEmail().equals(""))){
			
		}else
			mensaje = "Correo de usuario requeriodo";
		*/
		
		
		return mensaje;
	}
		
	public String validarEnvioDeCorreo(DevelopmentTool dev, Email emailDevelopment){
		//---------------- testing funcionamiento del correo ----------------
		String mensajeEnvio = "";
		Contacto c = new Contacto();
		c.setEmail(emailDevelopment);
		
		Persona per = new Persona();
		per.setApellidoPaterno("Este es un mensaje");
		per.setApellidoMaterno("para probar la configuracion");
		per.setNombre("del servicio de correo");
		per.setContacto(c);
		
		
		mensajeEnvio = sendEmail(usuario,
				per,
				"Configuracion correcta",
				"Smtp config + port",
				dev, null);
		//---------------- END testing funcionamiento del correo ----------------
		//https://www.google.com/settings/security/lesssecureapps
		return mensajeEnvio;
	}


	public void recuperarModalidad(){
		toolModalidadCalculo = (DevelopmentTool) developmentToolRest.getByDescripcion(StockConstants.SISTEMA_CONFIG_MODE).getSingle();
		if(toolModalidadCalculo != null){
			if(toolModalidadCalculo.getValue().equals(StockConstants.SISTEMA_PEPS))
				checkPeps = true;
			else if(toolModalidadCalculo.getValue().equals(StockConstants.SISTEMA_UEPS))
				checkUeps = true;
			else if(toolModalidadCalculo.getValue().equals(StockConstants.SISTEMA_PROMEDIO))
				checkPromedio = true;
		}
	}
}

