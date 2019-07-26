//package com.cplsystems.stock.app.utils;
//
//import java.io.BufferedReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.io.StringReader;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import org.xml.sax.XMLReader;
//import org.xml.sax.helpers.XMLReaderFactory;
//
//import com.cplsystems.stock.app.beans.HibernateManual;
//import com.cplsystems.stock.domain.ConffyaPartidaGenerica;
//import com.cplsystems.stock.domain.Contacto;
//import com.cplsystems.stock.domain.Direccion;
//import com.cplsystems.stock.domain.Email;
//import com.cplsystems.stock.domain.LabelsModulos;
//import com.cplsystems.stock.domain.Organizacion;
//import com.cplsystems.stock.domain.Persona;
//import com.cplsystems.stock.domain.Producto;
//import com.cplsystems.stock.domain.Telefono;
//import com.cplsystems.stock.domain.Usuarios;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//public class ConexionManual {
//
//	static public Connection con;
//	
//	private HibernateManual getConexionHib(String xml){
//		HanddlerHibernate handdlerCfdi = new HanddlerHibernate();
//		try {
//			XMLReader reader = XMLReaderFactory.createXMLReader();
//			InputSource is = new InputSource();
//			is.setCharacterStream(new StringReader(xml));
//
//			reader.setContentHandler(handdlerCfdi);
//			reader.parse(is);
//			return handdlerCfdi.getConexion();
//		} catch (SAXException localSAXException) {
//			localSAXException.printStackTrace();
//			return null;
//		} catch (IOException localIOException) {
//			localIOException.printStackTrace();
//			return null;
//		}
//	}
//	
//	public ConexionManual() {
//		try {
//			InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.cfg.xml");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			String line;
//			String xml = "";
//			while ((line = br.readLine()) != null)
//				xml += line;
//			
//			HibernateManual con = getConexionHib(xml);
//			abrirConexion(con);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void abrirConexion(HibernateManual conexion) {
//		try {
//			if (con == null) {
//				Class.forName(conexion.getDriver()).newInstance();
//				con = DriverManager.getConnection(conexion.getUrl(), conexion.getUserName(), conexion.getPassword());
//			} else {
//				if (con.isClosed()) {
//					con = DriverManager.getConnection(conexion.getUrl(), conexion.getUserName(), conexion.getPassword());
//				}
//			}
//		} catch (IllegalAccessException | SQLException | ClassNotFoundException | InstantiationException e) {
//			System.err.println("Conexion rechazada: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
//
//	public static void cerrar() {
//		try {
//			if (!con.isClosed())
//				con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Usuarios getUsuarioOwner() {
//		Usuarios usuario = null;
//		Organizacion organizacion = new Organizacion();
//		Contacto contacto = new Contacto();
//		Email email = new Email();
//		Telefono telefono = new Telefono();
//
//		try {
//			String sql = "SELECT org.nombre, em.email, em.web, tel.numero, org.logotipo " + "FROM Usuarios AS u "
//					+ "INNER JOIN Organizacion AS org " + "ON u.organizacion = org.idOrganizacion "
//					+ "INNER JOIN Contacto AS cont " + "ON org.contacto = cont.idContacto " + "INNER JOIN Email AS em "
//					+ "ON cont.email = em.idEmails " + "INNER JOIN Telefono AS tel "
//					+ "ON cont.telefono = tel.idTelefono " + "WHERE u.owner = 1";
//
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				usuario = new Usuarios();
//				organizacion.setNombre(rs.getString(1));
//				email.setEmail(rs.getString(2));
//				email.setWeb(rs.getString(3));
//				telefono.setNumero(rs.getString(4));
//				organizacion.setLogotipo(rs.getBytes(5));
//			}
//			rs.close();
//			stmt.close();
//			contacto.setEmail(email);
//			contacto.setTelefono(telefono);
//			organizacion.setContacto(contacto);
//			usuario.setOrganizacion(organizacion);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return usuario;
//	}
//
//
//	private long getCountRowsLabelsModulos() {
//		long count = 0;
//		try {
//			if (con != null) {
//				String sql = "SELECT COUNT(idLabelsModulos) FROM LabelsModulos";
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				while (rs.next())
//					count = new Long(String.valueOf(rs.getInt(1)));
//				rs.close();
//				stmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return count;
//	}
//
//	public List<Usuarios> getUsuariosActivos() {
//		List<Usuarios> usuarios = null;
//		try {
//			if (con != null) {
//				String query = "SELECT u.idUsuario, u.benutzer, u.kennwort, org.nombre, org.rfc, u.fechaCaducidad "
//						+ "FROM Usuarios AS u " + "INNER JOIN Organizacion AS org "
//						+ "ON u.organizacion = org.idOrganizacion "
//						+ "WHERE u.client = 1 AND org.activar = 1";
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(query);
//				while (rs.next()) {
//					if (usuarios == null)
//						usuarios = new ArrayList<>();
//					Usuarios usuario = new Usuarios();
//					Organizacion org = new Organizacion();
//					Direccion dir = new Direccion();
//					Persona p = new Persona();
//
//					usuario.setIdUsuario(rs.getLong(1));
//					usuario.setBenutzer(rs.getString(2));
//					usuario.setKennwort(rs.getString(3));
//					org.setNombre(rs.getString(4));
//					org.setRfc(rs.getString(5));
//					usuario.setFechaCaducidad(rs.getDate(6));
//					usuario.setOrganizacion(org);
//					usuarios.add(usuario);
//				}
//				rs.close();
//				stmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return usuarios;
//	}
//
//	public void saveOrUpdateUsuario(Usuarios usuario) {
//		if (usuario.getIdUsuario() == null)
//			insertUsuario(usuario);
//		else
//			updateUsuario(usuario);
//	}
//
//	private void insertUsuario(Usuarios usuario) {
//		try {
//			if (con != null) {
//				int client = 0;
//				if (usuario.getClient() != null && usuario.getClient())
//					client = 1;
//				int owner = 0;
//				if (usuario.getOwner() != null && usuario.getOwner())
//					owner = 1;
//				Long areaId = null;
//				if (usuario.getArea() != null && usuario.getArea().getIdArea() != null)
//					areaId = usuario.getArea().getIdArea();
//				Long organizacionId = null;
//				if (usuario.getOrganizacion() != null && usuario.getOrganizacion().getIdOrganizacion() != null)
//					organizacionId = usuario.getOrganizacion().getIdOrganizacion();
//				Long personaId = null;
//				if (usuario.getPersona() != null && usuario.getPersona().getIdPersona() != null)
//					personaId = usuario.getPersona().getIdPersona();
//
//				Statement stm = con.createStatement();
//
//				String insert = "INSERT INTO Usuarios " + "VALUES( "
//
//						+ "'" + usuario.getBenutzer() + "', " + client + ", " + usuario.getFechaCaducidad() + ", " + "'"
//						+ usuario.getKennwort() + "', " + owner + ", " + areaId + ", " + organizacionId + ", "
//						+ personaId + ")";
//				stm.executeUpdate(insert);
//				stm.close();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void updateUsuario(Usuarios usuario) {
//		try {
//			if (con != null) {
//				Statement stm = con.createStatement();
//				int client = 0;
//				if (usuario.getClient() != null && usuario.getClient())
//					client = 1;
//				int owner = 0;
//				if (usuario.getOwner() != null && usuario.getOwner())
//					owner = 1;
//				Long areaId = null;
//				if (usuario.getArea() != null && usuario.getArea().getIdArea() != null)
//					areaId = usuario.getArea().getIdArea();
//				Long organizacionId = null;
//				if (usuario.getOrganizacion() != null && usuario.getOrganizacion().getIdOrganizacion() != null)
//					organizacionId = usuario.getOrganizacion().getIdOrganizacion();
//				Long personaId = null;
//				if (usuario.getPersona() != null && usuario.getPersona().getIdPersona() != null)
//					personaId = usuario.getPersona().getIdPersona();
//
//				String queryUpdate = "UPDATE Usuarios " + "SET benutzer = '" + usuario.getBenutzer() + "', "
//						+ "client = " + client + ", " + "fechaCaducidad = " + usuario.getFechaCaducidad() + ", "
//						+ "kennwort = '" + usuario.getKennwort() + "', " + "owner = " + owner + ", " + "area = "
//						+ areaId + ", " + "organizacion = " + organizacionId + ", " + "persona = " + personaId + " "
//						+ "WHERE idUsuario = " + usuario.getIdUsuario();
//				stm.executeUpdate(queryUpdate);
//				stm.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void debugClaves() {
//		List<Usuarios> usuarios = null;
//		try {
//
//			if (con != null) {
//				FileWriter fichero = null;
//				PrintWriter pw = null;
//				fichero = new FileWriter("c:/prueba.txt");
//				pw = new PrintWriter(fichero);
//
//				String query = "select DISTINCT(clave) from ConffyaPartidaGenerica " + "order by clave asc";
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(query);
//				while (rs.next()) {
//					for (int i = 0; i < 17; i++) {
//						System.err.println(rs.getString(1));
//						pw.println(rs.getString(1));
//					}
//				}
//				if (null != fichero)
//					fichero.close();
//				rs.close();
//				stmt.close();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public boolean recuperarConfiguracionActivacionProveedor() {
//		boolean checked = false;
//		try {
//			if (con != null) {
//				String sql = "SELECT * FROM DevelopmentTool " + "WHERE nombre = '"
//						+ StockConstants.SISTEMA_CONFIG_ACTIVA_PROVEEDOR + "'";
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				String value = "";
//				while (rs.next()) {
//					value = rs.getString(4);
//				}
//				if (!value.isEmpty()) {
//					JsonParser parser = new JsonParser();
//					JsonObject jobject = parser.parse(new StockUtils().Desencriptar(value)).getAsJsonObject();
//					if (jobject.get("activado").toString().equals("true"))
//						checked = true;
//				}
//				rs.close();
//				stmt.close();
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return checked;
//	}
//
//	public String recuperarMensajeEncabezadoLogin() {
//		String reconstruccion = "";
//		try {
//			if (con != null) {
//				String sql = "SELECT * FROM DevelopmentTool " + "WHERE nombre = '"
//						+ StockConstants.SISTEMA_CONFIG_ENCABEZADO + "'";
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				String stringTotal = "";
//				while (rs.next()) {
//					stringTotal = rs.getString(4);
//				}
//				if (!stringTotal.isEmpty()) {
//					JsonParser parser = new JsonParser();
//					JsonObject jobject = parser.parse(new StockUtils().Desencriptar(stringTotal)).getAsJsonObject();
//					if (!jobject.get("encabezado").toString().isEmpty())
//						reconstruccion = extraerMensajeSinComillas(jobject.get("encabezado").toString());
//				}
//				rs.close();
//				stmt.close();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return reconstruccion;
//	}
//
//
//	public List<ConffyaPartidaGenerica> getAllPartidagenericaByOrg(Organizacion organizacion, boolean todos) {
//		List<ConffyaPartidaGenerica> lista = null;
//		try {
//			if (con != null) {
//				String sql = "SELECT part.clave, part.nombre, org.nombre FROM ConffyaPartidaGenerica AS part "
//						+ "INNER JOIN Organizacion AS org ON  org.idOrganizacion = part.organizacion "
//						+ "WHERE organizacion = " + organizacion.getIdOrganizacion();
//
//				if (todos) {
//					sql = "SELECT part.clave, part.nombre, org.nombre FROM ConffyaPartidaGenerica AS part "
//							+ "INNER JOIN Organizacion AS org ON  org.idOrganizacion = part.organizacion ";
//				}
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				while (rs.next()) {
//					if (lista == null)
//						lista = new ArrayList<>();
//					ConffyaPartidaGenerica item = new ConffyaPartidaGenerica();
//					item.setClave(rs.getString(1));
//					item.setNombre(rs.getString(2));
//
//					Organizacion org = new Organizacion();
//					org.setNombre(rs.getString(3));
//					item.setOrganizacion(org);
//					lista.add(item);
//				}
//				rs.close();
//				stmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lista;
//	}
//
//	public List<Producto> getAllProductosNoRepetidosFromKardex(Organizacion organizacion) {
//		List<Producto> lista = null;
//		try {
//			if (con != null) {
//				String sql = "SELECT DISTINCT(k.producto), p.clave, p.nombre FROM Kardex AS k "
//						+ "INNER JOIN Producto AS p ON p.idProducto = k.producto " + "WHERE k.organizacion = "
//						+ organizacion.getIdOrganizacion();
//
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				while (rs.next()) {
//					if (lista == null)
//						lista = new ArrayList<>();
//					Producto item = new Producto();
//					item.setIdProducto(rs.getLong(1));
//					item.setClave(rs.getString(2));
//					item.setNombre(rs.getString(3));
//					lista.add(item);
//				}
//				rs.close();
//				stmt.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lista;
//	}
//
//}
