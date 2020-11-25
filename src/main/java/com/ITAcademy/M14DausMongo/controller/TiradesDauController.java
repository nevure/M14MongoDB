package com.ITAcademy.M14DausMongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ITAcademy.M14DausMongo.dto.User;
import com.ITAcademy.M14DausMongo.dto.TiradesDau;
import com.ITAcademy.M14DausMongo.services.TiradesDauService;
import com.ITAcademy.M14DausMongo.services.UserService;


/**
 * Controlador de las tiradas de dados. Puntos de entrada
 * @author Rubén Rodríguez 
 *
 */
@RestController
public class TiradesDauController {
	
	//inyectamos el servicio
	@Autowired
	TiradesDauService dausService;
	
	@Autowired
	UserService usuarioService;
	
	@PostMapping("/players/{id}/games")
	public ResponseEntity<Object> lanzamientoDados(@PathVariable("id") Long id) {
		TiradesDau tirada;
		if (usuarioService.existeUserById(id)) {
			
			User usuario = usuarioService.selectUserById(id);
						
			tirada = new TiradesDau(usuario);
			if (tirada.isResultat())
				usuario.setNumVictories(usuario.getNumVictories()+1);
			usuario.setNumLanzamientos(usuario.getNumLanzamientos()+1);			//Incrementamos el número de lanzamientos realizado por el jugador.

			usuario.calculoExito();   			//Actualizamos el porcentaje de éxito del jugador

			usuarioService.creaUsuario(usuario);  //actualizamos estos datos en la base de datos.

			return ResponseEntity.ok().body(dausService.novaTirada(tirada)); 		//dentro del return guardamos la tirada en la BD
		
		}
		else
			return ResponseEntity.ok().body("El usuario con id "+id+" No existe");
	
	}
	
	/**
	 * Eliminamos las tiradas del jugador con identificado id
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/players/{id}/games", method = RequestMethod.DELETE)
	public String eliminaTiradasUser(@PathVariable("id") Long id) {
		User usuario = usuarioService.selectUserById(id);
		
		//Actualizamos los datos del usuario respecto a las tiradas, poniéndolo todo a 0
		usuario.setNumLanzamientos(0);
		usuario.setNumVictories(0);
		usuario.setPercentExit(0);
		
		usuarioService.creaUsuario(usuario); //actualizamos con los datos a 0 de tiradas.
		
		dausService.eliminarTiradasByUser(id);  //eliminamos las tiradas.
		return "Tiradas Eliminadas";
	}
	
	//Lista de tiradas por usuario.
	@GetMapping("/players/{id}/games")
	public List<TiradesDau> listadoTiradas(@PathVariable("id")Long id){
		return dausService.listaTiradaByUser(id);
	
	}	
}
