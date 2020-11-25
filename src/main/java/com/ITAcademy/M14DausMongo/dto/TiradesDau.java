package com.ITAcademy.M14DausMongo.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ITAcademy.M14DausMongo.utilidades.UtilidadesJuego;

/**
 * Clase que mapea la colección noSQL TiradesDau
 * @author Rubén Rodriguez Fernández
 *
 */

@Document(collection = "TiradesDau")
public class TiradesDau {

	@Id
	 private String id;
	
	private int dau1;
	
	@Field(name="dau2")
	private int dau2;
	private boolean resultat;
		
	private User usuario; //Mapeo del objeto User que realiza la tirada

	/**
	 * Como en el anterior ejemplo con mysql el constructor lanza el método aJugar() que realiza la tirada de dados.
	 */
	public TiradesDau() {
		this.aJugar();
	}
	
	public TiradesDau(User usuario) {
		this.usuario = usuario;
		this.aJugar();

	}

	public TiradesDau(int dau1, int dau2, boolean resultat, User usuario) {
		this.dau1 = dau1;
		this.dau2 = dau2;
		this.resultat = resultat;
		this.usuario = usuario;
	}

	public void aJugar() {
		this.dau1 = UtilidadesJuego.generaAleatorioDesdeUno(6);
		this.dau2 = UtilidadesJuego.generaAleatorioDesdeUno(6);
		this.resultat = (this.dau1+this.dau2 == 7);
		
	}
	
	//SETERRS Y GETTERS
	public int getDau1() {
		return dau1;
	}			


	public void setDau1(int dau1) {
		this.dau1 = dau1;
	}

	public int getDau2() {
		return dau2;
	}

	public void setDau2(int dau2) {
		this.dau2 = dau2;
	}

	public boolean isResultat() {
		return resultat;
	}

	public void setResultat(boolean resultat) {
		this.resultat = resultat;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "TiradesDau [id=" + id + ", dau1=" + dau1 + ", dau2=" + dau2 + ", resultat=" + resultat + ", usuario="
				+ usuario + "]";
	}
	
	
}

