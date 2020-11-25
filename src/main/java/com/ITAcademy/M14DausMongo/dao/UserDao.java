package com.ITAcademy.M14DausMongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ITAcademy.M14DausMongo.dto.User;

//@RepositoryRestResource(exported=true,path = "players")
@Repository
public interface UserDao extends MongoRepository<User, String> {
	
		public Boolean existsByNomUsuari(String nomUsuari);
		
		User findTopByOrderByPercentExitDesc(); //Usuario en la parte top de una lista ordenada por porcentaje de éxito descenciente.
		User findTopByOrderByPercentExitAsc();  //Usuario en la parte top de una lista ordenada por porcentaje de éxito ascendente.
		User findByCodigo(Long codigo);
		void deleteByCodigo(Long codigo);
		Boolean existsByCodigo(Long codigo); //busca usuario por el campo codigo (NO POR ID de MONGO)

		
}