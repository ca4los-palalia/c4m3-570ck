package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Telefono;

public abstract interface ContactoDAO {
	public abstract void save(Contacto paramContacto);

	public abstract void delete(Contacto paramContacto);

	public abstract Contacto getById(Long paramLong);

	public abstract Contacto getByTelefono(Telefono paramTelefono);

	public abstract Contacto getByIdEmail(Email paramEmail);

	public abstract List<Contacto> getAll();

	public abstract Contacto getUltimoRegistroContacto();

	public abstract Contacto getContactoByEmail(Email paramEmail);
}
