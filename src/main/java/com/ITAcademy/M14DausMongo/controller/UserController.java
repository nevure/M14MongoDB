/**
 * 
 */
package com.ITAcademy.M14DausMongo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ITAcademy.M14DausMongo.dto.User;
import com.ITAcademy.M14DausMongo.services.SecuenciaServicio;
import com.ITAcademy.M14DausMongo.services.UserService;

/**
 * 
 * Controlador de los puntos de entrada del USER
 * @author Rubén Rodríguez 
 *
 */
//@RepositoryRestController
//@BasePathAwareController
@RestController
@RequestMapping(value="players")
public class UserController {

	@Autowired
	UserService userServicio;
	@Autowired
	SecuenciaServicio secuenciaServicio;
	
	@RequestMapping(produces="application/hal+json", method=RequestMethod.POST)
	public ResponseEntity<Object>  addPlayer(@RequestBody User user) {
		DateFormat fechaFormato=new SimpleDateFormat("yyyy-MM-dd");
		
		if (user.esAnonimo() || !userServicio.existeUser(user.getNomUsuari())) {
			user.setCodigo(secuenciaServicio.generadorDeLaSecuencia(User.SEQUENCE_NAME));
	        user.setDataRegistre(fechaFormato.format(Calendar.getInstance().getTime()));
			//return EntityModel.of(userServicio.creaUsuario(user));
			return ResponseEntity.ok().body(userServicio.creaUsuario(user));

		}
		else
			return ResponseEntity.ok().body("El usuario "+user.getNomUsuari()+ " ya existe");
		
	}
	/**
	 * Método que modifica el nombre de usuario.
	 * Similar al método anterior en cuanto al tema de los anónimos. 
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping(value="/players/{id}")
	public ResponseEntity<Object> modifUsuario(@PathVariable(name="id") Long id, @RequestBody User user) {
		
		//Tenemos que controlar que el nombre d eusuario no exista ya en la BBDD. Puede escoger ser anónimo ahora.
		if (user.esAnonimo() || !userServicio.existeUser(user.getNomUsuari())) {
			//Con el id pasado por parámetro recuperamos el usuario guardado en la BBDD para luego asignarle el nuevo nombre y guardarlo
			User usuarioModificado = userServicio.selectUserById(id); 
			usuarioModificado.setNomUsuari(user.getNomUsuari());
			return ResponseEntity.ok().body(userServicio.creaUsuario(usuarioModificado));

		}
		else
			return ResponseEntity.ok().body("El usuario con nombre "+user.getNomUsuari()+ " ya existe");
		
	}
	
	/**
	 * Retorna un llistat dels jugadors 
	 * @return Listado jugadores
	 */
	@RequestMapping(value="/players", method = RequestMethod.GET)
	public List<User> listadoUsuarios(){
		return userServicio.listUsers();
	}
	
	/**
	 * Elimina usuario por ID
	 * @param id
	 * @return String informando de la eliminación
	 */
	@DeleteMapping("/players/{id}")
	public String eliminaUsuario(@PathVariable(name="id") Long id) {
		userServicio.eliminaUserById(id);
		return "Usuario Eliminado";
	}
	
	/**
	 * Método que calcula la media del porcentaje de éxito de los jugadores.
	 * @return
	 */
	@GetMapping("/ranking")
	public ResponseEntity<Object> rankingMigJugadors(){
		return ResponseEntity.ok().body("El porcentaje medio de éxito de los jugadores es: "+userServicio.sumaPorcentajesExito()/userServicio.totalJugadores());	
		
	}
	
	/**
	 * Devuelve una respuesta con el peor jugador 
	 * @return
	 */
	@GetMapping(value="/ranking/loser")
	public ResponseEntity<Object> usuarioPeorPorcentaje(){
		System.out.println("dentor ranking loser");
		return ResponseEntity.ok().body(userServicio.jugadorMenorExito());
	}
	
	/**
	 * Devuelve una respuesta con el mejor jugador.
	 * @return
	 */
	@RequestMapping(value="/ranking/winner", method = RequestMethod.GET)
	public ResponseEntity<Object> usuarioMejorPorcentaje() {
		return ResponseEntity.ok().body(userServicio.jugadorMayorExito());
	}
}

