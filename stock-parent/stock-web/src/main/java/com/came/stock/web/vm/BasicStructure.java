package com.came.stock.web.vm;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code128.EAN128;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.zkoss.bind.BindContext;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Window;

import com.came.stock.beans.SistemaOperativo;
import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.ControlUeps;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.ExcelFileReadWriteUtil;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.layer.LayerWebData;
import com.came.stock.web.services.confya.ServicioWebConffyaSoapStub;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public abstract class BasicStructure extends LayerWebData {
	private static final long serialVersionUID = 3686010678115196973L;
	private boolean ocultarColumnaPromedio;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void init() {
		areas = new ArrayList();
		posiciones = new ArrayList();
		requisicion = new Requisicion();
		libroHssf = new HSSFWorkbook();
		cotizacionesList = new ArrayList();
		sistemaOperativo = new SistemaOperativo();
	}
	
	public void getStylesGlobal(){
		styleModuloFontTitle = "@import url('https://fonts.googleapis.com/css?family=Baloo+Bhaina'); font-family: 'Baloo Bhaina', cursive; font-weight: bold; font-size: 130%; color: " + StockConstants.COLOR_FONT_GLOBAL;
		styleGlobalLabelFont = "color: " + StockConstants.COLOR_FONT_GLOBAL + "; + font-size:smaller; font-weight: bold;";
		styleGlobalLabelTableContentFont = "color: " + StockConstants.COLOR_FONT_GLOBAL + "; + font-size:smaller;";
		styleGlobalLabelTableHeaderFont = "color: " + StockConstants.COLOR_FONT_GLOBAL;
	}

	public void newRecord() {
	}

	public void deleteRecord() {
	}

	public void saveChanges() {
	}

	public void performSerch() {
	}

	public String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}

	public Process openPdf(String url) {
		Process p = null;
		try {
			if (new File(url).exists()) {
				p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

				p.waitFor();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			closePdf(url);
		}
		return p;
	}

	public void fileCopy(String sourceFile, String destinationFile) {
		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}

	public boolean closePdf(String nombreAplicacion) {
		boolean eliminado = false;
		String osName = System.getProperty("os.name");
		String cmd = "";
		if (osName.toUpperCase().contains("WIN")) {
			cmd = cmd + "tskill " + nombreAplicacion;
		} else {
			cmd = cmd + "killall " + nombreAplicacion;
		}
		try {
			Process hijo = Runtime.getRuntime().exec(cmd);
			hijo.waitFor();
			if (hijo.exitValue() == 0) {
				eliminado = true;
			}
		} catch (IOException e) {
			System.out.println("Incapaz de matar: Excepcion IOE");
		} catch (InterruptedException e) {
			System.out.println("Incapaz de matar: Excepcion InterruptedException");
		}
		return eliminado;
	}

	public String obtenerVAlorDeCeldaDeExcel(Cell cell) {
		String valor = "";
		switch (cell.getCellType()) {
		case 0:
			Integer entero = Integer.valueOf((int) cell.getNumericCellValue());
			valor = String.valueOf(entero);
			break;
		case 1:
			valor = cell.getStringCellValue();
			break;
		case 2:
			valor = String.valueOf(cell.getCachedFormulaResultType());
		}
		return valor;
	}
	
	

	public InputStream getStreamMediaExcel(BindContext contexto) {
		InputStream stream = null;
		
		UploadEvent upEvent = null;
		Object objUploadEvent = contexto.getTriggerEvent();
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent)))
			upEvent = (UploadEvent) objUploadEvent;

		if (upEvent != null) {
			Media media = upEvent.getMedia();
			stream = media.getStreamData();
		}
		return stream;
	}
	
	public byte[] getBytesMediaExcel(BindContext contexto) {
		byte[] bytes = null;

		UploadEvent upEvent = null;
		Object objUploadEvent = contexto.getTriggerEvent();
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent)))
			upEvent = (UploadEvent) objUploadEvent;

		if (upEvent != null) {
			Media media = upEvent.getMedia();
			bytes = media.getByteData();
		}
		return bytes;
	}

	public boolean validarExtension(BindContext contexto, String extensionAValidar) {
		boolean correcto = false;

		UploadEvent upEvent = null;
		Object objUploadEvent = contexto.getTriggerEvent();
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent)))
			upEvent = (UploadEvent) objUploadEvent;

		if (upEvent != null) {
			Media media = upEvent.getMedia();
			if(media.getFormat().equals(extensionAValidar))
				correcto = true;
		}
		return correcto;
	}

	public String getExtensionUploadedFile(BindContext contexto) {
		String extension = "";

		UploadEvent upEvent = null;
		Object objUploadEvent = contexto.getTriggerEvent();
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent)))
			upEvent = (UploadEvent) objUploadEvent;

		if (upEvent != null) {
			Media media = upEvent.getMedia();
			String cadena = media.getName();
			boolean extraer = false;
			int count = 0;
			for (int i = 0; i < cadena.length(); i++) {
				String caracter = cadena.substring(i, (i + 1));
				if (caracter.equals(".")) {
					count++;
					extraer = true;
				}

				if (extraer && count == 1)
					extension += caracter;

			}
		}
		return extension;
	}

	public InputStream getStreamExcelPathString(String path) {
		InputStream stream = null;
		try {
			File file = new File(path);
			stream = new FileInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	

	public String suprimirComillas(String input) {
		String salida = input;
		if (input.contains("\""))
			salida = input.substring(1, (input.length() - 1));
		return salida;
	}

	public void generarArbolDirectorios() {
	}

	public String sendEmail(Usuarios usuario, Persona persona, String mensaje, String subject,
			DevelopmentTool development, File file) {
		String debug = "";
		try {
			JsonParser parser = new JsonParser();
			JsonObject jobject = parser.parse(stockUtils.Desencriptar(development.getValue())).getAsJsonObject();

			final String us = suprimirComillas(String.valueOf(jobject.get("user")));
			final String pa = suprimirComillas(String.valueOf(jobject.get("pass")));
			final String puerto = suprimirComillas(String.valueOf(jobject.get("puerto")));
			final String servidor = suprimirComillas(String.valueOf(jobject.get("servidor")));

			Properties props = new Properties();
			props.put("mail.smtp.host", new String(servidor));
			props.put("mail.smtp.socketFactory.port", puerto);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", puerto);
			
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(us, pa);
				}
			});

			
			String correoDe = us;
			if(usuario.getPersona().getContacto() != null)
				correoDe = usuario.getPersona().getContacto().getEmail().getEmail();
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correoDe));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(persona.getContacto().getEmail().getEmail()));
			message.setSubject(subject);

			// ---------------------------
			Multipart multipart = new MimeMultipart("alternative");
			MimeBodyPart textPart = new MimeBodyPart();
			String textContent = persona.getNombreCompleto() + " " + mensaje;

			textPart.setText(textContent);

			MimeBodyPart htmlPart = new MimeBodyPart();
			String htmlContent = "<html><h1>Hola " + persona.getNombreCompleto() + "</h1><p> <b>"
					+ usuario.getPersona().getNombreCompleto() + " ("
					+ correoDe + ")</b><br/><b style='color:red;'> "
					+ mensaje + "</b></p></html>";
			htmlPart.setContent(htmlContent, "text/html");

			multipart.addBodyPart(textPart);
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);

			if (file != null) {// Adjuntar archivo al correo
				DataSource ds = new FileDataSource(file);
				htmlPart.setDataHandler(new DataHandler(ds));

				htmlPart.setFileName(file.getName());
				htmlPart.setDisposition(Part.ATTACHMENT);
				multipart.addBodyPart(htmlPart);
			}

			// -------------------------
			Transport.send(message);

		} catch (Exception e) {
			debug = e.getMessage();
			//throw new RuntimeException(e);
		}
		return debug;
	}

	public DevelopmentTool crearCorreoDeServicioEmail(Email email, DevelopmentTool dev) {
		if (email.getEmail() != null && email.getEmail().contains("@")) {
			JsonObject object = crearJsonObjectEmailDevelopment(email);
			dev.setValue(stockUtils.Encriptar(object.toString()));
		} else
			dev.setDescripcion("El correo debe tener un dominio para el funcionamiento del servicio");
		return dev;
	}

	public JsonObject crearJsonObjectEmailDevelopment(Email email) {
		JsonObject object = new JsonObject();
		object.addProperty("user", email.getEmail());
		object.addProperty("pass", email.getTipo());
		object.addProperty("puerto", email.getWeb());
		object.addProperty("servidor", email.getContacto());
		object.addProperty("activado", true);

		return object;
	}

	public List<String> enviarCorreos(Usuarios usuario, Organizacion org, List<Privilegios> privilegios, String mensaje,
			String asunto, File file) {
		List<String> mensajesEnvioCorreo = new ArrayList<>();
		// --- enviar correo
		for (Privilegios item : privilegios) {
			if (item.getUsuarios().getPersona().getContacto() != null) {
				String mensajeEnvio = sendEmail(usuario, item.getUsuarios().getPersona(), mensaje, asunto,
						org.getDevelopmentTool(), file);

 				if (!mensajeEnvio.equals(""))
 					mensajesEnvioCorreo.add(item.getUsuarios().getPersona().getContacto().getEmail().getEmail() + ": " + mensajeEnvio + " ERROR");
 				else
 					mensajesEnvioCorreo.add(item.getUsuarios().getPersona().getContacto().getEmail().getEmail() + ": ¡OK!");
			}
		}
		return mensajesEnvioCorreo;
	}

	public List<String> getExtensionArchivo(String palabra, String caracter) {
		List<String> parts = new ArrayList<String>();
		String nuevaPalabra = "";
		for (int i = 0; i < palabra.length(); i++) {
			String item = palabra.substring(i, (i + 1));
			if (!item.equals("."))
				nuevaPalabra += item;
			if (item.equals(caracter)) {
				if (!nuevaPalabra.equals(".") && !nuevaPalabra.equals("")) {
					parts.add(nuevaPalabra);
					nuevaPalabra = "";
				}
			}
		}
		if (!nuevaPalabra.equals(""))
			parts.add(nuevaPalabra);
		return parts;
	}

	@SuppressWarnings("rawtypes")
	public List getDataExcel(InputStream inPutStream, int indiceSheet) {
		ExcelFileReadWriteUtil excelUtil = new ExcelFileReadWriteUtil();
		List cellDataList = excelUtil.readXLSXFile(inPutStream, indiceSheet);		
		return cellDataList;
	}

	public String getTextoFromClipboard() {
		String texto = "";
		try {
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable t = cb.getContents(this);
			DataFlavor dataFlavorStringJava = new DataFlavor(
					"application/x-java-serialized-object; class=java.lang.String");
			if (t.isDataFlavorSupported(dataFlavorStringJava)) { // Si es Texto
				texto = (String) t.getTransferData(dataFlavorStringJava);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return texto;
	}

	public void setTextoToClipboar(String textoAPortapapeles) {
		try {
			Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection ss = new StringSelection(textoAPortapapeles);
			cb.setContents(ss, ss);

			cb.setContents(ss, new ClipboardOwner() {
				public void lostOwnership(Clipboard clipboard, Transferable contents) {
					System.out.println("Clipboard actualizado");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public JsonElement inicializarConexionJsonUrl(String cp) {
		Calendar calendario = Calendar.getInstance();
		boolean continuar = false;
		int count = 0;

		do {
			try {
				String uri = "https://api-codigos-postales.herokuapp.com/v2/codigo_postal/" + cp;
				URL url = new URL(uri);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Conectando a URL");
				JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Haciendo Parseo");
				parser = new JsonParser();
				datosGlobalesJSON = parser.parse(reader);
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Extrayendo informacion");
				continuar = true;
			} catch (Exception e) {
				count++;
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > " + e.getMessage()
						+ ": Retrying connection " + count);
			}
		} while (!continuar);

		return datosGlobalesJSON;
	}

	public void dumpCodigoPostalJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				direccionJSon = construirDireccionCodigoPostal(entrada.getKey(), entrada.getValue(), direccionJSon);
				dumpCodigoPostalJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpCodigoPostalJSONElement(entrada);
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

	@SuppressWarnings("static-access")
	public Direccion construirDireccionCodigoPostal(String key, JsonElement value, Direccion direccionBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		stringOut = stockUtils.convertFromUTF8(stringOut);

		if (key.equals("codigo_postal")) {
			if (!stringOut.equals("\"\"")) {
				if (!stringOut.equals("")) {
					direccionBuiding.setCp(stringOut);
					contadorCamposCodigoPostal++;
				}

			}
		}
		if (key.equals("municipio")) {
			if (!stringOut.equals("\"\"")) {
				if (!stringOut.equals("")) {
					direccionBuiding.setMunicipio(iteratorList.getMunicipioFromListByName(municipios, stringOut));
					direccionBuiding.setMunicipioTemporal(stringOut);
					contadorCamposCodigoPostal++;
				}
			}
		}
		if (key.equals("estado")) {
			if (!stringOut.equals("\"\"")) {
				if (!stringOut.equals("")) {
					if (stringOut.contains("Ciudad de M")) {
						stringOut = "Distrito Federal";
					}
					direccionBuiding.setEstado(iteratorList.getEstadoFromListByName((List<Estado>) estadoRest.getAll().getSingle(), stringOut));
					municipios = (List<Municipio>) municipioRest.getByEstado(direccionBuiding.getEstado()).getSingle();
					direccionBuiding.setMunicipio(iteratorList.getMunicipioFromListByName(municipios, direccionBuiding.getMunicipioTemporal()));
					
					contadorCamposCodigoPostal++;
				}
			}
		}
		if (key.equals("colonias")) {
			if (!stringOut.equals("\"\"")) {

				String splitArray[] = stringOut.split(",");
				List<String> colonias = new ArrayList<>();

				if (splitArray.length > 0) {
					for (int i = 0; i < splitArray.length; i++) {
						stringOut = suprimirComillas(splitArray[i]);
						colonias.add(stringOut);
					}
					direccionBuiding.setColonias(colonias);
					contadorCamposCodigoPostal++;
				}
			}
		}

		return direccionBuiding;
	}

	/**
	 * @param numero
	 *            entero que será convertido a short.
	 * @return numero tipo short convertido.
	 */
	public short intToShort(Integer entero) {
		try {
			return entero.shortValue();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Convertidor de numero Long a short
	 * 
	 * @param numero
	 *            Long que será convertido a short.
	 * @return numero tipo short convertido.
	 */
	public short longToShort(Long longValue) {
		try {
			return new Short(String.valueOf(longValue));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * @return Imagen <b>AImage</b> inicial de una empresa cuando la
	 *         organizacion no tiene logotipo
	 **/
	public AImage getAImageOrganizacionInicial(String urlImagen) {
		AImage aimagen = null;
		File file = null;
		try {
			file = new File(getClass().getClassLoader().getResource(urlImagen).toURI());
			aimagen = new AImage(file);

		} catch (Exception e) {
			System.out.println("El fichero " + file.getAbsolutePath() + " no se encuentra en el sistema");
		}

		return aimagen;
	}

	// -------------- CONTROL PANEL LECTURA DE LAYOUTS ---------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Area> leerDatosDesdeExcelArea(List cellDataList) {
		List<Area> areaNuevosExcel = new ArrayList();
		SimpleDateFormat sdf;
		try {
			for (int i = 0; i < cellDataList.size(); i++) {
				Area nuevoArea = new Area();
				
				List cellTempList = (List) cellDataList.get(i);
				for (int j = 0; j < cellTempList.size(); j++) {
					XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
					if(i > 0)
						nuevoArea = crearArea(nuevoArea, hssfCell, j);
				}
				
				if(i > 0){
					nuevoArea.setOrganizacion(organizacion);
					nuevoArea.setUsuario(usuario);

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoArea.setFechaActualizacion(date);
					nuevoArea.setToolTipIndice("Seleccionar Area");
					nuevoArea.setToolTipNombre("Clic sobre esta columna para editar nombre");
					areaNuevosExcel.add(nuevoArea);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Almacen> leerDatosDesdeExcelAlmacenes(List cellDataList) {
		List<Almacen> almacenNuevosExcel = new ArrayList();
		try {
			for (int i = 0; i < cellDataList.size(); i++) {
				Almacen nuevoAlmacen = new Almacen();
				List cellTempList = (List) cellDataList.get(i);
				if (i > 0) {
					for (int j = 0; j < cellTempList.size(); j++) {
						XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
						nuevoAlmacen = crearAlmacen(nuevoAlmacen, hssfCell, j);
						j++;
					}
					nuevoAlmacen.setOrganizacion(organizacion);
					almacenNuevosExcel.add(nuevoAlmacen);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return almacenNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Banco> leerDatosDesdeExcelBanco(InputStream inPutStream, int indiceSheet) {
		List<Banco> bancoNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Banco nuevoBanco = new Banco();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 1)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoBanco = crearBanco(nuevoBanco, hssfCell, j);
						j++;
					}
					nuevoBanco.setOrganizacion(organizacion);
					nuevoBanco.setUsuario(usuario);

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoBanco.setFechaActualizacion(date);
					nuevoBanco.setToolTipIndice("Seleccionar moneda");
					nuevoBanco.setToolTipNombre("Clic sobre esta columna para editar nombre");
					bancoNuevosExcel.add(nuevoBanco);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bancoNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Moneda> leerDatosDesdeExcelMoneda(InputStream inPutStream, int indiceSheet) {
		List<Moneda> monedaNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Moneda nuevoMoneda = new Moneda();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoMoneda = crearMoneda(nuevoMoneda, hssfCell, j);

						j++;
					}
					nuevoMoneda.setOrganizacion(organizacion);
					nuevoMoneda.setUsuario(usuario);

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoMoneda.setFechaActualizacion(date);
					nuevoMoneda.setToolTipIndice("Seleccionar moneda");
					nuevoMoneda.setToolTipNombre("Clic sobre esta columna para editar nombre");
					monedaNuevosExcel.add(nuevoMoneda);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monedaNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Posicion> leerDatosDesdeExcelPosicion(InputStream inPutStream, int indiceSheet) {
		List<Posicion> posicionNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Posicion nuevoPosicion = new Posicion();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoPosicion = crearPosicion(nuevoPosicion, hssfCell, j);

						j++;
					}
					nuevoPosicion.setOrganizacion(organizacion);
					nuevoPosicion.setUsuario(usuario);

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoPosicion.setFechaActualizacion(date);
					nuevoPosicion.setToolTipIndice("Seleccionar puesto");
					nuevoPosicion.setToolTipNombre("Clic sobre esta columna para editar nombre");
					posicionNuevosExcel.add(nuevoPosicion);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posicionNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<ProductoTipo> leerDatosDesdeExcelTipoProductos(InputStream inPutStream, int indiceSheet) {
		List<ProductoTipo> productoTipoNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				ProductoTipo nuevoProductoTipo = new ProductoTipo();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoProductoTipo = crearTipoProductos(nuevoProductoTipo, hssfCell, j);
						j++;
					}
					nuevoProductoTipo.setOrganizacion(organizacion);
					nuevoProductoTipo.setUsuario(usuario);

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoProductoTipo.setFechaActualizacion(date);
					productoTipoNuevosExcel.add(nuevoProductoTipo);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoTipoNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Unidad> leerDatosDesdeExcelUnidadMedida(InputStream inPutStream, int indiceSheet) {
		List<Unidad> unidadNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				Unidad nuevoUnidad = new Unidad();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoUnidad = crearUnidadMedida(nuevoUnidad, hssfCell, j);
						j++;
					}
					nuevoUnidad.setOrganizacion(organizacion);
					nuevoUnidad.setUsuario(usuario);
					nuevoUnidad.setFechaActualizacion(Calendar.getInstance());
					unidadNuevosExcel.add(nuevoUnidad);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unidadNuevosExcel;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Presentacion> leerDatosDesdeExcelPresentacion(InputStream inPutStream, int indiceSheet) {
		List<Presentacion> presentacionesNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				Presentacion nuevaPresentacion = new Presentacion();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 3)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevaPresentacion = crearPresentacion(nuevaPresentacion, hssfCell, j);
						j++;
					}
					nuevaPresentacion.setOrganizacion(organizacion);
					presentacionesNuevosExcel.add(nuevaPresentacion);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return presentacionesNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<Giro> leerDatosDesdeExcelGiros(InputStream inPutStream, int indiceSheet) {
		List<Giro> giroNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				Giro nuevoGiro = new Giro();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 4)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoGiro = crearGiro(nuevoGiro, hssfCell, j);
						j++;
					}
					nuevoGiro.setOrganizacion(organizacion);
					giroNuevosExcel.add(nuevoGiro);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return giroNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "resource" })
	public List<ProductoNaturaleza> leerDatosDesdeExcelNaturaleza(InputStream inPutStream, int indiceSheet) {
		List<ProductoNaturaleza> productoNaturalezaNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				ProductoNaturaleza nuevoProductoNaturaleza = new ProductoNaturaleza();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 3)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoProductoNaturaleza = crearNaturaleza(nuevoProductoNaturaleza, hssfCell, j);
						j++;
					}
					productoNaturalezaNuevosExcel.add(nuevoProductoNaturaleza);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoNaturalezaNuevosExcel;
	}
	// -------------- FIN CONTROL PANEL LECTURA DE LAYOUTS --------------------

	// *************** CONTROL PANEL CREAR OBJETOS ****************************
	private Area crearArea(Area area, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				area.setNombre(valor);
		}
		return area;
	}

	private Almacen crearAlmacen(Almacen nuevoAlmacen, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				nuevoAlmacen.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				nuevoAlmacen.setDescripcion(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0")) {
					valor = stockUtilString.removerPuntoCero(valor);
				}
				nuevoAlmacen.setArea(iteratorList.getAreasFromList((List<Area>)areaRest.getAll((Organizacion) sessionUtils.getFromSession("FIRMA")).getSingle(), Long.parseLong(valor)));
				nuevoAlmacen.setAreaJson(valor);
			}
			break;
		}
		return nuevoAlmacen;
	}

	private Banco crearBanco(Banco banco, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				banco.setNombre(valor);
		}
		return banco;
	}

	private Moneda crearMoneda(Moneda moneda, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				moneda.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				moneda.setSimbolo(valor);
		}
		return moneda;
	}

	private Posicion crearPosicion(Posicion puesto, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				puesto.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				puesto.setDescripcion(valor);
		}
		return puesto;
	}

	private ProductoTipo crearTipoProductos(ProductoTipo productoTipo, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoTipo.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoTipo.setDescripcion(valor);
		}
		return productoTipo;
	}

	private Unidad crearUnidadMedida(Unidad unidad, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				unidad.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				unidad.setAbreviatura(valor);
		}
		return unidad;
	}
	
	private Presentacion crearPresentacion(Presentacion objeto, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if (valor != null && !valor.isEmpty())
				objeto.setNombre(valor);
			break;
		case 1:
			if (valor != null && !valor.isEmpty())
				objeto.setClave(valor);
			break;
		case 2:
			if (valor != null && !valor.isEmpty())
				objeto.setDescripcion(valor);
			break;
		}
		return objeto;
	}

	private Giro crearGiro(Giro giro, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {

		case 0:
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				giro.setNombre(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				giro.setDescripcion(valor);
		
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				giro.setSector(valor);
		}
		return giro;
	}

	private ProductoNaturaleza crearNaturaleza(ProductoNaturaleza productoNaturaleza, XSSFCell valorDePropiedad,
			int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoNaturaleza.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoNaturaleza.setSimbolo(valor);

			break;
		}
		return productoNaturaleza;
	}
	// *************** FIN CONTROL PANEL CREAR OBJETOS ********************

	/**
	 * Escribe un archivo proveniente de arreglo de bytes[] al disco
	 * 
	 * @param byteValue
	 *            bytes del archivo que se va a escribir
	 * @param path
	 *            ruta en donde se escribirá el archivo <b>(deve incluir el
	 *            nombre del archivo con extension)</b> ej. nombreArchivo.xlsx
	 * 
	 * @return si la escritura se llevo acabo regresa la direccion en donde se
	 *         escribio, de lo contrario regresa una letra R de error mas el
	 *         mensaje del error.
	 **/
	public String escribirByteToDisk(byte[] byteValue, String path) {
		// Execution exec = Executions.getCurrent();
		// Filedownload.save(new
		// File(exec.toAbsoluteURI(generarUrlString("layout/LayoutArea.xlsx"),
		// false)), null);

		String mensaje = "";
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(byteValue);
			fos.close();
		} catch (Exception e) {
			mensaje = "R ";
			mensaje += e.getMessage();
		}
		return mensaje;
	}

	/**
	 * Genera el numero aleatorio para un codigo de barras de un producto
	 * concatenando el id del producto y id de Organizacion
	 * 
	 * @param minimo
	 *            valor minimo para randomizar
	 * @param maximo
	 *            valor maximo para randomizar
	 * @param pro
	 *            informacion del producto
	 * @return codigo de barras tipo String
	 **/
	public static String generaNumeroAleatorioProducto(int minimo, int maximo, Producto pro) {
		int num = (int) Math.floor(Math.random() * (minimo - (maximo + 1)) + (maximo + 1));
		String code = String.valueOf(num) + pro.getIdProducto().toString()
				+ pro.getOrganizacion().getIdOrganizacion().toString();
		if (code.length() > 8)
			code = code.substring(1);
		return code;
	}

	/**
	 * Genera el codigo de barras con su respectiva imagen verificando que no
	 * exista en la BD o en la lista que ya han sido agregados como nuevos, en
	 * caso de existir genera otro codigo hasta que sea uno no registrado.
	 * 
	 * @param pro
	 *            producto que se asignara al objeto <b>CodigoBarrasProducto</b>
	 * 
	 * @return regresa un objeto <b>CodigoBarrasProducto</b> ya construido que
	 *         contiene imagen binaria del codigo de barras, el codigo de barras
	 *         y el producto que le corresponde
	 **/
	public CodigoBarrasProducto generarBarCode(Producto pro) {
		CodigoBarrasProducto barCodeTemp = new CodigoBarrasProducto();
		List<CodigoBarrasProducto> codigosEnDb = (List<CodigoBarrasProducto>) codigoBarrasProductoRest.getAll().getSingle();
		boolean continuarConstruyendoCodigo = true;
		String code = "";

		do {
			code = generaNumeroAleatorioProducto(100000, 999999, pro);
			continuarConstruyendoCodigo = codeBarNoDuplicadoValidar(codigosEnDb, codigosBarrasProductos, code);

			if (continuarConstruyendoCodigo) {
				try {
					barCodeTemp.setBarCode(barCodeEan128(code));
				} catch (Exception e) {
					continuarConstruyendoCodigo = false;
				}
			}
		} while (!continuarConstruyendoCodigo);

		barCodeTemp.setCodigo(code);
		barCodeTemp.setProducto(pro);

		return barCodeTemp;
	}

	/**
	 * valida que no exista el codigo nuevo en la DB y la lista de nuevos
	 * codigos que se van agregando
	 * 
	 * @param lista1
	 *            lista CodigoBarrasProducto con inmformacion de la DB
	 * @param lista2
	 *            lista CodigoBarrasProducto con inmformacion de los objetos que
	 *            se van agregando
	 * @param code
	 *            codigo nuevo que será verificado
	 * @return valor boleano donde true = no existe en las listas y false = si
	 *         existe en las listas (duplicado)
	 **/
	private boolean codeBarNoDuplicadoValidar(List<CodigoBarrasProducto> lista1, List<CodigoBarrasProducto> lista2,
			String code) {
		boolean ok = codeBarNoDuplicadoEnListas(lista1, code);
		if (ok)
			ok = codeBarNoDuplicadoEnListas(lista2, code);
		return ok;
	}

	private boolean codeBarNoDuplicadoEnListas(List<CodigoBarrasProducto> list, String code) {
		boolean ok = true;
		if (list != null) {
			for (CodigoBarrasProducto item : list) {
				if (item.getCodigo() != null && item.getCodigo().equals(code)) {
					ok = false;
					break;
				}
			}
		}
		return ok;
	}

	public static byte[] barCodeEan128(String codigo) {
		EAN128 bean = new EAN128();

		final int dpi = 150;

		// Configure the barcode generator

		// Open output file
		ByteArrayOutputStream out = null;
		out = new ByteArrayOutputStream();
		try {
			// Set up the canvas provider for monochrome PNG output
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/x-png", dpi,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);

			// Generate the barcode
			bean.generateBarcode(canvas, codigo);

			// Signal end of generation
			canvas.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}

	/**
	 * Iniciar conexion con webservice wsdl <b>service</b>
	 **/
	public void inicializarServicioWebService() {
		try {
			serviceWs = new ServicioWebConffyaSoapStub(new URL(StockConstants.URL_WSDL), null);
		} catch (Exception e) {
			System.err.println("Error al conectar con legajo:");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Producto> leerDatosDesdeExcelArticulosDeProveedorByList(List cellDataList) {
		List<Producto> artículosNuevosExcel = new ArrayList();
		StockUtils utils = new StockUtils();
		SimpleDateFormat sdf;
		for (int i = 0; i < cellDataList.size(); i++) {
			Producto nuevoProducto = new Producto();
			
			List cellTempList = (List) cellDataList.get(i);
			for (int j = 0; j < cellTempList.size(); j++) {
				XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
				if(i > 0)
					nuevoProducto = crearProducto(nuevoProducto, hssfCell, j);
			}
			
			if(i > 0){
				sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(utils.convertirCalendarToDate(Calendar.getInstance()));
				nuevoProducto.setFechaActualizacion(date);
				artículosNuevosExcel.add(nuevoProducto);
			}
		}
		return artículosNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Producto> leerDatosDesdeExcelArticulosDeProveedor(Iterator rowIterator) {
		List<Producto> artículosNuevosExcel = new ArrayList();
		try {
			Integer i = 0;
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Producto nuevoProducto = new Producto();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 9)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoProducto = crearProducto(nuevoProducto, hssfCell, j);
						j++;
					}

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoProducto.setFechaActualizacion(date);
					artículosNuevosExcel.add(nuevoProducto);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return artículosNuevosExcel;
	}

	private Producto crearProducto(Producto productoItem, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {

			}
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoItem.setClave(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoItem.setNombre(valor);
			break;
		case 3:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);

				productoItem.setEnExistencia(Integer.parseInt(valor));
			}

			break;
		case 4:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoItem.setModelo(valor);
			break;
		case 5:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoItem.setMarca(valor);
			break;
		case 6:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoItem.setDescripcion(valor);
			break;
		case 7:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				List<ProductoPrecios> listaPrecios = new ArrayList<>();
				ProductoPrecios item = new ProductoPrecios();
				item.setPrecioValue(Float.parseFloat(valor));
				item.setActualizacion(Calendar.getInstance());
				item.setPrecioDescripcion("Precio importado");
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				listaPrecios.add(item);
				productoItem.setProductoPrecios(listaPrecios);
				productoItem.setPrecio(Float.parseFloat(valor));
			}

			break;
		case 8:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0"))
					valor = stockUtilString.removerPuntoCero(valor);
				List<ProductoCostos> listaCostos = new ArrayList<>();
				ProductoCostos item = new ProductoCostos();
				
				try {
					item.setCostoValue(Float.parseFloat(valor));
					productoItem.setCostoUltimo(Float.parseFloat(valor));
				} catch (Exception e) {
					item.setCostoValue(productoItem.getEnExistencia() * productoItem.getPrecio());
					productoItem.setCostoUltimo(productoItem.getEnExistencia() * productoItem.getPrecio());
				}
				
				item.setActualizacion(Calendar.getInstance());
				item.setCostoDescripcion("Costo Ultimo");
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				listaCostos.add(item);
				productoItem.setProductoCostos(listaCostos);
				
				
				
			}

			break;
		}

		return productoItem;
	}
	
	/**
	 * @param title, titulo de la ventana
	 * @param content, contenido del mensaje
	 * @param executeNameMethodAfterAccept, nombre del metodo que se ejecutara despues de aceptar el mensaje
	 * @param icon, url del icono que desea colocar, tambien puede colocar una de las
	 * constantes ya creadas (ICON_WIN_INFORMATION, ICON_WIN_WARNING, ICON_WIN_ERROR)
	 * **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showWindowInformationMessage(String title, String content, String executeNameMethodAfterAccept, String icon) {

		Map<String, Object> inputParams = new HashMap();
		inputParams.put("executeNameMethod", executeNameMethodAfterAccept);
		inputParams.put("titulo", title);
		inputParams.put("contenido", content);
		inputParams.put("icon", icon);
		
		Window productoModalView = stockUtils.createModelDialogWithParams("/modulos/utilidades/windowInformation.zul",
				inputParams);
		productoModalView.doModal();

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void showWindowConfirmationMessage(String title, String content, String executeNameMethodAfterAccept, String icon, Object value) {

		Map<String, Object> inputParams = new HashMap();
		inputParams.put("executeNameMethod", executeNameMethodAfterAccept);
		inputParams.put("titulo", title);
		inputParams.put("contenido", content);
		inputParams.put("icon", icon);
		inputParams.put("object", value);
		
		Window productoModalView = stockUtils.createModelDialogWithParams("/modulos/utilidades/windowConfirmation.zul",
				inputParams);
		productoModalView.doModal();

	}

	@SuppressWarnings("unused")
	public Properties getPropertiesFiles(){
		Properties propiedades = new Properties();
		String language = System.getProperty("user.country");
		//System.getProperty("user.language");
		
		try {
			propiedades
		     .load(new FileInputStream(
		    		 generarUrlString(StockConstants.LANGUAGE_ESP)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propiedades;
	}
	
	
	public void kardexRecuperarModalidadCalculoCosto(DevelopmentTool modalidadCalculo){
		
		if(modalidadCalculo.getValue().equals(StockConstants.SISTEMA_PEPS)){
			ocultarColumnaPromedio = false;
			checkPeps = true;
		}
			
		else if(modalidadCalculo.getValue().equals(StockConstants.SISTEMA_UEPS)){
			ocultarColumnaPromedio = false;
			checkUeps = true;
		}else if(modalidadCalculo.getValue().equals(StockConstants.SISTEMA_PROMEDIO)){
			checkPromedio = true;
			ocultarColumnaPromedio = true;
		}
			
	}
	
	public void kardexCargarProductosConCalculoCosto(){
		if(checkPeps)
			metodoEvaluacionInventarioUepsOrPeps(false);
		else if(checkUeps)
			metodoEvaluacionInventarioUepsOrPeps(true);
		else if(checkPromedio)
			metodoEvaluacionPorPromedio();
	}
	
	private void metodoEvaluacionPorPromedio(){
		visibleConstruccion = false;
		visibleContenido = true;
		
		if(kardexListInside != null){
			Integer controlAcumulativoExistencia = 0;
			Float controlAcumulativoSaldo = 0f;
			for (Kardex kardexItem : kardexListInside) {
				if(kardexItem.getEntradaCantidad() != null && kardexItem.getEntradaCantidad() > 0){
					
					//Asignar subtotal (debe)
					kardexItem.setDebe(kardexItem.getEntradaCantidad() * kardexItem.getPrecioUnitario());
				
					//Calcular existencia del producto
					controlAcumulativoExistencia += kardexItem.getEntradaCantidad();
					kardexItem.setExistenciaCantidad(controlAcumulativoExistencia);
					
					//Saldo
					controlAcumulativoSaldo += kardexItem.getDebe();
					kardexItem.setSaldo(controlAcumulativoSaldo);
					
					kardexItem.setCostoPromedio(kardexItem.getSaldo() / kardexItem.getExistenciaCantidad());
					
					kardexItem.setEntradaReadOnly(true);
					if(kardexItem.getEntradaLote() != null){
						kardexItem.setEntradaLoteReadOnly(true);
						if(kardexItem.getEntradaLote() == null || kardexItem.getExistenciaLote() == null)
							kardexItem.setEnableSaveBotton(true);
					}
						
					else
						kardexItem.setEnableSaveBotton(true);
					
					kardexItem.setSalidaReadOnly(true);
					kardexItem.setSalidaLoteReadOnly(true);
				}else{
					//Asignar subtotal (haber)
					kardexItem.setHaber(kardexItem.getSalidaCantidad() * kardexItem.getPrecioUnitario());
					
					//Calcular existencia del producto
					controlAcumulativoExistencia -= kardexItem.getSalidaCantidad();
					kardexItem.setExistenciaCantidad(controlAcumulativoExistencia);
					
					//Saldo
					controlAcumulativoSaldo -= kardexItem.getHaber();
					kardexItem.setSaldo(controlAcumulativoSaldo);
					
					//Costo promedio
					kardexItem.setCostoPromedio(kardexItem.getSaldo() / kardexItem.getExistenciaCantidad());
					
					kardexItem.setEntradaReadOnly(true);
					kardexItem.setEntradaLoteReadOnly(true);
					kardexItem.setSalidaReadOnly(true);
					if(kardexItem.getSalidaLote() != null)
						kardexItem.setSalidaLoteReadOnly(true);
					else
						kardexItem.setEnableSaveBotton(true);
					
					kardexItem.setSalidaId(kardexItem.getIdKardex().toString());
				}
			}
		}
	}
	
	
	private void metodoEvaluacionInventarioUepsOrPeps(boolean modoUeps){
		visibleConstruccion = false;
		visibleContenido = true;
		
		//******************* TEMPORAL PRUEBAS
		//kardexListInside = getListaFicticiaOrdenada();
		//*******************
		List<Kardex> kardexModoUeps = new ArrayList<>();
		
		if(kardexListInside != null){
			reordenarKardexPorFechaMasReciente();
			int indice = 0;
			controlAcumulativoExistencia = 0;
			controlAcumulativoSaldo = 0f;
			for (int i = 0; i < kardexListInside.size(); i++) {
				Kardex kardexItem = kardexListInside.get(i);
			
			//for (Kardex kardexItem : kardexListInside) {
				if(kardexItem.getEntradaCantidad() != null && kardexItem.getEntradaCantidad() > 0){
					
					//Asignar subtotal (debe)
					kardexItem.setDebe(kardexItem.getEntradaCantidad() * kardexItem.getPrecioUnitario());
				
					//Calcular existencia del producto
					controlAcumulativoExistencia += kardexItem.getEntradaCantidad();
					kardexItem.setExistenciaCantidad(controlAcumulativoExistencia);
					
					//Saldo
					controlAcumulativoSaldo += kardexItem.getDebe();
					kardexItem.setSaldo(controlAcumulativoSaldo);
					
					kardexItem.setCostoPromedio(kardexItem.getSaldo() / kardexItem.getExistenciaCantidad());
					
					kardexItem.setEntradaReadOnly(true);
					if(kardexItem.getEntradaLote() != null){
						kardexItem.setEntradaLoteReadOnly(true);
						if(kardexItem.getEntradaLote() == null || kardexItem.getExistenciaLote() == null)
							kardexItem.setEnableSaveBotton(true);
					}
						
					else
						kardexItem.setEnableSaveBotton(true);
					
					kardexItem.setSalidaReadOnly(true);
					kardexItem.setSalidaLoteReadOnly(true);
					
					kardexModoUeps.add(kardexItem);
				}else if(kardexItem.getSalidaCantidad() != null){
					
					//Ejecutar control de salida UEPS
					List<ControlUeps> controlDezglozadoVentas = null;
					if(modoUeps){
						List<Kardex> desgloseList = calcularVentaDesgloseUeps(kardexItem.getSalidaCantidad(), kardexItem, indice);
						for (Kardex kardex : desgloseList){
							controlAcumulativoExistencia -= kardex.getSalidaCantidad();
							controlAcumulativoSaldo -= kardex.getHaber();
							
							if(kardex.getExistenciaCantidad() != controlAcumulativoExistencia)
								kardex.setExistenciaCantidad(controlAcumulativoExistencia);
							if(kardex.getSaldo() != controlAcumulativoSaldo)
								kardex.setSaldo(controlAcumulativoSaldo);
							kardex.setIdKardexClonacion(kardexItem.getIdKardex());
							kardexModoUeps.add(kardex);
						}
							
						
					}else
					//Ejecutar control de salida PEPS
						controlDezglozadoVentas = desglozarVentaMetodoPeps(kardexItem, kardexItem.getSalidaCantidad());
					
					
					int j = 0;
					if(controlDezglozadoVentas != null)
						for (ControlUeps item : controlDezglozadoVentas) {
							item.getKardex().setEntradaReadOnly(true);
							item.getKardex().setEntradaLoteReadOnly(true);
							item.getKardex().setSalidaReadOnly(true);
							if(item.getKardex().getSalidaLote() != null)
								item.getKardex().setSalidaLoteReadOnly(true);
							else
								item.getKardex().setEnableSaveBotton(true);
							
							item.getKardex().setSalidaId(item.getKardex().getIdKardex().toString() + "" + j);
							kardexModoUeps.add(item.getKardex());
							j++;
						}
				}else if(kardexItem.isDevolucionVenta()){
					realizarDevolucion();
				}
				indice++;
			}
			kardexListInside.clear();
			kardexListInside = kardexModoUeps;
		}
		
	}
	
	private void reordenarKardexPorFechaMasReciente(){
		List<Long> idsKardex = new ArrayList<>();
		for (Kardex item : kardexListInside)
			idsKardex.add(item.getIdKardex());
		kardexListInside.clear();
		
		Organizacion orgOnline = (Organizacion) sessionUtils.getFromSession("FIRMA");
		kardexListInside = (List<Kardex>) kardexRest.getKardexOrderByDateMasReciente(idsKardex, orgOnline).getSingle();
	}
	
	
	
	
	public List<Kardex> calcularVentaDesgloseUeps(Integer cantidad, Kardex kardexDeVenta, int indice){
		List<Kardex> returnList = new ArrayList<>();
		
		if(kardexListInside != null && kardexListInside.size() > 0){
			
			int contadorRestantesProductos = kardexDeVenta.getSalidaCantidad();
			
			Kardex ultimoRegistroProducto = null;
			boolean ok= false;
			//int sizeList = kardexListInside.size();
			do {
				if(indice > -1){
					ultimoRegistroProducto = kardexListInside.get(indice - 1);
					if(ultimoRegistroProducto.getEntradaCantidad() != null){
						
						if(ultimoRegistroProducto.getUepsRestan() == null)
							ultimoRegistroProducto.setUepsRestan(ultimoRegistroProducto.getEntradaCantidad());
						
						if(ultimoRegistroProducto.getUepsRestan() > 0){
							if(contadorRestantesProductos > ultimoRegistroProducto.getUepsRestan()){
								//if(ultimoRegistroProducto.getUepsRestan() < contadorRestantesProductos ){
								
									Kardex salidaKardex = new Kardex();
									salidaKardex.setOrganizacion(ultimoRegistroProducto.getOrganizacion());
									salidaKardex.setUsuario(usuario);
									
									salidaKardex.setSalidaCantidad(ultimoRegistroProducto.getUepsRestan());
									salidaKardex.setHaber(salidaKardex.getSalidaCantidad() * ultimoRegistroProducto.getPrecioUnitario());
									int existencia = ultimoRegistroProducto.getExistenciaCantidad() - ultimoRegistroProducto.getUepsRestan();
									salidaKardex.setExistenciaCantidad(existencia);
									salidaKardex.setSaldo(ultimoRegistroProducto.getSaldo() - salidaKardex.getHaber());
									
									
									salidaKardex.setProducto(ultimoRegistroProducto.getProducto());
									salidaKardex.setPrecioUnitario(ultimoRegistroProducto.getPrecioUnitario());
									salidaKardex.setFechaEntrada(new Date());
									salidaKardex.setTypeFormat("S");
									//salidaKardex.setSalidaId(String.valueOf(listaGlobalKardexLocal.size()));
									//salidaKardex.setEnableSaveBotton(true);
									salidaKardex.setReferenciaKardex(ultimoRegistroProducto.getIdKardex());
									
									
									returnList.add(salidaKardex);
									
									ultimoRegistroProducto.setUepsRestan(0);
									
									contadorRestantesProductos -= ultimoRegistroProducto.getEntradaCantidad();
									if(contadorRestantesProductos > 0){
										indice -= 1;
									}else
										ok = true;
								}else{
									Kardex salidaKardex = new Kardex();
									salidaKardex.setOrganizacion(ultimoRegistroProducto.getOrganizacion());
									salidaKardex.setUsuario(usuario);
									
									salidaKardex.setSalidaCantidad(contadorRestantesProductos);
									salidaKardex.setHaber(salidaKardex.getSalidaCantidad() * ultimoRegistroProducto.getPrecioUnitario());
									int existencia = ultimoRegistroProducto.getExistenciaCantidad() - contadorRestantesProductos;
									salidaKardex.setExistenciaCantidad(existencia);
									salidaKardex.setSaldo(ultimoRegistroProducto.getSaldo() - salidaKardex.getHaber());
									
									salidaKardex.setProducto(ultimoRegistroProducto.getProducto());
									salidaKardex.setPrecioUnitario(ultimoRegistroProducto.getPrecioUnitario());
									salidaKardex.setFechaEntrada(new Date());
									salidaKardex.setTypeFormat("S");
									//salidaKardex.setSalidaId(String.valueOf(listaGlobalKardexLocal.size()));
									//salidaKardex.setEnableSaveBotton(true);
									salidaKardex.setReferenciaKardex(ultimoRegistroProducto.getIdKardex());
									
									returnList.add(salidaKardex);
									
									ultimoRegistroProducto.setUepsRestan(ultimoRegistroProducto.getUepsRestan() - contadorRestantesProductos);
									
									contadorRestantesProductos = 0;
									ok = true;
								}
						}else
							indice -= 1;
					}else
						indice -= 1;
				}
			} while (!ok);
		}
		return returnList;
	}
	
	
	private List<ControlUeps> desglozarVentaMetodoPeps(Kardex kardexAnalizado, int cantidad/*, int acumulativoExistencia, float acumulativoSaldo*/){
		Kardex kardexItem = null;
		List<ControlUeps> controlesUeps = new ArrayList<>();
		
		int restan = cantidad;
		boolean ok = false;
		int indice = 0;
		Organizacion orgOnline = (Organizacion) sessionUtils.getFromSession("FIRMA");
		do {
			if(indice < kardexListInside.size()) {
				
				
				kardexItem = (Kardex) kardexRest.getById(kardexListInside.get(indice).getIdKardex(), orgOnline).getSingle();
						
				
				if(kardexItem.getEntradaCantidad() != null){
					
					if(kardexListInside.get(indice).getUepsRestan() == null)
						kardexListInside.get(indice).setUepsRestan(kardexItem.getEntradaCantidad());
					
					if(kardexListInside.get(indice).getUepsRestan() > 0){
						ControlUeps control = new ControlUeps();
						if(restan > kardexListInside.get(indice).getUepsRestan()){
							control.setCantidadExtraida(kardexListInside.get(indice).getUepsRestan());
							control.setCompleto(false);
							control.setDescripcion(kardexListInside.get(indice).getUepsRestan() + "-" + cantidad);
							control.setPrecio(kardexItem.getPrecioUnitario());
							control.setRestan(restan - kardexListInside.get(indice).getUepsRestan());
							
							Kardex kardexDesglozado = getKardexDBSimulator(kardexAnalizado.getIdKardex());
							kardexDesglozado.setSalidaCantidad(control.getCantidadExtraida());
							kardexDesglozado.setPrecioUnitario(control.getPrecio());
							kardexDesglozado.setHaber(kardexDesglozado.getSalidaCantidad() * kardexDesglozado.getPrecioUnitario());
							controlAcumulativoExistencia -= kardexDesglozado.getSalidaCantidad();
							kardexDesglozado.setExistenciaCantidad(controlAcumulativoExistencia);
							controlAcumulativoSaldo -= kardexDesglozado.getHaber();
							kardexDesglozado.setSaldo(controlAcumulativoSaldo);
							
							control.setKardex(kardexDesglozado);
							controlesUeps.add(control);
							
							restan -= kardexListInside.get(indice).getUepsRestan();
							
							//---------------------
							kardexListInside.get(indice).setUepsRestan(0);
							//---------------------
							
							if(control.getRestan() > 0)
								indice ++;
							else
								ok = true;
						}else{
							control.setCantidadExtraida(restan);
							control.setRestanEnKardex(kardexItem.getEntradaCantidad() - restan);
							control.setCompleto(true);
							control.setDescripcion("OK");
							control.setPrecio(kardexItem.getPrecioUnitario());
							control.setRestan(0);
							
							Kardex kardexDesglozado = getKardexDBSimulator(kardexAnalizado.getIdKardex());
							kardexDesglozado.setSalidaCantidad(control.getCantidadExtraida());
							kardexDesglozado.setPrecioUnitario(control.getPrecio());
							kardexDesglozado.setHaber(kardexDesglozado.getSalidaCantidad() * kardexDesglozado.getPrecioUnitario());
							controlAcumulativoExistencia -= kardexDesglozado.getSalidaCantidad();
							kardexDesglozado.setExistenciaCantidad(controlAcumulativoExistencia);
							controlAcumulativoSaldo -= kardexDesglozado.getHaber();
							kardexDesglozado.setSaldo(controlAcumulativoSaldo);
							
							control.setKardex(kardexDesglozado);
							controlesUeps.add(control);
							
							kardexListInside.get(indice).setUepsRestan(kardexItem.getEntradaCantidad() - restan);
							
							ok = true;
						}
					}else
						indice++;
				}else
					indice++;
			}	
			
		} while (!ok);
		
		
		return controlesUeps;
	}
	
	
	private void realizarDevolucion(){
		
	}
	@SuppressWarnings("unused")
	private List<Kardex> getListaFicticiaOrdenada(){
		List<Kardex> registros = new ArrayList<>();
		
		
		Date fecha1 = stockUtils.convertirStringToCalendar(2, 7, 2001).getTime();
		Producto producto1 = new Producto();
		producto1.setNombre("Inventario inicial");
		Kardex item1 = new Kardex();
		item1.setIdKardex(1L);
		item1.setEntradaCantidad(30);
		item1.setPrecioUnitario(250F);
		item1.setFechaEntrada(fecha1);
		item1.setTypeFormat("E");
		item1.setProducto(producto1);
		
		Date fecha2 = stockUtils.convertirStringToCalendar(5, 7, 2001).getTime();
		Producto producto2 = new Producto();
		producto2.setNombre("Compra F-326");
		Kardex item2 = new Kardex();
		item2.setIdKardex(2L);
		item2.setEntradaCantidad(24);
		item2.setPrecioUnitario(270F);
		item2.setFechaEntrada(fecha2);
		item2.setTypeFormat("E");
		item2.setProducto(producto2);
		
		Date fecha3 = stockUtils.convertirStringToCalendar(8, 8, 2001).getTime();
		Producto producto3 = new Producto();
		producto3.setNombre("Venta F-18");
		Kardex item3 = new Kardex();
		item3.setIdKardex(3L);
		item3.setSalidaCantidad(35);
		item3.setPrecioUnitario(500F);
		item3.setFechaEntrada(fecha3);
		item3.setTypeFormat("S");
		item3.setProducto(producto3);
		
		Date fecha4 = stockUtils.convertirStringToCalendar(11, 8, 2001).getTime();
		Producto producto4 = new Producto();
		producto4.setNombre("Compra F-412");
		Kardex item4 = new Kardex();
		item4.setIdKardex(4L);
		item4.setEntradaCantidad(60);
		item4.setPrecioUnitario(255F);
		item4.setFechaEntrada(fecha4);
		item4.setTypeFormat("E");
		item4.setProducto(producto4);
		
		Date fecha5 = stockUtils.convertirStringToCalendar(13, 8, 2001).getTime();
		Producto producto5 = new Producto();
		producto5.setNombre("Venta F-32");
		Kardex item5 = new Kardex();
		item5.setIdKardex(5L);
		item5.setSalidaCantidad(63);
		item5.setPrecioUnitario(575F);
		item5.setFechaEntrada(fecha5);
		item5.setTypeFormat("S");
		item5.setProducto(producto5);
		
		Date fecha6 = stockUtils.convertirStringToCalendar(15, 8, 2001).getTime();
		Producto producto6 = new Producto();
		producto6.setNombre("Compra F-680");
		Kardex item6 = new Kardex();
		item6.setIdKardex(6L);
		item6.setEntradaCantidad(50);
		item6.setPrecioUnitario(255F);
		item6.setFechaEntrada(fecha6);
		item6.setTypeFormat("E");
		item6.setProducto(producto6);
		
		
		registros.add(item1);
		registros.add(item2);
		registros.add(item3);
		registros.add(item4);
		registros.add(item5);
		registros.add(item6);
		return registros;
	}
	
	private Kardex getKardexDBSimulator(Long id){
		Kardex returnKardex = null;
		
		switch (Integer.parseInt(String.valueOf(id))) {
		case 1:
			Date fecha1 = stockUtils.convertirStringToCalendar(2, 7, 2001).getTime();
			Producto producto1 = new Producto();
			producto1.setNombre("Inventario inicial");
			Kardex item1 = new Kardex();
			item1.setIdKardex(1L);
			item1.setEntradaCantidad(30);
			item1.setPrecioUnitario(250F);
			item1.setFechaEntrada(fecha1);
			item1.setTypeFormat("E");
			item1.setProducto(producto1);
			returnKardex = item1;
			break;
		case 2:
			Date fecha2 = stockUtils.convertirStringToCalendar(5, 7, 2001).getTime();
			Producto producto2 = new Producto();
			producto2.setNombre("Compra F-326");
			Kardex item2 = new Kardex();
			item2.setIdKardex(2L);
			item2.setEntradaCantidad(24);
			item2.setPrecioUnitario(270F);
			item2.setFechaEntrada(fecha2);
			item2.setTypeFormat("E");
			item2.setProducto(producto2);	
			returnKardex = item2;
					break;
		case 3:
			Date fecha3 = stockUtils.convertirStringToCalendar(8, 8, 2001).getTime();
			Producto producto3 = new Producto();
			producto3.setNombre("Venta F-18");
			Kardex item3 = new Kardex();
			item3.setIdKardex(3L);
			item3.setSalidaCantidad(35);
			item3.setPrecioUnitario(500F);
			item3.setFechaEntrada(fecha3);
			item3.setTypeFormat("S");
			item3.setProducto(producto3);
			returnKardex = item3;
			break;
		case 4:
			Date fecha4 = stockUtils.convertirStringToCalendar(11, 8, 2001).getTime();
			Producto producto4 = new Producto();
			producto4.setNombre("Compra F-412");
			Kardex item4 = new Kardex();
			item4.setIdKardex(4L);
			item4.setEntradaCantidad(60);
			item4.setPrecioUnitario(255F);
			item4.setFechaEntrada(fecha4);
			item4.setTypeFormat("E");
			item4.setProducto(producto4);
			returnKardex = item4;
			break;
		case 5:
			Date fecha5 = stockUtils.convertirStringToCalendar(13, 8, 2001).getTime();
			Producto producto5 = new Producto();
			producto5.setNombre("Venta F-32");
			Kardex item5 = new Kardex();
			item5.setIdKardex(5L);
			item5.setSalidaCantidad(63);
			item5.setPrecioUnitario(575F);
			item5.setFechaEntrada(fecha5);
			item5.setTypeFormat("S");
			item5.setProducto(producto5);
			returnKardex = item5;
			break;
		case 6:
			Date fecha6 = stockUtils.convertirStringToCalendar(15, 8, 2001).getTime();
			Producto producto6 = new Producto();
			producto6.setNombre("Compra F-680");
			Kardex item6 = new Kardex();
			item6.setIdKardex(6L);
			item6.setEntradaCantidad(50);
			item6.setPrecioUnitario(255F);
			item6.setFechaEntrada(fecha6);
			item6.setTypeFormat("E");
			item6.setProducto(producto6);
			returnKardex = item6;
			break;
		case 7:
			Date fecha7 = stockUtils.convertirStringToCalendar(17, 9, 2001).getTime();
			Producto producto7 = new Producto();
			producto7.setNombre("Devolucion venta F-18");
			Kardex item7 = new Kardex();
			item7.setIdKardex(7L);
			item7.setEntradaCantidad(15);
			
			item7.setFechaEntrada(fecha7);
			item7.setTypeFormat("DV");
			item7.setProducto(producto7);
			returnKardex = item7;
			break;
		}
		return returnKardex;
		
	}
	
	public void copiarLayouts(String origen, String destino) {
		String copiarLayouts = generarUrlString(origen);

		File archivoLayout = new File(destino);
		if (!archivoLayout.exists()) {
			fileCopy(copiarLayouts, destino);
		}
	}

	public boolean isOcultarColumnaPromedio() {
		return ocultarColumnaPromedio;
	}
	
	public void recuperarConfiguracionActivacionProveedor(){
		try {
			toolACtivacionProveedores = (DevelopmentTool) developmentToolRest.getByNombre(StockConstants.SISTEMA_CONFIG_ACTIVA_PROVEEDOR).getSingle();
			if(toolACtivacionProveedores != null){
				parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(toolACtivacionProveedores.getValue())).getAsJsonObject();
				if(jobject.get("activado").toString().equals("true"))
					activarProveedorCheck = true;
				else
					activarProveedorCheck = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recuperarMensajeEncabezadoLogin(){
		try {
			toolMensajeEncabezado = (DevelopmentTool) developmentToolRest.getByNombre(StockConstants.SISTEMA_CONFIG_ENCABEZADO).getSingle();
			if(toolMensajeEncabezado.getIdDevelopmentTool() != null){
				parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(toolMensajeEncabezado.getValue())).getAsJsonObject();
				if(!jobject.get("encabezado").toString().isEmpty()){
					encabezadoLogin = extraerMensajeSinComillas(jobject.get("encabezado").toString());
				}
					
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void recuperarVersionDelSistema(){
		try {
			toolVersionSistema = (DevelopmentTool) developmentToolRest.getByNombre(StockConstants.SISTEMA_CONFIG_VERSION).getSingle();
			if(toolVersionSistema.getIdDevelopmentTool() != null){
				parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(toolVersionSistema.getValue())).getAsJsonObject();
				if(!jobject.get(StockConstants.SISTEMA_CONFIG_VERSION).toString().isEmpty()){
					versionSistema = extraerMensajeSinComillas(jobject.get(StockConstants.SISTEMA_CONFIG_VERSION).toString());
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String extraerMensajeSinComillas(String stringTotal){
		String reconstruccion ="";
		String charString ="";
		for (int i = 0; i < stringTotal.length(); i++) {
			charString = stringTotal.substring(i, (i+1));
			if(!charString.contains("\"")){
				reconstruccion += charString;
			}
		}
		return reconstruccion;
	}
}
