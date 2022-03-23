package com.lib.orders.models;

public class Response {
	
	private String msg;

	public String getMsge() {
		return msg;
	}

	public void setMsge(String msge) {
		this.msg = msge;
	}

	public Response(String msge) {
		super();
		this.msg = msge;
	}

	public Response() {
		super();
	}

}
