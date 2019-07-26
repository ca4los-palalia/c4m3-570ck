package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ClaveArmonizada;

public abstract interface ClaveArmonizadaDAO {
	public abstract void save(ClaveArmonizada paramClaveArmonizada);

	public abstract void delete(ClaveArmonizada paramClaveArmonizada);

	public abstract ClaveArmonizada getById(Long paramLong);

	public abstract List<ClaveArmonizada> getAll();

	public abstract List<ClaveArmonizada> getByGrupo(Integer paramInteger);

	public abstract List<ClaveArmonizada> getBySubGrupo(Integer paramInteger);

	public abstract List<ClaveArmonizada> getByClase(Integer paramInteger);

	public abstract ClaveArmonizada getByClave(String paramString);

	public abstract ClaveArmonizada getByDescripcion(String paramString);
}
