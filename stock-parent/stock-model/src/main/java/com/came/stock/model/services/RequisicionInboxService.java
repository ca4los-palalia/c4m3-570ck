package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.RequisicionInboxDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.RequisicionInbox;

@Service
public class RequisicionInboxService {
	@Autowired
	private RequisicionInboxDAO requisicionInboxDAO;

	public void save(RequisicionInbox requisicionInbox) {
		requisicionInboxDAO.save(requisicionInbox);
	}

	public void delete(RequisicionInbox requisicionInbox) {
		requisicionInboxDAO.delete(requisicionInbox);
	}

	public List<RequisicionInbox> getAllNews(Organizacion organizacion) {
		return requisicionInboxDAO.getAllNews(organizacion);
	}

	public List<RequisicionInbox> getAll(Organizacion organizacion) {
		return requisicionInboxDAO.getAll(organizacion);
	}
}
