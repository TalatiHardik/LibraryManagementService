package com.lib.cart.model;

public class Response {
	
	private String msge;

	public String getMsge() {
		return msge;
	}

	public void setMsge(String msge) {
		this.msge = msge;
	}

	public Response(String msge) {
		super();
		this.msge = msge;
	}

	public Response() {
		super();
	}

}
