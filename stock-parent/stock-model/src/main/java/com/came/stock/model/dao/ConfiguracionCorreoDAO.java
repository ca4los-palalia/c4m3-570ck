package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConfiguracionCorreo;

public abstract interface ConfiguracionCorreoDAO {
	public abstract void save(ConfiguracionCorreo ConfiguracionCorreo);
	public abstract ConfiguracionCorreo getById(Long idConfiguracionCorreo);
	public abstract List<ConfiguracionCorreo> getAll();
}
