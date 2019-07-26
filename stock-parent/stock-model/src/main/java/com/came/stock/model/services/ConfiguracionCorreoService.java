package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ConfiguracionCorreoDAO;
import com.came.stock.model.domain.ConfiguracionCorreo;

@Service
public class ConfiguracionCorreoService {
	@Autowired
	private ConfiguracionCorreoDAO configuracionCorreoDAO;

	public void save(ConfiguracionCorreo configuracionCorreo) throws DataAccessException {
		configuracionCorreoDAO.save(configuracionCorreo);
	}

	public ConfiguracionCorreo getById(Long idConfiguracionCorreo) throws DataAccessException {
		return configuracionCorreoDAO.getById(idConfiguracionCorreo);
	}
	public List<ConfiguracionCorreo> getAll(){
		return configuracionCorreoDAO.getAll();
	}
}
