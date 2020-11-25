package com.ITAcademy.M14DausMongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ITAcademy.M14DausMongo.dto.TiradesDau;

/**
 * Interfaz repoisitorio para las tiradas de dado. 
 * @author Rubén Rodríguez
 *
 */
@Repository
public interface TiradesDauDao extends MongoRepository<TiradesDau, String> { 

	public void deleteAllByUsuarioCodigo(Long codigo);
	public List<TiradesDau> findAllByUsuarioCodigo(Long codigo);
}

	

