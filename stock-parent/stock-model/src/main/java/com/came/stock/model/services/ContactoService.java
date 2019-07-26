package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ContactoDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Telefono;

@Service
public class ContactoService {
	@Autowired
	private ContactoDAO contactoDAO;

	public void save(Contacto contacto) throws DataAccessException {
		contactoDAO.save(contacto);
	}

	public void delete(Contacto contacto) throws DataAccessException {
		contactoDAO.delete(contacto);
	}

	public Contacto getById(Long idContacto) throws DataAccessException {
		return contactoDAO.getById(idContacto);
	}

	public Contacto getByTelefono(Telefono telefono) throws DataAccessException {
		return contactoDAO.getByTelefono(telefono);
	}

	public Contacto getByIdEmail(Email email) throws DataAccessException {
		return contactoDAO.getByIdEmail(email);
	}

	public List<Contacto> getAll() throws DataAccessException {
		return contactoDAO.getAll();
	}

	public Contacto getUltimoRegistroContacto() {
		return contactoDAO.getUltimoRegistroContacto();
	}

	public Contacto getContactoByEmail(Email email) {
		return contactoDAO.getContactoByEmail(email);
	}
}
