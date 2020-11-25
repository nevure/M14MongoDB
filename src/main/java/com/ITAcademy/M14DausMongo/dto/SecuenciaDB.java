package com.ITAcademy.M14DausMongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase de apoyo de cara a crear un código autoincremental. De esta manera mantenemos el id que crea mongo automáticamente y a la vez
 * mantenemos un codigo más legible y autoincremental para manejar las url.
 * @author ru
 *
 */
@Document(collection="secuenciaDB")
public class SecuenciaDB {
	@Id
	private String id;
	
	private Long sec;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSec() {
		return sec;
	}

	public void setSec(Long sec) {
		this.sec = sec;
	}

	public SecuenciaDB(String id, Long sec) {
		super();
		this.id = id;
		this.sec = sec;
	}

	public SecuenciaDB() {
		super();
	}
	
	

}
