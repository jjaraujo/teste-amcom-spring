package com.amcom.teste.model;

public class Estado {

	private String uf;
	private long cont;
	
	public Estado(String uf, long cont) {
		this.uf = uf;
		this.cont = cont;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public long getCont() {
		return cont;
	}
	public void setCont(long cont) {
		this.cont = cont;
	}

}
