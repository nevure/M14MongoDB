package com.ITAcademy.M14DausMongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ITAcademy.M14DausMongo.dto.SecuenciaDB;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;
/**
 * Clase Servicio para el DTO de SecuenciaDB
 * @author ru
 *
 */

@Service
public class SecuenciaServicio {

    private MongoOperations mongoOperations;
    
    @Autowired
    public SecuenciaServicio(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generadorDeLaSecuencia(String seqName) {

        SecuenciaDB contador = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("sec",1), options().returnNew(true).upsert(true),
                SecuenciaDB.class);
        System.out.println("contador :"+contador);
        //Devolvemos el contador actual o bien, si es el primero , devuelve 1.
        return !Objects.isNull(contador) ? contador.getSec(): 1;

    }
}

