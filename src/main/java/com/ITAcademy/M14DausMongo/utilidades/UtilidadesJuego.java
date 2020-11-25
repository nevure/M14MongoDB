/**
 * 
 */
package com.ITAcademy.M14DausMongo.utilidades;

/**
 * Clase utilidades que tiene un  método que genera un número aleatorio entre 0 y un máximo pasado como parámetro
 * @author Rubén Rodríguez
 *
 */
public class UtilidadesJuego {
	
	public static int generaAleatorioDesdeUno(int maximo) {

		return (int) Math.floor(Math.random()*maximo+1);
	}
}
