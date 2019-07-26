package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Banner;

public abstract interface BannerDAO {

	List<Banner> getAll();

	Banner getById(Long idBanner);

	void delete(Banner banner);

	void save(Banner banner);

}
