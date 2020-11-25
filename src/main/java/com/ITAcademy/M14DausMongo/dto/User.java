package com.ITAcademy.M14DausMongo.dto;


/*
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;*/

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Clase User. Mapeado a la Colección user de la base de datos noSQL MongoDB alojada en ATLAS.
 * Tiene un id único y autoincrementable que gestiona el propio mongo. Un nombre de usuario y un porcentaje de éxito.
 * Hemos agregador un campo codigo autoincrementable para tener un codigo de usuario más legible (al modo de los id de mysql).
 * 
 * Guardamos también el número de víctorias acumuladas y el número de lanzamientos realizados. De esta manera el cálculo del 
 * porcentaje de éxito se cálcula por medio de estos campos.
 * 
 * @author Rubén Rodríguez
 *
 */
@Document(collection = "Usuario")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "secuencia_usuarios";
	
	@Id
	private String id;
	
	private Long codigo;
	
	@NotBlank
	private String nomUsuari;
	
	private double percentExit;
    
	private String dataRegistre;
	private int numLanzamientos=0;
	private int numVictories=0;
	

	/**
	 * Método que calcula el porcentaje de éxito en función del número de víctorias acumuladas y el número de lanzamientos.
	 */
	public void calculoExito() {
		
		this.percentExit = (double)getNumVictories()/getNumLanzamientos() * 100;
		
	}
	//Constructor
	public User(String id, @NotBlank String nomUsuari, double percentExit, String dataRegistre) {
		this.id = id;
		this.nomUsuari = nomUsuari;
		this.percentExit = percentExit;
		this.dataRegistre = dataRegistre;
	}
	

	//Si creamos un usuario con el body en blanco {}  el nombre será anónimo
	public User() {
		this.nomUsuari="ANONIMO";

	}

	//Nos dice si el usuario tiene nombre anónimo 
	public boolean esAnonimo() {
		 return (this.nomUsuari.equalsIgnoreCase("ANONIMO"));
	}

	//SETTERS Y GETTERS
	public int getNumVictories() {
		return numVictories;
	}

	public void setNumVictories(int numVictories) {
		this.numVictories = numVictories;
	}

	public int getNumLanzamientos() {
		return numLanzamientos;
	}

	public void setNumLanzamientos(int numLanzamientos) {
		this.numLanzamientos = numLanzamientos;
	}

	
	public String getNomUsuari() {
		return nomUsuari;
	}

	//Si el usuario es aónimo lo guardamos en mayúsuclas.
	public void setNomUsuari(String nomUsuari) {
		if (nomUsuari.equalsIgnoreCase("ANONIMO"))
			this.nomUsuari="ANONIMO";
		else
			this.nomUsuari = nomUsuari;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public double getPercentExit() {
		return percentExit;
	}

	public void setPercentExit(double percentExit) {
		this.percentExit = percentExit;
	}

	public String getDataRegistre() {
		return dataRegistre;
	}

	public void setDataRegistre(String dataRegistre) {
		this.dataRegistre = dataRegistre;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", codigo=" + codigo + ", nomUsuari=" + nomUsuari + ", percentExit=" + percentExit
				+ ", dataRegistre=" + dataRegistre + ", numLanzamientos=" + numLanzamientos + "]";
	}

}
