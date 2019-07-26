package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CotizacionInboxDAO;
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.Organizacion;

@Service
public class CotizacionInboxService {
	@Autowired
	private CotizacionInboxDAO cotizacionInboxDAO;

	public void save(CotizacionInbox cotizacionInbox) {
		this.cotizacionInboxDAO.save(cotizacionInbox);
	}

	public void delete(CotizacionInbox cotizacionInbox) {
		this.cotizacionInboxDAO.delete(cotizacionInbox);
	}

	public List<CotizacionInbox> getAllNews(Organizacion organizacion) {
		return this.cotizacionInboxDAO.getAllNews(organizacion);
	}

	public List<CotizacionInbox> getAll(Organizacion organizacion) {
		return this.cotizacionInboxDAO.getAll(organizacion);
	}
}
