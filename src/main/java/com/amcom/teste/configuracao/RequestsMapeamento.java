package com.amcom.teste.configuracao;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.amcom.teste.repositorios.MunicipioRepository;

@Controller
public class RequestsMapeamento {

	@Autowired
	private MunicipioRepository municipioRepository;
	
	@RequestMapping("busca/{ordenaPorNome}")
	@GetMapping
	public String buscaCapitais(@RequestParam("ordenaPorNome") boolean ordenaPorNome) {
		String ordenacao = ordenaPorNome ? " ORDER BY name" : "";
	//	List<Municipio> mun = municipioRepository.findCapitais(ordenacao);
	//	System.out.println(mun);
		return "";
	}
	
	@RequestMapping("insertDeCsv")
	@PostMapping
	public void insertCsv() {
		ConversorCsv csv = new ConversorCsv();
		String nomeArquivo = "C:\\Users\\Joao Junior\\Documents\\Trabalho Java - Cidades.csv";
		System.out.println(nomeArquivo);
		csv.csvParaBaseSql(nomeArquivo);
	}
}
