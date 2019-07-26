package com.came.stock.beans;

import java.io.Serializable;

public class CtrlRestService implements Serializable {

	private static final long serialVersionUID = -4411084049610950892L;

	private Object single;
	private String response;
	private boolean ok;

	public Object getSingle() {
		return single;
	}

	public void setSingle(Object single) {
		this.single = single;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}
