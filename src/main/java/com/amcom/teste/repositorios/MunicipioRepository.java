package com.amcom.teste.repositorios;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.amcom.teste.model.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {
	
	@Async
	@Query(value = "SELECT m FROM municipio m WHERE capital = 1 :ordenacao", nativeQuery = true)
	@Bean
	public List<Municipio> findCapitais(@Param("ordenacao") String ordenacao);
	
}
