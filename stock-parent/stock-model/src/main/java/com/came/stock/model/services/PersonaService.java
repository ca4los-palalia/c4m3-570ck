package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.PersonaDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Persona;

@Service
public class PersonaService {
	@Autowired
	private PersonaDAO personaDAO;

	public void save(Persona persona) throws DataAccessException {
		personaDAO.save(persona);
	}

	public Persona getById(Long persona) throws DataAccessException {
		return personaDAO.getById(persona);
	}

	public List<Persona> getAll() throws DataAccessException {
		return personaDAO.getAll();
	}

	public List<Persona> getBySexo(Long sexo) throws DataAccessException {
		return personaDAO.getBySexo(sexo);
	}

	public void delete(Persona persona) throws DataAccessException {
		personaDAO.delete(persona);
	}

	public List<Persona> getByDireccion(Direccion direccion) throws DataAccessException {
		return personaDAO.getByDireccion(direccion);
	}

	public List<Persona> getByContacto(Contacto contacto) throws DataAccessException {
		return personaDAO.getByContacto(contacto);
	}

	public Persona getUltimoRegistroPersona() throws DataAccessException {
		return personaDAO.getUltimoRegistroPersona();
	}
}
