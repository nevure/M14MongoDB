package com.ITAcademy.M14DausMongo.services;

import java.util.List;

import com.ITAcademy.M14DausMongo.dto.TiradesDau;


public interface ITiradesDauService {

	public TiradesDau novaTirada(TiradesDau tirada);
	public List<TiradesDau> listaTiradaByUser(Long id);
	//public int cuantasTiradasByUser(Long id);
	//public int cuantasVictoriasByUser(Long id);
	public void eliminarTiradasByUser(Long id);
	public List<TiradesDau> listaTodasTiradas();
	public boolean reiniciojuego();
	

}