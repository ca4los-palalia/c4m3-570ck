package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.Organizacion;

public abstract interface CotizacionInboxDAO {
	public abstract void save(CotizacionInbox paramCotizacionInbox);

	public abstract void delete(CotizacionInbox paramCotizacionInbox);

	public abstract List<CotizacionInbox> getAllNews(Organizacion paramOrganizacion);

	public abstract List<CotizacionInbox> getAll(Organizacion paramOrganizacion);
}
