package com.came.stock.core.spring;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zkoss.image.AImage;

import com.came.stock.beans.CtrlError;
import com.came.stock.beans.SistemaOperativo;
import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.StockConstants;
import com.came.stock.model.IteratorList;
import com.came.stock.model.domain.ClaveArmonizada;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.LabelsModulos;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Telefono;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.model.services.ClaveArmonizadaService;
import com.came.stock.model.services.ConffyaFuenteFinanciamientoService;
import com.came.stock.model.services.ConffyaPartidaGenericaService;
import com.came.stock.model.services.ConffyaProgService;
import com.came.stock.model.services.ConffyaPyService;
import com.came.stock.model.services.ContactoService;
import com.came.stock.model.services.CostosTiposService;
import com.came.stock.model.services.DevelopmentToolService;
import com.came.stock.model.services.EmailService;
import com.came.stock.model.services.EstadoService;
import com.came.stock.model.services.EstatusRequisicionService;
import com.came.stock.model.services.LabelsModulosService;
import com.came.stock.model.services.MunicipioService;
import com.came.stock.model.services.OrganizacionService;
import com.came.stock.model.services.PaisService;
import com.came.stock.model.services.PersonaService;
import com.came.stock.model.services.TelefonoService;
import com.came.stock.model.services.UsuarioService;
import com.came.stock.utilidades.StockUtilFile;
import com.came.stock.utilidades.StockUtilString;
import com.came.stock.utilidades.StockUtils;

@Configuration
public class InitialData {

	private static final Logger logger = Logger.getLogger(InitialData.class);

	@Autowired
	private StockUtils stockUtils;
	@Autowired
	private StockUtilFile stockUtilFile;
	@Autowired
	private StockUtilString stockUtilString;
	@Autowired
	private IteratorList iteratorList;
	
	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ConffyaFuenteFinanciamientoService cofiaFuenteFinanciamientoService;
	@SuppressWarnings("unused")
	@Autowired
	private ConffyaPartidaGenericaService cofiaPartidaGenericaService;
	@Autowired
	private ConffyaProgService cofiaProgService;
	@Autowired
	private ConffyaPyService cofiaPyService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private EstatusRequisicionService estatusRequisicionService;
	@Autowired
	private PaisService paisService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private ClaveArmonizadaService claveArmonizadaService;
	@Autowired
	private CostosTiposService costosTiposService;
	@Autowired
	private DevelopmentToolService developmentToolService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private TelefonoService telefonoService;
	@Autowired
	private ContactoService contactoService;
	@Autowired
	private LabelsModulosService labelsModulosService;
	
	private FileInputStream fileInputStream;
	private XSSFWorkbook workBook;
	private Organizacion organizacion;
	private Usuarios usuario;
	
	private List<Estado> estados;
	
	@Bean
	public String inicializarConfiguracion() {
		crearDirectoriosDeTrabajo();
		crearUsuarioInicial();
		crearCatalogosPorDefecto();
		crearLayouts();
		crearConfiguracionTransicionBanner();
		crearModoDeCalculoCosto();
		getLabelsLoginVM();
		organizacion = null;
		usuario = null;
		
		CtrlError banner = stockUtilFile.readInputStream(InitialData.class.getClassLoader().getResourceAsStream("utils/banner.txt"));
		logger.info(banner.isOk() ? banner.getMensaje() : "BANNER No Encontrado");
		
		return "Configuracion inicial terminada";
	}
	
	
	private void crearModoDeCalculoCosto(){
		DevelopmentTool modalidad = developmentToolService.getByDescripcion(StockConstants.SISTEMA_CONFIG_MODE);
		if(modalidad == null){
			modalidad = new DevelopmentTool();
			modalidad.setDescripcion(StockConstants.SISTEMA_CONFIG_MODE);
			modalidad.setValue(StockConstants.SISTEMA_PROMEDIO);
			developmentToolService.save(modalidad);
		}
	}

	private void crearLayouts() {
		if (developmentToolService.getCountLayouts() == null) {
			DevelopmentTool l1 = new DevelopmentTool();

			l1.setValue(StockConstants.LAYOUT_EXCEL_AREA.substring(7));
			l1.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_AREA)));
			developmentToolService.save(l1);

			DevelopmentTool l2 = new DevelopmentTool();
			l2.setValue(StockConstants.LAYOUT_EXCEL_BANCO.substring(7));
			l2.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_BANCO)));
			developmentToolService.save(l2);

			DevelopmentTool l3 = new DevelopmentTool();
			l3.setValue(StockConstants.LAYOUT_EXCEL_GIRO.substring(7));
			l3.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_GIRO)));
			developmentToolService.save(l3);

			DevelopmentTool l4 = new DevelopmentTool();
			l4.setValue(StockConstants.LAYOUT_EXCEL_DIVISA.substring(7));
			l4.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_DIVISA)));
			developmentToolService.save(l4);

			DevelopmentTool l5 = new DevelopmentTool();
			l5.setValue(StockConstants.LAYOUT_EXCEL_POSICION.substring(7));
			l5.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_POSICION)));
			developmentToolService.save(l5);

			DevelopmentTool l6 = new DevelopmentTool();
			l6.setValue(StockConstants.LAYOUT_EXCEL_PRODUCTO.substring(7));
			l6.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_PRODUCTO)));
			developmentToolService.save(l6);

			DevelopmentTool l7 = new DevelopmentTool();
			l7.setValue(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO.substring(7));
			l7.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO)));
			developmentToolService.save(l7);

			DevelopmentTool l8 = new DevelopmentTool();
			l8.setValue(StockConstants.LAYOUT_EXCEL_PROVEEDOR.substring(7));
			l8.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_PROVEEDOR)));
			developmentToolService.save(l8);

			DevelopmentTool l9 = new DevelopmentTool();
			l9.setValue(StockConstants.LAYOUT_EXCEL_UNIDAD.substring(7));
			l9.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_UNIDAD)));
			developmentToolService.save(l9);
			
			DevelopmentTool l10 = new DevelopmentTool();
			l10.setValue(StockConstants.LAYOUT_EXCEL_NATURALEZA.substring(7));
			l10.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_NATURALEZA)));
			developmentToolService.save(l10);
			
			DevelopmentTool l11 = new DevelopmentTool();
			l11.setValue(StockConstants.LAYOUT_EXCEL_PRESENTACION.substring(7));
			l11.setValueByte(getBytesXlsx(generarUrlString(StockConstants.LAYOUT_EXCEL_PRESENTACION)));
			developmentToolService.save(l11);
		}
	}

	public byte[] getBytesXlsx(String filePath) {
		File file = null;
		FileInputStream fis;
		ByteArrayOutputStream bos = null;
		try {
			file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];

			for (int readNum; (readNum = fis.read(buf)) != -1;)
				bos.write(buf, 0, readNum);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		byte[] bytes = bos.toByteArray();
		return bytes;
	}

	private void crearUsuarioInicial() {
		Persona personaTemp = null;
		List<Persona> personas = personaService.getAll();
		if (personas == null) {
			personaTemp = new Persona();
			personaTemp.setApellidoPaterno("Paterno admin");
			personaTemp.setApellidoMaterno("Materno admin");
			personaTemp.setNombre("Nombre admin");
			personaService.save(personaTemp);
		}
		Integer rows = organizacionService.getCountRows();
		if (rows == null || rows == 0) {
			organizacion = new Organizacion();
			organizacion.setNombre("CAME Accounting Solutions, S.A de C.V.");
			organizacion.setRfc("MAB850101541");
			organizacion.setNumero(1020L);
			//organizacion.setNumero(2076L);
			organizacion.setEjercicio(2015L);
			
			organizacion.setSucursalId(0);
			organizacion.setActivar(true);
			organizacion.setDisableActiv(true);
			organizacion.setProveedor(false);
			
			Email emailOrg = new Email();
			emailOrg.setEmail("ventas@camesoftware.com");
			emailOrg.setWeb("camesoftware.com/");
			emailService.save(emailOrg);
			
			Telefono telefonoOrg = new Telefono();
			telefonoOrg.setNumero("+52 (222) 285 40 51");
			telefonoService.save(telefonoOrg);
			
			Contacto contactoOrg = new Contacto();
			contactoOrg.setEmail(emailOrg);
			contactoOrg.setTelefono(telefonoOrg);
			contactoService.save(contactoOrg);
			
			organizacion.setContacto(contactoOrg);
			
			AImage aimagen = getAImageOrganizacionInicial(ConstAtributos.PATH_HOME + ConstAtributos.PATH_CFG + "CAMELogo.png");
			organizacion.setLogotipo(aimagen.getByteData());

			organizacionService.save(organizacion);

		}
		List<Usuarios> usuarios = usuarioService.getAll();
		if (usuarios == null) {
			usuario = new Usuarios();
			String same = stockUtils.Encriptar("came");
			usuario.setBenutzer(same);
			usuario.setKennwort(same);
			usuario.setPersona(personaTemp);
			usuario.setOrganizacion(organizacion);
			usuario.setOwner(Boolean.valueOf(true));
			usuario.setClient(Boolean.valueOf(false));
			usuarioService.save(usuario);
		}
	}

	private void crearCatalogosPorDefecto() {
		try {
			if (paisService.getAll() == null) {
				cargarScriptExcel();
				leerDatosDesdeExcel(0);
				System.err.print("... Precarga de paises Terminada!\n");
			}
			if (estadoService.getAll() == null) {
				if(workBook == null)
					cargarScriptExcel();
				
				leerDatosDesdeExcel(1);
				
				System.err.print("... Precarga de Estados Terminada!\n");
			}
			if (municipioService.getAll() == null) {
				if(workBook == null)
					cargarScriptExcel();
				leerDatosDesdeExcel(2);
				System.err.print("... Precarga de Municipios Terminada!\n");
			}

			/*
			 * if (cofiaFuenteFinanciamientoService.getAll() == null) {
			 * leerDatosDesdeExcel(3); System.err.print(
			 * "... Precarga de Fuente de financiamiento Terminada!\n"); } if
			 * (cofiaPartidaGenericaService.getAll() == null) {
			 * leerDatosDesdeExcel(4); System.err.print(
			 * "... Precarga de Partida generica Terminada!\n"); } if
			 * (cofiaProgService.getAll() == null) { leerDatosDesdeExcel(5);
			 * System.err.print("... Precarga de Programa Terminada!\n"); }
			 * 
			 * if (cofiaPyService.getAll() == null) { leerDatosDesdeExcel(6);
			 * System.err.print("... Precarga de Py Terminada!\n"); }
			 */
			if (estatusRequisicionService.getAll() == null) {
				if(workBook == null)
					cargarScriptExcel();
				leerDatosDesdeExcel(7);
				System.err.print("... Precarga de Estatus requisiciones Terminada!\n");
			}
			if (claveArmonizadaService.getAll() == null) {
				if(workBook == null)
					cargarScriptExcel();
				leerDatosDesdeExcel(8);
				System.err.print("... Precarga de Clave armonizada Terminada!\n");
			}
			if (costosTiposService.getAll(false) == null) {
				if(workBook == null)
					cargarScriptExcel();
				leerDatosDesdeExcel(9);
				System.err.print("... Precarga de Tipos de costos Terminada!\n");
			}
			if(fileInputStream != null)
				fileInputStream.close();

		} catch (IOException e) {
			logger.error(("IOException al cerrar fileInputStream: " + fileInputStream+  ":\n" + e.getMessage()), e);
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerPaisesDeExcel(Iterator rowIterator) {
		List<Pais> paisesTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Pais nuevoPais = new Pais();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();

			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoPais = crearPais(nuevoPais, hssfCell, j);
					j++;
				}
				paisesTemp.add(nuevoPais);
			}
			i++;
		}
		if (paisesTemp.size() > 0) {
			for (Pais item : paisesTemp) {
				paisService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerEstadosDeExcel(Iterator rowIterator) {
		List<Estado> estadosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Estado nuevoEstado = new Estado();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();

			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 4)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoEstado = crearEstado(nuevoEstado, hssfCell, j);
					j++;
				}
				estadosTemp.add(nuevoEstado);
			}
			i++;
		}
		if (estadosTemp.size() > 0) {
			for (Estado item : estadosTemp) {
				estadoService.save(item);
			}
			estados = estadosTemp;
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerMunicipiosDeExcel(Iterator rowIterator) {
		List<Municipio> municipiosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Municipio nuevoMunicipio = new Municipio();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 3)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoMunicipio = crearMunicipio(nuevoMunicipio, hssfCell, j);
					j++;
				}
				municipiosTemp.add(nuevoMunicipio);
			}
			i++;
		}
		if (municipiosTemp.size() > 0) {
			for (Municipio item : municipiosTemp) {
				municipioService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerEstatusRequisicionDeExcel(Iterator rowIterator) {
		List<EstatusRequisicion> estatusRequisicionTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			EstatusRequisicion nuevoestatusRequisicion = new EstatusRequisicion();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 5)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoestatusRequisicion = crearEstatusRequisicion(nuevoestatusRequisicion, hssfCell, j);
					j++;
				}
				estatusRequisicionTemp.add(nuevoestatusRequisicion);
			}
			i++;
		}
		if (estatusRequisicionTemp.size() > 0) {
			for (EstatusRequisicion item : estatusRequisicionTemp) {
				estatusRequisicionService.save(item);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void extraerCofiaPyDeExcel(Iterator rowIterator) {
		List<ConffyaPy> cofiaPyTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ConffyaPy nuevoCofiaPy = new ConffyaPy();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaPy = crearCofiaPy(nuevoCofiaPy, hssfCell, j);
					j++;
				}
				cofiaPyTemp.add(nuevoCofiaPy);
			}
			i++;
		}
		if (cofiaPyTemp.size() > 0) {
			for (ConffyaPy item : cofiaPyTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaPyService.save(item);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void extraerCofiaProgramaDeExcel(Iterator rowIterator) {
		List<ConffyaProg> cofiaProgTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ConffyaProg nuevoCofiaProg = new ConffyaProg();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaProg = crearCofiaProg(nuevoCofiaProg, hssfCell, j);
					j++;
				}
				cofiaProgTemp.add(nuevoCofiaProg);
			}
			i++;
		}
		if (cofiaProgTemp.size() > 0) {
			for (ConffyaProg item : cofiaProgTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaProgService.save(item);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void extraerCofiaPartidaGenericaDeExcel(Iterator rowIterator) {
		List<ConffyaPartidaGenerica> cofiaPartidaGenericaTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ConffyaPartidaGenerica nuevoCofiaPartidaGenerica = new ConffyaPartidaGenerica();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaPartidaGenerica = crearCofiaPartidaGenerica(nuevoCofiaPartidaGenerica, hssfCell, j);
					j++;
				}
				cofiaPartidaGenericaTemp.add(nuevoCofiaPartidaGenerica);
			}
			i++;
		}
		if (cofiaPartidaGenericaTemp.size() > 0) {
			for (ConffyaPartidaGenerica item : cofiaPartidaGenericaTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				// cofiaPartidaGenericaService.save(item);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void extraerCofiaFuenteFinanciamientoDeExcel(Iterator rowIterator) {
		List<ConffyaFuenteFinanciamiento> cofiaFuenteFinanciamientoTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ConffyaFuenteFinanciamiento nuevoCofiaFuenteFinanciamiento = new ConffyaFuenteFinanciamiento();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaFuenteFinanciamiento = crearCofiaFuenteFinanciamiento(nuevoCofiaFuenteFinanciamiento,
							hssfCell, j);
					j++;
				}
				cofiaFuenteFinanciamientoTemp.add(nuevoCofiaFuenteFinanciamiento);
			}
			i++;
		}
		if (cofiaFuenteFinanciamientoTemp.size() > 0) {
			for (ConffyaFuenteFinanciamiento item : cofiaFuenteFinanciamientoTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaFuenteFinanciamientoService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerClaveArmonizadaDeExcel(Iterator rowIterator) {
		List<ClaveArmonizada> claveArmonizadaTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ClaveArmonizada nuevoClaveArmonizada = new ClaveArmonizada();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 7)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoClaveArmonizada = crearClaveArmonizada(nuevoClaveArmonizada, hssfCell, j);
					j++;
				}
				nuevoClaveArmonizada.setFechaActualizacion(Calendar.getInstance());
				claveArmonizadaTemp.add(nuevoClaveArmonizada);
			}
			i++;
		}
		if (claveArmonizadaTemp.size() > 0) {
			List<ConffyaPartidaGenerica> listTemp = new ArrayList<>();
			for (ClaveArmonizada item : claveArmonizadaTemp) {

				ConffyaPartidaGenerica temp = new ConffyaPartidaGenerica();
				temp.setNombre(item.getDescripcion());
				temp.setGrupo(item.getGrupo());
				temp.setSubgrupo(item.getSubGrupo());
				temp.setClase(item.getClase());
				temp.setSubClase(item.getSubclase());
				temp.setClasificacionId(item.getClasificacionId());
				temp.setClasificacionNombre(item.getClasificacionNombre());
				temp.setDescripcion(item.getDescripcion());

				temp.setUltimaActualizacion(Calendar.getInstance());
				temp.setFechaActualizacion(StockUtils.getFechaActualConHora());
				temp.setOrganizacion(organizacion);
				temp.setUsuario(usuario);
				listTemp.add(temp);

				claveArmonizadaService.save(item);
			}

			/*
			 * for (CofiaPartidaGenerica item : listTemp) {
			 * item.setUltimaActualizacion(Calendar.getInstance());
			 * item.setFechaActualizacion(StockUtils.getFechaActualConHora());
			 * item.setOrganizacion(organizacion); item.setUsuario(usuario);
			 * cofiaPartidaGenericaService.save(item); }
			 */

		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerCostosTiposDeExcel(Iterator rowIterator) {
		List<CostosTipos> costosTiposTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			CostosTipos nuevoCostosTipos = new CostosTipos();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCostosTipos = crearCostosTipo(nuevoCostosTipos, hssfCell, j);
					j++;
				}
				nuevoCostosTipos.setOrganizacion(organizacion);
				nuevoCostosTipos.setUsuario(usuario);
				costosTiposTemp.add(nuevoCostosTipos);
			}
			i++;
		}
		if (costosTiposTemp.size() > 0) {
			for (CostosTipos item : costosTiposTemp) {
				costosTiposService.save(item);
			}
		}
	}

	private Pais crearPais(Pais pais, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				pais.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				pais.setAbreviatura(valor);
			break;
		}
		return pais;
	}

	private Estado crearEstado(Estado estado, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setCapital(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setAbreviatura(valor);
			break;
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				estado.setSimbolo(valor);
			break;
		}
		return estado;
	}

	private Municipio crearMunicipio(Municipio municipio, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			municipio.setNombre(valor);

			break;
		case 1:
			if (valor.contains(".0")) {
				valor = stockUtilString.removerPuntoCero(valor);
			}
			municipio.setEstado(iteratorList.getEstadoFromList(estados, Long.parseLong(valor)));
			break;
		case 2:
			if (valor.contains(".0")) {
				valor = stockUtilString.removerPuntoCero(valor);
			}
			municipio.setNumeroMunicipio(String.valueOf(valor));
		}
		return municipio;
	}

	private ConffyaFuenteFinanciamiento crearCofiaFuenteFinanciamiento(
			ConffyaFuenteFinanciamiento nuevoCofiaFuenteFinanciamiento, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaFuenteFinanciamiento.setNombre(valor);
			break;
		}
		return nuevoCofiaFuenteFinanciamiento;
	}

	private ConffyaPartidaGenerica crearCofiaPartidaGenerica(ConffyaPartidaGenerica nuevoCofiaPartidaGenerica,
			XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaPartidaGenerica.setNombre(valor);
			break;
		}
		return nuevoCofiaPartidaGenerica;
	}

	private ConffyaProg crearCofiaProg(ConffyaProg nuevoCofiaProg, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaProg.setNombre(valor);
			break;
		}
		return nuevoCofiaProg;
	}

	private ConffyaPy crearCofiaPy(ConffyaPy nuevoCofiaPy, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaPy.setNombre(valor);
			break;
		}
		return nuevoCofiaPy;
	}

	private EstatusRequisicion crearEstatusRequisicion(EstatusRequisicion nuevoestatusRequisicion,
			XSSFCell valorDePropiedad, int indice) {

		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoestatusRequisicion.setClave(valor);
			break;
		case 1:
			nuevoestatusRequisicion.setNombre(valor);
			break;
		case 2:
			nuevoestatusRequisicion.setDescripcion(valor);
			break;
		case 3:
			nuevoestatusRequisicion.setColor(valor);
			break;
		case 4:
			nuevoestatusRequisicion.setColorFont(valor);
			break;
		}
		return nuevoestatusRequisicion;
	}

	private ClaveArmonizada crearClaveArmonizada(ClaveArmonizada nuevoClaveArmonizada, XSSFCell valorDePropiedad,
			int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoClaveArmonizada.setClasificacionId(valor);
			break;
		case 1:
			nuevoClaveArmonizada.setClasificacionNombre(valor);
			break;
		case 2:
			nuevoClaveArmonizada.setGrupo(Integer.parseInt(stockUtilString.removerPuntoCero(valor)));
			break;
		case 3:
			nuevoClaveArmonizada.setSubGrupo(Integer.parseInt(stockUtilString.removerPuntoCero(valor)));
			break;
		case 4:
			nuevoClaveArmonizada.setClase(Integer.parseInt(stockUtilString.removerPuntoCero(valor)));
			break;
		case 5:
			nuevoClaveArmonizada.setClave(valor);
			break;
		case 6:
			nuevoClaveArmonizada.setDescripcion(valor);
			break;
		}
		return nuevoClaveArmonizada;
	}

	private CostosTipos crearCostosTipo(CostosTipos nuevoCostosTipos, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCostosTipos.setNombre(valor);
			break;
		case 1:
			nuevoCostosTipos.setDescripcion(valor);
			break;
		}
		return nuevoCostosTipos;
	}

	@SuppressWarnings("rawtypes")
	private void leerDatosDesdeExcel(int indiceSheet) {
		XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
		Iterator rowIterator = hssfSheet.rowIterator();

		switch (indiceSheet) {
		case 0:// Pais
			extraerPaisesDeExcel(rowIterator);
			break;
		case 1:// Estados
			extraerEstadosDeExcel(rowIterator);
			break;
		case 2:// Municipios
			extraerMunicipiosDeExcel(rowIterator);
			break;
		/*
		 * case 3:// extraerCofiaFuenteFinanciamientoDeExcel(rowIterator);
		 * break; case 4:// extraerCofiaPartidaGenericaDeExcel(rowIterator);
		 * break; case 5:// extraerCofiaProgramaDeExcel(rowIterator); break;
		 * case 6:// extraerCofiaPyDeExcel(rowIterator); break;
		 */
		case 7://
			extraerEstatusRequisicionDeExcel(rowIterator);
			break;
		case 8://
			extraerClaveArmonizadaDeExcel(rowIterator);
			break;
		case 9://
			extraerCostosTiposDeExcel(rowIterator);
			break;
		}
	}

	private void crearConfiguracionTransicionBanner(){
		if(developmentToolService.getByNombre("transicion") == null){
			DevelopmentTool imagetransition = new DevelopmentTool();
			imagetransition.setNombre("transicion");
			imagetransition.setValue("true");
			developmentToolService.save(imagetransition);
		}
	}
	
	
	private String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}
	
	/**
	 * @return Imagen <b>AImage</b> inicial de una empresa cuando la
	 *         organizacion no tiene logotipo
	 **/
	private AImage getAImageOrganizacionInicial(String urlImagen) {
		AImage aimagen = null;
		File file = null;
		try {
			file = new File(urlImagen);
			aimagen = new AImage(file);

		} catch (Exception e) {
			logger.error(("Error al leer " + urlImagen +  ":\n" + e.getMessage()), e);
		}

		return aimagen;
	}
	
	private void cargarScriptExcel() {
		try {
			
			//fileInputStream = new FileInputStream(generarUrlString(StockConstants.LAYOUT_EXCEL_SCRIPT));
			fileInputStream = new FileInputStream(new File(ConstAtributos.PATH_HOME + ConstAtributos.PATH_LAYOUT + "Script.xlsx"));
			workBook = new XSSFWorkbook(fileInputStream);
		} catch (Exception e) {
			logger.error(("Error al leer " + (ConstAtributos.PATH_HOME + ConstAtributos.PATH_LAYOUT + "Script.xlsx") +  ":\n" + e.getMessage()), e);
		}
		
	}
	
	private void crearDirectoriosDeTrabajo() {
		SistemaOperativo os = new SistemaOperativo();
		
		String home = crearCarpeta(ConstAtributos.PATH_HOME);
		home += System.getProperty("file.separator");
		crearCarpeta(home + ConstAtributos.PATH_CFG);
		crearCarpeta(home + ConstAtributos.PATH_LAYOUT);
		
		
	}
	
	private String crearCarpeta(String path) {
		File file = new File(path);
		if(!file.exists())
			file.mkdirs();
		return file.getAbsolutePath();
	}
	
	
	
	private void getLabelsLoginVM() {
		boolean finalizarConsulta = false;
		do {
			List<LabelsModulos> labels = labelsModulosService.getAll();
			if (labels != null)
				finalizarConsulta = true;

			if (!finalizarConsulta) {
				LabelsModulos label1 = new LabelsModulos();
				label1.setNombre("loginVMUsuario");
				label1.setModulo("LOGIN_VM");
				label1.setDescripcion("Usuario");

				LabelsModulos label2 = new LabelsModulos();
				label2.setNombre("loginVMContrasena");
				label2.setModulo("LOGIN_VM");
				label2.setDescripcion("Contraseña");

				LabelsModulos label3 = new LabelsModulos();
				label3.setNombre("loginVMAccesar");
				label3.setModulo("LOGIN_VM");
				label3.setDescripcion("Accesar");

				LabelsModulos label4 = new LabelsModulos();
				label4.setNombre("loginVMAutentificar");
				label4.setModulo("LOGIN_VM");
				label4.setDescripcion("Autentificar");

				LabelsModulos label5 = new LabelsModulos();
				label5.setNombre("loginVMEscríbenos");
				label5.setModulo("LOGIN_VM");
				label5.setDescripcion("Escríbenos");

				labelsModulosService.save(label1);
				labelsModulosService.save(label2);
				labelsModulosService.save(label3);
				labelsModulosService.save(label4);
				labelsModulosService.save(label5);
			}
		} while (!finalizarConsulta);
	}
	
}