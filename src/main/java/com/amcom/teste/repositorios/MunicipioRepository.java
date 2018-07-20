package com.amcom.teste.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amcom.teste.controle.Funcoes;
import com.amcom.teste.model.Estado;
import com.amcom.teste.model.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

	@Query("FROM municipio WHERE capital = 1 ORDER BY name")
	public List<Municipio> findCapitais();
	
	@Query("SELECT name FROM municipio WHERE uf LIKE :uf")
	public List<String> nomeCidades(@Param("uf") String uf);
	
	@Query ("SELECT new com.amcom.teste.model.Estado(uf,count(uf) AS CONT) FROM municipio m GROUP BY uf ORDER BY CONT ASC")
	public List<Estado> municipiosMaxPorEstado(Pageable pageable);
	
	@Query ("SELECT new com.amcom.teste.model.Estado(uf,count(uf) AS CONT) FROM municipio m GROUP BY uf ORDER BY CONT DESC")	
	public List<Estado> municipiosMinPorEstado(Pageable pageable);

	@Query ("SELECT new com.amcom.teste.model.Estado(uf,count(uf) AS CONT) FROM municipio m GROUP BY uf ORDER BY CONT DESC")	
	public List<Estado> municipiosPorEstado();

	
	public default List<Municipio> pesquisaPorColunaEValor(String coluna, String valor, EntityManager manager) {
		String query = "SELECT * FROM municipio WHERE #coluna ";
		query += Funcoes.isInt(valor) ? " = #valor " : " LIKE '%#valor%'";
		query = query.replace("#coluna", coluna).replace("#valor", valor);
		
	    return manager.createNativeQuery(query, Municipio.class).getResultList();
	}
	
	public default int pesquisaQtdRegistrosPorColuna(String coluna, EntityManager manager) {
		String query = "SELECT COUNT(DISTINCT #coluna) AS CONT FROM municipio";
		query = query.replace("#coluna", coluna);
	    return (int) manager.createNativeQuery(query).getResultList().get(0);
	}
}
