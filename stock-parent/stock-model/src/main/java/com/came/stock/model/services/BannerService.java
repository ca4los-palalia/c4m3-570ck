package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.BannerDAO;
import com.came.stock.model.domain.Banner;

@Service
public class BannerService {
	@Autowired
	private BannerDAO bannerDAO;

	public void save(Banner banner) throws DataAccessException {
		bannerDAO.save(banner);
	}

	public void delete(Banner banner) throws DataAccessException {
		bannerDAO.delete(banner);
	}

	public Banner getById(Long idBanner) throws DataAccessException {
		return bannerDAO.getById(idBanner);
	}

	public List<Banner> getAll() throws DataAccessException {
		return bannerDAO.getAll();
	}
}
