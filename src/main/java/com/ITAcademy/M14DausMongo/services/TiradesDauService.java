/**
 * 
 */
package com.ITAcademy.M14DausMongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ITAcademy.M14DausMongo.dao.TiradesDauDao;
import com.ITAcademy.M14DausMongo.dto.TiradesDau;

/**
 * Clase Serivicio del repositorio de tiradas de dado
 * @author Rubén Rodríguez
 *
 */
@Service
public class TiradesDauService implements ITiradesDauService {

	//inyectamos el repositorio
	@Autowired
	TiradesDauDao tiradasDao;

	@Override
	public TiradesDau novaTirada(TiradesDau tirada) {
		return tiradasDao.save(tirada);
	}

	@Override
	public List<TiradesDau> listaTiradaByUser(Long id) {
		return tiradasDao.findAllByUsuarioCodigo(id);
	}

	@Override
	public void eliminarTiradasByUser(Long id) {
		tiradasDao.deleteAllByUsuarioCodigo(id);
	}

	@Override
	public List<TiradesDau> listaTodasTiradas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean reiniciojuego() {
		// TODO Auto-generated method stub
		return false;
	}

}
