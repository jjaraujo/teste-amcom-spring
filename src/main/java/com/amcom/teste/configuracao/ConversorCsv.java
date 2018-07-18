package com.amcom.teste.configuracao;

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
	
	@Autowired
	MunicipioRepository municipioRepository;
	
	public static void main(String[] args) {
	}

	public void csvParaBaseSql(String arquivo) {
		// "C:\\Users\\Joao Junior\\Documents\\Trabalho Java - Cidades.csv"
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
			String linha = null;
			List<Municipio> setMunicipios = new ArrayList<>();
			
			while ((linha = reader.readLine()) != null) {

				String[] dadosUsuario = linha.split(",");
				setMunicipios.add(newMunicipio(dadosUsuario));
			}
			
			municipioRepository.saveAll(setMunicipios);
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
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
		municipio.setLon(dadosUsuario[4]);
		municipio.setNo_accents(dadosUsuario[5]);
		municipio.setAlternative_names(dadosUsuario[6]);
		municipio.setMicroregion(dadosUsuario[7]);
		municipio.setMesoregion(dadosUsuario[8]);
		
		return municipio;
	}
	
}
