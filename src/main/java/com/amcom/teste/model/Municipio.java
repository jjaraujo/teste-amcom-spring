package com.amcom.teste.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="municipio")
public class Municipio {
	
	@Id
	private long ibge_id;
	private String uf;
	private String name;
	private boolean capital;
	private String lon;
	private String lat;
	private String no_accents;
	private String alternative_names;
	private String microregion;
	private String mesoregion;
	
	
	public long getIbge_id() {
		return ibge_id;
	}
	public void setIbge_id(long ibge_id) {
		this.ibge_id = ibge_id;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isCapital() {
		return capital;
	}
	public void setCapital(boolean capital) {
		this.capital = capital;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getNo_accents() {
		return no_accents;
	}
	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
	}
	public String getAlternative_names() {
		return alternative_names;
	}
	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
	}
	public String getMicroregion() {
		return microregion;
	}
	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}
	public String getMesoregion() {
		return mesoregion;
	}
	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
	
	
	
	
	
}
