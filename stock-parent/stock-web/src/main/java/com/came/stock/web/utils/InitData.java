package com.came.stock.web.utils;

import javax.annotation.PostConstruct;

public class InitData {
	
	@PostConstruct
	public void init() {
		insertProductos();
	}

	private void insertProductos() {
	}
}
