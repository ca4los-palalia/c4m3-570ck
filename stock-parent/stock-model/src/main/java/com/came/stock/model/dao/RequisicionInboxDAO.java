package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.RequisicionInbox;

public abstract interface RequisicionInboxDAO {
	public abstract void save(RequisicionInbox requisicionInbox);

	public abstract void delete(RequisicionInbox requisicionInbox);

	public abstract List<RequisicionInbox> getAllNews(Organizacion organizacion);

	public abstract List<RequisicionInbox> getAll(Organizacion organizacion);
}
