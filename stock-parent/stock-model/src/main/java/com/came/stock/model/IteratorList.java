package com.came.stock.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.ClaveArmonizada;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;

@Repository
public class IteratorList {

	public Organizacion getOrganizacionesListByNombreAndEjercicioAndNumero(List<Organizacion> lista, Organizacion itemOrg) {
		Organizacion retornar = null;
		if (lista != null) {
			for (Organizacion item : lista) {
				String nombreBuscado = itemOrg.getNombre() + " " + itemOrg.getEjercicio().toString();
				if (item.getNombre().equals(nombreBuscado) && itemOrg.getEjercicio().equals(item.getEjercicio())
						&& itemOrg.getNumero().equals(item.getNumero())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Organizacion getOrganizacionByNumeroAndEjercicio(List<Organizacion> lista, Organizacion organizacion) {
		Organizacion retornar = null;
		if (lista != null) {
			for (Organizacion item : lista) {

				if (organizacion.getNumero().equals(item.getNumero())
						&& organizacion.getEjercicio().equals(item.getEjercicio())
						&& organizacion.getNombre().equals(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Giro getGiroByIdFromList(Long idGiro, List<Giro> list) {
		Giro retornar = null;
		if (list != null) {
			for (Giro item : list) {
				if (idGiro.equals(item.getIdGiro())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public CostosTipos getCostosTiposByIdFromList(Long idCostosTipos, List<CostosTipos> list) {
		CostosTipos retornar = null;
		if (list != null) {
			for (CostosTipos item : list) {
				if (idCostosTipos.equals(item.getIdCostosTipos())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public CostosTipos getCostosTiposByNombreFromList(String buscar, List<CostosTipos> list) {
		CostosTipos retornar = null;
		if (list != null) {
			for (CostosTipos item : list) {
				if (item.getNombre().toUpperCase().contains(buscar.toUpperCase())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public ClaveArmonizada getClaveArminizadaFromList(List<ClaveArmonizada> lista, String clave) {
		ClaveArmonizada retornar = null;
		if (lista != null) {
			for (ClaveArmonizada item : lista) {
				if(item.getClave() != null && !item.getClave().isEmpty()){
					String[] output = item.getClave().split(" ");
					if (output.length > 0 && clave.equals(output[0])) {
						retornar = item;
						break;
					}
				}
			}
		}
		return retornar;
	}

	public Unidad getUnidadFromList(List<Unidad> lista, Long idUnidad) {
		Unidad retornar = null;
		if (lista != null) {
			for (Unidad item : lista) {
				if (idUnidad.equals(item.getIdUnidad())) {
					retornar = item;
					break;
				}
			}
		}

		return retornar;
	}

	public ProductoNaturaleza getProductoNaturalezaFromList(List<ProductoNaturaleza> lista, Long idProductoNaturaleza) {
		ProductoNaturaleza retornar = null;
		if (lista != null) {
			for (ProductoNaturaleza item : lista) {
				if (idProductoNaturaleza.equals(item.getIdProductoNaturaleza())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Presentacion getPresentacionFromList(List<Presentacion> lista, Long idPresentacion) {
		Presentacion retornar = null;
		if (lista != null) {
			for (Presentacion item : lista) {
				if (idPresentacion.equals(item.getIdPresentacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Producto getProductoFromList(Long idProducto, List<Producto> lista) {
		Producto retornar = null;
		if (lista != null) {
			for (Producto item : lista) {
				if (idProducto.equals(item.getIdProducto())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Usuarios getUsuarioFromList(Long idUsuario, List<Usuarios> lista) {
		Usuarios retornar = null;
		if (lista != null && idUsuario != null) {
			for (Usuarios item : lista) {
				if (idUsuario.equals(item.getIdUsuario())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Proveedor getProveedorFromList(Long idProveedor, List<Proveedor> lista) {
		Proveedor retornar = null;
		if (lista != null) {
			for (Proveedor item : lista) {
				if (idProveedor.equals(item.getIdProveedor())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Moneda getMonedaFromList(List<Moneda> lista, Long idMoneda) {
		Moneda retornar = null;
		if (lista != null) {
			for (Moneda item : lista) {
				if (idMoneda.equals(item.getIdMoneda())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Banco getBancoByIdFromList(Long idBanco, List<Banco> list) {
		Banco retornar = null;
		if (list != null) {
			for (Banco item : list) {
				if (idBanco.equals(item.getIdBanco())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Area getAreasFromList(List<Area> lista, Long idArea) {
		Area retornar = null;
		if (lista != null) {
			for (Area item : lista) {
				if (idArea.equals(item.getIdArea())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Area getAreasFromListByNombreAndOrganizacion(List<Area> lista, String nombre, Long idOrganizacion) {
		Area retornar = null;
		if (lista != null) {
			for (Area item : lista) {
				if (nombre.equals(item.getNombre())
						&& idOrganizacion.equals(item.getOrganizacion().getIdOrganizacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Estado getEstadoFromList(List<Estado> lista, Long idEstado) {
		Estado retornar = null;
		if (lista != null) {
			for (Estado item : lista) {
				if (idEstado.equals(item.getIdEstado())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public ProductoTipo getProductoTipoFromList(List<ProductoTipo> list, Long idProductoTipo) {
		ProductoTipo retornar = null;
		if (list != null) {
			for (ProductoTipo item : list) {
				if (idProductoTipo.equals(item.getIdProductoTipo())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Estado getEstadoFromListByName(List<Estado> lista, String name) {
		Estado retornar = null;
		if (lista != null) {
			for (Estado item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioFromList(List<Municipio> lista, Long idMunicipio) {
		Municipio retornar = null;
		if (lista != null) {
			for (Municipio item : lista) {
				if (idMunicipio.equals(item.getIdMunicipio())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioFromListByName(List<Municipio> lista, String name) {
		Municipio retornar = null;
		if (lista != null) {
			for (Municipio item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisFromList(List<Pais> lista, Long idPais) {
		Pais retornar = null;
		if (lista != null) {
			for (Pais item : lista) {
				if (idPais.equals(item.getIdPais())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisFromListByName(List<Pais> lista, String name) {
		Pais retornar = null;
		if (lista != null) {
			for (Pais item : lista) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Direccion getDireccionFromList(List<Direccion> lista, Long idDireccion) {
		Direccion retornar = null;
		if (lista != null) {
			for (Direccion item : lista) {
				if (idDireccion.equals(item.getIdDireccion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public ConffyaFuenteFinanciamiento getFuenteFinanciamientoFromListByClave(ConffyaFuenteFinanciamiento ff,
			List<ConffyaFuenteFinanciamiento> lista, Organizacion orgIn) {
		ConffyaFuenteFinanciamiento retornar = null;
		if (lista != null) {
			for (ConffyaFuenteFinanciamiento item : lista) {
				if (ff.getClave().equals(item.getClave())
						&& ff.getOrganizacion().getIdOrganizacion().equals(orgIn.getIdOrganizacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public ConffyaPartidaGenerica getPartidaGenericaFromListByClave(ConffyaPartidaGenerica pg,
			List<ConffyaPartidaGenerica> lista, Organizacion orgItem) {
		ConffyaPartidaGenerica retornar = null;
		if (lista != null) {
			for (ConffyaPartidaGenerica item : lista) {
				if (pg.getClave().equals(item.getClave())
						&& orgItem.getIdOrganizacion().equals(item.getOrganizacion().getIdOrganizacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public ConffyaPartidaGenerica getPartidaGenericaFromListById(ConffyaPartidaGenerica pg,
			List<ConffyaPartidaGenerica> lista) {
		ConffyaPartidaGenerica retornar = null;
		if (lista != null) {
			for (ConffyaPartidaGenerica item : lista) {
				if (pg.getIdConffyaPartidaGenerica().equals(item.getIdConffyaPartidaGenerica())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	

	public ConffyaProg getCofiaProgFromListByClave(ConffyaProg cProg, List<ConffyaProg> lista, Organizacion orgItem) {
		ConffyaProg retornar = null;
		if (lista != null) {
			for (ConffyaProg item : lista) {
				if (cProg.getClave().equals(item.getClave())
						&& item.getOrganizacion().getIdOrganizacion().equals(orgItem.getIdOrganizacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public ConffyaPy getCofiaPyFromListByClave(ConffyaPy cProy, List<ConffyaPy> lista, Organizacion orgItem) {
		ConffyaPy retornar = null;
		if (lista != null) {
			for (ConffyaPy item : lista) {
				if (cProy.getClave().equals(item.getClave())
						&& orgItem.getIdOrganizacion().equals(item.getOrganizacion().getIdOrganizacion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public List<Almacen> getAlmacenesByAreaFromList(List<Almacen> almacenList, Area area){
	List<Almacen> extraccion = new ArrayList<Almacen>();
	for (Almacen item : almacenList) {
		if(item.getArea().getIdArea().equals(area.getIdArea()))
			extraccion.add(item);
	}
	return extraccion;
}


	public Almacen getAlmacenFromList(List<Almacen> almacenList, Long id){
		Almacen extraccion = null;
		for (Almacen item : almacenList) {
			if(item.getIdAlmacen().equals(id)){
				extraccion = item;
				break;
			}
		}
		return extraccion;
	}
}
