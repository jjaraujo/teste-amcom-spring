package com.amcom.teste.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.amcom.teste.model.Municipios;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipios, Long> {
	
	//@Async
	@Query("FROM municipios WHERE capital = 1 ORDER BY name")
//	@Bean
	public List<Municipios> findCapitais();
	
	@Query("SELECT name FROM municipios WHERE uf LIKE :uf")
	public List<String> nomeCidades(@Param("uf") String uf);
	

	
}
