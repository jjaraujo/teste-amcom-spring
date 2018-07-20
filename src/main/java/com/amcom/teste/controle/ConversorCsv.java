package com.amcom.teste.controle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.amcom.teste.model.Municipio;

import com.amcom.teste.repositorios.MunicipioRepository;

public class ConversorCsv {
	
	public List<Municipio> csvParaList(String arquivo) {
		// "C:\\Users\\Joao Junior\\Documents\\Trabalho Java - Cidades.csv"
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
			String linha = null;
			List<Municipio> municipios = new ArrayList<>();
			
			while ((linha = reader.readLine()) != null) {

				String[] dadosUsuario = linha.split(",");
				municipios.add(newMunicipio(dadosUsuario));
			}

			reader.close();
			
			return municipios;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private boolean stringToBoolean(String s) {
		return s.trim().equals("true") ? true : false;
	}
	
	private Municipio newMunicipio(String[] dadosUsuario) {
		
		Municipio municipio = new Municipio();
		municipio.setIbge_id(Long.parseLong(dadosUsuario[0]));
		municipio.setUf(dadosUsuario[1]);
		municipio.setName(dadosUsuario[2]);
		municipio.setCapital(stringToBoolean(dadosUsuario[3]));
		municipio.setLon(Double.parseDouble(dadosUsuario[4]));
		municipio.setLat(Double.parseDouble(dadosUsuario[5]));
		municipio.setNo_accents(dadosUsuario[6]);
		municipio.setAlternative_names(dadosUsuario[7]);
		municipio.setMesoregion(dadosUsuario[9].trim());
		
		return municipio;
	}
}
