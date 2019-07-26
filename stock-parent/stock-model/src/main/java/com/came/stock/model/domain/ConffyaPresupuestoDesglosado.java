package com.came.stock.model.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ConffyaPresupuestoDesglosado {
	private Long idConffyaPresupuestoDesglosado;
	private String clave;
	private String descripcion;
	private String autorizado;
	private Float comprometido;
	private Float porEjercer;
	private Long R1;
	private String R1_DESCRIPCION;
	private String UR;
	private String UR_DESCRIPCION;
	private Long GF;
	private String GF_DESCRIPCION;
	private Long FN;
	private String FN_DESCRIPCION;
	private Long SF;
	private String SF_DESCRIPCION;
	private String AC;
	private String AC_DESCRIPCION;
	private String PP;
	private String PP_DESCRIPCION;
	private String PYC;
	private String PYC_DESCRIPCION;
	private Long CA;
	private String CA_DESCRIPCION;
	private Long CO;
	private String CO_DESCRIPCION;
	private Long PA;
	private String PA_DESCRIPCION;
	private Long PE;
	private String PE_DESCRIPCION;
	private Long TG1;
	private String TG1_DESCRIPCION;
	private Long TG2;
	private String TG2_DESCRIPCION;
	private Long TG3;
	private String TG3_DESCRIPCION;
	private Long TG4;
	private String TG4_DESCRIPCION;
	private Long TG5;
	private String TG5_DESCRIPCION;
	private Long FF;
	private String FF_DESCRIPCION;
	private String FF1;
	private String FF1_DESCRIPCION;
	
	private Calendar ultimaActualizacion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdConffyaPresupuestoDesglosado() {
		return idConffyaPresupuestoDesglosado;
	}
	public void setIdConffyaPresupuestoDesglosado(Long idConffyaPresupuestoDesglosado) {
		this.idConffyaPresupuestoDesglosado = idConffyaPresupuestoDesglosado;
	}
	
	@Column
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@Column
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column
	public String getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}
	
	@Column
	public Float getComprometido() {
		return comprometido;
	}
	public void setComprometido(Float comprometido) {
		this.comprometido = comprometido;
	}
	
	@Column
	public Float getPorEjercer() {
		return porEjercer;
	}
	public void setPorEjercer(Float porEjercer) {
		this.porEjercer = porEjercer;
	}
	
	@Column
	public Long getR1() {
		return R1;
	}
	public void setR1(Long r1) {
		R1 = r1;
	}
	
	@Column
	public String getR1_DESCRIPCION() {
		return R1_DESCRIPCION;
	}
	public void setR1_DESCRIPCION(String r1_DESCRIPCION) {
		R1_DESCRIPCION = r1_DESCRIPCION;
	}
	
	@Column
	public String getUR() {
		return UR;
	}
	public void setUR(String uR) {
		UR = uR;
	}
	
	@Column
	public String getUR_DESCRIPCION() {
		return UR_DESCRIPCION;
	}
	public void setUR_DESCRIPCION(String uR_DESCRIPCION) {
		UR_DESCRIPCION = uR_DESCRIPCION;
	}
	
	@Column
	public Long getGF() {
		return GF;
	}
	public void setGF(Long gF) {
		GF = gF;
	}
	
	@Column
	public String getGF_DESCRIPCION() {
		return GF_DESCRIPCION;
	}
	public void setGF_DESCRIPCION(String gF_DESCRIPCION) {
		GF_DESCRIPCION = gF_DESCRIPCION;
	}
	
	@Column
	public Long getFN() {
		return FN;
	}
	public void setFN(Long fN) {
		FN = fN;
	}
	
	@Column
	public String getFN_DESCRIPCION() {
		return FN_DESCRIPCION;
	}
	public void setFN_DESCRIPCION(String fN_DESCRIPCION) {
		FN_DESCRIPCION = fN_DESCRIPCION;
	}
	
	@Column
	public Long getSF() {
		return SF;
	}
	public void setSF(Long sF) {
		SF = sF;
	}
	
	@Column
	public String getSF_DESCRIPCION() {
		return SF_DESCRIPCION;
	}
	public void setSF_DESCRIPCION(String sF_DESCRIPCION) {
		SF_DESCRIPCION = sF_DESCRIPCION;
	}
	
	@Column
	public String getAC() {
		return AC;
	}
	public void setAC(String aC) {
		AC = aC;
	}
	
	@Column
	public String getAC_DESCRIPCION() {
		return AC_DESCRIPCION;
	}
	public void setAC_DESCRIPCION(String aC_DESCRIPCION) {
		AC_DESCRIPCION = aC_DESCRIPCION;
	}
	
	@Column
	public String getPP() {
		return PP;
	}
	public void setPP(String pP) {
		PP = pP;
	}
	
	@Column
	public String getPP_DESCRIPCION() {
		return PP_DESCRIPCION;
	}
	public void setPP_DESCRIPCION(String pP_DESCRIPCION) {
		PP_DESCRIPCION = pP_DESCRIPCION;
	}
	
	@Column
	public String getPYC() {
		return PYC;
	}
	public void setPYC(String pYC) {
		PYC = pYC;
	}
	
	@Column
	public String getPYC_DESCRIPCION() {
		return PYC_DESCRIPCION;
	}
	public void setPYC_DESCRIPCION(String pYC_DESCRIPCION) {
		PYC_DESCRIPCION = pYC_DESCRIPCION;
	}
	
	@Column
	public Long getCA() {
		return CA;
	}
	public void setCA(Long cA) {
		CA = cA;
	}
	
	@Column
	public String getCA_DESCRIPCION() {
		return CA_DESCRIPCION;
	}
	public void setCA_DESCRIPCION(String cA_DESCRIPCION) {
		CA_DESCRIPCION = cA_DESCRIPCION;
	}
	
	@Column
	public Long getCO() {
		return CO;
	}
	public void setCO(Long cO) {
		CO = cO;
	}
	
	@Column
	public String getCO_DESCRIPCION() {
		return CO_DESCRIPCION;
	}
	public void setCO_DESCRIPCION(String cO_DESCRIPCION) {
		CO_DESCRIPCION = cO_DESCRIPCION;
	}
	
	@Column
	public Long getPA() {
		return PA;
	}
	public void setPA(Long pA) {
		PA = pA;
	}
	
	@Column
	public String getPA_DESCRIPCION() {
		return PA_DESCRIPCION;
	}
	public void setPA_DESCRIPCION(String pA_DESCRIPCION) {
		PA_DESCRIPCION = pA_DESCRIPCION;
	}
	
	@Column
	public Long getPE() {
		return PE;
	}
	public void setPE(Long pE) {
		PE = pE;
	}
	
	@Column
	public String getPE_DESCRIPCION() {
		return PE_DESCRIPCION;
	}
	public void setPE_DESCRIPCION(String pE_DESCRIPCION) {
		PE_DESCRIPCION = pE_DESCRIPCION;
	}
	
	@Column
	public Long getTG1() {
		return TG1;
	}
	public void setTG1(Long tG1) {
		TG1 = tG1;
	}
	
	@Column
	public String getTG1_DESCRIPCION() {
		return TG1_DESCRIPCION;
	}
	public void setTG1_DESCRIPCION(String tG1_DESCRIPCION) {
		TG1_DESCRIPCION = tG1_DESCRIPCION;
	}
	
	@Column
	public Long getTG2() {
		return TG2;
	}
	public void setTG2(Long tG2) {
		TG2 = tG2;
	}
	
	@Column
	public String getTG2_DESCRIPCION() {
		return TG2_DESCRIPCION;
	}
	public void setTG2_DESCRIPCION(String tG2_DESCRIPCION) {
		TG2_DESCRIPCION = tG2_DESCRIPCION;
	}
	
	@Column
	public Long getTG3() {
		return TG3;
	}
	public void setTG3(Long tG3) {
		TG3 = tG3;
	}
	
	@Column
	public String getTG3_DESCRIPCION() {
		return TG3_DESCRIPCION;
	}
	public void setTG3_DESCRIPCION(String tG3_DESCRIPCION) {
		TG3_DESCRIPCION = tG3_DESCRIPCION;
	}
	
	@Column
	public Long getTG4() {
		return TG4;
	}
	public void setTG4(Long tG4) {
		TG4 = tG4;
	}
	
	@Column
	public String getTG4_DESCRIPCION() {
		return TG4_DESCRIPCION;
	}
	public void setTG4_DESCRIPCION(String tG4_DESCRIPCION) {
		TG4_DESCRIPCION = tG4_DESCRIPCION;
	}
	
	@Column
	public Long getTG5() {
		return TG5;
	}
	public void setTG5(Long tG5) {
		TG5 = tG5;
	}
	
	@Column
	public String getTG5_DESCRIPCION() {
		return TG5_DESCRIPCION;
	}
	public void setTG5_DESCRIPCION(String tG5_DESCRIPCION) {
		TG5_DESCRIPCION = tG5_DESCRIPCION;
	}
	
	@Column
	public Long getFF() {
		return FF;
	}
	public void setFF(Long fF) {
		FF = fF;
	}
	
	@Column
	public String getFF_DESCRIPCION() {
		return FF_DESCRIPCION;
	}
	public void setFF_DESCRIPCION(String fF_DESCRIPCION) {
		FF_DESCRIPCION = fF_DESCRIPCION;
	}
	
	@Column
	public String getFF1() {
		return FF1;
	}
	public void setFF1(String fF1) {
		FF1 = fF1;
	}
	
	@Column
	public String getFF1_DESCRIPCION() {
		return FF1_DESCRIPCION;
	}
	public void setFF1_DESCRIPCION(String fF1_DESCRIPCION) {
		FF1_DESCRIPCION = fF1_DESCRIPCION;
	}
	
	@Column
	public Calendar getUltimaActualizacion() {
		return ultimaActualizacion;
	}
	public void setUltimaActualizacion(Calendar ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
		
	
}
