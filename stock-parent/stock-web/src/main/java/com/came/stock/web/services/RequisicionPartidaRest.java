package com.came.stock.web.services;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

@Repository
public class RequisicionPartidaRest extends RestMetaclas {
	
	@PostConstruct
	public void init() {
		super.init();
	}
}
