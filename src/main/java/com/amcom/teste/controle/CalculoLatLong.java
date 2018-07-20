package com.amcom.teste.controle;

import java.util.ArrayList;
import java.util.List;

import com.amcom.teste.model.Municipio;

public class CalculoLatLong {

	public double distancia(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius da terra

		double latDistancia = deg2rad(lat2 - lat1);
		double lonDistancia = deg2rad(lon2 - lon1);
		double a = Math.sin(latDistancia / 2) * Math.sin(latDistancia / 2) + Math.cos(deg2rad(lat1))
				* Math.cos(deg2rad(lat2)) * Math.sin(lonDistancia / 2) * Math.sin(lonDistancia / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c;

		distance = Math.pow(distance, 2);
		return Math.sqrt(distance);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	public List<Municipio> maiorDistancia(List<Municipio> listMunicipios) {
		List<Municipio> listMaiorDistancia = new ArrayList<>();

		double maiorDistancia = 0;

		for (Municipio municipio : listMunicipios) {

			for (int i = 0; i < listMunicipios.size(); i++) {
				Municipio municipioAtual = listMunicipios.get(i);

				double distanciaMunicipios = distancia(municipio.getLat(), municipioAtual.getLat(),municipio.getLon(), municipioAtual.getLon());
				if(distanciaMunicipios > maiorDistancia) {
					maiorDistancia = distanciaMunicipios;
					listMaiorDistancia.clear();
					listMaiorDistancia.add(municipio);
					listMaiorDistancia.add(municipioAtual);
				}
			}
		}
		return listMaiorDistancia;
	}

}
