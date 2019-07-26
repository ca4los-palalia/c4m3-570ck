package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;

public abstract interface OrdenCompraInboxDAO {
	public abstract void save(OrdenCompraInbox paramOrdenCompraInbox);

	public abstract void delete(OrdenCompraInbox paramOrdenCompraInbox);

	public abstract List<OrdenCompraInbox> getAllNews(Organizacion paramOrganizacion);

	public abstract List<OrdenCompraInbox> getAll(Organizacion paramOrganizacion);
}
