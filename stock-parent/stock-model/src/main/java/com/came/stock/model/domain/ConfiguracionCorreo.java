package com.came.stock.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class ConfiguracionCorreo implements Serializable {
	private static final long serialVersionUID = 4373490847821834679L;
	private Long idConfiguracionCorreo;
	
	
	
	private String servidorSmtpHost;
	private String port;
	private String socketFactory;
	private String auth;
		
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdConfiguracionCorreo() {
		return idConfiguracionCorreo;
	}

	public void setIdConfiguracionCorreo(Long idConfiguracionCorreo) {
		this.idConfiguracionCorreo = idConfiguracionCorreo;
	}

	@Column
	public String getServidorSmtpHost() {
		return servidorSmtpHost;
	}

	public void setServidorSmtpHost(String servidorSmtpHost) {
		this.servidorSmtpHost = servidorSmtpHost;
	}

	@Column
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Column
	public String getSocketFactory() {
		return socketFactory;
	}

	public void setSocketFactory(String socketFactory) {
		this.socketFactory = socketFactory;
	}

	@Column
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	
}
