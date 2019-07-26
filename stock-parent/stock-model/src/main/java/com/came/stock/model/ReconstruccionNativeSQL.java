package com.came.stock.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtilString;

@Repository
public class ReconstruccionNativeSQL {
	
	private static final long serialVersionUID = 578744743076568820L;
	
	@Autowired
	private StockUtilString stockUtilString;
	
	public Producto getProducto(Object[] row) {
		Producto salida = new Producto();
		int i = 0;
		for (Object object : row) {
			String valor = "";

			if (object != null) {
				switch (i) {
				case 0:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = stockUtilString.removerPuntoCero(valor);
						}
						salida.setIdProducto(Long.parseLong(valor));
					}
					break;
				case 1:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setClave(valor);
					}
					break;
				case 2:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setNombre(valor);
					}
					break;
				}
			}
			i++;
		}

		return salida;
	}
	
	public ConffyaPartidaGenerica getConffyaPartidaGenerica(Object[] row) {
		ConffyaPartidaGenerica salida = new ConffyaPartidaGenerica();
		int i = 0;
		for (Object object : row) {
			String valor = "";

			if (object != null) {
				switch (i) {
				case 0:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = stockUtilString.removerPuntoCero(valor);
						}
						salida.setIdConffyaPartidaGenerica(Long.parseLong(valor));
					}
					break;
				case 1:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
						salida.setNombre(valor);
					break;
				case 2:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = stockUtilString.removerPuntoCero(valor);
						}
						Organizacion org = new Organizacion();
						org.setIdOrganizacion(Long.parseLong(valor));
						salida.setOrganizacion(org);
					}
					break;
				case 3:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.getOrganizacion().setNombre(valor);
					}
					break;
				}
			}
			i++;
		}
		return salida;
	}
	
	public Usuarios getUsuario(Object[] row) {
		Usuarios salida = new Usuarios();
		int i = 0;
		for (Object object : row) {
			String valor = "";

			if (object != null) {
				switch (i) {
				case 0:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = stockUtilString.removerPuntoCero(valor);
						}
						salida.setIdUsuario(Long.parseLong(valor));
					}
					break;
				case 1:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
						salida.setBenutzer(valor);
					break;
				case 2:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = stockUtilString.removerPuntoCero(valor);
						}
						Organizacion org = new Organizacion();
						org.setNombre(valor);
						salida.setOrganizacion(org);
					}
					break;
				}
			}
			i++;
		}
		return salida;
	}
}
