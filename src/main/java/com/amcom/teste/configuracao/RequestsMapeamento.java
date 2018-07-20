package com.amcom.teste.configuracao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amcom.teste.controle.CalculoLatLong;
import com.amcom.teste.controle.ConversorCsv;
import com.amcom.teste.model.Estado;
import com.amcom.teste.model.Municipio;
import com.amcom.teste.repositorios.MunicipioRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class RequestsMapeamento {
	
	
	
	@Autowired
	private MunicipioRepository municipioRepository;
	
	@PersistenceContext
	private EntityManager manager;

	@RequestMapping("insertDeCsv")
	@ResponseBody
	public String insertCsv() {
		ConversorCsv csv = new ConversorCsv();
		String nomeArquivo = "C:\\Users\\Joao Junior\\Documents\\Trabalho Java - Cidades.csv";
		List<Municipio> listMunicipio = csv.csvParaList(nomeArquivo);
		municipioRepository.saveAll(listMunicipio);
		return "Municipios inseridos";
	}
	
	@RequestMapping("buscaCapitais")
	@ResponseBody
	public Object[] buscaCapitais() {
		List<Municipio> mun = municipioRepository.findCapitais();
		return mun.toArray();
	}

	
	@RequestMapping("municipiosMinMaxPorEstado")
	@ResponseBody
	public Object[] municipiosMinMaxPorEstado() {
		PageRequest page = PageRequest.of(0, 1);
		List<Estado> estados = new ArrayList<>();
		estados.add(municipioRepository.municipiosMaxPorEstado(page).get(0));
		estados.add(municipioRepository.municipiosMinPorEstado(page).get(0));
	
		return estados.toArray();
	}

	
	@RequestMapping("municipiosPorEstado")
	@ResponseBody
	public Object[] municipiosPorEstado() {
		
		return municipioRepository.municipiosPorEstado().toArray();
	}
	
	@RequestMapping("findMuncipioById/{id}")
	@ResponseBody
	public String findMuncipioById(@PathVariable("id") long id) {
		Optional<Municipio> municipios = municipioRepository.findById(id);
		Gson gson = new Gson();
		try {
			String json = gson.toJson(municipios.get());
			return json;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	@RequestMapping("nomeMunicipiosPorUf/{uf}")
	@ResponseBody
	public Object[] nomeMunicipiosPorUf(@PathVariable("uf") String uf) {
		return municipioRepository.nomeCidades(uf).toArray();
	}
	
	@RequestMapping("insertMunicipio")
	@ResponseBody
	public void insertMunicipio(@RequestBody Municipio municipio) {
		municipioRepository.save(municipio);
	}
	
	@RequestMapping("deleteMunicipio/{id}")
	@ResponseBody
	public void deleteMunicipio(@PathVariable("id") long id) {
		municipioRepository.deleteById(id);
	}
	
	
	@RequestMapping("pesquisaPorColunaEValor/{coluna}/{valor}")
	@ResponseBody
	public Object[] pesquisaPorColunaEValor(@PathVariable("coluna") String coluna, @PathVariable("valor") String valor) {
		return municipioRepository.pesquisaPorColunaEValor(coluna,valor,manager).toArray();
	}
	

	@RequestMapping("qtdRegistrosPorColuna/{coluna}")
	@ResponseBody
	public int pesquisaQtdRegistrosPorColuna(@PathVariable("coluna") String coluna) {
		return municipioRepository.pesquisaQtdRegistrosPorColuna(coluna,manager);
	}
	
	
	@RequestMapping("countMunicipios")
	@ResponseBody
	public String countMunicipios() {
		return String.valueOf(municipioRepository.count());
	}	
	
	
	@RequestMapping("municipiosMaisDistantes")
	@ResponseBody
	public Object[] municipiosMaisDistantes() {
		CalculoLatLong calc = new CalculoLatLong();
		
		Iterable<Municipio> s = municipioRepository.findAll();
		List<Municipio> listMunicipios = new ArrayList<>();
		s.forEach(listMunicipios::add);
		Object[] array = calc.maiorDistancia(listMunicipios).toArray();
		return array;
	}
}
