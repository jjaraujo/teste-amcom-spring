package com.amcom.teste.configuracao;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.amcom.teste.model.Municipios;
import com.amcom.teste.repositorios.MunicipioRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

@Controller
public class RequestsMapeamento {

	@Autowired
	private MunicipioRepository municipioRepository;

	@RequestMapping("busca/{ordenaPorNome}")
	@ResponseBody
	public Object[] buscaCapitais(@PathVariable("ordenaPorNome") boolean ordenaPorNome) {
		String ordenacao = ordenaPorNome ? " ORDER BY name" : "";
		List<Municipios> mun = municipioRepository.findCapitais();
		System.out.println(mun);
		return mun.toArray();
	}

	@RequestMapping("insertDeCsv")
	@ResponseBody
	public String insertCsv() {
		ConversorCsv csv = new ConversorCsv();
		String nomeArquivo = "C:\\Users\\Joao Junior\\Documents\\Trabalho Java - Cidades.csv";
		System.out.println(nomeArquivo);
		List<Municipios> listMunicipio = csv.csvParaList(nomeArquivo);
		municipioRepository.saveAll(listMunicipio);
		return "Municipios inseridos";
	}

	@RequestMapping("findMuncipioById/{id}")
	@ResponseBody
	public String findMuncipioById(@PathVariable("id") long id) {
		Optional<Municipios> municipios = municipioRepository.findById(id);
		Gson gson = new Gson();
		try {
			String json = gson.toJson(municipios.get());
			return json;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@RequestMapping("insertMunicipio")
	@ResponseBody
	public void insertMunicipio(@RequestBody Municipios municipio) {
		municipioRepository.save(municipio);
	}
	
	@RequestMapping("deleteMunicipio/{id}")
	@ResponseBody
	public void deleteMunicipio(@PathVariable("id") long id) {
		municipioRepository.deleteById(id);
	}
	
	
	@RequestMapping("countMunicipios")
	@ResponseBody
	public String countMunicipios() {
		return municipioRepository.count() + "";
	}
	
	@RequestMapping("nomeMunicipiosPorUf/{uf}")
	@ResponseBody
	public Object[] nomeMunicipiosPorUf(@PathVariable("uf") String uf) {
		return municipioRepository.nomeCidades(uf).toArray();
	}

}
