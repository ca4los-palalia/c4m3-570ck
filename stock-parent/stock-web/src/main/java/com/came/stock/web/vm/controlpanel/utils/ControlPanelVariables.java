package com.came.stock.web.vm.controlpanel.utils;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.came.stock.beans.TabControlSelected;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.web.vm.BasicStructure;

public class ControlPanelVariables extends BasicStructure {
	private static final long serialVersionUID = -806765252363100225L;
	protected String mensajeDeCambios;

	protected Organizacion orgTemp;
	protected List<Organizacion> organizacionestemp;
	protected boolean extraer = false;
	protected Integer contadorCamposJson;
	protected List<Area> areasUr;
	protected TabControlSelected controlBotoArea;
	
	
	@Wire("#controlPanelWindowContainer")
	protected Window compControlPanelWindowContainer;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1") 
	protected Borderlayout compBorderlayoutContainer1;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1") 
	protected Tabbox compTabBox1;
	
	//---------------- TAPANEL 1: AREAS -------------
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1") 
	protected Tabpanel compTabPanel1; 
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas")
	protected Borderlayout compLayoutCatalogoAreas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelDatosCatalogAreas")
	protected North compPanelDatosCatalogAreas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas")
	protected Center compPanelControlCatalogAreas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl")
	protected Borderlayout compLayoutCtrl;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAreas")
	protected West compLayoutCtrlAreas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAreas, #windowCtrArea")
	protected Window compWindowCtrArea;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAreas, #windowCtrArea, #txtAreaNueva")
	protected Textbox compTxtAreaNueva;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen")
	protected Center compLayoutCtrlAlmacen;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen")
	protected Window compWindowCtrlAlmacen;
	*/	
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #txtAlmacenNombre")
	protected Textbox compTxtAlmacenNombre;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #txtAlmacenCalle")
	protected Textbox compTxtAlmacenCalle;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #txtAlmacenNumero")
	protected Textbox compTxtAlmacenNumero;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #txtAlmacenCp")
	protected Textbox compTxtAlmacenCp;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #btnBuscaCp")
	protected Button compBtnBuscaCp;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel1, #layoutCatalogoAreas, #panelControlCatalogAreas, #layoutCtrl, #layoutCtrlAlmacen, #windowCtrlAlmacen, #btnAddAlmacen")
	protected Button compBtnAddAlmacen;
	//---------------- FIN TAPANEL 1: AREAS -------------
	
	
	//---------------- TAPANEL 2: BANCOS -------------
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2") 
	protected Tabpanel compTabPanel2;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos")
	protected Borderlayout compLayoutCatalogoBancos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelDatosCatalogBanco")
	protected North compPanelDatosCatalogBanco;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelDatosCatalogBanco, #windowDatosbanco")
	protected Window compWindowDatosbanco;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelDatosCatalogBanco, #windowDatosbanco, #listBancosCatalogo")
	protected Listbox compListBancosCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco")
	protected Center compPanelControlCatalogBanco;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco, #windowCtrlBanco")
	protected Window compWindowCtrlBanco;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco, #windowCtrlBanco, #btnAddNuevoBanco")
	protected Button compBtnAddNuevoBanco;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco, #windowCtrlBanco, #btnUploadImportBanco")
	protected Button compBtnUploadImportBanco;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco, #windowCtrlBanco, #txtBancoNombre")
	protected Textbox compTxtBancoNombre;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel2, #layoutCatalogoBancos, #panelControlCatalogBanco, #windowCtrlBanco, #btnSaveBanco")
	protected Button compBtnSaveBanco;
	
	//---------------- FIN TAPANEL 2: BANCOS -------------
	
	//---------------- TAPANEL 3: CONFFYA -------------
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel3") 
	protected Tabpanel compTabPanel3;
	//---------------- FIN TAPANEL 3: CONFFYA -------------
	
	//---------------- TAPANEL 4: CONTRATOS -------------
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4") 
	protected Tabpanel compTabPanel4;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos")
	protected Borderlayout compLayoutCatalogoContratos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelDatosCatalogContratos")
	protected North compPanelDatosCatalogContratos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelDatosCatalogContratos, #windowDatosContrato")
	protected Window compWindowDatosContrato;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelDatosCatalogContratos, #windowDatosContrato, #listContratosCatalogo")
	protected Listbox compListContratosCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato")
	protected Center compPanelControlCatalogContrato;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato")
	protected Window compWindowCtrlContrato;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #btnAddNuevoContrato")
	protected Button compBtnAddNuevoContrato;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #btnSaveContrato")
	protected Button compBtnSaveContrato;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #txtNombreContrato")
	protected Textbox compTxtNombreContrato;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #txtDescripcionContrato")
	protected Textbox compTxtDescripcionContrato;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #dateBoxVigenciaInicio")
	protected Datebox compDateBoxVigenciaInicio;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel4, #layoutCatalogoContratos, #panelControlCatalogContrato, #windowCtrlContrato, #dateBoxVigenciaFin")
	protected Datebox compDateBoxVigenciaFin;
	//---------------- FIN TAPANEL 4: CONTRATOS -------------
	
	
	
	
	//---------------- TAPANEL 5: DIVISAS -------------
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5") 
	protected Tabpanel compTabPanel5;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas")
	protected Borderlayout compLayoutCatalogoDivisas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelDatosCatalogDivisas")
	protected North compPanelDatosCatalogDivisas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelDatosCatalogDivisas, #windowDatosDivisas")
	protected Window compWindowDatosDivisas;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelDatosCatalogDivisas, #windowDatosDivisas, #listDivisasCatalogo")
	protected Listbox compListDivisasCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas")
	protected Center compPanelControlCatalogDivisas;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas")
	protected Window compWindowCtrlDivisas;
	*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas, #btnNuevaDivisa")
	protected Button compBtnNuevaDivisa;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas, #btnUploadDivisa")
	protected Button compBtnUploadDivisa;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas, #txtNombreDivisa")
	protected Textbox compTxtNombreDivisa;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas, #txtSimboloDivisa")
	protected Textbox compTxtSimboloDivisa;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel5, #layoutCatalogoDivisas, #panelControlCatalogDivisas, #windowCtrlDivisas, #btnSaveDivisa")
	protected Button compBtnSaveDivisa;
	//---------------- FIN TAPANEL 5: DIVISAS -------------
	
	
	//---------------- TAPANEL 6: PUESTOS -------------
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6") 
	protected Tabpanel compTabPanel6;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos")
	protected Borderlayout compLayoutCatalogoPuestos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelDatosCatalogPuestos")
	protected North compPanelDatosCatalogPuestos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelDatosCatalogPuestos, #windowDatosPuestos")
	protected Window compWindowDatosPuestos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelDatosCatalogPuestos, #windowDatosPuestos, #listPuestosCatalogo")*/
	protected Listbox compListPuestosCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos")
	protected Center compPanelControlCatalogPuestos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos, #windowCtrlPuestos")
	protected Window compWindowCtrlPuestos;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos, #windowCtrlPuestos, #btnNuevoPuesto")
	protected Button compBtnNuevoPuesto;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos, #windowCtrlPuestos, #btnUploadPuestos")
	protected Button compBtnUploadPuestos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos, #windowCtrlPuestos, #txtNombrePosicion")
	protected Textbox compTxtNombrePosicion;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel6, #layoutCatalogoPuestos, #panelControlCatalogPuestos, #windowCtrlPuestos, #btnSavePuesto")
	protected Button compBtnSavePuesto;
	//---------------- FIN TAPANEL 6: PUESTOS -------------
	
	//---------------- TAPANEL 7: TIPO DE PRODUCTOS -------------
	/*@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7") 
	protected Tabpanel compTabPanel7;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos")
	protected Borderlayout compLayoutCatalogoTipoProductos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelDatosCatalogTipoProductos")
	protected North compPanelDatosCatalogTipoProductos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelDatosCatalogTipoProductos, #windowDatosTipoProductos")
	protected Window compWindowDatosTipoProductos;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelDatosCatalogTipoProductos, #windowDatosTipoProductos, #listTipoProductosCatalogo")
	protected Listbox compListTipoProductosCatalogo;/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos")
	protected Center compPanelControlCatalogTipoProductos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos")
	protected Window compWindowCtrlTipoProductos;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos, #btnNuevoTipoProductos")
	protected Button compBtnNuevoTipoProductos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos, #btnUploadTipoProductos")
	protected Button compBtnUploadTipoProductos;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos, #txtNombreTipoProductos")
	protected Textbox compTxtNombreTipoProductos;
	
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos, #txtDescripcionTipoProductos")
	protected Textbox compTxtDescripcionTipoProductos;
	
	
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel7, #layoutCatalogoTipoProductos, #panelControlCatalogTipoProductos, #windowCtrlTipoProductos, #btnSaveTipoProductos")
	protected Button compBtnSaveTipoProductos;
	//---------------- FIN TAPANEL 7: TIPO DE PRODUCTOS -------------
	
	//---------------- TAPANEL 8: UNIDADES -------------
	/*@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8") 
	protected Tabpanel compTabPanel8;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades")
	protected Borderlayout compLayoutCatalogoUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelDatosCatalogUnidades")
	protected North compPanelDatosCatalogUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelDatosCatalogUnidades, #windowDatosUnidades")
	protected Window compWindowDatosUnidades;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelDatosCatalogUnidades, #windowDatosUnidades, #listUnidadesCatalogo")
	protected Listbox compListUnidadesCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades")
	protected Center compPanelControlCatalogUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades")
	protected Window compWindowCtrlUnidades;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades, #btnNuevoUnidades")
	protected Button compBtnNuevoUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades, #btnUploadUnidades")
	protected Button compBtnUploadUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades, #txtNombreUnidades")
	protected Textbox compTxtNombreUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades, #txtAbreviaturaUnidades")
	protected Textbox compTxtAbreviaturaUnidades;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel8, #layoutCatalogoUnidades, #panelControlCatalogUnidades, #windowCtrlUnidades, #btnSaveUnidades")
	protected Button compBtnSaveUnidades;
	//---------------- FIN TAPANEL 8: UNIDADES -------------
	
	//---------------- TAPANEL 9: GIROS -------------
	/*@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9") 
	protected Tabpanel compTabPanel9;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros")
	protected Borderlayout compLayoutCatalogoGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelDatosCatalogGiros")
	protected North compPanelDatosCatalogGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelDatosCatalogGiros, #windowDatosGiros")
	protected Window compWindowDatosGiros;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelDatosCatalogGiros, #windowDatosGiros, #listGirosCatalogo")
	protected Listbox compListGirosCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros")
	protected Center compPanelControlCatalogGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros, #windowCtrlGiros")
	protected Window compWindowCtrlGiros;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros, #windowCtrlGiros, #btnNuevoGiros")
	protected Button compBtnNuevoGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros, #windowCtrlGiros, #btnUploadGiros")
	protected Button compBtnUploadGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros, #windowCtrlGiros, #txtNombreGiros")
	protected Textbox compTxtNombreGiros;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel9, #layoutCatalogoGiros, #panelControlCatalogGiros, #windowCtrlGiros, #btnSaveGiros")
	protected Button compBtnSaveGiros;
	//---------------- FIN TAPANEL 9: GIROS -------------
	
	//---------------- TAPANEL 10: PRODUCTO NATURALEZA -------------
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10") 
	protected Tabpanel compTabPanel10;	
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza")
	protected Borderlayout compLayoutCatalogoProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelDatosCatalogProductoNaturaleza")
	protected North compPanelDatosCatalogProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelDatosCatalogProductoNaturaleza, #windowDatosProductoNaturaleza")
	protected Window compWindowDatosProductoNaturaleza;*/
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelDatosCatalogProductoNaturaleza, #windowDatosProductoNaturaleza, #listProductoNaturalezaCatalogo")
	protected Listbox compListProductoNaturalezaCatalogo;
	/*
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza")
	protected Center compPanelControlCatalogProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza")
	protected Window compWindowCtrlProductoNaturaleza;*/	
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza, #btnNuevoProductoNaturaleza")
	protected Button compBtnNuevoProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza, #btnUploadProductoNaturaleza")
	protected Button compBtnUploadProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza, #txtNombreProductoNaturaleza")
	protected Textbox compTxtNombreProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza, #txtClaveMaestraProductoNaturaleza")
	protected Textbox compTxtClaveMaestraProductoNaturaleza;
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel10, #layoutCatalogoProductoNaturaleza, #panelControlCatalogProductoNaturaleza, #windowCtrlProductoNaturaleza, #btnSaveProductoNaturaleza")
	protected Button compBtnSaveProductoNaturaleza;
	//---------------- FIN TAPANEL 10: PRODUCTO NATURALEZA -------------
	
	//---------------- TAPANEL 11: LAYOUTS -------------
	@Wire("#controlPanelWindowContainer, #borderlayoutContainer1, #tabBox1, #tabPanel11") 
	protected Tabpanel compTabPanel11;
	//---------------- FIN TAPANEL 11: LAYOUTS -------------
	
	
	
	
	protected Organizacion organizacionSelected;
	
	public String getMensajeDeCambios() {
		return this.mensajeDeCambios;
	}

	public void setMensajeDeCambios(String mensajeDeCambios) {
		this.mensajeDeCambios = mensajeDeCambios;
	}


	public Organizacion getOrganizacionSelected() {
		return organizacionSelected;
	}

	public void setOrganizacionSelected(Organizacion organizacionSelected) {
		this.organizacionSelected = organizacionSelected;
	}

	public TabControlSelected getControlBotoArea() {
		return controlBotoArea;
	}

	public void setControlBotoArea(TabControlSelected controlBotoArea) {
		this.controlBotoArea = controlBotoArea;
	}

	
	
}
