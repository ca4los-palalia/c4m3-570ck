package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Email;

public abstract interface EmailDAO {
	public abstract void save(Email paramEmail);

	public abstract void delete(Email paramEmail);

	public abstract Email getById(Long paramLong);

	public abstract List<Email> getAll();

	public abstract Email getUltimoRegistroEmail();
}
