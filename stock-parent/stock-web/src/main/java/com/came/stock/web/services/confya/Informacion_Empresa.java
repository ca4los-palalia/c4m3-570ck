package com.came.stock.web.services.confya;

public class Informacion_Empresa implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int idSucursal;

	private java.lang.String sucid;

	private long empresa;

	private long anio;

	private java.lang.String nombreBase;

	private java.lang.String RFCEmpresa;

	public Informacion_Empresa() {
	}

	public Informacion_Empresa(int idSucursal, java.lang.String sucid, long empresa, long anio,
			java.lang.String nombreBase, java.lang.String RFCEmpresa) {
		this.idSucursal = idSucursal;
		this.sucid = sucid;
		this.empresa = empresa;
		this.anio = anio;
		this.nombreBase = nombreBase;
		this.RFCEmpresa = RFCEmpresa;
	}

	/**
	 * Gets the idSucursal value for this Informacion_Empresa.
	 * 
	 * @return idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}

	/**
	 * Sets the idSucursal value for this Informacion_Empresa.
	 * 
	 * @param idSucursal
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * Gets the sucid value for this Informacion_Empresa.
	 * 
	 * @return sucid
	 */
	public java.lang.String getSucid() {
		return sucid;
	}

	/**
	 * Sets the sucid value for this Informacion_Empresa.
	 * 
	 * @param sucid
	 */
	public void setSucid(java.lang.String sucid) {
		this.sucid = sucid;
	}

	/**
	 * Gets the empresa value for this Informacion_Empresa.
	 * 
	 * @return empresa
	 */
	public long getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa value for this Informacion_Empresa.
	 * 
	 * @param empresa
	 */
	public void setEmpresa(long empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the anio value for this Informacion_Empresa.
	 * 
	 * @return anio
	 */
	public long getAnio() {
		return anio;
	}

	/**
	 * Sets the anio value for this Informacion_Empresa.
	 * 
	 * @param anio
	 */
	public void setAnio(long anio) {
		this.anio = anio;
	}

	/**
	 * Gets the nombreBase value for this Informacion_Empresa.
	 * 
	 * @return nombreBase
	 */
	public java.lang.String getNombreBase() {
		return nombreBase;
	}

	/**
	 * Sets the nombreBase value for this Informacion_Empresa.
	 * 
	 * @param nombreBase
	 */
	public void setNombreBase(java.lang.String nombreBase) {
		this.nombreBase = nombreBase;
	}

	/**
	 * Gets the RFCEmpresa value for this Informacion_Empresa.
	 * 
	 * @return RFCEmpresa
	 */
	public java.lang.String getRFCEmpresa() {
		return RFCEmpresa;
	}

	/**
	 * Sets the RFCEmpresa value for this Informacion_Empresa.
	 * 
	 * @param RFCEmpresa
	 */
	public void setRFCEmpresa(java.lang.String RFCEmpresa) {
		this.RFCEmpresa = RFCEmpresa;
	}

	private java.lang.Object __equalsCalc = null;

	@SuppressWarnings("unused")
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof Informacion_Empresa))
			return false;
		Informacion_Empresa other = (Informacion_Empresa) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && this.idSucursal == other.getIdSucursal()
				&& ((this.sucid == null && other.getSucid() == null)
						|| (this.sucid != null && this.sucid.equals(other.getSucid())))
				&& this.empresa == other.getEmpresa() && this.anio == other.getAnio()
				&& ((this.nombreBase == null && other.getNombreBase() == null)
						|| (this.nombreBase != null && this.nombreBase.equals(other.getNombreBase())))
				&& ((this.RFCEmpresa == null && other.getRFCEmpresa() == null)
						|| (this.RFCEmpresa != null && this.RFCEmpresa.equals(other.getRFCEmpresa())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		_hashCode += getIdSucursal();
		if (getSucid() != null) {
			_hashCode += getSucid().hashCode();
		}
		_hashCode += new Long(getEmpresa()).hashCode();
		_hashCode += new Long(getAnio()).hashCode();
		if (getNombreBase() != null) {
			_hashCode += getNombreBase().hashCode();
		}
		if (getRFCEmpresa() != null) {
			_hashCode += getRFCEmpresa().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
			Informacion_Empresa.class, true);

	static {
		typeDesc.setXmlType(new javax.xml.namespace.QName("CatalogosConffya/", "Informacion_Empresa"));
		org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("idSucursal");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "IdSucursal"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("sucid");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "Sucid"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("empresa");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "Empresa"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("anio");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "Anio"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("nombreBase");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "NombreBase"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
		elemField = new org.apache.axis.description.ElementDesc();
		elemField.setFieldName("RFCEmpresa");
		elemField.setXmlName(new javax.xml.namespace.QName("CatalogosConffya/", "RFCEmpresa"));
		elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		elemField.setNillable(false);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static org.apache.axis.description.TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	@SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	@SuppressWarnings("rawtypes")
	public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
			java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
		return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
