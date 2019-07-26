package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.OrdenCompraInboxDAO;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;

@Service
public class OrdenCompraInboxService {
	@Autowired
	private OrdenCompraInboxDAO ordenCompraInboxDAO;

	public void save(OrdenCompraInbox ordenCompraInbox) {
		ordenCompraInboxDAO.save(ordenCompraInbox);
	}

	public void delete(OrdenCompraInbox ordenCompraInbox) {
		ordenCompraInboxDAO.delete(ordenCompraInbox);
	}

	public List<OrdenCompraInbox> getAllNews(Organizacion organizacion) {
		return ordenCompraInboxDAO.getAllNews(organizacion);
	}

	public List<OrdenCompraInbox> getAll(Organizacion organizacion) {
		return ordenCompraInboxDAO.getAll(organizacion);
	}
}
